package com.whstone.auth.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 高级加密标准
 * Advanced Encryption Standard
 */
public class AESUtil {

    private static final String AES = "AES";
    private static final String charset = null; // 编码格式；默认null为GBK
    private static final int keysizeAES = 128;
    private static final String KEY = "whstone";

    /**
     * 使用 AES 进行加密
     */
    public static String encode(String res) {
        return keyGeneratorES(res, AES, KEY, keysizeAES, true);
    }

    /**
     * 使用 AES 进行加密
     */
    public static String encode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, true);
    }

    /**
     * 使用 AES 进行解密
     */
    public static String decode(String res) {
        return keyGeneratorES(res, AES, KEY, keysizeAES, false);
    }

    /**
     * 使用 AES 进行解密
     */
    public static String decode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, false);
    }

    // 使用KeyGenerator双向加密，DES/AES，注意这里转化为字符串的时候是将2进制转为16进制格式的字符串，不是直接转，因为会出错
    private static String keyGeneratorES(String res, String algorithm, String key, int keysize, boolean isEncode) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            if (keysize == 0) {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                kg.init(new SecureRandom(keyBytes));
            } else if (key == null) {
                kg.init(keysize);
            } else {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);

                String os = System.getProperty("os.name");
                if (os.contains("Linux") || os.contains("AIX")) {
                    SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                    random.setSeed(keyBytes);
                    kg.init(keysize, random);
                } else {
                    kg.init(keysize, new SecureRandom(keyBytes));
                }

            }
            SecretKey sk = kg.generateKey();
            SecretKeySpec sks = new SecretKeySpec(sk.getEncoded(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            if (isEncode) {
                cipher.init(Cipher.ENCRYPT_MODE, sks);
                byte[] resBytes = charset == null ? res.getBytes() : res.getBytes(charset);
                return parseByte2HexStr(cipher.doFinal(resBytes));
            } else {
                cipher.init(Cipher.DECRYPT_MODE, sks);
                return new String(cipher.doFinal(parseHexStr2Byte(res)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 将二进制转换成16进制
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    // 将16进制转换为二进制
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(encode("P@ssw0rd"));
    }
}
