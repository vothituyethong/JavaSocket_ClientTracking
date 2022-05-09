package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Server {
    private final ServerSocket serverSocket;
    private final List<Worker> workers = new ArrayList<>();
    private Socket socket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    private void waitForConnection() throws IOException {
        while (true) {
            socket = serverSocket.accept();
            Worker worker = new Worker(socket);
            addWorker(worker);
            worker.start();
        }
    }

    private void addWorker(Worker worker) {
        synchronized (this) {
            workers.add(worker);            
        }
    }

    private void removeWorker(Worker worker) {
        synchronized (this) {
            workers.remove(worker);
            worker.close();
        }
    }
    
    private void sendListPCToAdmin(){
        String result = "";
        for(int i=0;i<workers.size();i++){
            Worker worker = workers.get(i);
            if(worker.role!=null&&worker.role.equals("user"))
                result += worker.socket+";";
                
        }
        if(!result.equals("")){
            for(int i=0;i<workers.size();i++){
                Worker worker = workers.get(i);
                if(worker.role.equals("admin")){
                    try {
                        worker.send(result);
                    } catch (IOException ex) {
                    }
                    break;
                }                
            }
        }
            
    }

    private void broadcastMessage(Worker from, String message) {
        synchronized (this) {
            message = String.format("%s: %s", from.role, message);
            
            for (int i = 0; i < workers.size(); i++) {
                Worker worker = workers.get(i);
                if (workers.size()>1&&!worker.equals(from)&&!worker.role.equals(from.role)) {
                    try {
                        worker.send(message);
                    } catch (IOException e) {
                        workers.remove(i--);
                        worker.close();
                    }
                }
            }
        }
    }
    
    private int demAdmin(){
        int dem = 0;
        for (int i = 0; i < workers.size(); i++){
            Worker worker = workers.get(i);
            if(worker.role!=null&&worker.role.equals("admin"))
                dem++;
        }
        return dem;
    }

    private class Worker extends Thread {
        private final Socket socket;
        private final BufferedWriter out;
        private final BufferedReader in;
        private String role = null;

        public Worker(Socket socket) throws IOException {
            this.socket = socket;
            System.out.println(socket);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        
        

        private void send(String message) throws IOException {
            //System.out.println(message.substring(0, 6));
            if(message.contains("user: "))
                message = StringUtils.substringAfter(message, "user: ");
            System.out.println(message);
            out.write(message);
            out.newLine();
            out.flush();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String message = in.readLine();
                    int flag = 0;
                    if (role == null){
                        if (message.equals("admin")){
                            if(workers.size()>1&&demAdmin()==1)
                                this.send("unacceptable");
                            else{
                                role = message;
                                this.send("ok");
                                System.out.println("role: "+role);
                            }
                        }
                        else{
                            role = message;
                            System.out.println("role: "+role);
                        }    
                        
                        sendListPCToAdmin();
                    }
                    else{
                        broadcastMessage(this, message);
                        flag++;
                    }
                    try {
                        if (flag==0&&workers.size()==2&&this.role.equals("user"))
                                workers.get(1).send("accept");
                        if (flag==0&&workers.size()==2&&this.role.equals("admin"))
                             workers.get(0).send("accept");
                    } catch (IOException ex) {
                            System.err.println(ex);
                    }
                }
            } catch (IOException e) {
            }
            removeWorker(this);
        }

        private void close() {
            try {
                socket.close();
                out.close();
                in.close();
            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server(3393);
            server.waitForConnection();
        } catch (IOException e) {
        }
    }

}