package com.yc.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.UUID;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-01-06
 * @Version: 1.0.0
 */
public class RandomUtil {

    public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private RandomUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 生成指定长度随机数字和字母
     * @param length 长度
     * @return random
     */
    public static String getRandomNumbersAndLetters(int length) {
        return getRandom(NUMBERS_AND_LETTERS, length);
    }

    /**
     * 生成指定长度的随机数字
     * @param length 长度
     * @return random
     */
    public static String getRandomNumbers(int length) {
        return getRandom(NUMBERS, length);
    }

    /**
     * 生成指定长度的随机字母
     * @param length 长度
     * @return random
     */
    public static String getRandomLetters(int length) {
        return getRandom(LETTERS, length);
    }

    /**
     * 生成指定长度的随机大写字母
     * @param length 长度
     * @return random
     */
    public static String getRandomCapitalLetters(int length) {
        return getRandom(CAPITAL_LETTERS, length);
    }

    /**
     * 生成指定长度的随机小写字母
     * @param length 长度
     * @return
     */
    public static String getRandomLowerCaseLetters(int length) {
        return getRandom(LOWER_CASE_LETTERS, length);
    }

    public static String getRandom(String source, int length) {
        return StringUtils.isBlank(source) ? null : getRandom(source.toCharArray(), length);
    }

    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar != null && sourceChar.length != 0 && length >= 0) {
            StringBuilder str = new StringBuilder(length);
            Random random = new Random();

            for(int i = 0; i < length; ++i) {
                str.append(sourceChar[random.nextInt(sourceChar.length)]);
            }

            return str.toString();
        } else {
            return null;
        }
    }

    public static int getRandom(int max) {
        return getRandom(0, max);
    }

    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        } else {
            return min == max ? min : min + (new Random()).nextInt(max - min);
        }
    }

    public static boolean shuffle(Object[] objArray) {
        return objArray == null ? false : shuffle(objArray, getRandom(objArray.length));
    }

    public static boolean shuffle(Object[] objArray, int shuffleCount) {
        int length;
        if (objArray != null && shuffleCount >= 0 && (length = objArray.length) >= shuffleCount) {
            for(int i = 1; i <= shuffleCount; ++i) {
                int random = getRandom(length - i);
                Object temp = objArray[length - i];
                objArray[length - i] = objArray[random];
                objArray[random] = temp;
            }

            return true;
        } else {
            return false;
        }
    }

    public static int[] shuffle(int[] intArray) {
        return intArray == null ? null : shuffle(intArray, getRandom(intArray.length));
    }

    public static int[] shuffle(int[] intArray, int shuffleCount) {
        int length;
        if (intArray != null && shuffleCount >= 0 && (length = intArray.length) >= shuffleCount) {
            int[] out = new int[shuffleCount];

            for(int i = 1; i <= shuffleCount; ++i) {
                int random = getRandom(length - i);
                out[i - 1] = intArray[random];
                int temp = intArray[length - i];
                intArray[length - i] = intArray[random];
                intArray[random] = temp;
            }

            return out;
        } else {
            return null;
        }
    }

    /**
     * 生成UUID
     * synchronized:当用它来修饰一个方法或者一个代码块的时候，可以保证在同一时刻最多仅仅有一个线程运行该段代码。
     * @return
     */
    public static synchronized String getUUID() {
        return (UUID.randomUUID().toString()).replace("-", "");
    }

}
