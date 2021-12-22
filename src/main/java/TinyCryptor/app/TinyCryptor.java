package TinyCryptor.app;

import TinyCryptor.controller.Controller;
import TinyCryptor.model.*;
import TinyCryptor.model.PBE.SimplePBEWithMD5;
import TinyCryptor.model.PBE.SimplePBEWithSHA1;
import TinyCryptor.model.asymmetric.SimpleRSA;
import TinyCryptor.model.hash.SimpleMD;
import TinyCryptor.model.hash.SimpleSHA;
import TinyCryptor.model.symmetric.*;
import TinyCryptor.view.logoFrame.LogoFrame;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.net.MalformedURLException;
import java.security.Security;

public class TinyCryptor {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        // show logo
        LogoFrame.showLogo();
        // add  BouncyCastleProvider
        Security.addProvider(new BouncyCastleProvider());
        // init algorithm type
        SymmetricType symmetricType = SymmetricType.create()
                // JCA
                .add(new SimpleAES())
                .add(new SimpleBlowfish())
                .add(new SimpleDES())
                .add(new SimpleDESede())
                .add(new SimpleRC2())
                .add(new SimpleRC4())
                .add(new SimpleRC5())
                // BouncyCastleProvider
                .add(new SimpleRC6())
                .add(new SimpleCAST5())
                .add(new SimpleCAST6())
                .add(new SimpleHC128())
                .add(new SimpleHC256())
                .add(new SimpleChaCha())
                .add(new SimpleCamellia());
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
        // dispose logo
        LogoFrame.disposeLogo();
    }
}
