package SystemInfo;

public class OSInfo {
    
    private String nameOS;
    private String versionOS;
    private String architectureOS;
    
    public OSInfo() {
        this.nameOS = System.getProperty("os.name");          
        this.versionOS = System.getProperty("os.version");    
        this.architectureOS = System.getProperty("os.arch");
 }

    public String getNameOS() {
        return  nameOS;
    }

    public void setNameOS(String nameOS) {
        this.nameOS = nameOS;
    }

    public String getVersionOS() {
        return  versionOS;
    }

    public void setVersionOS(String versionOS) {
        this.versionOS = versionOS;
    }

    public String getArchitectureOS() {
        return  architectureOS;
    }

    public void setArchitectureOS(String architectureOS) {
        this.architectureOS = architectureOS;
    }
    
}