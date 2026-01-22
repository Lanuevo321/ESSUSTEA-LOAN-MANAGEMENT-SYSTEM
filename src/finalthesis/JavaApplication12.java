/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package finalthesis;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Sandy Ogaro
 */
public class JavaApplication12 extends PlainDocument{

    private int limit;
    
    public JavaApplication12(int limitaion){
        this.limit = limitaion;
    }
    
     public void insertString(int offset, String str, AttributeSet set) throws BadLocationException{
        if (str == null){
            return;
        }else if ((getLength() + str.length()) <= limit){
            str = str.toUpperCase();
            super.insertString(offset, str, set);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
