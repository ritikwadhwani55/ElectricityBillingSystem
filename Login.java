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
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class Login extends JFrame implements ActionListener{
    JPanel p;
    JTextField t1;JPasswordField p1;
    JButton submit,cancel,new_acc;
    public Login()
    {
        setLayout(null);
        setBounds(410,210,700,400);
        p=new JPanel();
        p.setBounds(90,50,500,285);
        p.setBackground(Color.white);
        p.setBorder(new TitledBorder(new LineBorder(Color.BLUE),"Login Details",TitledBorder.CENTER,TitledBorder.DEFAULT_JUSTIFICATION,new Font("sans-serif",Font.BOLD,17)));
        add(p);
        p.setLayout(null);
        JLabel l1=new JLabel("Username: ");
        l1.setFont(new Font("sans-serif",Font.PLAIN,15));
        l1.setBounds(60,40,100,20);
        p.add(l1);
        t1=new JTextField();
        t1.setBounds(200,40,150,33);
        p.add(t1);
        JLabel l2=new JLabel("Password: ");
        l2.setBounds(60,100,100,20);
        l2.setFont(new Font("sans-serif",Font.PLAIN,15));
        p.add(l2);
        p1=new JPasswordField();
        p1.setBounds(200,100,150,33);
        p.add(p1);
        submit=new JButton("Submit");
        submit.setBounds(68,185,120,30);
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        p.add(submit);
        cancel=new JButton("Cancel");
        cancel.setBounds(220,185,120,30);
        cancel.setBackground(Color.BLUE);
        cancel.addActionListener(this);
        cancel.setForeground(Color.white);
        p.add(cancel);
        
        new_acc=new JButton("New User?Sign Up");
        new_acc.setBounds(130,230,180,30);
        new_acc.setBackground(Color.BLUE);
        new_acc.addActionListener(this);
        new_acc.setForeground(Color.white);
        p.add(new_acc);
        
        getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==submit){
            String username=t1.getText();
            String pswd=p1.getText();
            if(username.equals("") || pswd.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Empty Login Details, Please fill it!");
            }
            else
            {
                Conn c=new Conn();
                String qu=null;
                qu="select * from admin where email='"+username+"' and password='"+pswd+"'";
                try{
                    ResultSet rs=c.s.executeQuery(qu);
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null,"Admin Successfully logged in!");
                        new Adminpage(rs.getString("email"),rs.getString("password"));
                        this.setVisible(false);
                    }
                    else{
                            qu="select * from customer where email='"+username+"' and password='"+pswd+"'";
                            rs=c.s.executeQuery(qu);
                            if(rs.next())
                            {
                                JOptionPane.showMessageDialog(null,"Customer Successfully logged in!");
                                new UserPage(rs.getString("email"),rs.getString("password"));
                                this.setVisible(false);
                            }
                            else
                            {
                                 JOptionPane.showMessageDialog(null,"Please enter a valid login details!");
                                t1.setText("");
                                p1.setText("");
                            }
                    }

                }
                catch(Exception ex){
                    ex.printStackTrace();}
                }
            }
        else if(a.getSource()==cancel)
        {
            this.setVisible(false);
            System.exit(0);
            
        }
        else if(a.getSource()==new_acc)
        {
            new CreateUserAccount();
            this.setVisible(false);
        }
    }
    public static void main(String args[])
    {
        new Login();
    }
}
