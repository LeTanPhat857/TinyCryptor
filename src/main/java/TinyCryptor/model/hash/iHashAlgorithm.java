package TinyCryptor.model.hash;

import java.util.List;

public interface iHashAlgorithm {

    public String getName();

    public List getTypeList();

    public iHashAlgorithm setSpec(String spec);

    public byte[] encrypt(byte[] plainText) throws Exception;
}
