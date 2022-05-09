package Main;

import Cmd.MyClipboard;
import Cmd.Screenshot;
import SystemInfo.CpuInfo;
import SystemInfo.DiskInfo;
import SystemInfo.OSInfo;
import SystemInfo.RamInfo;
import SystemInfo.myProcess;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

public class Client extends Thread {
    Socket socket;
    BufferedWriter out;
    BufferedReader in ;
    String dataReceive = "" ;    
    
    OSInfo os = new OSInfo();
    CpuInfo cpu = new CpuInfo();
    DiskInfo disk = new DiskInfo();
    RamInfo ram = new RamInfo();
    myProcess process = new myProcess();
    String sellectedPC;
    String preMsg = null;

    public String getSellectedPC() {
        return sellectedPC;
    }

    public void setSellectedPC(String sellectedPC) {
        this.sellectedPC = sellectedPC;
    }
    
    public Client(String serverAddress, int serverPort) throws UnknownHostException, IOException {
        socket = new Socket(InetAddress.getByName(serverAddress), serverPort);
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
    }

    public String getDataReceive() {
        return dataReceive;
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
                if(preMsg == null || !preMsg.equals(dataReceive)){
                    preMsg = dataReceive;
                    handleDataReceive();
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
    public void sendInfo(){
        try {
            send("INFO_OS_"+os.getNameOS());
            send("OS_"+os.getVersionOS());
            send("OS_"+os.getArchitectureOS());
            
            send("CPU_"+cpu.getVendor());
            send("CPU_"+cpu.getModel());
            send("CPU_"+cpu.getMhz());
            send("CPU_"+cpu.getTotalCPU());
            send("CPU_"+cpu.getPhysicalCPU());
            send("CPU_"+cpu.getCorePerCPU());
            
            send("RAM_"+ram.getRam());
            send("RAM_"+ram.getTotalMemo());            
            
            send("DISK_"+disk.getInfo()+"_END");
        } catch (IOException ex) {
        }
    }
    public void getRamRealTime(String message){
        while(!message.equals("stop")){
            RamInfo memory = new RamInfo();
            try {
                send("RAMRT_"+memory.getUsedMemo());
                System.out.println("RAMRT_"+memory.getUsedMemo());
            } catch (IOException ex) {                
            }
        }
    }
    public void getAllProcess(){
        try {
            send("PROCESS_"+process.getProcessString());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void endTask(String pid){
        try {
            Runtime.getRuntime().exec("taskkill /pid "+pid+" /f");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void shutdown(){
        try {
            Runtime.getRuntime().exec("shutdown -s  -t 30 -c \"Admin want to shutdown your PC.\"");
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    public void logout(){
        try {
            Runtime.getRuntime().exec("shutdown -l");
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    public void getClipboard(){
        try {
            MyClipboard c = new MyClipboard();
            send("CB_"+c.getClipboard()+"_ENDCB");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    public void getScreenshot(){
        Screenshot sc = new Screenshot();
        try {
            send("SC_"+sc.encodeImageToString("scr.png")+"_ENDSC");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    public void handleDataReceive(){        
            if(getDataReceive().contains("PID_")){
                String pid = StringUtils.substringAfter(getDataReceive(), "PID_");
                endTask(pid);                
            }
            switch (getDataReceive()){
                case "admin: StopRam":{
                    getRamRealTime("stop");
                    break;
                }
                case "admin: option 1":{
                    sendInfo();
                    break;
                }
                case "admin: option 2":{
                    System.out.println("Tôi muốn xem RAM.");
                    getRamRealTime("start");
                    break;
                }
                case "admin: stop option 2":{
                    System.out.println("Tôi dừng xem RAM.");
                    getRamRealTime("stop");
                    break;
                }
                case "admin: option 3":{                    
                    getAllProcess();
                    break;
                }
                case "admin: shutdown":{
                    shutdown();
                    break;
                }
                case "admin: logout":{
                    logout();
                    break;
                }
                case "admin: clipboard":{
                    getClipboard();
                    break;
                } 
                case "admin: screenshot":{
                    getScreenshot();
                    break;
                }
            }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = null;
        String role = "user";
        try {
            client = new Client("localhost", 3393);
            client.send(role);
            client.start();

            while (true) {
                scanner.nextLine(); //keep connection
             }
        } catch (IOException e) {
        }
        if (client != null)
            client.close();
        scanner.close();    
    }    
}