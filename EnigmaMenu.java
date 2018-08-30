 
package enigma;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

 
public class EnigmaMenu extends JMenuBar {
    
    public EnigmaMenu() {
        
        
        fc = new JFileChooser();
        
        info = new JMenuItem("About");
        import_text = new JMenuItem("Load Plaintext");
        export_text = new JMenuItem("Save Ciphertext");
        
        info.setToolTipText("Project's description.");
        import_text.setToolTipText("Choose a text file to import the plain message to be encrypted");
        export_text.setToolTipText("Create(select) a text file to save the encrypted message.");
        
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doInfo();
            }
        });
        
        import_text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLoad();
            }
        });
        
        export_text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doExportText();
            }
        });
        
        
        add(import_text);
        add(export_text);
        add(info);
        
    }
 
    private void doLoad() {
        
        String load;
        load = "";
        
        fc.showOpenDialog(this); //modal
        theFile = fc.getSelectedFile();
        
        if(theFile == null) {
            return;
        }
        
        try {
            myFile = new FileReader(theFile);
            buff = new BufferedReader(myFile);
            
            while(true) {
                String line = buff.readLine();
                
                if(line == null)
                    break;
                
                load = load + line + "\n";
            }
            
        } catch (IOException e) {
            System.out.println("File doesn't exist!");
            e.printStackTrace();
        
        } finally {
        
            System.out.println(load);
            
            try{
                buff.close();
                myFile.close();
            } catch(IOException e) {
                
                e.printStackTrace();
                
            }
        }
        
    }
    
    private void doExportText() {
        
        
    }
    
    
    private void doInfo() {
        JOptionPane.showMessageDialog(this, "Program emulates the Enigma\nmachine encryption.Adjusting\nthe steps "
                + "on the rotors creates\ndifferent outputs.\nDeveloped by Mouzakitis Nikolaos\n                   2018\n", "About", JOptionPane.INFORMATION_MESSAGE);
       
    }
    private JTextField cr;
    private File theFile;
    private JFileChooser fc;
    private FileReader myFile = null;
    private BufferedReader buff = null;
    private JMenuItem info;
    private JMenuItem import_text, export_text;
}
