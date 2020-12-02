/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.vnpay.fee.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author truongnq
 */
public class SafeHtmlValidator
        implements ConstraintValidator<ValidatedSafeHtml, Object> {

    private Whitelist whitelist;

    private String baseURI;

    @Override
    public void initialize(ValidatedSafeHtml constraintAnnotation) {

        switch (constraintAnnotation.whitelistType()) {
            case BASIC:
                whitelist = Whitelist.basic();
                break;
            case BASIC_WITH_IMAGES:
                whitelist = Whitelist.basicWithImages();
                break;
            case NONE:
                whitelist = Whitelist.none();
                break;
            case RELAXED:
                whitelist = Whitelist.relaxed();
                break;
            case SIMPLE_TEXT:
                whitelist = Whitelist.simpleText();
                break;
        }
        baseURI = constraintAnnotation.baseURI();
        whitelist.addTags(constraintAnnotation.additionalTags());

        for (ValidatedSafeHtml.Tag tag : constraintAnnotation.additionalTagsWithAttributes()) {
            whitelist.addTags(tag.name());
            if (tag.attributes().length > 0) {
                whitelist.addAttributes(tag.name(), tag.attributes());
            }

            for (ValidatedSafeHtml.Attribute attribute : tag.attributesWithProtocols()) {
                whitelist.addAttributes(tag.name(), attribute.name());

                if (attribute.protocols().length > 0) {
                    whitelist.addProtocols(tag.name(), attribute.name(), attribute.protocols());
                }
            }
        }
    }

    @Override
    public boolean isValid(Object obj,
            ConstraintValidatorContext context) {
        if(null == obj){
            return true;
        }
        Set<String> errorFields = new HashSet<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        Cleaner cleaner = new Cleaner(whitelist);
        for (Field field : fields) {
            if (!field.getType().equals(String.class)) {
                continue;
            }
            String fieldValue;
            try {
                if (Modifier.isPrivate(field.getModifiers())) {
            	   field.setAccessible(true);
                }
                fieldValue = (String) field.get(obj);
            } catch (Exception ex) {
                errorFields.add(field.getName());
                continue;
            }
            if(Strings.isBlank(fieldValue)){
                continue;
            }
            if(cleaner.isValid(getFragmentAsDocument(fieldValue))){
                continue;
            }
            errorFields.add(field.getName());

        }
        if(errorFields.isEmpty()){
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Fields: " + errorFields.toString() + " not safe").addConstraintViolation();
        return false;
    }

    /**
     * Returns a document whose {@code <body>} element contains the given HTML
     * fragment.
     */
    private Document getFragmentAsDocument(CharSequence value) {
        // using the XML parser ensures that all elements in the input are retained, also if they actually are not allowed at the given
        // location; E.g. a <td> element isn't allowed directly within the <body> element, so it would be used by the default HTML parser.
        // we need to retain it though to apply the given white list properly; See HV-873
        Document fragment = Jsoup.parse(value.toString(), baseURI, Parser.xmlParser());
        Document document = Document.createShell(baseURI);

        // add the fragment's nodes to the body of resulting document
        Iterator<Element> nodes = fragment.children().iterator();
        while (nodes.hasNext()) {
            document.body().appendChild(nodes.next());
        }

        return document;
    }
}
