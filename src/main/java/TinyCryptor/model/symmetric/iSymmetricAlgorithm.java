package TinyCryptor.model.symmetric;

import java.util.List;

public interface iSymmetricAlgorithm {

    public String getName();

    public List getKeyList();

    public List getModeList();

    public List getPaddingList();

    public void generateKey(int keySize) throws Exception;

    public byte[] getKey();

    public iSymmetricAlgorithm setSpec(String spec);

    public iSymmetricAlgorithm setKey(byte[] key);

    public iSymmetricAlgorithm setInitVec(byte[] initVec);

    public byte[] encrypt(byte[] plainText) throws Exception;

    public byte[] decrypt(byte[] cipherText) throws Exception;
}
