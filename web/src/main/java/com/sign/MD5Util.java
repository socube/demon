package com.sign;

import java.security.MessageDigest;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/27.
 */
public class MD5Util {


    private static final String Encoding = "UTF-8";

    public MD5Util() {
    }

    public static String getMD5Str(String str) {
        String result = null;

        try {
            MessageDigest ex = null;
            ex = MessageDigest.getInstance("MD5");
            ex.reset();
            ex.update(str.getBytes("UTF-8"));
            byte[] byteArray = ex.digest();
            StringBuffer md5StrBuff = new StringBuffer();

            for(int i = 0; i < byteArray.length; ++i) {
                if(Integer.toHexString(255 & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(255 & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(255 & byteArray[i]));
                }
            }

            result = md5StrBuff.toString().toUpperCase();
        } catch (Exception var6) {
            result = null;
        }

        return result;
    }

    public static String getMD5LowerStr(String str) {
        String result = null;

        try {
            MessageDigest ex = null;
            ex = MessageDigest.getInstance("MD5");
            ex.reset();
            ex.update(str.getBytes("UTF-8"));
            byte[] byteArray = ex.digest();
            StringBuffer md5StrBuff = new StringBuffer();

            for(int i = 0; i < byteArray.length; ++i) {
                if(Integer.toHexString(255 & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(255 & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(255 & byteArray[i]));
                }
            }

            result = md5StrBuff.toString().toLowerCase();
        } catch (Exception var6) {
            result = null;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getMD5LowerStr("京NQ8J09421208WyouxinpaitestCGLOqO3Y0caH5QYZXWGcSg==220161227145542"));
    }
}
