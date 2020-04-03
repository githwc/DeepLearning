package com.yc.common.utils;

import com.yc.common.global.error.Error;
import com.yc.common.global.error.ErrorException;
import org.apache.commons.lang3.StringUtils;

/**
 * 功能描述：密码强度检测
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-03
 * @Version: 1.0.0
 */
public class PasswordCheckUtil {

    public enum LEVEL {
        EASY, MIDIUM, STRONG, VERY_STRONG, EXTREMELY_STRONG
    }

    // 数字
    private static final int NUM = 1;
    // 小写字母
    private static final int SMALL_LETTER = 2;
    // 大写字母
    private static final int CAPITAL_LETTER = 3;
    // 特殊字符
    private static final int OTHER_CHAR = 4;

    /**
     * 简单的密码字典
     */
    private final static String[] DICTIONARY = {"password", "abc123", "iloveyou", "adobe123", "123123",
            "111111", "a1b2c3", "123qwe", "aaa111", "qweasd", "admin", "123456"};

    private final static int[] SIZE_TABLE = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999,
            Integer.MAX_VALUE};

    /**
     *  获得密码强度等级，包括简单、复杂、强、很强、极强
     *
     * @param password 密码
     * @return level
     */
    public static LEVEL getPwdStrong(String password) {
        int level = getPwdLevel(password);
        switch (level) {
            case 0:
            case 1:
            case 2:
            case 3:
                return LEVEL.EASY;
            case 4:
            case 5:
            case 6:
                return LEVEL.MIDIUM;
            case 7:
            case 8:
            case 9:
                return LEVEL.STRONG;
            case 10:
            case 11:
            case 12:
                return LEVEL.VERY_STRONG;
            default:
                return LEVEL.EXTREMELY_STRONG;
        }
    }

    /**
     * 检查密码的强度
     *
     * @param password 密码
     * @return strength level
     */
    public static int getPwdLevel(String password) {
        if (StringUtils.isBlank(password)) {
            throw new ErrorException(Error.ParameterNotFound);
        }
        int len = password.length();
        int level = 0;
        // 增加点
        //  判断密码是否含有数字有level++
        if (countLetter(password, NUM) > 0) {
            level++;
        }
        //判断密码是否含有小写字母有level++
        if (countLetter(password, SMALL_LETTER) > 0) {
            level++;
        }
        //判断密码是否还有大写字母有level++
        if (len > 4 && countLetter(password, CAPITAL_LETTER) > 0) {
            level++;
        }
        //判断密码是否还有特殊字符有level++
        if (len > 6 && countLetter(password, OTHER_CHAR) > 0) {
            level++;
        }
        //密码长度大于4并且2种类型组合......（不一一概述）
        if (len > 4 && countLetter(password, NUM) > 0 && countLetter(password, SMALL_LETTER) > 0
                || countLetter(password, NUM) > 0 && countLetter(password, CAPITAL_LETTER) > 0
                || countLetter(password, NUM) > 0 && countLetter(password, OTHER_CHAR) > 0
                || countLetter(password, SMALL_LETTER) > 0 && countLetter(password, CAPITAL_LETTER) > 0
                || countLetter(password, SMALL_LETTER) > 0 && countLetter(password, OTHER_CHAR) > 0
                || countLetter(password, CAPITAL_LETTER) > 0 && countLetter(password, OTHER_CHAR) > 0) {
            level++;
        }
        //密码长度大于6并且3中类型组合......（不一一概述）
        if (len > 6 && countLetter(password, NUM) > 0 && countLetter(password, SMALL_LETTER) > 0
                && countLetter(password, CAPITAL_LETTER) > 0 || countLetter(password, NUM) > 0
                && countLetter(password, SMALL_LETTER) > 0 && countLetter(password, OTHER_CHAR) > 0
                || countLetter(password, NUM) > 0 && countLetter(password, CAPITAL_LETTER) > 0
                && countLetter(password, OTHER_CHAR) > 0 || countLetter(password, SMALL_LETTER) > 0
                && countLetter(password, CAPITAL_LETTER) > 0 && countLetter(password, OTHER_CHAR) > 0) {
            level++;
        }
        //密码长度大于8并且4种类型组合......（不一一概述）
        if (len > 8 && countLetter(password, NUM) > 0 && countLetter(password, SMALL_LETTER) > 0
                && countLetter(password, CAPITAL_LETTER) > 0 && countLetter(password, OTHER_CHAR) > 0) {
            level++;
        }
        //密码长度大于6并且2种类型组合每种类型长度大于等于3或者2......（不一一概述）
        if (len > 6 && countLetter(password, NUM) >= 3 && countLetter(password, SMALL_LETTER) >= 3
                || countLetter(password, NUM) >= 3 && countLetter(password, CAPITAL_LETTER) >= 3
                || countLetter(password, NUM) >= 3 && countLetter(password, OTHER_CHAR) >= 2
                || countLetter(password, SMALL_LETTER) >= 3 && countLetter(password, CAPITAL_LETTER) >= 3
                || countLetter(password, SMALL_LETTER) >= 3 && countLetter(password, OTHER_CHAR) >= 2
                || countLetter(password, CAPITAL_LETTER) >= 3 && countLetter(password, OTHER_CHAR) >= 2) {
            level++;
        }
        //密码长度大于8并且3种类型组合每种类型长度大于等于3或者2......（不一一概述）
        if (len > 8 && countLetter(password, NUM) >= 2 && countLetter(password, SMALL_LETTER) >= 2
                && countLetter(password, CAPITAL_LETTER) >= 2 || countLetter(password, NUM) >= 2
                && countLetter(password, SMALL_LETTER) >= 2 && countLetter(password, OTHER_CHAR) >= 2
                || countLetter(password, NUM) >= 2 && countLetter(password, CAPITAL_LETTER) >= 2
                && countLetter(password, OTHER_CHAR) >= 2 || countLetter(password, SMALL_LETTER) >= 2
                && countLetter(password, CAPITAL_LETTER) >= 2 && countLetter(password, OTHER_CHAR) >= 2) {
            level++;
        }
        //密码长度大于10并且4种类型组合每种类型长度大于等于2......（不一一概述）
        if (len > 10 && countLetter(password, NUM) >= 2 && countLetter(password, SMALL_LETTER) >= 2
                && countLetter(password, CAPITAL_LETTER) >= 2 && countLetter(password, OTHER_CHAR) >= 2) {
            level++;
        }
        //特殊字符>=3 level++;
        if (countLetter(password, OTHER_CHAR) >= 3) {
            level++;
        }
        //特殊字符>=6 level++;
        if (countLetter(password, OTHER_CHAR) >= 6) {
            level++;
        }
        //长度>12 >16 level++
        if (len > 12) {
            level++;
            if (len >= 16) {
                level++;
            }
        }
        // 减少点
        if ("abcdefghijklmnopqrstuvwxyz".indexOf(password) > 0 || "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(password) > 0) {
            level--;
        }
        if ("qwertyuiop".indexOf(password) > 0 || "asdfghjkl".indexOf(password) > 0 || "zxcvbnm".indexOf(password) > 0) {
            level--;
        }
        if (StringUtils.isNumeric(password) && ("01234567890".indexOf(password) > 0 || "09876543210".indexOf(password) > 0)) {
            level--;
        }
        if (countLetter(password, NUM) == len || countLetter(password, SMALL_LETTER) == len
                || countLetter(password, CAPITAL_LETTER) == len) {
            level--;
        }
        if (len % 2 == 0) { // aaabbb
            String part1 = password.substring(0, len / 2);
            String part2 = password.substring(len / 2);
            if (part1.equals(part2)) {
                level--;
            }
            if (isCharEqual(part1) && isCharEqual(part2)) {
                level--;
            }
        }
        if (len % 3 == 0) { // ababab
            String part1 = password.substring(0, len / 3);
            String part2 = password.substring(len / 3, len / 3 * 2);
            String part3 = password.substring(len / 3 * 2);
            if (part1.equals(part2) && part2.equals(part3)) {
                level--;
            }
        }
        if (StringUtils.isNumeric(password) && len >= 6) { // 19881010 or 881010
            int year = 0;
            if (len == 8 || len == 6) {
                year = Integer.parseInt(password.substring(0, len - 4));
            }
            int size = sizeOfInt(year);
            int month = Integer.parseInt(password.substring(size, size + 2));
            int day = Integer.parseInt(password.substring(size + 2, len));
            if (year >= 1950 && year < 2050 && month >= 1 && month <= 12 && day >= 1 && day <= 31) {
                level--;
            }
        }
        if (null != DICTIONARY && DICTIONARY.length > 0) {// dictionary
            for (int i = 0; i < DICTIONARY.length; i++) {
                if (password.equals(DICTIONARY[i]) || DICTIONARY[i].indexOf(password) >= 0) {
                    level--;
                    break;
                }
            }
        }
        if (len <= 6) {
            level--;
            if (len <= 4) {
                level--;
                if (len <= 3) {
                    level = 0;
                }
            }
        }
        if (isCharEqual(password)) {
            level = 0;
        }
        if (level < 0) {
            level = 0;
        }
        return level;
    }

    /**
     *  检查字符类型
     *
     * @param c char
     * @return  数字、大写字母、小写字母和其他字符。
     */
    private static int checkCharacterType(char c) {
        if (c >= 48 && c <= 57) {
            return NUM;
        }
        if (c >= 65 && c <= 90) {
            return CAPITAL_LETTER;
        }
        if (c >= 97 && c <= 122) {
            return SMALL_LETTER;
        }
        return OTHER_CHAR;
    }

    /**
     * 按不同类型计算密码的数量
     *
     * @param password  密码
     * @param type 类型
     * @return  数量
     */
    private static int countLetter(String password, int type) {
        int count = 0;
        if (null != password && password.length() > 0) {
            for (char c : password.toCharArray()) {
                if (checkCharacterType(c) == type) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 判断字符串的每个字符是否相等
     *
     * @param str 字符串
     * @return boolean
     */
    private static boolean isCharEqual(String str) {
        return str.replace(str.charAt(0), ' ').trim().length() == 0;
    }

    /**
     * 计算一个整数的大小
     *
     * @param x x
     * @return size
     */
    private static int sizeOfInt(int x) {
        for(int i = 0; ; i++){
            if (x <= SIZE_TABLE[i]) {
                return i + 1;
            }
        }
    }


}
