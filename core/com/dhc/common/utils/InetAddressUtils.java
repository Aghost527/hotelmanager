package com.dhc.common.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A collection of utilities relating to InetAddresses.
 */
public class InetAddressUtils {
	/*
	 * public static void main(String[] args){ String addr="192.168.1.2";
	 * System.out.println(isIPv4Address(addr)); }
	 */

	private static final Pattern IPV4_PATTERN = Pattern
			.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

	private static final Pattern IPV6_STD_PATTERN = Pattern
			.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");

	private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern
			.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

	private static final Pattern MASK_PATTERN = Pattern.compile("^(255)\\."
			+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
			+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
			+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");

	public static boolean isIPv4Address(final String input) {
		return IPV4_PATTERN.matcher(input).matches();
	}

	public static boolean isIPv6StdAddress(final String input) {
		return IPV6_STD_PATTERN.matcher(input).matches();
	}

	public static boolean isIPv6HexCompressedAddress(final String input) {
		return IPV6_HEX_COMPRESSED_PATTERN.matcher(input).matches();
	}

	public static boolean isIPv6Address(final String input) {
		return isIPv6StdAddress(input) || isIPv6HexCompressedAddress(input);
	}

	/**
	 * 检验子网掩码
	 */
	public static boolean isLegalMask(final String input) {
		return MASK_PATTERN.matcher(input).matches();
	}

	/**
	 * ip格式的内容转成数字
	 */
	public static int[] IpStringToNumArray(String ip) {
		String[] gIp = ip.split("\\.");// ip地址分割
		int intIp[] = { 0, 0, 0, 0 };
		for (int i = 0; i < 4; i++) {
			try {
				intIp[i] = Integer.parseInt(gIp[i]);
			} catch (Exception e) {
				return intIp;
			}
		}
		return intIp;
	}

	/**
	 * 得到正确网段，用于判断输入首地址是否正确
	 */
	public static String countSubnet(int[] intIp, int[] intMask) {
		int a[] = { 0, 0, 0, 0 };
		for (int i = 0; i < 4; i++) {
			a[i] = intIp[i] & intMask[i];
		}
		String Subnet = a[0] + "." + a[1] + "." + a[2] + "." + a[3];
		return Subnet;
	}

	
	
	
	/**
	 * 产生地址段
	 */
	public static ArrayList<String> getADDF(String ip, String mask) {// 产生地址段
		int intIp[] = IpStringToNumArray(ip);
		int intMask[] = IpStringToNumArray(mask);
		int IpRoom[] = { 0, 0, 0, 0 };

		for (int i = 0; i < 4; i++) // 通过子网掩码检查地址段长度
		{
			IpRoom[i] = 255 - intMask[i];
		}
		ArrayList<String> Segment = new ArrayList<String>();
		// for (int seg1 = 0; seg1 < IpRoom[0]; seg1++)
		for (int seg2 = 0; seg2 < IpRoom[1]; seg2++)
			for (int seg3 = 0; seg3 < IpRoom[2]; seg3++)
				for (int seg4 = 0; seg4 < IpRoom[3]; seg4++) {
					Segment.add(Integer.toString(intIp[0]) + "."
							+ Integer.toString(intIp[1] + seg2) + "."
							+ Integer.toString(intIp[2] + seg3) + "."
							+ Integer.toString(intIp[3] + seg4));

				}

		return Segment;

	}

	/**
	 * 检查子网掩码格式
	 */
	public static boolean checkMask(int[] IntMask) {
		int digit[] = { 0, 0, 0, 0 };
		// boolean endLock = true;// 验证是否是最后一列
		for (int j = 0; j < 4; j++) {
			if (IntMask[j] == 255) {
				digit[j] = 8;
			} else if (IntMask[j] == 254) {
				digit[j] = 7;
			} else if (IntMask[j] == 252) {
				digit[j] = 6;
			} else if (IntMask[j] == 248) {
				digit[j] = 5;
			} else if (IntMask[j] == 240) {
				digit[j] = 4;
			} else if (IntMask[j] == 224) {
				digit[j] = 3;
			} else if (IntMask[j] == 192) {
				digit[j] = 2;
			} else if (IntMask[j] == 128) {
				digit[j] = 1;
			} else if (IntMask[j] == 0) {
				digit[j] = 0;
			} else
				return false;
		}
		if (digit[0] != 8) {
			return false;
		}
		if (digit[1] != 8) {
			if ((digit[2] != 0) || (digit[3] != 0)) {
				return false;
			} else
				return true;
		} else {
			if ((digit[2] == 8)) {
				return true;
			} else if (digit[3] != 0) {
				return false;
			} else
				return true;
		}

	}

	/**
	 * 求子网掩码位数
	 */
	public static int MaskToInt(int[] IntMask) {// 求子网掩码位数
		int digit = 0;
		for (int j = 0; j < 4; j++) {
			if (IntMask[j] == 255) {
				digit += 8;
			} else if (IntMask[j] == 254) {
				digit += 7;
			} else if (IntMask[j] == 252) {
				digit += 6;
			} else if (IntMask[j] == 248) {
				digit += 5;
			} else if (IntMask[j] == 240) {
				digit += 4;
			} else if (IntMask[j] == 224) {
				digit += 3;
			} else if (IntMask[j] == 192) {
				digit += 2;
			} else if (IntMask[j] == 128) {
				digit += 1;
			} else if (IntMask[j] == 0) {
				digit += 0;
			}
		}
		return digit;
	}

	/**
	 * 用整数表示网段
	 */

	public static long IprangeToInt(String ip, long mask) {
		String[] ips = ip.split("\\.");
		return ((Integer.parseInt(ips[0]) << 24)
				+ (Integer.parseInt(ips[1]) << 16)
				+ (Integer.parseInt(ips[2]) << 8) + Integer.parseInt(ips[3]))
				* 100 + mask;
	}

	/**
	 * 用整数表示网段
	 * @param fullString 数据形式：192.168.0.0/16
	 * 
	 * 
	 */
	public static long IprangeToInt(String fullString) {
		String[] ips = fullString.split("\\.");
		String[] mask=ips[3].split("/");
 		return ((Integer.parseInt(ips[0]) << 24)
				+ (Integer.parseInt(ips[1]) << 16)
				+ (Integer.parseInt(ips[2]) << 8) + Integer.parseInt(mask[0]))
				* 100 + Integer.parseInt(mask[1]);
	}
	/**
	 * 检查ip地址是否符合该网段 尚未处理全1和全0状况
	 * 
	 * @param mask
	 *            所属网段子网掩码
	 * @param ip
	 *            待检验ip地址
	 * @param iprange
	 *            网段
	 */
	public static boolean isThisIpInTheRange(String rangemask, String ip,
			String iprange) {
		int intIp[] = IpStringToNumArray(ip);
		int intMask[] = IpStringToNumArray(rangemask);
		int intIprange[] = IpStringToNumArray(iprange);
		for (int i = 0; i < 4; i++) {
			if ((intIp[i] & intMask[i]) != (intIprange[i] & intMask[i])) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 求末地址
	 * 
	 * @param mask
	 *            掩码
	 * @param iprange
	 *            网段地址
	 */
	public static String countEndIP(String mask, String iprange) {
		int intone[] = { 255, 255, 255, 255 };
		int intIprange[] = IpStringToNumArray(iprange);
		int intMask[] = IpStringToNumArray(mask);
		String result = null;
		for (int i = 0; i < 4; i++) {
			intone[i] = intone[i] ^ intMask[i];
			intIprange[i] = intIprange[i] | intone[i];

		}

		result = intIprange[0] + "." + intIprange[1] + "." + intIprange[2]
				+ "." + intIprange[3];
		return result;
	}

	/**
	 * 求首地址
	 * 
	 * @param iprange
	 *            网段地址
	 */
	public static String countStartIP(String iprange) {

		int intIprange[] = IpStringToNumArray(iprange);
		intIprange[3] += 1;
		String result = intIprange[0] + "." + intIprange[1] + "."
				+ intIprange[2] + "." + intIprange[3];
		return result;
	}

	/*
	 * 根据第一个字节判断IP地址类型
	 */
	public static String TellIpType(int num) {
		if (num < 127)
			return "A";
		else if (num < 192)
			return "B";
		else if (num < 224)
			return "C";
		else if (num < 240)
			return "D";
		else
			return "E";
	}

	/*
	 * 根据根据掩码位数得到掩码字段
	 */
	public static String intMask2String(long intMask) {
		
		String rs="";
       for(int i=0;i<4;i++)
       {
    	   if(intMask>7){rs+="255";intMask-=8;}
    	   else
    	   {switch ((int)intMask)
    		   {
    	   case 0:rs+="0";break;
    	   case 1:rs+="128";intMask-=1;break;
    	   case 2:rs+="192";intMask-=2;break;
    	   case 3:rs+="224";intMask-=3;break;
    	   case 4:rs+="240";intMask-=4;break;
    	   case 5:rs+="248";intMask-=5;break;
    	   case 6:rs+="252";intMask-=6;break;
    	   case 7:rs+="254";intMask-=7;break;
    		   }
    	  }
    	   if(i==3){break;}
    	   else rs+=".";
       }
       return rs;
    }
}
