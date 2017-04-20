package com;

import com.spi.Spi;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by xd.wang on 16/11/14.
 */
public class Test {


    static final String enc = "GB2312";

    static String digout(byte[] paramArrayOfByte)
    {
        StringBuffer localStringBuffer = new StringBuffer();
        for (int i = 0; i < 20; i++)
        {
            char c1 = (char)(paramArrayOfByte[i] >>> 4 & 0xF);
            char c2 = (char)(paramArrayOfByte[i] & 0xF);
            c1 = (char)(c1 > '\t' ? 97 + (c1 - '\n') : '0' + c1);
            c2 = (char)(c2 > '\t' ? 97 + (c2 - '\n') : '0' + c2);
            localStringBuffer.append(c1);
            localStringBuffer.append(c2);
        }
        return localStringBuffer.toString();
    }

    public static byte[] hash(byte[] paramArrayOfByte, String paramString)
    {
        try
        {
            MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
            localMessageDigest.update(paramArrayOfByte);
            return localMessageDigest.digest();
        }
        catch (Exception localException) {}
        return null;
    }

    public static byte[] hash(String paramString1, String paramString2)
    {
        try
        {
            byte[] arrayOfByte = paramString1.getBytes("GB2312");
            return hash(arrayOfByte, paramString2);
        }
        catch (Exception localException) {}
        return null;
    }

    public static String hashString(String paramString1, String paramString2)
    {
        byte[] arrayOfByte = hash(paramString1, paramString2);
        if (arrayOfByte == null) {
            return "";
        }
        return digout(arrayOfByte);
    }

    static String combine(String paramString1, String paramString2, String paramString3)
    {
        return combine(paramString1, paramString2, paramString3, "");
    }

    static String combine(String paramString1, String paramString2, String paramString3, String paramString4)
    {
        Double num = 0.02909671613289655;
        String str = num + "|" + paramString2 + "<$CmbSplitter$>" + paramString3 + paramString4;
        byte[] arrayOfByte1 = hash(paramString1, "MD5");
        SecretKeySpec localSecretKeySpec = new SecretKeySpec(arrayOfByte1, "RC4");
        byte[] arrayOfByte2;
        try
        {
            Cipher localCipher = Cipher.getInstance("RC4");
            localCipher.init(1, localSecretKeySpec);
            arrayOfByte2 = localCipher.doFinal(str.getBytes("GB2312"));
        }
        catch (Exception localException1)
        {
            byte[] arrayOfByte3;
            try
            {
                arrayOfByte3 = str.getBytes("GB2312");
            }
            catch (Exception localException2)
            {
                return "";
            }
            arrayOfByte2 = new byte[arrayOfByte3.length];
            RC4Encrypt(arrayOfByte3, arrayOfByte3.length, arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length);
        }
        return Base64Coder.Base64Encode(arrayOfByte2);
    }

    public static String genMerchantCode(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10)
    {
        return genMerchantCode(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramString10, "", "", "");
    }

    public static String genMerchantCode(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13)
    {
        StringBuffer localStringBuffer = new StringBuffer(256);
        if (paramString11.length() > 0) {
            localStringBuffer.append("<$ClientIP$>").append(paramString11).append("</$ClientIP$>");
        }
        if (paramString12.length() > 0) {
            localStringBuffer.append("<$GoodsType$>").append(paramString12).append("</$GoodsType$>");
        }
        if (paramString13.length() > 0) {
            localStringBuffer.append("<$Reserved$>").append(paramString13).append("</$Reserved$>");
        }
        String str = combine(paramString1, paramString9, paramString10, localStringBuffer.toString());
        return "|" + str + "|" + hashString(new StringBuilder().append(paramString1).append(str).append(paramString2).append(paramString3).append(paramString4).append(paramString5).append(paramString6).append(paramString7).append(paramString8).toString(), "SHA-1");
    }

    static int b2i(byte paramByte)
    {
        return paramByte >= 0 ? paramByte : 256 + paramByte;
    }

    public static int RC4Encrypt(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, byte[] paramArrayOfByte3, int paramInt3)
    {
        int[] arrayOfInt1 = new int[256];
        int[] arrayOfInt2 = new int[256];
        for (int i = 0; i < 256; i++)
        {
            arrayOfInt1[i] = i;
            arrayOfInt2[i] = b2i(paramArrayOfByte2[(i % paramInt2)]);
        }
        int j = 0;
        int k;
        for (int i = 0; i < 256; i++)
        {
            j = (j + arrayOfInt1[i] + arrayOfInt2[i]) % 256;
            k = arrayOfInt1[i];
            arrayOfInt1[i] = arrayOfInt1[j];
            arrayOfInt1[j] = k;
        }
        int i = 0;
        j = 0;
        int i1 = 0;
        while (paramInt1 != 0)
        {
            i = (i + 1) % 256;
            j = (j + arrayOfInt1[i]) % 256;
            k = arrayOfInt1[i];
            arrayOfInt1[i] = arrayOfInt1[j];
            arrayOfInt1[j] = k;
            int m = (arrayOfInt1[i] + arrayOfInt1[j]) % 256;
            int n = arrayOfInt1[m];
            paramArrayOfByte3[i1] = ((byte)((b2i(paramArrayOfByte1[i1]) ^ n) & 0xFF));
            i1++;
            paramInt1--;
        }
        return i1;
    }

    public static void main(String[] args) {

            System.out.print("hello");

    }
}
