package SystemInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author ACER
 */
public class myProcess {
    String processString = null;
    
    public myProcess(){
        try {
            String line;
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(Runtime.getRuntime()
                            .exec("tasklist.exe /fo csv /nh").getInputStream()));
            String t ="";
            
            while ((line = input.readLine()) != null) {
                t+=line+'\n';
            }
            this.processString = t;
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public String getProcessString() {
        return processString;
    }

    public void setProcessString(String processString) {
        this.processString = processString;
    }
    
}
