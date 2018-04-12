
package java_contact_app;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author shane
 */
public class Myfunc {
    
    //MAKES IMAGE FIT INTO JLABEL ON SIGN UP FORM.
    public ImageIcon resizePic(String picPath,byte[] BLOBpic, int wdth, int hgt){
        
        ImageIcon myImg;
        
        if(picPath != null){
             myImg = new ImageIcon(picPath);
            
        }
        else {
            myImg = new ImageIcon(BLOBpic);
        }
        
        
        Image img = myImg.getImage().getScaledInstance(wdth, hgt, Image.SCALE_SMOOTH);
        ImageIcon myPicture = new ImageIcon(img);
        return myPicture;
        
    }
    public String browseImage(JLabel lbl){
        
        String path = "";
        
        
          JFileChooser filec = new JFileChooser();
        filec.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        // FILE EXTENSION - ERROR HANDLING will only accept certain pictures
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("*.Images","jpg","png","gif");
        filec.addChoosableFileFilter(fileFilter);
        
        int fileState = filec.showSaveDialog(null);
        
        //IF THE USER SELECTS A FILE 
        if(fileState == JFileChooser.APPROVE_OPTION){
            File selectedFile = filec.getSelectedFile();
             path = selectedFile.getAbsolutePath();
            
//                imagePth = path;
            
            //DISPLAY THE IMAGE IN THE JLABEL USING RESIZE IMAGE
            lbl.setIcon(resizePic(path, null, lbl.getWidth(), lbl.getHeight()));
        }
        //IF THE USER CANCELS
        else if(fileState == JFileChooser.CANCEL_OPTION){
            System.out.println("No Image Selected");
            
        }
        return path;
    }
}
