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
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class ViewConnectionRequest extends JFrame implements ActionListener{
    JPanel panel;
    Choice request_response;
    JButton go,new_conn;
    int c_id;
    public ViewConnectionRequest()
    {
        super("View Connection Request");
        this.setVisible(true);
        this.setBounds(200,180,920,550);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.blue);
        ImageIcon image=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/plug-connector-icon.jpg"));
        Image resize=image.getImage().getScaledInstance(300,550,Image.SCALE_DEFAULT);
        ImageIcon img_resized=new ImageIcon(resize);
        JLabel adjusted_img_lab=new JLabel(img_resized);
        adjusted_img_lab.setBounds(0,0,300,550);
        add(adjusted_img_lab);
        setLayout(null);
        
        panel=new JPanel();
        panel.setBounds(304,4,614,550);
        add(panel);
        panel.setBackground(Color.white);
        JLabel top=new JLabel("Connection Details");
        top.setBounds(200,5,400,30);
        top.setFont(new Font("Tahoma",Font.BOLD,22));
        panel.add(top);
        panel.setLayout(null);
        JLabel respond=new JLabel("Respond to Request: ");
        respond.setBounds(20,70,180,30);
        respond.setFont(new Font("Tahoma",Font.PLAIN,17));
        panel.add(respond);
        
        request_response=new Choice();
        request_response.add("");
        request_response.setBounds(200,70,200,30);
        request_response.setFont(new Font("Tahoma",Font.PLAIN,17));
        panel.add(request_response);
        Conn c=new Conn();
        String query="select c.full_name from customer c inner join conn_request con on con.customer_id=c.customer_id where con.request_status is null";
        try
        {
            ResultSet result=c.s.executeQuery(query);
            while(result.next())
            {
                request_response.add(result.getString("full_name"));
//                this.c_id=Integer.parseInt(result.getString("customer_id"));
            }
            go=new JButton("Go");
            this.go.setBounds(400,70,150,30);
            panel.add(this.go);
            go.setBackground(Color.blue);
            go.setForeground(Color.white);
            go.setFont(new Font("Tahoma",Font.PLAIN,18));
            go.addActionListener( this);
            JLabel add_new_conn=new JLabel("Click below to add a new Connection");
            add_new_conn.setBounds(100,140,400,30);
            add_new_conn.setFont(new Font("Tahoma",Font.PLAIN,18));
            panel.add(add_new_conn);
            
            //new connection
            new_conn=new JButton("New Connection");
            this.new_conn.setBounds(140,190,180,38);
            panel.add(this.new_conn);
            new_conn.setBackground(Color.blue);
            new_conn.setForeground(Color.white);
            new_conn.setFont(new Font("Tahoma",Font.PLAIN,18));
            new_conn.addActionListener(this);

        }
        
        catch(SQLException sqlexc){}
    }
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==go)
        {
            String name=request_response.getSelectedItem();
            Conn c=new Conn();
            String qu="select c.customer_id from conn_request con inner join customer c on c.customer_id=con.customer_id where c.full_name='"+name+"'";
            try
            {
                ResultSet r=c.s.executeQuery(qu);
                if(r.next())
                {
                    this.setVisible(false);
                    new AddConnection(Integer.parseInt(r.getString("customer_id")));
                    
                }
            }
            catch(SQLException ex){}
        }
        else if(a.getSource()==new_conn)
        {
            this.setVisible(false);
            new AddConnection();
            
        }
    }
    public static void main(String args[])
    {
        new ViewConnectionRequest();
    }
}
