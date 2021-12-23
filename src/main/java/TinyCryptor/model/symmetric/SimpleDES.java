package TinyCryptor.model.symmetric;

public class SimpleDES extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleDES() {
        spec = "DES/ECB/pkcs5padding";
        key = new byte[7];
        initVec = new byte[8];

        keySizeList = new int[]{56};
    }

    // methods
    @Override
    public String getName() {
        return "DES";
    }
}
