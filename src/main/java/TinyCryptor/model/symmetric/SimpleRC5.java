package TinyCryptor.model.symmetric;

public class SimpleRC5 extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleRC5() {
        spec = "RC5";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{64, 128, 256, 512, 1024};
        modesList = new String[]{"ECB", "CBC", "CFB", "OFB", "CTR"};
    }

    // methods
    @Override
    public String getName() {
        return "RC5";
    }
}
