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
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class RequestForConnection extends JFrame implements ActionListener{
    JPanel panel;
    JTextArea connection_location;
    JTextField connection_city;
    JButton submit,cancel;
    String email_id,password;
    public RequestForConnection(String email,String pwd)
    {
        super("Request for a Connection");
        this.email_id=email;
        this.password=pwd;
        this.setVisible(true);
        this.setBounds(200,180,920,450);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);
        ImageIcon image=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/electrical_plug.png"));
        Image resize=image.getImage().getScaledInstance(300,450,Image.SCALE_DEFAULT);
        ImageIcon img_resized=new ImageIcon(resize);
        JLabel adjusted_img_lab=new JLabel(img_resized);
        adjusted_img_lab.setBounds(0,0,300,450);
        add(adjusted_img_lab);
        this.setLayout(null);
        
        //connection details
        panel=new JPanel();
        panel.setBounds(301,0,620,450);
        add(panel);
        panel.setBackground(Color.white);
        JLabel top=new JLabel("Connection Details");
        top.setBounds(200,5,400,30);
        top.setFont(new Font("Tahoma",Font.BOLD,22));
        panel.add(top);
        panel.setLayout(null);
        JLabel conn_add=new JLabel("Connection Address: ");
        conn_add.setBounds(20,50,180,30);
        conn_add.setFont(new Font("Tahoma",Font.PLAIN,17));
        panel.add(conn_add);
        
        JLabel city=new JLabel("City Name: ");
        city.setBounds(20,160,180,30);
        city.setFont(new Font("Tahoma",Font.PLAIN,17));
        panel.add(city);
        
        connection_location=new JTextArea();
        connection_location.setBounds(220,50,250,80);
        connection_location.setFont(new Font("Tahoma",Font.PLAIN,17));
        connection_location.setBackground(Color.LIGHT_GRAY);
        panel.add(connection_location);
        
        connection_city=new JTextField();
        connection_city.setBounds(220,160,250,30);
        connection_city.setFont(new Font("Tahoma",Font.PLAIN,17));
        panel.add(connection_city);
        
        submit=new JButton("Submit");
            this.submit.setBounds(20,230,150,30);
            panel.add(this.submit);
            submit.setBackground(Color.blue);
            submit.setForeground(Color.white);
            submit.setFont(new Font("Tahoma",Font.PLAIN,17));
            submit.addActionListener(this);
            
            cancel=new JButton("Cancel");
            this.cancel.setBounds(220,230,150,30);
            panel.add(this.cancel);
            cancel.setBackground(Color.blue);
            cancel.setForeground(Color.white);
            cancel.setFont(new Font("Tahoma",Font.PLAIN,17));
            cancel.addActionListener(this);
    }
    public void actionPerformed(ActionEvent acevnt)
    {
        if(acevnt.getSource()==submit)
        {
            Conn c=new Conn();
            String query="select customer_id from customer where email='"+this.email_id+"' and password='"+this.password+"'";
            try
            {
                ResultSet r=c.s.executeQuery(query);
                if(r.next())
                {
                    int cust_id=Integer.parseInt(r.getString("customer_id"));
                    query="insert into conn_request(customer_id,connection_address,city) values("+cust_id+",'"+this.connection_location.getText()+"','"+this.connection_city.getText()+"')";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Your request is registered, Thankyou!");
                    this.setVisible(false);
                }
            }
            catch(SQLException db_exc){}
        }
        else if(acevnt.getSource()==cancel)
        {
            this.setVisible(false);
        }
    }
    public static void main(String args[])
    {
        new RequestForConnection("kamal@gmail.com","kamal");
    }
}
