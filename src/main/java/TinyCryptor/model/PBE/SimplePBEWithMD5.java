package TinyCryptor.model.PBE;

public class SimplePBEWithMD5 extends AbstractPBEAlgorithm{

    // fields

    // constructor
    public SimplePBEWithMD5() {
        spec = "PBEWithMD5AndDES";
        secretKey = null;
        salt = new byte[8];

        symmetricAlgorithmList = new String[] {"DES", "TripleDES"};
    }

    // methods
    @Override
    public String getName() {
        return "MD5";
    }
}
