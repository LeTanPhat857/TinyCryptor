package TinyCryptor.model.symmetric;

public class SimpleChaCha extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleChaCha() {
        spec = "ChaCha/ECB/pkcs5padding";
        key = new byte[16];
        initVec = new byte[16];
    }

    @Override
    public String getName() {
        return "ChaCha";
    }
}