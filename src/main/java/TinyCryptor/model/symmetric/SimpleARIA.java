package TinyCryptor.model.symmetric;

public class SimpleARIA extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleARIA() {
        spec = "ARIA/ECB/pkcs5padding";
        key = new byte[16];
        initVec = new byte[16];
    }

    @Override
    public String getName() {
        return "ARIA";
    }
}