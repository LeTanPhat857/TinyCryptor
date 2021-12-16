package TinyCryptor.model.symmetric;

public class SimpleDES extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleDES() {
        spec = "DES/ECB/pkcs5padding";
        key = new byte[7];
        initVec = new byte[8];

        keySizeList = new int[]{56};
        modesList = new String[]{"ECB", "CBC", "PCBC", "CFB", "OFB", "CTR"};
        paddingList = new String[]{"NoPadding", "ISO10126Padding", "PKCS5Padding"};
    }

    // methods
    @Override
    public String getName() {
        return "DES";
    }
}
