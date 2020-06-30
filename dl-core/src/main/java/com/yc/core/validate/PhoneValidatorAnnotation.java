package com.yc.core.validate;

/**
 * 功能描述:

 *

 * @Author: xieyc && 紫色年华
 * @Date: 2020-06-12
 * @Version: 1.0.0
 */
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneValidator.class})
public @interface PhoneValidatorAnnotation {

    boolean required() default true;

    String message() default "请输入正确的手机格式";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
