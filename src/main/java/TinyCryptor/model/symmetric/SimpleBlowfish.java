package TinyCryptor.model.symmetric;

public class SimpleBlowfish extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleBlowfish() {
        spec = "Blowfish/ECB/pkcs5padding";
        key = new byte[8];
        initVec = new byte[8];

        keySizeList = new int[]{64, 128, 256};
    }

    // methods
    @Override
    public String getName() {
        return "Blowfish";
    }
}
