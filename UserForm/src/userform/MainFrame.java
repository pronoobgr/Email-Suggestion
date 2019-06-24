/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userform;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Aggelos
 */
public class MainFrame extends Frame{
    
    private Label firstNameLbl, lastNameLbl;
    private TextField firstNameTF, lastNameTF, emailTF;
    private Button suggestEmailBtn, saveBtn, clearBtn;

    public MainFrame() {
        
        //Create UI
        firstNameLbl = new  Label("First Name:\t");
        firstNameTF = new TextField(32);

        lastNameLbl = new  Label("Last Name:\t");
        lastNameTF = new TextField(32);
        
        emailTF=new TextField(42);
        emailTF.setEnabled(false);
    
        suggestEmailBtn = new Button("Suggest email");
        saveBtn = new Button("Save Form");
        clearBtn = new Button("Clear Form");
        
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        this.add(firstNameLbl);
        this.add(firstNameTF);
        
        this.add(lastNameLbl);
        this.add(lastNameTF);
        
        this.add(emailTF);
        
        this.add(suggestEmailBtn);
        this.add(saveBtn);
        this.add(clearBtn);
        
        
        //handle window events
        this.addWindowListener(new MyWindowListener());
        
        
        //implementation of clear button               
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //workaround for Java bug: can't clear textfield unless we first call getText or set non empty string
                firstNameTF.getText();
                lastNameTF.getText();
                emailTF.getText();
                
                firstNameTF.setText("");
                lastNameTF.setText("");
                emailTF.setText("");
            }
        });
        
        //implementation of suggest email button
        suggestEmailBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameTF.getText();
                String lastName = lastNameTF.getText();
            
                if (!firstName.isEmpty() && !lastName.isEmpty()) {
                    String email = firstName.substring(0, 1) + "." + lastName.substring(0, Math.min(4, lastName.length())) + "@uniwa.gr";
                    emailTF.setText(email);
                } else {
                    JOptionPane.showMessageDialog(saveBtn, "Please fill first and last name", "Can't suggest email", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //implementation of save button 
        //save appends the user data (first name, last name and email) in a file named "users.txt"        
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String firstName = firstNameTF.getText();
                String lastName = lastNameTF.getText();
                String email = emailTF.getText();
            
                if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty()) {
                    try {
                        BufferedWriter file = new BufferedWriter(new FileWriter("Users.txt", true));
                        String str4 = firstName + "\t" + lastName + "\t" + email;                       
                        file.write(str4);
                        file.newLine();
                        file.close(); 
                        JOptionPane.showMessageDialog(saveBtn, "Record saved to Users.txt", "Save Completed", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(saveBtn, "Can't access Users.txt", "File Access Error" +
                                ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(saveBtn, "Nothing to save", "Save not completed", JOptionPane.WARNING_MESSAGE);
                }                             
            }
        });       
                     
    }

        
}
