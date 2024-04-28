package com.wingflare.lib.core.utils;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * IP地址工具
 */
public class IPAddressUtil {

    /**
     * ip数据转换
     *
     * @param ipAddress
     * @return
     */
    public static long ipToLong(String ipAddress) throws UnknownHostException {
        InetAddress ip = InetAddress.getByName(ipAddress);
        byte[] octets = ip.getAddress();
        long result = 0;
        for (byte octet : octets) {
            result <<= 8;
            result |= octet & 0xff;
        }
        return result;
    }

    public static long ipToLong(InetAddress ip) {
        byte[] octets = ip.getAddress();
        long result = 0;
        for (byte octet : octets) {
            result <<= 8;
            result |= octet & 0xff;
        }
        return result;
    }

    /**
     * ip数据转换
     *
     * @param ip
     * @return
     */
    public static String longToIpStr(long ip) {
        String s1 = String.valueOf((ip & 4278190080L) / 16777216L);
        String s2 = String.valueOf((ip & 16711680L) / 65536L);
        String s3 = String.valueOf((ip & 65280L) / 256L);
        String s4 = String.valueOf(ip & 255L);
        return s1 + "." + s2 + "." + s3 + "." + s4;
    }


    public static InetAddress longToIp(long ip) throws UnknownHostException {
        return InetAddress.getByName(longToIpStr(ip));
    }

    /**
     * 验证ip是否在ip段内
     *
     * @param ipStart
     * @param ipEnd
     * @param ipToCheck
     * @return
     */
    public static boolean isValidRange(String ipStart, String ipEnd, String ipToCheck) {
        try {
            long ipLo = ipToLong(InetAddress.getByName(ipStart));
            long ipHi = ipToLong(InetAddress.getByName(ipEnd));
            long ipToTest = ipToLong(InetAddress.getByName(ipToCheck));
            return (ipToTest >= ipLo && ipToTest <= ipHi);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据IP和位数返回该IP网段的所有IP
     *
     * @param ip
     * @param mask
     * @return
     */
    public static List<String> parseIpMaskRange(String ip, String mask) throws UnknownHostException {
        List<String> list = new ArrayList<>();
        if ("32".equals(mask)) {
            list.add(ip);
        } else {
            String startIp = getBeginIpStr(ip, mask);
            String endIp = getEndIpStr(ip, mask);
            if (!"31".equals(mask)) {
                String subStart = startIp.split("\\.")[0] + "." + startIp.split("\\.")[1] + "." + startIp.split("\\.")[2] + ".";
                String subEnd = endIp.split("\\.")[0] + "." + endIp.split("\\.")[1] + "." + endIp.split("\\.")[2] + ".";
                startIp = subStart + (Integer.parseInt(startIp.split("\\.")[3]) + 1);
                endIp = subEnd + (Integer.parseInt(endIp.split("\\.")[3]) - 1);
            }
            list = parseIpRange(startIp, endIp);
        }
        return list;
    }

    /**
     * 根据位数返回IP总数
     *
     * @param mask
     * @return
     */
    public static int getIpCount(String mask) {
        return BigDecimal.valueOf(Math.pow(2, 32 - Integer.parseInt(mask)))
                .setScale(0, BigDecimal.ROUND_DOWN).intValue();//IP总数，去小数点
    }

    /**
     * 根据 ip/掩码位 计算IP段的起始IP 如 IP串 218.240.38.69/30
     *
     * @param ip      给定的IP，如218.240.38.69
     * @param maskBit 给定的掩码位，如30
     * @return
     */
    public static String getBeginIpStr(String ip, String maskBit) throws UnknownHostException {
        return longToIpStr(getBeginIpLong(ip, maskBit));
    }

    /**
     * 根据 ip/掩码位 计算IP段的起始IP
     *
     * @param ip
     * @param maskBit
     * @return
     * @throws UnknownHostException
     */
    public static long getBeginIpLong(String ip, String maskBit) throws UnknownHostException {
        return ipToLong(ip) & ipToLong(getMaskByMaskBit(maskBit));
    }

    /**
     * 根据 ip/掩码位 计算IP段的终止IP
     *
     * @param ip
     * @param maskBit
     * @return
     */
    public static String getEndIpStr(String ip, String maskBit) throws UnknownHostException {
        return longToIpStr(getEndIpLong(ip, maskBit));
    }

    /**
     * 根据 ip/掩码位 计算IP段的终止IP
     *
     * @param ip
     * @param maskBit
     * @return
     * @throws UnknownHostException
     */
    public static Long getEndIpLong(String ip, String maskBit) throws UnknownHostException {
        return getBeginIpLong(ip, maskBit)
                + ~ipToLong(getMaskByMaskBit(maskBit));
    }

    /**
     * 根据掩码位获取掩码
     *
     * @param maskBit
     * @return
     */
    public static String getMaskByMaskBit(String maskBit) {
        return getMaskMap(maskBit);
    }

    private static String getMaskMap(String maskBit) {
        switch (maskBit) {
            case "1":
                return "128.0.0.0";
            case "2":
                return "192.0.0.0";
            case "3":
                return "224.0.0.0";
            case "4":
                return "240.0.0.0";
            case "5":
                return "248.0.0.0";
            case "6":
                return "252.0.0.0";
            case "7":
                return "254.0.0.0";
            case "8":
                return "255.0.0.0";
            case "9":
                return "255.128.0.0";
            case "10":
                return "255.192.0.0";
            case "11":
                return "255.224.0.0";
            case "12":
                return "255.240.0.0";
            case "13":
                return "255.248.0.0";
            case "14":
                return "255.252.0.0";
            case "15":
                return "255.254.0.0";
            case "16":
                return "255.255.0.0";
            case "17":
                return "255.255.128.0";
            case "18":
                return "255.255.192.0";
            case "19":
                return "255.255.224.0";
            case "20":
                return "255.255.240.0";
            case "21":
                return "255.255.248.0";
            case "22":
                return "255.255.252.0";
            case "23":
                return "255.255.254.0";
            case "24":
                return "255.255.255.0";
            case "25":
                return "255.255.255.128";
            case "26":
                return "255.255.255.192";
            case "27":
                return "255.255.255.224";
            case "28":
                return "255.255.255.240";
            case "29":
                return "255.255.255.248";
            case "30":
                return "255.255.255.252";
            case "31":
                return "255.255.255.254";
            case "32":
                return "255.255.255.255";
            default:
                return "-1";
        }
    }

    /**
     * 根据子网掩码转换为掩码位 如 255.255.255.252转换为掩码位 为 30
     *
     * @param netmarks
     * @return
     */
    public static int getNetMask(String netmarks) {
        StringBuilder sbf;
        String str;
        int inetmask = 0;
        int count = 0;
        String[] ipList = netmarks.split("\\.");
        for (int n = 0; n < ipList.length; n++) {
            sbf = toBin(Integer.parseInt(ipList[n]));
            str = sbf.reverse().toString();
            count = 0;
            for (int i = 0; i < str.length(); i++) {
                i = str.indexOf('1', i);
                if (i == -1) {
                    break;
                }
                count++;
            }
            inetmask += count;
        }
        return inetmask;
    }

    private static StringBuilder toBin(int x) {
        StringBuilder result = new StringBuilder();
        result.append(x % 2);
        x /= 2;
        while (x > 0) {
            result.append(x % 2);
            x /= 2;
        }
        return result;
    }

    /**
     * 根据ip段获取全部ip
     *
     * @param ipfrom
     * @param ipto
     * @return
     */
    public static List<String> parseIpRange(String ipfrom, String ipto) {
        List<String> ips = new ArrayList<String>();
        String[] ipfromd = ipfrom.split("\\.");
        String[] iptod = ipto.split("\\.");
        int[] int_ipf = new int[4];
        int[] int_ipt = new int[4];

        for (int i = 0; i < 4; i++) {
            int_ipf[i] = Integer.parseInt(ipfromd[i]);
            int_ipt[i] = Integer.parseInt(iptod[i]);
        }

        for (int A = int_ipf[0]; A <= int_ipt[0]; A++) {
            for (int B = (A == int_ipf[0] ? int_ipf[1] : 0); B <= (A == int_ipt[0] ? int_ipt[1]
                    : 255); B++) {
                for (int C = (B == int_ipf[1] ? int_ipf[2] : 0); C <= (B == int_ipt[1] ? int_ipt[2]
                        : 255); C++) {
                    for (int D = (C == int_ipf[2] ? int_ipf[3] : 0); D <= (C == int_ipt[2] ? int_ipt[3]
                            : 255); D++) {
                        ips.add(A + "." + B + "." + C + "." + D);
                    }
                }
            }
        }

        return ips;
    }

}
