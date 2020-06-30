package com.yc.common.utils;

import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 功能描述:流水号生成规则(按默认规则递增，数字从1-99开始递增，数字到99，递增字母;位数不够增加位数)
 *      A001
 *      A001A002
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2019-10-31 13:48
 */
public class YouBianCodeUtil {

	private static final int numLength = 2;

	/**
	 * 根据前一个code，获取同级下一个code
	 * 例如:当前最大code为D01A04，下一个code为：D01A05
	 *
	 * @param code 当前最大code
	 * @return newCode
	 */
	public static synchronized String getNextYouBianCode(String code) {
		String newcode;
		if (StringUtil.isNullOrEmpty(code)) {
			String zimu = "A";
			String num = getStrNum(1);
			newcode = zimu + num;
		} else {
			String beforeCode = code.substring(0, code.length() - 1- numLength);
			String afterCode = code.substring(code.length() - 1 - numLength,code.length());
			char afterCodeZimu = afterCode.substring(0, 1).charAt(0);
			Integer afterCodeNum = Integer.parseInt(afterCode.substring(1));

			String nextNum;
			char nextZimu = 'A';
			// 先判断数字等于999*，则计数从1重新开始，递增
			if (afterCodeNum == getMaxNumByLength(numLength)) {
				nextNum = getNextStrNum(0);
			} else {
				nextNum = getNextStrNum(afterCodeNum);
			}
			// 先判断数字等于999*，则字母从A重新开始,递增
			if(afterCodeNum == getMaxNumByLength(numLength)) {
				nextZimu = getNextZiMu(afterCodeZimu);
			}else{
				nextZimu = afterCodeZimu;
			}
			// 例如Z99，下一个code就是Z99A01
			if ('Z' == afterCodeZimu && getMaxNumByLength(numLength) == afterCodeNum) {
				newcode = code + (nextZimu + nextNum);
			} else {
				newcode = beforeCode + (nextZimu + nextNum);
			}
		}
		return newcode;
	}

	/**
	 * 根据父亲code,获取下级的下一个code
	 *
	 * 例如：父亲CODE:A01
	 *       当前CODE:A01B03
	 *       获取的code:A01B04
	 *
	 * @param parentCode   上级code
	 * @param localCode    同级code
	 * @return  newCode
	 */
	public static synchronized String getSubYouBianCode(String parentCode,String localCode) {
		if(StringUtils.isNotEmpty(localCode)){
			return getNextYouBianCode(localCode);
		}else{
			parentCode = parentCode + "A"+ getNextStrNum(0);
		}
		return parentCode;
	}

	/**
	 * 将数字前面位数补零
	 *
	 * @param num 数字
	 * @return 补全后的str
	 */
	private static String getNextStrNum(int num) {
		return getStrNum(getNextNum(num));
	}

	/**
	 * 将数字前面位数补零
	 *
	 * @param num 数字
	 * @return new str
	 */
	private static String getStrNum(int num) {
		return String.format("%0" + numLength + "d", num);
	}

	/**
	 * 递增获取下个数字
	 *
	 * @param num 当前数字
	 * @return 下个数字
	 */
	private static int getNextNum(int num) {
		num++;
		return num;
	}

	/**
	 * 递增获取下个字母
	 *
	 * @param zimu 当前字母
	 * @return 下个字母
	 */
	private static char getNextZiMu(char zimu) {
		if (zimu == 'Z') {
			return 'A';
		}
		zimu++;
		return zimu;
	}

	/**
	 * 根据数字位数获取最大值
	 * @param length 数字位数
	 * @return 最大值
	 */
	private static int getMaxNumByLength(int length){
		if(length==0){
			return 0;
		}
		String maxNum = "";
		for (int i=0;i<length;i++){
			maxNum = maxNum + "9";
		}
		return Integer.parseInt(maxNum);
	}

}
