package TinyCryptor.model.symmetric;

public class SimpleRC6 extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleRC6() {
        spec = "RC6/ECB/pkcs5padding";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{64, 128, 256, 512, 1024};
        modesList = new String[]{"ECB", "CBC", "CFB", "OFB", "CTR"};
    }

    @Override
    public String getName() {
        return "RC6";
    }
}