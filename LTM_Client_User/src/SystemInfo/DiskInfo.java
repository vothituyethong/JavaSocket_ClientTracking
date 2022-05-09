package SystemInfo;

import Main.Client;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiskInfo {
    
    private String info="";
    
    public DiskInfo(){
        // Get a list of all filesystem roots on this system
            File[] roots = File.listRoots();
            
            // For each filesystem root, print some info
            for (File root : roots) {
                double free = root.getFreeSpace()*1.0/1024/1024/1024;
                double total = root.getTotalSpace()*1.0/1024/1024/1024;
                double used = total - free;
                this.info += "File system root: " + root.getAbsolutePath()+"\n";
                this.info += "Used space (GB):" + Math.round(used*100.0)/100.0 + "\n";
                this.info += "Free space (GB): " + Math.round(free*100.0)/100.0 +"\n";
                this.info += "Total space (GB): " + Math.round(total*100.0)/100.0 +"\n";
            }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
}
