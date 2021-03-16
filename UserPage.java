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
public class UserPage extends JFrame implements ActionListener{
    JMenuBar m;
    JMenu view,edit,connection,bill;
    JMenuItem viewprofile,editprofile,view_conn_details,request_connection,paybill,bill_history;
    String username,password;
    JButton logout;
    public UserPage(String email,String pswd)
    {
        super("User Home Page");
        this.username=email;
        this.password=pswd;
        this.setVisible(true);
        this.setBounds(10,10,1540,800);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/MahaVitaran-logo.jpg"));
        setLayout(null);
        Image imageResize=img.getImage().getScaledInstance(1000,560,Image.SCALE_DEFAULT);
        ImageIcon adjusted=new ImageIcon(imageResize);
        JLabel imgLabel=new JLabel(adjusted);
        imgLabel.setBounds(250,100,1000,560);
        add(imgLabel);
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m=new JMenuBar();
        m.setBackground(Color.black);
        m.setForeground(Color.WHITE);
        m.setBounds(0,0,1540,30);
        add(m);
        
        //view menu
        view =new JMenu("View");
        view.setForeground(Color.WHITE);
        view.setFont(new Font("Tahoma",Font.PLAIN,17));
        m.add(view);
        viewprofile=new JMenuItem("View Profile");
        viewprofile.setForeground(Color.WHITE);
        viewprofile.setBackground(Color.black);
        viewprofile.setFont(new Font("Tahoma",Font.PLAIN,17));
        view.add(viewprofile);
        viewprofile.addActionListener(this);
        
        
        
        //edit menu
        edit=new JMenu("Edit");
        edit.setForeground(Color.WHITE);
        edit.setFont(new Font("Tahoma",Font.PLAIN,17));
        m.add(edit);
        editprofile=new JMenuItem("Edit Profile");
        editprofile.setForeground(Color.WHITE);
        editprofile.setBackground(Color.black);
        editprofile.setFont(new Font("Tahoma",Font.PLAIN,17));
        edit.add(editprofile);
        editprofile.addActionListener(this);
        
        
        //connection menu
        connection =new JMenu("Connection");
        connection.setForeground(Color.WHITE);
        connection.setFont(new Font("Tahoma",Font.PLAIN,17));
        m.add(connection);
        view_conn_details=new JMenuItem("View Connection Details");
        view_conn_details.setForeground(Color.WHITE);
        view_conn_details.setBackground(Color.black);
        view_conn_details.setFont(new Font("Tahoma",Font.PLAIN,17));
        connection.add(view_conn_details);
        request_connection=new JMenuItem("Request for Connection");
        request_connection.setForeground(Color.WHITE);
        request_connection.setBackground(Color.black);
        request_connection.setFont(new Font("Tahoma",Font.PLAIN,17));
        connection.add(request_connection);
        view_conn_details.addActionListener(this);
        request_connection.addActionListener(this);
        
        //bill menu
        bill =new JMenu("Bill");
        bill.setForeground(Color.WHITE);
        bill.setFont(new Font("Tahoma",Font.PLAIN,17));
        m.add(bill);
        bill_history=new JMenuItem("View Bill History");
        bill_history.setForeground(Color.WHITE);
        bill_history.setBackground(Color.black);
        bill_history.setFont(new Font("Tahoma",Font.PLAIN,17));
        bill.add(bill_history);
        bill_history.addActionListener(this);
        
        paybill=new JMenuItem("Pay Bill");
        paybill.setForeground(Color.WHITE);
        paybill.setBackground(Color.black);
        paybill.setFont(new Font("Tahoma",Font.PLAIN,17));
        bill.add(paybill);
        paybill.addActionListener(this);
        
        //logout
        logout=new JButton("Logout");
        m.add(logout);
        logout.setFont(new Font("sans-serif",Font.PLAIN,21));
        logout.setForeground(Color.white);
        logout.setBackground(Color.black);
//        imgLabel.add(top);
        logout.addActionListener(this);
        
        //Welcome label
        JLabel top=new JLabel("MSEDCL Team Welcomes You!");
        top.setBounds(600,46,400,20);
        top.setFont(new Font("sans-serif",Font.BOLD,21));
        top.setForeground(Color.GRAY);
        add(top);
    }
    public void actionPerformed(ActionEvent actn_event)
    {
        if(actn_event.getSource()==viewprofile)
        {
            new ViewCustomerProfile(username,password);
        }
        else if(actn_event.getSource()==request_connection)
        {
            new RequestForConnection(username,password);
        }
        else if(actn_event.getSource()==view_conn_details)
        {
            new ViewConnections(username,password);
        }
        else if(actn_event.getSource()==editprofile)
        {
            new EditUserProfile(username,password);
        }
        else if(actn_event.getSource()==paybill)
        {
            new UserPayBill(username,password);
        }
        else if(actn_event.getSource()==bill_history)
        {
            new ViewBillHistory(username,password);
        }
        else if(actn_event.getSource()==logout)
        {
            System.exit(0);
        }
        
    }
    public static void main(String args[])
    {
        new UserPage("kamal@gmail.com","kamal");
    }
    
}
