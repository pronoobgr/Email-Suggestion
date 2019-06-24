/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userform;

/**
 *
 * @author aggelos
 */
public class UserForm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setSize(380, 180);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle("User Form");
        frame.setVisible(true);
    }
    
}
