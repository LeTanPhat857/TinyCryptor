package TinyCryptor.model.symmetric;

public class SimpleHC256 extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleHC256() {
        spec = "HC256/ECB/pkcs5padding";
        key = new byte[16];
        initVec = new byte[16];
    }

    @Override
    public String getName() {
        return "HC256";
    }
}