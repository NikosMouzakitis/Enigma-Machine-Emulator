package enigma;

import java.util.ArrayList;

public class Rotor {

    public Rotor(int[] differences, int startPos, String  name, ArrayList <Rotor> list) {
        
        rotors = new ArrayList <Rotor>();
        rotors = list;
        int r = 0;
        this.letters = new char[26];
        this.rname =  name;
        this.step = startPos;
        System.out.println("Rotor created!");
        
        if("Right".equals(rname)) {
            kickStep = 1;
        } else {
            kickStep = 26;
        }
        
        for (char c = 'a'; c <= 'z'; c++, r++) {
            this.letters[r] = c;
        }

        this.swift = new int[26];

        for (int i = 0; i < 26; i++) {
            swift[i] = differences[i];
        }
    }

    private int FindPos(char in) {
        
        int rv = -1;

        for (int i = 0; i < 26; i++) {
            if (letters[i] == in) {
                rv = i;
                System.out.println("pos of " + in + "is : "+ rv);
                break;
            }
        }
        return rv;
    }

    public  void setKickNext() {
        System.out.println("I am rotor: "+rname);
        
        if( rname.equals("Right")) {
      
            rotors.get(1).step++;
        
            if( (rotors.get(1).step % kickStep ) == 0) {
                rotors.get(1).step = 0;
                rotors.get(2).step++;
                
                if( (rotors.get(2).step % kickStep ) == 0) {
                     rotors.get(2).step = 0;
                }
            }
        }
    }
    
    public char Backward(char in) {
        char tmp,test, now;
        int i;
        
        if( (in == ' ') || (in == '.') || (in == ',') || (in == '\n'))
        {
            System.out.println("punct");
            return in;
        }
        
         
        
        for( test = 'a'; test <= 'z'; test++) {
            
            int vn = FindPos(test);
            now = (char) (test + swift[(vn + step) %26 ]);
            
            // under/over bound control.
            
            if (now > 'z') {

                tmp = now;

                for (i = 0; tmp != 'z'; i++) {
                    tmp--;
                }
                now = letters[i - 1];

            } else if (now < 'a') {
                tmp = now;

                for (i = 0; tmp != 'a'; i++) {
                    tmp++;
                }

                now = letters[25 - (i - 1)];
            }
                        
            if(now == in) {
               break;
            }
        }
        
        //kick step
        
        if(rname.equals("Right")){
                step++;
            } else {
                
                    System.out.println("todo");
            }      
                
        if( (step % kickStep) == 0) {
                step = 0;
                setKickNext();
        }
        
        
        System.out.println("returning: "+ test);
        
        return test;
    }
    
    public char Forward(char in) {
        
        char tmp;
        int i = 0;
        int index = FindPos(in);
        
        
        if( (in == ' ') || (in == ',') || (in == '.') || (in == '\n') )
            return in;
        
        
        char now = (char) (in + swift[(index + step) %26 ]);
        
        
        /* Code just for reflector */
        if( rname.equals("Reflector")) {
            
            System.out.println("Reflecting back: " + now);
            return now;
        }
       
        if(  (now >= 'a') && (now <= 'z') ) {
             
            System.out.println("returning" + now);
            
            
            return now;
            
        } else if( now > 'z') {
           
            tmp = now;
            
            for(i = 0; tmp !=  'z'; i++){
                tmp--;
            }
            now = letters[i-1];
            
            
        
        } else if( now < 'a') {
            
            //to-do fix
           
            tmp = now;
            
            for(i = 0; tmp != 'a'; i++)
                tmp++;
            
            System.out.println( i);
            now = letters[ 25 - (i - 1)];
            
            
            
            
        }
        
        System.out.println("returning" + now);
        return now;
    }
    
    public void setStep( int index) {
        this .step = index;
    }
    
    public int getStep() {
        return this.step;
    }
    ArrayList <Rotor> rotors;
    
    private static int kickStep;
    public String rname;
    private int step;
    private char[] letters;
    private int[] swift;
}