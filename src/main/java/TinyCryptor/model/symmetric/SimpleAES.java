package TinyCryptor.model.symmetric;

public class SimpleAES extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleAES() {
        spec = "AES/ECB/pkcs5padding";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{128, 192, 256};
    }

    @Override
    public String getName() {
        return "AES";
    }

    // methods

}
