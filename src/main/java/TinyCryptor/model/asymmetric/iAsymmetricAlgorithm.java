package TinyCryptor.model.asymmetric;

import java.util.List;

public interface iAsymmetricAlgorithm {

    public String getName();

    public List getKeyList();

    public List getModeList();

    public List getPaddingList();

    public byte[] getPublicKey();

    public byte[] getPrivateKey();

    public void generateKey(int keySize) throws Exception;

    public iAsymmetricAlgorithm setSpec(String spec);

    public iAsymmetricAlgorithm setPublicKey(byte[] publicKey) throws Exception;

    public iAsymmetricAlgorithm setPrivateKey(byte[] privateKey) throws Exception;

    public byte[] encrypt(byte[] plainText) throws Exception;

    public byte[] decrypt(byte[] cipherText) throws Exception;
}
