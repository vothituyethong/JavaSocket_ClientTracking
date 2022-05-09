package SystemInfo; 
 
import org.hyperic.sigar.CpuPerc; 
import org.hyperic.sigar.Sigar; 
import org.hyperic.sigar.SigarException; 

public class CpuInfo { 
 
    private String vendor;
    private String model;
    private String mhz;
    private String totalCPU;
    private String physicalCPU;
    private String corePerCPU;
    
    public CpuInfo() { 
        //super();
        Sigar sigar = new Sigar();
        org.hyperic.sigar.CpuInfo infos[];
        try {
            infos = sigar.getCpuInfoList();
            CpuPerc cpuList[] = null;
            cpuList = sigar.getCpuPercList();
            org.hyperic.sigar.CpuInfo info = infos[0]; 
            long cacheSize = info.getCacheSize(); 
            this.vendor = info.getVendor(); 
            this.model = info.getModel(); 
            this.mhz = String.valueOf(info.getMhz()); 
            this.totalCPU = String.valueOf(info.getTotalCores()); 
            if ((info.getTotalCores() != info.getTotalSockets()) || 
                (info.getCoresPerSocket() > info.getTotalCores())){ 
                this.physicalCPU = String.valueOf(info.getTotalSockets()); 
                this.corePerCPU =  String.valueOf(info.getCoresPerSocket());                 
            }
            
        
        } catch (SigarException ex) {            
        }
    } 

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMhz() {
        return mhz;
    }

    public void setMhz(String mhz) {
        this.mhz = mhz;
    }

    public String getTotalCPU() {
        return totalCPU;
    }

    public void setTotalCPU(String totalCPU) {
        this.totalCPU = totalCPU;
    }

    public String getPhysicalCPU() {
        return physicalCPU;
    }

    public void setPhysicalCPU(String physicalCPU) {
        this.physicalCPU = physicalCPU;
    }

    public String getCorePerCPU() {
        return corePerCPU;
    }

    public void setCorePerCPU(String corePerCPU) {
        this.corePerCPU = corePerCPU;
    }
 
}