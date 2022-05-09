package Cmd;
    
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;
public class Screenshot {

    public Screenshot() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        GraphicsDevice[] screens = ge.getScreenDevices();       
        Rectangle allScreenBounds = new Rectangle();  
        for (GraphicsDevice screen : screens) {  
            Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();              
            allScreenBounds.width += screenBounds.width;  
            allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
            allScreenBounds.x=Math.min(allScreenBounds.x, screenBounds.x);
            allScreenBounds.y=Math.min(allScreenBounds.y, screenBounds.y);
        } 
        try {
            Robot robot = new Robot();
            BufferedImage bufferedImage = robot.createScreenCapture(allScreenBounds);
            File file = new File("scr.png");
            if(!file.exists())
                file.createNewFile();
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            ImageIO.write( bufferedImage, "png", fos );
        } catch (Exception ex) {
            System.out.println("Lỗi rồi kìa.");
        }
    }
    
    public String encodeImageToString(String path){
        File file = new File(path);
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.getEncoder().encodeToString(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedfile;
    }
    
}