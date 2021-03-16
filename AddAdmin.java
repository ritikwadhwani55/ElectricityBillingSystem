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
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;
//import org.jdatepicker;
//import electricitybillingsystem.;
//import o rg.jdesktop.swingx.*;
import java.awt.event.*;
public class AddAdmin extends JFrame implements ActionListener{
    JTextField name,age,salary,contact,email;
    JTextArea address;
    ButtonGroup b1;
    JPasswordField pswd;
    JRadioButton m,f,o;
//    JDatePicker date;
    JButton submit,cancel;
    
    public AddAdmin()
    {
        super("Add Admin");
        JLabel heading=new JLabel("Add an Employee");
        heading.setFont(new Font("Tahoma",Font.BOLD,22));
        heading.setBounds(230,20,250,30);
        add(heading);
        this.setLayout(null);
//        cal =new GregorianCalendar();
//        ImageIcon image=new ImageIcon(getClass().getResource("/user.png"));
//        Image i1=image.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
//        ImageIcon image1=new ImageIcon(i1);
//        JLabel image_lab=new JLabel(image1);
//        image_lab.setBounds(0,0,300,300);
//        add(image_lab);
        setVisible(true);
        setBounds(230,65,700,600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel name=new JLabel("Full Name: ");
        name.setFont(new Font("Tahoma",Font.PLAIN,17));
        name.setBounds(30,80,100,30);
        add(name);
        this.name=new JTextField();
        this.name.setBounds(250,80,200,30);
        add(this.name);
        JLabel gender=new JLabel("Gender: ");
        gender.setFont(new Font("Tahoma",Font.PLAIN,17));
        gender.setBounds(30,120,100,30);
        add(gender);
        JLabel age=new JLabel("Age: ");
        age.setFont(new Font("Tahoma",Font.PLAIN,17));
        age.setBounds(30,160,100,30);
        add(age);
        JLabel salary=new JLabel("Salary: ");
        salary.setFont(new Font("Tahoma",Font.PLAIN,17));
        salary.setBounds(30,200,100,30);
        add(salary);
        JLabel address=new JLabel("Address: ");
        address.setFont(new Font("Tahoma",Font.PLAIN,17));
        address.setBounds(30,240,100,30);
        add(address);
        JLabel email_id=new JLabel("Email Id: ");
        email_id.setFont(new Font("Tahoma",Font.PLAIN,17));
        email_id.setBounds(30,360,100,30);
        add(email_id);
        JLabel pass=new JLabel("Password: ");
        pass.setFont(new Font("Tahoma",Font.PLAIN,17));
        pass.setBounds(30,400,100,30);
        add(pass);
        JLabel phone=new JLabel("Contact: ");
        phone.setFont(new Font("Tahoma",Font.PLAIN,17));
        phone.setBounds(30,440,100,30);
        add(phone);
        b1=new ButtonGroup();
        m=new JRadioButton("Male");
        m.setBounds(250,120,100,30);
        b1.add(m);
        add(m);
        f=new JRadioButton("Female");
        f.setBounds(380,120,100,30);
        b1.add(f);
        add(f);
        o=new JRadioButton("Other");
        o.setBounds(500,120,100,30);
        b1.add(o);
        add(o);
//        b1.setBounds(250,120,300,30);
//        add(b1);
        this.age=new JTextField();
        this.age.setBounds(250,160,200,30);
        add(this.age);
        this.salary=new JTextField();
        this.salary.setBounds(250,200,200,30);
        add(this.salary);
        this.address=new JTextArea();
        this.address.setBounds(250,240,350,90);
        this.address.setBackground(Color.LIGHT_GRAY);
        add(this.address);
        this.email=new JTextField();
        this.email.setBounds(250,360,200,30);
        add(this.email);
        this.pswd=new JPasswordField();
        this.pswd.setBounds(250,400,200,30);
        add(this.pswd);
        contact=new JTextField();
        this.contact.setBounds(250,440,200,30);
        add(this.contact);
        submit=new JButton("Submit");
        submit.setBounds(50,480,150,30);
        submit.setBackground(Color.blue);
        submit.setForeground(Color.white);
        add(submit);
        submit.addActionListener(this);
        cancel=new JButton("Cancel");
        cancel.setBounds(280,480,150,30);
        cancel.setBackground(Color.blue);
        cancel.setForeground(Color.white);
        add(cancel);
        cancel.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==submit)
        {
            String gender=null;
            if(m.isSelected())
                gender=m.getText();
            else if(f.isSelected())
                gender=f.getText();
            else
                gender=o.getText();
            Conn c=new Conn();
            try
            {
                
                String query="insert into admin(full_name,gender,age,salary,address,contact,doj,email,password) values('"+name.getText()+"','"+gender+"',"+Integer.parseInt(age.getText())+","+Integer.parseInt(salary.getText())+",'"+address.getText()+"',"+Long.parseLong(contact.getText())+",CURRENT_DATE,'"+email.getText()+"','"+pswd.getText()+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"New Employee Added Successfully!");
                this.setVisible(false);
            }
            catch(SQLException sq)
            {
                this.setVisible(false);
            }
           
        }
        else if(ae.getSource()==cancel)
        {
            this.setVisible(false);
        }
    }
    public static void main(String args[])
    {
        new AddAdmin();
    }
}
