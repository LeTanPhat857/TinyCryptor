package test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.util.Base64;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws Exception {
        Cipher cipher0 = Cipher.getInstance("PBEWithMD5AndDESede");
        Cipher cipher01 = Cipher.getInstance("PBEWithMD5AndDES");
        Cipher cipher02 = Cipher.getInstance("PBEWithHmacSHA256AndAES_128");
        Cipher cipher03 = Cipher.getInstance("PBEWithHmacSHA256AndAES_256");

        byte[] input = "an toan bao mat he thong thong tin".getBytes();

        // encrypt
        String password = "javapapers";
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory
                .getInstance("PBEWithMD5AndTripleDES");
        SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

        byte[] salt = new byte[8];
        Random random = new Random();
        random.nextBytes(salt);

        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);

        byte[] output = Base64.getEncoder().encode(cipher.doFinal(input));

        System.out.println(new String(output));

        // decrypt
        PBEKeySpec pbeKeySpec1 = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory secretKeyFactory1 = SecretKeyFactory
                .getInstance("PBEWithMD5AndTripleDES");
        SecretKey secretKey1 = secretKeyFactory1.generateSecret(pbeKeySpec1);

        PBEParameterSpec pbeParameterSpec1 = new PBEParameterSpec(salt, 100);

        Cipher cipher1 = Cipher.getInstance("PBEWithMD5AndTripleDES");
        cipher1.init(Cipher.DECRYPT_MODE, secretKey1, pbeParameterSpec1);

        byte[] finals = Base64.getDecoder().decode(output);

        System.out.println(new String(cipher1.doFinal(finals)));
    }
}
