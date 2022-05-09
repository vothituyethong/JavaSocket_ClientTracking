package Cmd;
import java.awt.datatransfer.*;
import java.awt.*;

public class MyClipboard {
    String clipboard;    
    
    public MyClipboard() {
        try{
            Clipboard c=Toolkit.getDefaultToolkit().getSystemClipboard();
            this.clipboard = c.getData(DataFlavor.stringFlavor).toString();
        }catch(Exception e){
            this.clipboard = "(Empty clipboard)";
        }
    }

    public String getClipboard() {
        return clipboard;
    }

    public void setClipboard(String clipboard) {
        this.clipboard = clipboard;
    }
}