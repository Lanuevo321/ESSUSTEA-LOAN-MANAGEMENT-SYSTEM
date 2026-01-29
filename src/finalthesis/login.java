
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class login extends JFrame{
    JFrame frame = new JFrame("Login Page");

    public void bodyMain(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,500));
        
        forgotPass();
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    public void forgotPass(){
        JPanel panel1 = new JPanel();
        frame.add(panel1);

        panel1.setPreferredSize(new Dimension(500,500));
        panel1.setBackground(Color.BLACK);
    }


    public void createAcc(){}


    public static void main(String args []){
        login log = new login();
        log.bodyMain();
    }
}