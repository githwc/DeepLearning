package com.lh.common.utils;

import java.math.BigDecimal;

/**
 * 功能描述：数字转中文
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-10-12
 * @Version: 1.0.0
 */
public class NumberToCN {

    private static final String[] CN_UPPER_NUMBER = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] CN_UPPER_MONETRAY_UNIT = new String[]{"分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟"};
    private static final String CN_FULL = "整";
    private static final String CN_NEGATIVE = "负";
    private static final int MONEY_PRECISION = 2;
    private static final String CN_ZEOR_FULL = "零元整";

    public NumberToCN() {
    }

    public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
        StringBuilder sb = new StringBuilder();
        int signum = numberOfMoney.signum();
        if (signum == 0) {
            return "零元整";
        } else {
            long number = numberOfMoney.movePointRight(2).setScale(0, 4).abs().longValue();
            long scale = number % 100L;
            int numIndex = 0;
            boolean getZero = false;
            if (scale <= 0L) {
                numIndex = 2;
                number /= 100L;
                getZero = true;
            }

            if (scale > 0L && scale % 10L <= 0L) {
                numIndex = 1;
                number /= 10L;
                getZero = true;
            }

            for(int zeroSize = 0; number > 0L; ++numIndex) {
                int numUnit = (int)(number % 10L);
                if (numUnit > 0) {
                    if (numIndex == 9 && zeroSize >= 3) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                    }

                    if (numIndex == 13 && zeroSize >= 3) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                    }

                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    getZero = false;
                    zeroSize = 0;
                } else {
                    ++zeroSize;
                    if (!getZero) {
                        sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    }

                    if (numIndex == 2) {
                        if (number > 0L) {
                            sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                        }
                    } else if ((numIndex - 2) % 4 == 0 && number % 1000L > 0L) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }

                    getZero = true;
                }

                number /= 10L;
            }

            if (signum == -1) {
                sb.insert(0, "负");
            }

            if (scale <= 0L) {
                sb.append("整");
            }

            return sb.toString();
        }
    }
}
