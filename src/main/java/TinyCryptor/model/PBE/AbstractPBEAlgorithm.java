package TinyCryptor.model.PBE;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public abstract class AbstractPBEAlgorithm implements iPBEAlgorithm {

    // fields
    protected String charset = "utf-8";

    protected String spec;
    protected SecretKey secretKey;
    protected byte[] salt;

    protected String[] symmetricAlgorithmList;

    // constructor
    public AbstractPBEAlgorithm() {
    }

    // methods
    @Override
    public iPBEAlgorithm setSpec(String spec) {
        this.spec = spec;
        return this;
    }

    @Override
    public iPBEAlgorithm setKey(char[] keyChars) throws Exception {
        secretKey = SecretKeyFactory.getInstance(spec).generateSecret(new PBEKeySpec(keyChars));
        return this;
    }

    @Override
    public abstract String getName();

    @Override
    public List getSymmetricAlgorithmList() {
        List<String> list = new ArrayList<>();
        for (String item : symmetricAlgorithmList) {
            list.add(item);
        }
        return list;
    }

    @Override
    public byte[] getSalt() {
        return salt;
    }

    @Override
    public void generateRandomSalt() {
        new Random().nextBytes(salt);
    }

    @Override
    public byte[] getEncryptedKey() {
        return secretKey.getEncoded();
    }

    @Override
    public byte[] encrypt(byte[] plainText) throws Exception {
        try {
            return Base64.getEncoder().encode(createCipher(Cipher.ENCRYPT_MODE).doFinal(plainText));
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public byte[] decrypt(byte[] cipherText) throws Exception {
        try {
            return createCipher(Cipher.DECRYPT_MODE).doFinal(Base64.getDecoder().decode(cipherText));
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new Exception(e.getMessage());
        }
    }

    protected Cipher createCipher(int mode) throws Exception {
        try {
            if (salt == null) {
                generateRandomSalt();
            }

            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance(spec);
            cipher.init(mode, secretKey, pbeParameterSpec);
            return cipher;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
