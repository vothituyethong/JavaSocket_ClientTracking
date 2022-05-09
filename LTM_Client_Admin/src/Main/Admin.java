package Main;

import GUI.SystemInfo;
import GUI.GUI_clientad;
import GUI.GUI_clientad;
import GUI.ListPC;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;

public class Admin extends Thread {
    private final Socket socket;
    private final BufferedWriter out;
    private final BufferedReader in;
    
    private String content = "";
    String dataReceive = "" ;
    String receiveForm = "";
    public static String message = null;
    
    SystemInfo systemInfo;
    GUI_clientad gui_clientad;
    ListPC listPC;

    public Admin(String serverAddress, int serverPort) throws UnknownHostException, IOException {
        socket = new Socket(InetAddress.getByName(serverAddress), serverPort);
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public String getDataReceive() {
        return dataReceive;
    }

    public String getReceiveForm() {
        return receiveForm;
    }

    public void setReceiveForm(String receiveForm) {
        this.receiveForm = receiveForm;
    }
    
    private void send(String message) throws IOException {
        out.write(message);
        out.newLine();
        out.flush();
    }

    @Override
    public void run() {
        try {
            while (true) {
                dataReceive = in.readLine();
                System.out.println(dataReceive);
                if((dataReceive.contains("OS_")&&content.contains("Socket"))
                        ||dataReceive.contains("INFO_")||content.contains("_END")
                        ||dataReceive.contains("RAMRT_")
                        ||dataReceive.contains("PROCESS_")
                        ||dataReceive.contains("CB_")
                        ||dataReceive.contains("SC_"))
                    content = "";
                content += dataReceive+";";
                try {
                    handleDataReceive();
                } catch (InterruptedException ex) {
                }
            }
        } catch (IOException e) {
        }
        close();
        System.exit(0);
    }

    private void close() {
        try {
            socket.close();
            out.close();
            in.close();
        } catch (IOException e) {
        }
    }
    public static BufferedImage decodeStringToImage(String imageString) {
 
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    public void setContentForForm(){       

            if(content.contains("_END"))
                gui_clientad.setContent(StringUtils.substringBetween(content,"INFO" ,"_END"));
            if(content.contains("RAMRT_"))
                gui_clientad.setContent(content);
            if(content.contains("PROCESS_"))
                gui_clientad.setContent(StringUtils.substringAfter(content, "PROCESS_"));
            if(content.contains("_ENDCB"))
                gui_clientad.setContent(StringUtils.substringBetween(content,"CB_" ,"_ENDCB"));
            if(content.contains("_ENDSC")){
                String imgString = StringUtils.substringBetween(content,"SC_" ,"_ENDSC");
                try {
                    File fileNew = new File("src/image/scrNew.png");
                    if(!fileNew.exists())                        
                       fileNew.createNewFile();
                    FileOutputStream fos;
                    fos = new FileOutputStream(fileNew);
                    ImageIO.write( decodeStringToImage(imgString), "png", fos );
                    
                } catch (IOException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
    }
    public void handleDataReceive() throws InterruptedException{        
        synchronized (this) {

            switch(dataReceive){
                case "ok":{
                    listPC = new ListPC();
                    listPC.setVisible(true);
                    listPC.admin=this;
                    systemInfo = new SystemInfo();
                    gui_clientad = new GUI_clientad();
                    gui_clientad.admin = this;
                    listPC.gui_clientad = this.gui_clientad;
                    listPC.gui_clientad.systemInfo = this.systemInfo;
                    break;
                }
                case "unacceptable":{
                    JOptionPane.showMessageDialog(null, "Only allow one admin to access.","Notify",0);
                    System.exit(0);
                }
                default:{
                    setContentForForm();
                }  
            }
            if(dataReceive.contains("Socket")){
                listPC.setContent(dataReceive);
                Thread.sleep(500);
                listPC.loadAll();
            }
        }
    }
    public String getMessageForm(){
        if(gui_clientad!=null){
            String msg = gui_clientad.getMessage();
            gui_clientad.setMessage(null);
            return msg;
        }  
        return null;
    }

    public static void main(String[] args) {
        Admin admin = null;
        String role = "admin";
        try {
            admin = new Admin("localhost", 3393);
            admin.send(role);
            admin.start();
            while (true) {
                String message = admin.getMessageForm();
                if(message !=null){
                    System.out.println("Form: "+message);
                    admin.send(message);
                }  
                else 
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            
        } catch (IOException e) {
        }
        if (admin != null)
            admin.close();
    }
}