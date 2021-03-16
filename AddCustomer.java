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
import java.awt.event.*;
import java.sql.*;
public class AddCustomer extends JFrame implements ActionListener{
    JTextField name,age,contact,city,email,password;
    JTextArea address;
    JRadioButton m,f,o;
    JButton submit,cancel;
    ButtonGroup g;
    public AddCustomer()
    {
        super("Add a Customer");
        setLayout(null);
        JLabel heading=new JLabel("Fill the Customer Details");
        heading.setFont(new Font("Tahoma",Font.BOLD,22));
        heading.setBounds(280,20,350,30);
        add(heading);
//        this.setLayout(null);
        setVisible(true);
        setBounds(135,110,890,650);
//        
        
        //entry
        JLabel name=new JLabel("Full Name: ");
        name.setFont(new Font("Tahoma",Font.PLAIN,17));
        name.setBounds(50,80,100,30);
        add(name);
        
        this.name=new JTextField();
        this.name.setBounds(250,80,200,30);
        this.name.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(this.name);
        
        JLabel gender=new JLabel("Gender: ");
        gender.setFont(new Font("Tahoma",Font.PLAIN,17));
//        gender.setFont(new Font("Tahoma",Font.BOLD,17));
        gender.setBounds(50,120,100,30);
        add(gender);
        
        m=new JRadioButton("Male");
        m.setBounds(250,120,100,30);
        m.setFont(new Font("Tahoma",Font.BOLD,17));
        add(m);
        
        f=new JRadioButton("Female");
        f.setBounds(350,120,100,30);
        f.setFont(new Font("Tahoma",Font.BOLD,17));
        add(f);

        o=new JRadioButton("Other");
        o.setBounds(450,120,100,30);
        o.setFont(new Font("Tahoma",Font.BOLD,17));
        add(o);
        
        g=new ButtonGroup();
        g.add(m);
        g.add(f);
        g.add(o);
        
        JLabel age=new JLabel("Age: ");
        age.setFont(new Font("Tahoma",Font.PLAIN,17));
        age.setBounds(50,160,100,30);
        add(age);
        
        this.age=new JTextField();
        this.age.setBounds(250,160,200,30);
        this.age.setFont(new Font("Tahoma",Font.BOLD,17));
        add(this.age);
        
        JLabel address=new JLabel("Address: ");
        address.setFont(new Font("Tahoma",Font.PLAIN,17));
        address.setBounds(50,200,100,30);
        
        add(address);
        
        this.address=new JTextArea();
        this.address.setBounds(250,200,400,90);
        this.address.setFont(new Font("Tahoma",Font.BOLD,17));
        this.address.setBackground(Color.LIGHT_GRAY);
        add(this.address);
        
        JLabel contact=new JLabel("Contact: ");
        contact.setFont(new Font("Tahoma",Font.PLAIN,17));
        contact.setBounds(50,320,100,30);
        add(contact);
        
        this.contact=new JTextField();
        this.contact.setBounds(250,320,200,30);
        add(this.contact);
        this.contact.setFont(new Font("Tahoma",Font.BOLD,22));
        
        JLabel email=new JLabel("Email ID: ");
        email.setFont(new Font("Tahoma",Font.PLAIN,17));
        email.setBounds(50,360,100,30);
        add(email);
        
        this.email=new JTextField();
        this.email.setBounds(250,360,200,30);
        this.email.setFont(new Font("Tahoma",Font.BOLD,17));
        add(this.email);

        JLabel pswd=new JLabel("Password: ");
        pswd.setFont(new Font("Tahoma",Font.PLAIN,17));
        pswd.setBounds(50,400,100,30);
        add(pswd);
        
        this.password=new JTextField();
        this.password.setBounds(250,400,200,30);
        this.password.setFont(new Font("Tahoma",Font.BOLD,17));
        add(this.password);
        
        JLabel city=new JLabel("City: ");
        city.setFont(new Font("Tahoma",Font.PLAIN,17));
        city.setBounds(50,440,100,30);
        add(city);
        
        this.city=new JTextField();
        this.city.setBounds(250,440,200,30);
        this.city.setFont(new Font("Tahoma",Font.BOLD,17));
        add(this.city);
        
        
        submit=new JButton("Submit");
        submit.setBounds(50,480,150,30);
        submit.setForeground(Color.white);
        submit.setBackground(Color.blue);
        submit.setFont(new Font("Tahoma",Font.BOLD,17));
        add(submit);
        submit.addActionListener(this);
        
        cancel=new JButton("Cancel");
        cancel.setBounds(250,480,150,30);
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.blue);
        cancel.setFont(new Font("Tahoma",Font.BOLD,17));
        add(cancel);
        cancel.addActionListener(this);
        getContentPane().setBackground(Color.white);
    }
    public void actionPerformed(ActionEvent actn)
    {
        if(actn.getSource()==submit)
        {
            String g1=null;
            if(m.isSelected())
                g1="Male";
            else if(f.isSelected())
                g1="Female";
            else
                g1="Other";
            String sql_query="insert into customer(full_name,gender,age,address,contact,email,password,city) values('"+this.name.getText()+"','"+g1+"',"+Integer.parseInt(this.age.getText())+",'"+this.address.getText()+"',"+Long.parseLong(this.contact.getText())+",'"+this.email.getText()+"','"+this.password.getText()+"','"+this.city.getText()+"')";
            Conn c=new Conn();
            try
            {
                c.s.executeUpdate(sql_query);
                JOptionPane.showMessageDialog(null, "A new Customer Details added!");
                this.setVisible(false);
//                new AddConnection()
                ResultSet resultset=c.s.executeQuery("select max(customer_id) from customer");
                if(resultset.next())
                    new AddConnection(Integer.parseInt(resultset.getString("max(customer_id)")));
            }
            catch(Exception ex){}
        }
        else if(actn.getSource()==cancel)
        {
            this.setVisible(false);
        }
    }
    public static void main(String args[])
    {
        new AddCustomer();
    }
}
