package TinyCryptor.utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class Utils {

    public static Image scaleImage(Image image, int width, int height, int scaleType) {
        try {
            return image.getScaledInstance(width, height, scaleType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image getImage(String imagePath) {
        try {
            return Toolkit.getDefaultToolkit().getImage(Utils.class.getClassLoader().getResource(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image getImage(String imagePath, int width, int height, int scaleType) {
        return Objects.requireNonNull(scaleImage(getImage(imagePath), width, height, scaleType));
    }

    public static ImageIcon getImageIcon(String imagePath, int width, int height, int scaleType) {
        return new ImageIcon(Objects.requireNonNull(scaleImage(getImage(imagePath), width, height, scaleType)));
    }

    public static File createFile(String path) throws Exception {
        return new File(path);
    }

    public static byte[] readFile(File file) throws Exception {
        return Files.readAllBytes(file.toPath());
    }

    public static void writeFile(byte[] data, File file) throws IOException {
        if (file != null) {
            Files.write(file.toPath(), data);
        }
    }

    public static String convertStringToHex(String string) throws Exception {
        char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        byte[] bytes = string.getBytes("utf-8");
        final int nBytes = bytes.length;
        char[] result = new char[2 * nBytes];         //  1 hex contains two chars
        //  hex = [0-f][0-f], e.g 0f or ff
        int j = 0;
        for (byte aByte : bytes) {                    // loop byte by byte
            // 0xF0 = FFFF 0000
            result[j++] = HEX[(0xF0 & aByte) >>> 4];    // get the top 4 bits, first half hex char
            // 0x0F = 0000 FFFF
            result[j++] = HEX[(0x0F & aByte)];          // get the bottom 4 bits, second half hex char
            // combine first and second half, we get a complete hex
        }
        return new String(result);
    }
}
