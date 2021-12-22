package TinyCryptor.model.symmetric;

public class SimpleHC128 extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleHC128() {
        spec = "HC128/ECB/pkcs5padding";
        key = new byte[16];
        initVec = new byte[16];
    }

    @Override
    public String getName() {
        return "HC128";
    }
}