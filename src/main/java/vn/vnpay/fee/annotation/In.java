/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.vnpay.fee.annotation;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import java.util.Arrays;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 *
 * @author truongnq
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = InConstraintValidator.class)
@Repeatable(Ins.class)
public @interface In
{
    String message() default "Value Not In";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};

    String[] values() default {};
    
    boolean required() default true;
}

class InConstraintValidator implements ConstraintValidator<In, Object>
{

    private Object[] values;
    private boolean required;

    @Override
    public final void initialize(final In annotation)
    {
        values = annotation.values();
        required = annotation.required();
    }

    @Override
    public final boolean isValid(final Object value, final ConstraintValidatorContext context)
    {
        if (!this.required)
        {
            return true;
        }
        String val = String.valueOf(value);
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Value not in: " + Arrays.toString(values)).addConstraintViolation();
        return Arrays.asList(values).contains(val);
    }

}