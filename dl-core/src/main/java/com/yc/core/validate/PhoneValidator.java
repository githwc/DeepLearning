package com.yc.core.validate;

import cn.hutool.core.util.ReUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 功能描述:
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-06-12
 * @Version: 1.0.0
 */
public class PhoneValidator implements ConstraintValidator<PhoneValidatorAnnotation, String> {

    /**
     * 匹配电话
     */
    public static final Pattern PHONE_REGEXP = Pattern.compile("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$");

    private boolean required = false;

    @Override
    public void initialize(PhoneValidatorAnnotation phoneValidatorAnnotation) {
        required = phoneValidatorAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ReUtil.isMatch(PHONE_REGEXP,value);
    }

}
