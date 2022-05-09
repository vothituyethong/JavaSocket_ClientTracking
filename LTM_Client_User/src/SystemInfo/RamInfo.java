package SystemInfo;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class RamInfo {
    
    private double freeMemo;
    private double usedMemo;
    private double totalMemo;
    private double ram;
    
    public RamInfo(){
        Sigar sigar = new Sigar();
        Mem mem = null;
        try {
            mem = sigar.getMem();
        } catch (SigarException se) {
        }

        this.freeMemo = mem.getFree()*1.0/1024/ 1024 / 1024;
        this.totalMemo = mem.getTotal()*1.0/1024/ 1024 / 1024;
        this.usedMemo = mem.getUsed()*1.0/1024/ 1024 / 1024; 
        this.ram = mem.getRam()*1.0/1024;
    }

    public String getRam() {
        return ""+Math.round(ram);
    }

    public void setRam(double ram) {
        this.ram = ram;
    }

    public String getFreeMemo() {
        return ""+Math.round(freeMemo*100.0)/100.0;
    }

    public void setFreeMemo(double freeMemo) {
        this.freeMemo = freeMemo;
    }

    public String getUsedMemo() {
        return ""+Math.round(usedMemo*100.0)/100.0;
    }

    public void setUsedMemo(double usedMemo) {
        this.usedMemo = usedMemo;
    }

    public String getTotalMemo() {
        return ""+Math.round(totalMemo*100.0)/100.0;
    }

    public void setTotalMemo(double totalMemo) {
        this.totalMemo = totalMemo;
    }
}
