/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricitybillingsystem;

/**
 *
 * @author HP
 */
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
public class ChangePswd extends JFrame implements ActionListener{
    String email_id,pswd;
    JTextField changepswd;
    JButton submit,cancel;
    Adminpage ref;
    public ChangePswd(String id,String pass,Adminpage ref)
    {
        super("Change Password");
        this.email_id=id;
        this.ref=ref;
        this.pswd=pass;
        setLayout(null);
        JLabel heading=new JLabel("Change Password");
        heading.setFont(new Font("Tahoma",Font.BOLD,22));
        heading.setBounds(110,20,250,30);
        add(heading);
//        this.setLayout(null);
        setVisible(true);
        setBounds(535,110,450,350);
        
        
        JLabel old=new JLabel("Old Password: ");
        old.setFont(new Font("Tahoma",Font.PLAIN,17));
        old.setBounds(20,65,160,30);
        add(old);
        
        Conn c=new Conn();
            try
            {
                
                String query="select password from admin where email='"+this.email_id+"' and password='"+this.pswd+"'";
                ResultSet result=c.s.executeQuery(query);
                if(result.next())
                {
                    JLabel old1=new JLabel(result.getString("password"));
                    old1.setFont(new Font("Tahoma",Font.PLAIN,17));
                    old1.setBounds(230,65,180,30);
                    add(old1);
                    getContentPane().setBackground(Color.white);
                    JLabel change=new JLabel("New Password");
                    change.setFont(new Font("Tahoma",Font.PLAIN,17));
                    change.setBounds(20,115,150,30);
                    add(change);

                    this.changepswd=new JTextField();
                    this.changepswd.setBounds(230,115,180,30);
                    add(this.changepswd);
                    this.changepswd.setFont(new Font("Tahoma",Font.PLAIN,16));


                    submit=new JButton("Submit");
                    submit.setBounds(20,170,150,30);
                    submit.setBackground(Color.blue);
                    submit.setForeground(Color.white);
                    add(submit);
                    submit.addActionListener(this);
                    cancel=new JButton("Cancel");
                    cancel.setBounds(230,170,150,30);
                    cancel.setBackground(Color.blue);
                    cancel.setForeground(Color.white);
                    add(cancel);
                    cancel.addActionListener(this);
                }
            }catch(Exception exc){}
            
        
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==submit)
        {
           String query1="update admin set password='"+this.changepswd.getText()+"' where email='"+this.email_id+"' and password='"+this.pswd+"'";
           Conn cn=new Conn();
           try {
               cn.s.executeUpdate(query1);
               JOptionPane.showMessageDialog(null,"Password Changed Successfully");
               this.setVisible(false);
               if(ref!=null)
                    ref.setVisible(false);
               new Login();
           }
           catch(Exception e){}
        }
        else if(ae.getSource()==cancel)
        {
            this.setVisible(false);
        }
    }
    public static void main(String args[])
    {
        Adminpage ad=null;
        new ChangePswd("ritik@gmail.com","ritik",ad);
    }
}
