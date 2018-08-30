package enigma;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Enigma extends JFrame {

    public Enigma() {
        initComponents();
    }

    public void initComponents() {
        ArrayList<Rotor> list = new ArrayList<Rotor>();
        
        ImageIcon image;
        JLabel blbl;
        image = new ImageIcon("enigma.jpg");
        blbl = new JLabel();
        blbl.setIcon(image);
        
        EnigmaMenu mn = new EnigmaMenu();
        JButton clearCipher = new JButton("Clear ciphertext");
        JButton clearPlain = new JButton("Clear plaintext");
        JButton testButton = new JButton("test");
        JButton inv1 = new JButton("test");
        
        leftP = new JPanel();
         
        rightP = new JPanel();
        
        rotor_i = new Rotor(diff_i, startPos1, "Right", list);
        rotor_ii = new Rotor(diff_ii, startPos2, "Middle", list);
        rotor_iii = new Rotor(diff_iii, startPos3, "Left", list);
        
        reflector = new Rotor(r_diff, RPos, "Reflector", list);
        
        rotor_ii.setStep(25);
        rotor_iii.setStep(25);

        list.add(rotor_i);
        list.add(rotor_ii);
        list.add(rotor_iii);

        tf = new JTextArea("enter your plaintext\nto the enigma machine");
        
        tf.setRows(5);
        sp = new JScrollPane(tf);
       
        labelr1 = new JLabel("Right  rotor conf.: ", SwingConstants.RIGHT);
        labelr2 = new JLabel("Middle rotor conf.: ", SwingConstants.RIGHT);
        labelr3 = new JLabel("Left   rotor conf.: ", SwingConstants.RIGHT);
        cr1 = new JComboBox(quan);
        cr2 = new JComboBox(quan);
        cr3 = new JComboBox(quan);
        crypted = new JTextField();
        crypted.setEditable(false);
        spc = new JScrollPane(crypted);
        textL = new JLabel("Plaintext:", SwingConstants.RIGHT);
        cipherL = new JLabel("Ciphertext:", SwingConstants.RIGHT);

        clearCipher.setHorizontalTextPosition(SwingConstants.RIGHT);
        clearCipher.setToolTipText("Clear the cipher-text area");
        clearPlain.setHorizontalTextPosition(SwingConstants.RIGHT);
        clearPlain.setToolTipText("Clear the plain-text area");
        cr1.setToolTipText("Define starting possition of first rotor.");
        cr2.setToolTipText("Define starting possition of second rotor.");
        cr3.setToolTipText("Define starting possition of third rotor.");
        
        
        
        center = new JPanel(new GridLayout( 9, 2));

        button = new JButton("Encrypt");
        button.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                enigmaCipher();
            }
        });

        clearCipher.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                crypted.setText("");
            }
        });
        
        clearPlain.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                tf.setText("");
            }
        });
        
        
        center.add(labelr1);
        center.add(cr1);
        center.add(labelr2);
        center.add(cr2);
        center.add(labelr3);
        center.add(cr3);
        center.add(textL);
        center.add(sp);
        center.add(cipherL);
        center.add(spc);
        center.add(testButton);
        testButton.setVisible(false);
        center.add(clearCipher);
        center.add(inv1);
        inv1.setVisible(false);
        
        center.add(clearPlain);
        
        
        setLayout(new BorderLayout());
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Virtual Enigma Emulator");
      
        
        setSize(870, 350);
        //setResizable(false);
        add(mn, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        leftP.add(blbl);
        add(leftP,BorderLayout.WEST);
        add(rightP,BorderLayout.EAST);
        leftP.setSize(400,400);
        add(button, BorderLayout.SOUTH);
        
        mn.setBackground(java.awt.Color.lightGray);
        
        leftP.setBackground(java.awt.Color.lightGray);
        rightP.setBackground(java.awt.Color.lightGray);
        //pack();
         
    }

    private void enigmaCipher() {
        String rv;
        int step1,step2, step3;
        char test;
        char out1, out2, out3, out4,out5 , out6, outF;
        encrypted_tf = "";
        rv = cr1.getSelectedItem().toString();
        step1 = Integer.parseInt(rv);
        
        rotor_i.setStep(step1);
        
        rv = cr2.getSelectedItem().toString();
        step2 = Integer.parseInt(rv);
        rotor_ii.setStep(step2);
        rv = cr3.getSelectedItem().toString();
        step3 = Integer.parseInt(rv);
        rotor_iii.setStep(step3);
        
        String plaintext = tf.getText();
        System.out.println("total letters: "+ plaintext.length());
               
        
        for (int i = 0; i < plaintext.length(); i++) {
             
            test  = plaintext.charAt(i);
            
            out1 = rotor_i.Forward(test);
            System.out.println(out1);
            
            
            
            out2 = rotor_ii.Forward(out1);
            out3 = rotor_iii.Forward(out2);
            
            out4 = reflector.Forward(out3);
            
            out5 = rotor_iii.Backward(out4);
            
            out6 = rotor_ii.Backward(out5);
            
            outF = rotor_i.Backward(out6);
            
            
            System.out.println("---");
            System.out.println(test);
            System.out.println(out1);
            System.out.println(out2);
            System.out.println(out3);
            System.out.println(out4);
            System.out.println(out5);
            System.out.println(out6);
            System.out.println(outF);
            System.out.println("---");
            
            String new_output = Character.toString(outF); 
            
            encrypted_tf = encrypted_tf + new_output;
            crypted.setText(encrypted_tf);
        }
        
    }
 
    public String getCrypted() {
        return crypted.getText();
    }
    
    public static void main(String[] args) {

        System.out.println("Enigma machine emulation");
         
        Enigma e = new Enigma();
        e.setVisible(true);
        
    }

    private String[] quan = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
    private JScrollPane sp, spc;
    private JComboBox cr1, cr2, cr3;
    private JLabel labelr1, labelr2, labelr3;
    private JLabel textL;
    private JPanel center;
    private JPanel leftP, rightP;
    private JLabel cipherL;
    private JTextField crypted;
    private JButton button, clearCipher, clearPlain, testButton, inv1;
    private JTextArea tf;
    private int startPos1, startPos2, startPos3, RPos;
    private String encrypted_tf;
    public static int diff_i[] = { 'b' - 'a', 'd' - 'b', 'f' - 'c', 'h' - 'd', 'j' - 'e', 'l' - 'f', -('g' - 'c'), 'p' - 'h', 'r' - 'i', 't' - 'j', 'x' - 'k', 'v' - 'l', 'z' - 'm', 0, 'y' - 'o', -('p' - 'e'), -('q' - 'i'), 'w'- 'r', -('s' - 'g'), -('t'-'a'), -('u' - 'k'), -('v' - 'm'), -('w' - 'u'), -('x' - 's'), -('y' - 'q'), -('z' - 'o')  };
    public static int diff_ii[] = {'a'-'a', 'j'-'b', 1, 'k'-'d', 's'-'e', 'i'-'f', 'r'-'g', 'u'-'h', 'x'-'i', -('j'-'b'),  1, -('l'-'h'), 'w'-'m', 't'-'n', -('o'-'m'), -('p'-'c'),  0, -('r'-'g'), 'z'-'s', -('t'-'n'), -('u'-'p'),   3,  9, -2, -10,  5};
    public static int diff_iii[] = {'e'-'a','k'-'b', 'm'-'c', 'f'-'d', 'l'-'e', 1, -('g'-'d'), 'q'-'h', 'v'-'i', 'z'-'j', 3, 't'-'l', 2, 'w'-'n', 'y'-'o', -('p'-'h'), 'x'-'q', 3, 0, -4, 6, -('v'-'i'), 5, -('x'-'r'), 4, -('z' - 'j')};
    public static int r_diff[] = { 'y' - 'a','r' - 'b','u' - 'c', 'h'-'d',  'q' - 'e', 's' - 'f', 'l'-'g', -('h'-'d'), 'p'-'i', 'x'-'j', 'n'-'k', -('l'-'g'), 'o'-'m', -('n'-'k'), -('o'-'m'), -('p'-'i'), -('q'-'e'), -('r'-'b'), -('s'-'f'), 'z'-'t',  -('u'-'c'), 'w' - 'v', -('w' - 'v'), -('x' - 'j'), -('y' - 'a'), -('z' - 't')  }; 

    public Rotor rotor_i, rotor_ii, rotor_iii, reflector;
    
}
