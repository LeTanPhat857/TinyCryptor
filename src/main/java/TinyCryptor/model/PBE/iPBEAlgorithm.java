package TinyCryptor.model.PBE;

import java.util.List;

public interface iPBEAlgorithm {

    public String getName();

    public List getSymmetricAlgorithmList();

    public byte[] getSalt();

    public byte[] getEncryptedKey();

    public void generateRandomSalt();

    public iPBEAlgorithm setSpec(String spec);

    public iPBEAlgorithm setKey(char[] keyChars) throws Exception;

    public byte[] encrypt(byte[] plainText) throws Exception;

    public byte[] decrypt(byte[] cipherText) throws Exception;
}
