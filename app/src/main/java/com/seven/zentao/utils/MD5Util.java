package com.seven.zentao.utils;/**
 * Created by ttarfall on 2016/10/25.
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ttarfall
 * @date 2016-10-25 15:56
 */
public class MD5Util {

    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String getFileMD5String(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        messagedigest.update(byteBuffer);
        return bufferToHex(messagedigest.digest());
    }


    public static String getMD5String(String s) throws Exception {
        return getMD5String(s.getBytes());
    }


    public static String getMD5String(byte[] bytes) throws Exception {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }


    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }


    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }


    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr) throws Exception {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    /**
     * @param s
     * @return
     */
    public static String getMD5Encoding(String s) {
        byte[] input = s.getBytes();
        String output = null;
        char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input);
            /*
             */
            byte[] tmp = md.digest();
            char[] str = new char[32];
            byte b = 0;
            for (int i = 0; i < 16; i++) {
                b = tmp[i];
                str[2 * i] = hexChar[b >>> 4 & 0xf];
                str[2 * i + 1] = hexChar[b & 0xf];
            }
            output = new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static void main(String[] args) {
        String str = MD5Util.getMD5Encoding("wkingston");
        System.out.println(str);
    }


    public static String passwordCrypt(String pStrPW) {
        String strCrypt = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte data[] = pStrPW.getBytes();
            int l = data.length;
            for (int i = 0; i < l; i++)
                md.update(data[i]);
            byte digest[] = md.digest();
            strCrypt = Base64.encodeToString(digest, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strCrypt;
    }


    /**
     * 代码中检查二次打包的签名
     */
    public static String signMd(Context c) {
        try {
            PackageInfo packageInfo = c.getPackageManager().getPackageInfo(
                    c.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];
            String MD5 = getMD5StringLowerCase(sign.toByteArray());
            return MD5;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String getMD5StringLowerCase(byte[] data) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(data);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
