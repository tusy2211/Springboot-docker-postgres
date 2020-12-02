/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.vnpay.fee.annotation;

import io.micrometer.core.instrument.util.StringUtils;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 *
 * @author truongnq
 */
@Target({TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = RequiredWhenValidator.class)
public @interface RequiredWhen {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field() default "";

    String value() default "";

    String[] requiredFields() default {};
}

class RequiredWhenValidator implements ConstraintValidator<RequiredWhen, Object> {

    private String validField;
    private String validValue;
    private String[] requiredFields;

    @Override
    public final void initialize(final RequiredWhen annotation) {
        validField = annotation.field();
        validValue = annotation.value();
        requiredFields = annotation.requiredFields();
    }

    @Override
    public final boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        Set<String> errorFields = new HashSet<>();
        HashMap<String, String> fieldsMap = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            //If field not in valid field and required field
            String name = field.getName();
            if (!name.equals(this.validField) && !Arrays.asList(requiredFields).contains(name)) {
                continue;
            }
            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }
            try {
                String value = String.valueOf(field.get(obj));
                if(!name.equals(this.validField)){
                    fieldsMap.put(name, value);
                    continue;
                }
                if(name.equals(this.validField) && !value.equals(this.validValue)){
                    return true;
                }
                
            } catch (Exception ex) {
                errorFields.add(name);
            }
        }
        fieldsMap.entrySet().stream().filter((field) -> (StringUtils.isBlank(field.getValue()))).forEach((field) -> {
            errorFields.add(field.getKey());
        });
        if(errorFields.isEmpty()){
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Fields: " + errorFields.toString() + " is required when field: " + this.validField + " = " + this.validValue).addConstraintViolation();
        return false;
    }

}
