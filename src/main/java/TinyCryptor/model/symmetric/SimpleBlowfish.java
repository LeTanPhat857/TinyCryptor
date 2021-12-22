package TinyCryptor.model.symmetric;

public class SimpleBlowfish extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleBlowfish() {
        spec = "Blowfish/ECB/pkcs5padding";
        key = new byte[8];
        initVec = new byte[8];

        keySizeList = new int[]{32, 64, 128, 256};
        modesList = new String[]{"ECB", "CBC", "PCBC", "CFB", "OFB", "CTR"};
//        paddingList = new String[]{"NoPadding", "ISO10126Padding", "PKCS5Padding"};
    }

    // methods
    @Override
    public String getName() {
        return "Blowfish";
    }
}
