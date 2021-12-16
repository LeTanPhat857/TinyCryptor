package TinyCryptor.app;

import TinyCryptor.controller.Controller;
import TinyCryptor.model.*;
import TinyCryptor.model.PBE.SimplePBEWithMD5;
import TinyCryptor.model.PBE.SimplePBEWithSHA1;
import TinyCryptor.model.asymmetric.SimpleRSA;
import TinyCryptor.model.hash.SimpleMD;
import TinyCryptor.model.hash.SimpleSHA;
import TinyCryptor.model.symmetric.*;

public class TinyCryptor {

    public static void main(String[] args) {
        // init algorithm type
        SymmetricType symmetricType = SymmetricType.create()
                .add(new SimpleAES())
                .add(new SimpleBlowfish())
                .add(new SimpleDES())
                .add(new SimpleDESede())
                .add(new SimpleRC2())
                .add(new SimpleRC4())
                .add(new SimpleRC5());
        AsymmetricType asymmetricType = AsymmetricType.create()
                .add(new SimpleRSA());
        HashType hashType = HashType.create()
                .add(new SimpleMD())
                .add(new SimpleSHA());
        PBEType pbeType = PBEType.create()
                .add(new SimplePBEWithMD5())
                .add(new SimplePBEWithSHA1());
        // init model
        Model model = Model.create()
                .add(symmetricType)
                .add(asymmetricType)
                .add(hashType)
                .add(pbeType);
        // init controller
        Controller.create(model).init();
    }
}
