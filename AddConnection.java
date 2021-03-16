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
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.event.*;

public class AddConnection extends JFrame implements ActionListener,ItemListener {
    JTextArea connectn_add;
    JTextField city;
    JComboBox meter_locn;
    Choice customer;
    JButton submit,cancel;
    JLabel meter_no_val;
    static final int POOL_SIZE = 20;
    static final int VAL_COUNT = 5;
    int customer_id;
    JPanel p;
    public AddConnection(int cust_id)
    {
        super("Add a Connection");
        setLayout(null);
        this.customer_id=cust_id;
        JLabel heading=new JLabel("Fill the Connection Details");
        heading.setFont(new Font("Tahoma",Font.BOLD,22));
        heading.setBounds(460,20,350,30);
        add(heading);
//        this.setLayout(null);
        
        setVisible(true);
        setBounds(185,110,1010,450);
        getContentPane().setBackground(Color.WHITE);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        p=new JPanel();
        p.setBounds(370,0,1010-370,450);
        add(p);
        p.setBackground(Color.white);
        p.setLayout(null);
        JLabel meter_no=new JLabel("Meter Number: ");
        meter_no.setFont(new Font("Tahoma",Font.BOLD,17));
        meter_no.setBounds(20,80,150,30);
        p.add(meter_no);
        Random rng = new Random(); // Ideally just create one instance globally
        Conn c=new Conn();
        int r=0;
        boolean f=true;
        try
       {
           ResultSet res=c.s.executeQuery("select meter_number from connection");
           ResultSetMetaData rsmd = res.getMetaData(); 
            int columnCount = rsmd.getColumnCount();

            Set<Integer> resultList = new HashSet<>(columnCount); 
            while (res.next()) {              
               int i = 1;
               
                resultList.add(Integer.parseInt(res.getString("meter_number")));
                
            }
            r= rng.nextInt(5000);
            while(resultList.contains(r))
            {
                r= rng.nextInt(5000);
                
            }
            
       
       }
       catch(Exception e){}
        
       
        meter_no_val=new JLabel(String.valueOf(r));
        meter_no_val.setFont(new Font("Tahoma",Font.BOLD,17));
        meter_no_val.setBounds(220,80,200,30);
        p.add(meter_no_val);
        
        
        JLabel conn_address=new JLabel("Connection Address: ");
        conn_address.setFont(new Font("Tahoma",Font.BOLD,17));
        conn_address.setBounds(20,120,180,30);
        p.add(conn_address);
        String addr=null;
        String sql=null;
        String city1=null;
        Conn c1=new Conn();
            
            try
            {
                
                sql="select address,city from customer where customer_id="+this.customer_id;
                ResultSet r2=c1.s.executeQuery(sql);
                if(r2.next())
                {
                    addr=r2.getString("address");
                    city1=r2.getString("city");
                }
          }
            catch(Exception e){}
        
        this.connectn_add=new JTextArea();
        this.connectn_add.setText(addr);
        this.connectn_add.setBounds(220,120,400,90);
        this.connectn_add.setFont(new Font("Tahoma",Font.BOLD,17));
        this.connectn_add.setBackground(Color.LIGHT_GRAY);
        p.add(connectn_add);
        
        JLabel city=new JLabel("City: ");
        city.setFont(new Font("Tahoma",Font.BOLD,17));
        
        city.setBounds(20,220,150,30);
        p.add(city);
        
        this.city=new JTextField();
        this.city.setBounds(220,220,200,30);
        this.city.setFont(new Font("Tahoma",Font.BOLD,17));
        this.city.setText(city1);
        p.add(this.city);
        
        JLabel meter_locn=new JLabel("Meter Location: ");
        meter_locn.setFont(new Font("Tahoma",Font.BOLD,17));
        meter_locn.setBounds(20,260,150,30);
        p.add(meter_locn);
        
        Vector<String> meter_loc=new Vector<>();
        meter_loc.add("Inside");
        meter_loc.add("Outside");
        this.meter_locn=new JComboBox(meter_loc);
        this.meter_locn.setBounds(220,260,200,30);
        this.meter_locn.setBackground(Color.blue);
        this.meter_locn.setForeground(Color.white);
        p.add(this.meter_locn);
        
        submit=new JButton("Submit");
        submit.setBounds(20,360,150,30);
        submit.setForeground(Color.white);
        submit.setBackground(Color.blue);
        submit.setFont(new Font("Tahoma",Font.BOLD,17));
        p.add(submit);
        submit.addActionListener(this);
        
        cancel=new JButton("Cancel");
        cancel.setBounds(220,360,150,30);
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.blue);
        cancel.setFont(new Font("Tahoma",Font.BOLD,17));
        p.add(cancel);
        cancel.addActionListener(this);
        ImageIcon ic=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/bulb.jpeg"));
        Image im=ic.getImage().getScaledInstance(370,450,Image.SCALE_DEFAULT);
        ImageIcon resize_ic=new ImageIcon(im);
        JLabel l=new JLabel(resize_ic);
        l.setBounds(0,0,370,450);
        add(l);
    }
    public AddConnection()
    {
        super("Add a Connection");
        setLayout(null);
//        this.customer_id=cust_id;
        JLabel heading=new JLabel("Fill the Connection Details");
        heading.setFont(new Font("Tahoma",Font.BOLD,22));
        heading.setBounds(460,20,350,30);
        add(heading);
//        this.setLayout(null);
        
        setVisible(true);
        setBounds(185,110,1010,450);
        getContentPane().setBackground(Color.WHITE);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        p=new JPanel();
        p.setBounds(370,0,1010-370,450);
        add(p);
        p.setBackground(Color.white);
        p.setLayout(null);
        JLabel meter_no=new JLabel("Meter Number: ");
        meter_no.setFont(new Font("Tahoma",Font.BOLD,17));
        meter_no.setBounds(20,110,150,30);
        p.add(meter_no);
        Random rng = new Random(); // Ideally just create one instance globally
        Conn c=new Conn();
        int r=0;
        boolean f=true;
        try
       {
           ResultSet res=c.s.executeQuery("select meter_number from connection");
           ResultSetMetaData rsmd = res.getMetaData(); 
            int columnCount = rsmd.getColumnCount();

            Set<Integer> resultList = new HashSet<>(columnCount); 
            while (res.next()) {              
               int i = 1;
               
                resultList.add(Integer.parseInt(res.getString("meter_number")));
                
            }
            r= rng.nextInt(5000);
            while(resultList.contains(r))
            {
                r= rng.nextInt(5000);
                
            }
            
       
       }
       catch(Exception e){}
        
        JLabel cust=new JLabel("Select customer: ");
        cust.setFont(new Font("Tahoma",Font.BOLD,17));
        cust.setBounds(20,60,150,30);
        p.add(cust);
        
        this.customer=new Choice();
        this.customer.add("");
        this.customer.setBounds(220,60,180,30);
        p.add(this.customer);
        this.customer.addItemListener(this);
        Conn cn=new Conn();
        String sql=null;
            try
            {
                
                sql="select customer_id from customer";
                ResultSet rst=cn.s.executeQuery(sql);
                while(rst.next())
                {
                    this.customer.add(rst.getString("customer_id"));
                }
//                JOptionPane.showMessageDialog(null,"A connection added successfully!");
//                this.setVisible(false);
            }
            catch(SQLException except){
                
           }
        
        meter_no_val=new JLabel(String.valueOf(r));
        meter_no_val.setFont(new Font("Tahoma",Font.BOLD,17));
        meter_no_val.setBounds(220,110,200,30);
        p.add(meter_no_val);
        
        JLabel city=new JLabel("City: ");
        city.setFont(new Font("Tahoma",Font.BOLD,17));
        
        city.setBounds(20,250,150,30);
        p.add(city);
        
        
        JLabel conn_address=new JLabel("Connection Address: ");
        conn_address.setFont(new Font("Tahoma",Font.BOLD,17));
        conn_address.setBounds(20,150,180,30);
        p.add(conn_address);
        
        submit=new JButton("Submit");
        submit.setBounds(20,370,150,30);
        submit.setForeground(Color.white);
        submit.setBackground(Color.blue);
        submit.setFont(new Font("Tahoma",Font.BOLD,17));
        p.add(submit);
        submit.addActionListener(this);
        
        JLabel meter_locn=new JLabel("Meter Location: ");
        meter_locn.setFont(new Font("Tahoma",Font.BOLD,17));
        meter_locn.setBounds(20,300,150,30);
        p.add(meter_locn);
        
        
        cancel=new JButton("Cancel");
        cancel.setBounds(220,370,150,30);
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.blue);
        cancel.setFont(new Font("Tahoma",Font.BOLD,17));
        p.add(cancel);
        cancel.addActionListener(this);
        ImageIcon ic=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/bulb.jpeg"));
        Image im=ic.getImage().getScaledInstance(370,450,Image.SCALE_DEFAULT);
        ImageIcon resize_ic=new ImageIcon(im);
        JLabel l=new JLabel(resize_ic);
        l.setBounds(0,0,370,450);
        add(l);
        
        this.connectn_add=new JTextArea();
//        this.connectn_add.setText(addr);
        this.connectn_add.setBounds(220,150,400,90);
        this.connectn_add.setFont(new Font("Tahoma",Font.BOLD,17));
        this.connectn_add.setBackground(Color.LIGHT_GRAY);
        this.p.add(connectn_add);
    }
    public void itemStateChanged(ItemEvent ie)
    {
        String addr=null;
        String sql=null;
        String city1=null;
        Conn c1=new Conn();
            
            try
            {
                
                sql="select address,city from customer where customer_id="+Integer.parseInt(customer.getSelectedItem());
                ResultSet r2=c1.s.executeQuery(sql);
                if(r2.next())
                {
                    addr=r2.getString("address");
                    city1=r2.getString("city");
                }
          }
            catch(Exception e){}
        this.connectn_add.setVisible(false);
        this.connectn_add=new JTextArea();
        this.connectn_add.setText(addr);
        this.connectn_add.setBounds(220,150,400,90);
        this.connectn_add.setFont(new Font("Tahoma",Font.BOLD,17));
        this.connectn_add.setBackground(Color.LIGHT_GRAY);
        this.p.add(connectn_add);
        
        if(this.city!=null)
            this.city.setVisible(false);
        this.city=new JTextField();
        this.city.setBounds(220,250,200,30);
        this.city.setFont(new Font("Tahoma",Font.BOLD,17));
        this.city.setText(city1);
        p.add(this.city);
        
        
        Vector<String> meter_loc=new Vector<>();
        meter_loc.add("Inside");
        meter_loc.add("Outside");
        this.meter_locn=new JComboBox(meter_loc);
        this.meter_locn.setBounds(220,300,200,30);
        this.meter_locn.setBackground(Color.blue);
        this.meter_locn.setForeground(Color.white);
        p.add(this.meter_locn);
        
    }
    public void actionPerformed(ActionEvent eve)
    {
        if(eve.getSource()==submit)
        {
            int meter_no=0;
            
                meter_no=Integer.parseInt(meter_no_val.getText());
            Conn cn=new Conn();
            String sql=null;
            if(customer!=null)
                this.customer_id=Integer.parseInt(customer.getSelectedItem());
            try
            {
//                if(this.customer==null)
                sql="insert into connection(customer_id,meter_number,conn_address,city,meter_location,installation_date) values("+this.customer_id+","+meter_no+",'"+this.connectn_add.getText()+"','"+this.city.getText()+"','"+this.meter_locn.getSelectedItem()+"',CURRENT_DATE)";
                cn.s.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"A connection added successfully!");
                this.setVisible(false);
                if(customer==null)
                {
                    sql="update conn_request set request_status='Accepted' where customer_id="+this.customer_id;
                    cn.s.executeUpdate(sql);
                    
                }
            }
            catch(SQLException except){
                
            }
        }
        else if(eve.getSource()==cancel)
        {
            this.setVisible(false);
        }
    }
    public static void main(String args[])
    {
        new AddConnection(4);
    }

    
}
