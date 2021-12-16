package TinyCryptor.model.symmetric;

public class SimpleDESede extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleDESede() {
        spec = "DESede/ECB/pkcs5padding";
        key = new byte[24];
        initVec = new byte[8];

        keySizeList = new int[]{112, 168};
        modesList = new String[]{"ECB", "CBC", "PCBC", "CFB", "OFB", "CTR"};
        paddingList = new String[]{"NoPadding", "ISO10126Padding", "PKCS5Padding"};

    }

    // methods
    // methods
    @Override
    public String getName() {
        return "DESede";
    }
}
