package TinyCryptor.model.asymmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public abstract class AbstractAsymmetricAlgorithm implements iAsymmetricAlgorithm {

    // fields
    protected String charset = "utf-8";

    protected String spec;
    protected PublicKey publicKey;
    protected PrivateKey privateKey;

    protected int[] keySizeList;
    protected String[] modesList;
    protected String[] paddingList;

    // constructor
    public AbstractAsymmetricAlgorithm() {
    }

    // methods
    @Override
    public void generateKey(int keySize) throws Exception {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(keySize);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public byte[] getPublicKey() {
        return publicKey.getEncoded();
    }

    @Override
    public byte[] getPrivateKey() {
        return privateKey.getEncoded();
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
    public iAsymmetricAlgorithm setSpec(String spec) {
        this.spec = spec;
        return this;
    }

    protected Cipher createCipher(int mode) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(spec);
            if (mode == Cipher.ENCRYPT_MODE) {
                cipher.init(mode, publicKey);
            } else {
                cipher.init(mode, privateKey);
            }
            return cipher;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public iAsymmetricAlgorithm setPublicKey(byte[] base64PublicKey) throws Exception {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey));
            publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return this;
    }

    @Override
    public iAsymmetricAlgorithm setPrivateKey(byte[] base64PrivateKey) throws Exception {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey));
            privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return this;
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
}
