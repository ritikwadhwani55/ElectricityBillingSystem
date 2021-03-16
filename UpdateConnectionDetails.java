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
import java.util.*;
import java.time.*;
public class UpdateConnectionDetails extends JFrame implements ActionListener,ItemListener{
    JLabel c_id,conn_address,cust_name,installation_date,city,meter_location;
    Choice ch,meter_no;
    JTextField meter_numb;
    int meter_number1;
    JCheckBox disconnect_date;
    JButton change,update,cancel;
    public UpdateConnectionDetails()
    {
        setBounds(190,100,760,650);
        setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        JLabel top=new JLabel("Update Connection Details");
        top.setBounds(245,5,320,30);
        top.setFont(new Font("Tahoma",Font.BOLD,20));
        add(top);
        
        JLabel connection=new JLabel("Connection ID:");
        connection.setFont(new Font("Tahoma",Font.BOLD,17));
        connection.setBounds(20,90,150,30);
        add(connection);
        
        ch=new Choice();
        ch.setBounds(280,42,180,40);
        ch.addItemListener(this);
        add(ch);
        
//        go=new JButton("Go");
//        go.setBounds(460,40,120,25);
//        go.setBackground(Color.blue);
//        go.setForeground(Color.white);
//        add(go);
        
        JLabel connection_id=new JLabel("Select Meter Number:");
        connection_id.setFont(new Font("Tahoma",Font.PLAIN,17));
        connection_id.setBounds(90,40,190,30);
        add(connection_id);
        
        JLabel cname=new JLabel("Customer Name: ");
        cname.setFont(new Font("Tahoma",Font.BOLD,17));
        cname.setBounds(20,140,150,30);
        add(cname);
        JLabel meter_no=new JLabel("Meter Number: ");
        meter_no.setFont(new Font("Tahoma",Font.BOLD,17));
        meter_no.setBounds(20,190,160,30);
        add(meter_no);
        JLabel address=new JLabel("Connection Address: ");
        address.setFont(new Font("Tahoma",Font.BOLD,17));
        address.setBounds(20,240,180,30);
        add(address);
        JLabel city=new JLabel("City: ");
        city.setFont(new Font("Tahoma",Font.BOLD,17));
        city.setBounds(20,330,150,30);
        add(city);
        JLabel m_locn=new JLabel("Meter Location: ");
        m_locn.setFont(new Font("Tahoma",Font.BOLD,17));
        m_locn.setBounds(20,380,180,30);
        add(m_locn);
        JLabel install_dt=new JLabel("Installation Date: ");
        install_dt.setFont(new Font("Tahoma",Font.BOLD,17));
        install_dt.setBounds(20,430,180,30);
        add(install_dt);
        JLabel disconnect_dt=new JLabel("Disconnection Date: ");
        disconnect_dt.setFont(new Font("Tahoma",Font.BOLD,17));
        disconnect_dt.setBounds(20,480,180,30);
        add(disconnect_dt);
        Conn c=new Conn();
        String sql="";
        try
        {
            sql="select meter_number from connection";
            ResultSet re=c.s.executeQuery(sql);
            while(re.next())
            {
                ch.add(re.getString("meter_number"));
                
            }
//            c.s.executeUpdate(sql);
            
        }
        catch(SQLException e){}
        installation_date=new JLabel();
        installation_date.setBounds(250,430,150,30);
        installation_date.setFont(new Font("Tahoma",Font.BOLD,17));
        add(installation_date);
        c_id=new JLabel();
        c_id.setBounds(250,90,150,30);
        c_id.setFont(new Font("Tahoma",Font.BOLD,17));
        add(c_id);
        
        cust_name=new JLabel();
        this.cust_name.setBounds(250,140,150,30);
        cust_name.setFont(new Font("Tahoma",Font.BOLD,17));
            add(cust_name);
            
        meter_numb=new JTextField();
            this.meter_numb.setBounds(250,190,150,30);
            meter_numb.setFont(new Font("Tahoma",Font.BOLD,17));
            add(meter_numb);
            
            conn_address=new JLabel();
            this.conn_address.setBounds(250,240,420,75);
            conn_address.setFont(new Font("Tahoma",Font.BOLD,17));
            add(conn_address);
    
            this.city=new JLabel();
            this.city.setBounds(250,330,150,30);
            this.city.setFont(new Font("Tahoma",Font.BOLD,17));
            add(this.city);
            
            meter_location=new JLabel();
            meter_location.setBounds(250,380,150,30);
            meter_location.setFont(new Font("Tahoma",Font.BOLD,17));
            add(meter_location);
        
            disconnect_date=new JCheckBox("This Month");
            disconnect_date.setBounds(250,480,150,30);
            disconnect_date.setFont(new Font("Tahoma",Font.BOLD,17));
            add(disconnect_date);
            
            this.meter_no=new Choice();
            Random rn=new Random();
            
            change=new JButton("Change");
            this.change.setBounds(430,190,150,30);
            add(this.change);
            change.setBackground(Color.blue);
            change.setForeground(Color.white);
            change.setFont(new Font("Tahoma",Font.PLAIN,16));
            change.addActionListener(this);
            
            update=new JButton("Update");
            this.update.setBounds(20,530,150,30);
            add(this.update);
            update.setBackground(Color.blue);
            update.setForeground(Color.white);
            update.setFont(new Font("Tahoma",Font.PLAIN,16));
            update.addActionListener(this);
            
            cancel=new JButton("Cancel");
            this.cancel.setBounds(250,530,150,30);
            add(this.cancel);
            cancel.setBackground(Color.blue);
            cancel.setForeground(Color.white);
            cancel.setFont(new Font("Tahoma",Font.PLAIN,16));
            cancel.addActionListener(this);
    }
    public void itemStateChanged(ItemEvent ie)
    {
        Conn cn=new Conn();
        int no=Integer.parseInt(ch.getSelectedItem());
        this.meter_number1=no;
        String sql="";
        sql="select c.connection_id,c.customer_id,cust.full_name,c.meter_number,c.conn_address,c.city,c.meter_location,c.installation_date,c.disconnect_date from connection c inner join customer cust on c.customer_id=cust.customer_id where c.meter_number="+no;
        try {
        ResultSet re=cn.s.executeQuery(sql);
        if(re.next())
        {
            
            c_id.setText(re.getString("connection_id"));
            
            
            cust_name.setText(re.getString("full_name"));
            
            
            meter_numb.setText(re.getString("meter_number"));
            
            
            conn_address.setText(re.getString("conn_address"));
            
            
            city.setText(re.getString("city"));
            
            
            meter_location.setText(re.getString("meter_location"));
            
            
//            installation_date.setText(re.getString("Installation Date"));
            installation_date.setText(re.getString("installation_date"));
            
            
            
        }
        }catch(Exception ex) {}
        
    }
    public void actionPerformed(ActionEvent act)
    {
            if(act.getSource()==change)
            {
                JOptionPane.showMessageDialog(null, "Searching for available meters, please wait!");
                Random rnd=new Random();
                Conn c=new Conn();
                int r=0;
                boolean f=true;
                try
                {
                    ResultSet rslt=c.s.executeQuery("select meter_number from connection");
                    
                       ResultSetMetaData rsmd = rslt.getMetaData(); 
                    int columnCount = rsmd.getColumnCount();

                    Set<Integer> resultList = new HashSet<>(); 
                    while (rslt.next()) {              
//                       int i = 1;

                        resultList.add(Integer.parseInt(rslt.getString("meter_number")));

                    }
                    r= rnd.nextInt(5000);
                    while(resultList.contains(r))
                    {
                        r= rnd.nextInt(5000);

                    }
                    meter_numb.setText(Integer.toString(r));
                }catch(Exception exc){}
                      
                }
            else if(act.getSource()==cancel)
            {
                this.setVisible(false);
            }
            else if(act.getSource()==update)
            {
                boolean f=false;
                if(this.disconnect_date.isSelected())
                    f=true;
                Conn c=new Conn();
                String sql=null;
//                System.out.println(this.meter_no.getSelectedItem());
                 if(f==false)   
                 {    
                        sql="update connection set meter_number="+Integer.parseInt(this.meter_numb.getText())+" where meter_number="+this.meter_number1;
                        try {
                        c.s.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,"Update done Successfully!");
                        this.setVisible(false);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                 }
                 else
                 {
//                     java.util.Date dt=new java.util.Date();
                     LocalDate local=LocalDate.now();
                     int month=local.getMonthValue();
                     int y=local.getYear();
                     String date=null;
                     if(month!=2)
                        date=Integer.toString(y)+"-"+Integer.toString(month)+"-31";
                     else 
                         date=Integer.toString(y)+"-"+Integer.toString(month)+"-28";
                     sql="update connection set meter_number="+Integer.parseInt(this.meter_numb.getText())+",disconnect_date='"+date+ "' where meter_number="+this.meter_number1;
                        try {
                        c.s.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,"Update done Successfully!");
                        this.setVisible(false);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        
                 }
             this.setVisible(false);
            }
            
                
            
        }

    public static void main(String args[])
    {
        new UpdateConnectionDetails();
    }
}
