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
}
