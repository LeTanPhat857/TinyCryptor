package TinyCryptor.model.symmetric;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public abstract class AbstractSymmetricAlgorithm implements iSymmetricAlgorithm {

    // fields
    protected String charset = "utf-8";

    protected String spec;
    protected byte[] key;
    protected byte[] initVec;

    protected int[] keySizeList;
    protected String[] modesList;
    protected String[] paddingList;

    // constructor
    public AbstractSymmetricAlgorithm() {
    }

    // methods
    @Override
    public void generateKey(int keySize) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(getName());
        keyGenerator.init(keySize);
        key = keyGenerator.generateKey().getEncoded();
    }

    @Override
    public byte[] getKey() {
        return Base64.getEncoder().encode(key);
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

    @Override
    public iSymmetricAlgorithm setSpec(String spec) {
        this.spec = spec;
        return this;
    }

    protected Cipher createCipher(int mode) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(spec);
            String mod = spec.substring(spec.indexOf("/") + 1, spec.lastIndexOf("/"));
            if (mod.equalsIgnoreCase("ECB")) {
                cipher.init(mode, new SecretKeySpec(key, getName()));
            } else {
                cipher.init(mode, new SecretKeySpec(key, getName()), new IvParameterSpec(initVec));
            }
            return cipher;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public abstract String getName();

    @Override
    public List getKeyList() {
        List<Integer> list = new ArrayList<>();
        for (Integer item : keySizeList) {
            list.add(item);
        }
        return list;
    }

    @Override
    public List getModeList() {
        List<String> list = new ArrayList<>();
        for (String item : modesList) {
            list.add(item);
        }
        return list;
    }

    @Override
    public List getPaddingList() {
        List<String> list = new ArrayList<>();
        for (String item : paddingList) {
            list.add(item);
        }
        return list;
    }

    @Override
    public iSymmetricAlgorithm setKey(byte[] key) {
        if (!Arrays.equals(this.key, Base64.getDecoder().decode(key))) {
            this.key = key;
        }
        return this;
    }

    @Override
    public iSymmetricAlgorithm setInitVec(byte[] initVec) {
//        if (this.initVec.length == initVec.length) {
        this.initVec = initVec;
//        }
        return this;
    }
}
