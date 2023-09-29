package akshaykanehitlu.topquiz.gui;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Utils {

    public static byte[] getImagesAsBytes(String imageName) {
        byte[] data;
        try {
            InputStream inputStream = Utils.class.getResourceAsStream(imageName);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            byte[] bytes = buffer.toByteArray();
            return bytes;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static InputStream getImagesAsStream(String imageName) {
        try {
            InputStream inputStream = Utils.class.getResourceAsStream(imageName);

            return inputStream;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
