package TinyCryptor.model.symmetric;

public class SimpleAES extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleAES() {
        spec = "AES/ECB/pkcs5padding";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{128, 192, 256};
        modesList = new String[]{"ECB", "CBC", "PCBC", "CFB", "OFB", "CTR"};
        paddingList = new String[]{"NoPadding", "ISO10126Padding", "PKCS5Padding"};
    }

    @Override
    public String getName() {
        return "AES";
    }

    // methods

}
