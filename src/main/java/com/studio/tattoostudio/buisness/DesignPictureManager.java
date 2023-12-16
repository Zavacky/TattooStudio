package com.studio.tattoostudio.buisness;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;

public class DesignPictureManager {
    public void convertToPicture(byte[] bytePicture, String nameOfPicture){
        try {
            InputStream inputStream = new ByteArrayInputStream(bytePicture);

            ImageInputStream imageInputStream = new MemoryCacheImageInputStream(inputStream);

            BufferedImage bufferedImage = ImageIO.read(imageInputStream);

            ImageIO.write(bufferedImage, "jpg", new File(nameOfPicture + ".jpg"));
        }catch (IOException e) {
            throw new RuntimeException("Unable to connvert to picture");
        }
    }

    public byte[] convertToBytes(String picturePath){
        File image = new File(picturePath);
        FileInputStream fis = null;
        ByteArrayOutputStream bos;

        try {
            fis = new FileInputStream(image);

            bos = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fis.read(buf)) != -1;) {

                bos.write(buf, 0, readNum);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File doesn't exist");
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert to picture");
        }
        return bos.toByteArray();
    }
}
