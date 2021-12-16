package TinyCryptor.model.hash;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHashAlgorithm implements iHashAlgorithm {

    // fields
    protected String charset = "utf-8";

    protected String spec = "";
    protected int[] typeList = {};

    // constructor
    public AbstractHashAlgorithm() {
    }

    // methods
    @Override
    public byte[] encrypt(byte[] plainText) throws Exception {
        try {
            return createMessageDigest().digest(plainText);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public iHashAlgorithm setSpec(String spec) {
        this.spec = spec;
        return this;
    }

    protected MessageDigest createMessageDigest() throws Exception {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(spec);
            return messageDigest;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public abstract String getName();

    @Override
    public List getTypeList() {
        List<Integer> list = new ArrayList<>();
        for (Integer item : typeList) {
            list.add(item);
        }
        return list;
    }
}
