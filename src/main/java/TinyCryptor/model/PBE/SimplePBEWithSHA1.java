package TinyCryptor.model.PBE;

public class SimplePBEWithSHA1 extends AbstractPBEAlgorithm {

    // fields

    // constructor
    public SimplePBEWithSHA1() {
        spec = "PBEWithSHA1RC2_40";
        secretKey = null;
        salt = new byte[8];

        symmetricAlgorithmList = new String[]{"RC2_40", "RC2_128", "RC4_40", "RC4_128", "DESede"};
    }

    // methods
    @Override
    public String getName() {
        return "SHA1";
    }
}
