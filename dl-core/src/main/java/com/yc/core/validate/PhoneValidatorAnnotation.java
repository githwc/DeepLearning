package com.yc.core.validate;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-06-12
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
