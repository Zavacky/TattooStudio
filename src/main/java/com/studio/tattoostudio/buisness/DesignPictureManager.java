package com.studio.tattoostudio.buisness;

import java.io.*;
import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;

public class DesignPictureManager {
    /**
     * Converts byte array to picture
     * @author Martin Zavacky
     * @param bytePicture
     */
    public static Image convertToPicture(byte[] bytePicture){
        ByteArrayInputStream bis = new ByteArrayInputStream(bytePicture);
        return new Image(bis);
    }

    /**
     * Converts picture to byte array
     * @author Martin Zavacky
     * @param image
     * @return byte array of picture
     */
    public static byte[] convertToBytes(Image image){
        byte[] b = new byte[0];
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", byteArrayOutputStream);
            b = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}
