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
import javax.swing.table.*;
import javax.swing.border.*;
import java.awt.event.*;
public class CreateUserAccount extends JFrame implements ActionListener{
    JPanel p;
    JTextField t1,name,age,city,contact;
    JPasswordField p1;
    JRadioButton m,f,o;
    JTextArea ta;
    JButton submit,cancel;
    public CreateUserAccount()
    {
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.white);
        setBounds(410,110,700,700);
        p=new JPanel();
        p.setBounds(90,50,550,590);
        p.setBackground(Color.white);
        p.setBorder(new TitledBorder(new LineBorder(Color.BLUE),"User Details",TitledBorder.CENTER,TitledBorder.DEFAULT_JUSTIFICATION,new Font("sans-serif",Font.BOLD,17)));
        add(p);
        p.setLayout(null);
        
        JLabel name=new JLabel("Full Name: ");
        name.setFont(new Font("sans-serif",Font.PLAIN,17));
        name.setBounds(60,40,100,20);
        p.add(name);
        
        this.name=new JTextField();
        this.name.setFont(new Font("sans-serif",Font.PLAIN,17));
        this.name.setBounds(200,40,200,33);
        p.add(this.name);
        
        JLabel age=new JLabel("Age: ");
        age.setFont(new Font("sans-serif",Font.PLAIN,17));
        age.setBounds(60,220,100,20);
        p.add(age);
        
        this.age=new JTextField();
        this.age.setFont(new Font("sans-serif",Font.PLAIN,17));
        this.age.setBounds(200,220,200,33);
        p.add(this.age);
        
        JLabel gender=new JLabel("Gender: ");
        gender.setFont(new Font("sans-serif",Font.PLAIN,17));
        gender.setBounds(60,280,100,20);
        p.add(gender);
        
        ButtonGroup g=new ButtonGroup();
        
        m=new JRadioButton("Male");
        m.setBounds(200,280,100,20);
        g.add(m);
        p.add(m);
        f=new JRadioButton("Female");
        f.setBounds(320,280,100,20);
        g.add(f);
        p.add(f);
        o=new JRadioButton("Other");
        o.setBounds(440,280,100,20);
        g.add(o);
        p.add(o);
        
        JLabel add=new JLabel("Address: ");
        add.setFont(new Font("sans-serif",Font.PLAIN,17));
        add.setBounds(60,340,100,20);
        p.add(add);
        
        ta=new JTextArea();
        ta.setFont(new Font("sans-serif",Font.PLAIN,17));
        ta.setBackground(Color.LIGHT_GRAY);
        ta.setBounds(200,340,300,80);
        p.add(ta);
        
        JLabel city=new JLabel("City: ");
        city.setFont(new Font("sans-serif",Font.PLAIN,17));
        city.setBounds(60,430,100,20);
        p.add(city);
        
        this.city=new JTextField();
        this.city.setFont(new Font("sans-serif",Font.PLAIN,17));
        this.city.setBounds(200,430,200,33);
        p.add(this.city);
        
        JLabel cont=new JLabel("Contact: ");
        cont.setFont(new Font("sans-serif",Font.PLAIN,17));
        cont.setBounds(60,490,100,20);
        p.add(cont);
        
        this.contact=new JTextField();
        this.contact.setFont(new Font("sans-serif",Font.PLAIN,17));
        this.contact.setBounds(200,490,200,33);
        p.add(this.contact);
        
        JLabel l1=new JLabel("Username: ");
        l1.setFont(new Font("sans-serif",Font.PLAIN,17));
        l1.setBounds(60,100,100,20);
        p.add(l1);
        
        t1=new JTextField();
        t1.setFont(new Font("sans-serif",Font.PLAIN,17));
        t1.setBounds(200,100,200,33);
        p.add(t1);
        JLabel l2=new JLabel("Password: ");
        l2.setFont(new Font("sans-serif",Font.PLAIN,17));
        l2.setBounds(60,160,100,20);
        l2.setFont(new Font("sans-serif",Font.PLAIN,17));
        p.add(l2);
        p1=new JPasswordField();
        p1.setBounds(200,160,200,33);
        p1.setFont(new Font("sans-serif",Font.PLAIN,17));
        p.add(p1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
            submit=new JButton("Submit");
            this.submit.setBounds(20,550,150,30);
            p.add(this.submit);
            submit.setBackground(Color.blue);
            submit.setForeground(Color.white);
            submit.setFont(new Font("Tahoma",Font.PLAIN,16));
            submit.addActionListener(this);

            cancel=new JButton("Cancel");
            this.cancel.setBounds(280,550,150,30);
            p.add(this.cancel);
            cancel.setBackground(Color.blue);
            cancel.setForeground(Color.white);
            cancel.setFont(new Font("Tahoma",Font.PLAIN,16));
            cancel.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ac)
    {
        if(ac.getSource()==cancel)
        {
            this.setVisible(false);
        }
        else if(ac.getSource()==submit)
        {
           Conn c=new Conn();
           try
           {
               String gend=null;
               if(f.isSelected())
                   gend="Female";
               else if(m.isSelected())
                   gend="Male";
               else
                   gend="Other";
               String qu="insert into customer(full_name,gender,age,address,contact,email,password,city) values('"+this.name.getText()+"','"+gend+"',"+Integer.parseInt(this.age.getText())+",'"+this.ta.getText()+"',"+Long.parseLong(this.contact.getText())+",'"+this.t1.getText()+"','"+this.p1.getText()+"','"+this.city.getText()+"')";
               c.s.executeUpdate(qu);
               JOptionPane.showMessageDialog(null,"Account created succesfully!");
               this.setVisible(false);
               new Login();
           }
           catch(Exception excptn){}
        }
    }
     public static void main(String args[])
    {
        new CreateUserAccount();
    }
}
