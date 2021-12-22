package test;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

public class TestBC {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, ClassNotFoundException, MalformedURLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        File file  = new File(Objects.requireNonNull(Utils.class.getClassLoader().getResource("lib/bcprov-ext-jdk15on-170.jar")).getPath());
//
//        System.load(file.getPath());
//
//        URL url = file.toURI().toURL();
//        URL[] urls = new URL[]{url};
//        System.out.println(Arrays.toString(urls));
//
//        URLClassLoader cl = new URLClassLoader(urls);
//        System.out.println(Arrays.toString(cl.getURLs()));
//
//        Class cls = cl.loadClass("org.bouncycastle.jcajce.provider.symmetric.Noekeon");
//        System.out.println(cls.getName());

        Security.addProvider(new BouncyCastleProvider());
        Cipher.getInstance("Noekeon");
    }
}
