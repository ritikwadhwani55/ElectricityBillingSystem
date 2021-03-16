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
public class UpdateCustomerDetails extends JFrame implements ActionListener{
    JPanel p;
    JButton update,cancel;
    int c_id;
    String n;
    JTextArea addr;
    JTextField age,contact,city;
    Choice c;
    JLabel fname,gender,id,pswd;
    public UpdateCustomerDetails()
    {
        setVisible(true);
//        setLayout(null);
        setBounds(180,100,1050,600);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/user.png"));
        setLayout(null);
        Image imageResize=img.getImage().getScaledInstance(370,600,Image.SCALE_DEFAULT);
        ImageIcon adjusted=new ImageIcon(imageResize);
        JLabel imgLabel=new JLabel(adjusted);
        imgLabel.setBounds(0,0,370,600);
        add(imgLabel);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p=new JPanel();
        p.setBounds(371,0,680,600);
        add(p);
        p.setBackground(Color.WHITE);
        p.setLayout(null);
        
        
        JLabel top=new JLabel("Update Customer Details");
        top.setBounds(225,5,320,30);
        top.setFont(new Font("Tahoma",Font.BOLD,20));
        p.add(top);
        
        JLabel name=new JLabel("Customer Name:");
        name.setBounds(20,80,180,30);
        name.setFont(new Font("Tahoma",Font.PLAIN,20));
        p.add(name);
        
        JLabel gender=new JLabel("Gender:");
        gender.setBounds(20,120,150,30);
        gender.setFont(new Font("Tahoma",Font.PLAIN,20));
        p.add(gender);
        
        JLabel age=new JLabel("Age:");
        age.setBounds(20,160,150,30);
        age.setFont(new Font("Tahoma",Font.PLAIN,20));
        p.add(age);
        
        JLabel address=new JLabel("Address:");
        address.setBounds(20,200,350,80);
        address.setFont(new Font("Tahoma",Font.PLAIN,20));
        p.add(address);
        
        JLabel phone=new JLabel("Contact:");
        phone.setBounds(20,340,300,30);
        phone.setFont(new Font("Tahoma",Font.PLAIN,20));
        p.add(phone);
        
        JLabel email=new JLabel("Email:");
        email.setBounds(20,380,150,30);
        email.setFont(new Font("Tahoma",Font.PLAIN,20));
        p.add(email);
        
        JLabel passwd=new JLabel("Password:");
        passwd.setBounds(20,420,150,30);
        passwd.setFont(new Font("Tahoma",Font.PLAIN,20));
        p.add(passwd);
        
        JLabel city=new JLabel("City");
        city.setBounds(20,300,150,30);
        city.setFont(new Font("Tahoma",Font.PLAIN,20));
        p.add(city);
        
        JLabel choice=new JLabel("Select the customer:");
        choice.setBounds(20,40,280,30);
        choice.setFont(new Font("Tahoma",Font.PLAIN,20));
        p.add(choice);
        update=new JButton("Update");
            this.update.setBounds(20,460,150,30);
            p.add(this.update);
            update.setBackground(Color.blue);
            update.setForeground(Color.white);
            update.setFont(new Font("Tahoma",Font.PLAIN,16));
            update.addActionListener(this);
            
            cancel=new JButton("Cancel");
            this.cancel.setBounds(250,460,150,30);
            p.add(this.cancel);
            cancel.setBackground(Color.blue);
            cancel.setForeground(Color.white);
            cancel.setFont(new Font("Tahoma",Font.PLAIN,16));
            cancel.addActionListener(this);
            
            
            this.c=new Choice();
            this.c.setBounds(320,40,180,30);
            
            fname=new JLabel();
            fname.setBounds(250,80,180,30);
            this.p.add(this.fname);
            fname.setFont(new Font("Tahoma",Font.PLAIN,16));
            
            this.gender=new JLabel();
            this.gender.setBounds(250,120,180,30);
            this.p.add(this.gender);
            this.gender.setFont(new Font("Tahoma",Font.PLAIN,16));
            
            this.age=new JTextField();
            this.age.setBounds(250,160,180,30);
            this.p.add(this.age);
            this.age.setFont(new Font("Tahoma",Font.PLAIN,16));
            
            this.addr=new JTextArea();
            this.addr.setBounds(250,200,350,80);
            this.p.add(this.addr);
            this.addr.setFont(new Font("Tahoma",Font.PLAIN,16));
            this.addr.setBackground(Color.LIGHT_GRAY);
            
            
            
            this.contact=new JTextField();
            this.contact.setBounds(250,340,180,30);
            this.p.add(this.contact);
            this.contact.setFont(new Font("Tahoma",Font.PLAIN,16));
            
            this.id=new JLabel();
            this.id.setBounds(250,380,180,30);
            this.p.add(this.id);
            this.id.setFont(new Font("Tahoma",Font.PLAIN,16));
                                   
            
            this.pswd=new JLabel();
                                   this.pswd.setBounds(250,420,180,30);
                                   this.p.add(this.pswd);
                                   this.pswd.setFont(new Font("Tahoma",Font.PLAIN,16));
            
            this.city=new JTextField();
            this.city.setBounds(250,300,180,30);
            this.city.setFont(new Font("Tahoma",Font.PLAIN,20));
            p.add(this.city);
//            this.city.setText(res.getString("city"));
            Conn con=new Conn();
            String qu="select customer_id,full_name from customer";
            try
            {
                ResultSet res=con.s.executeQuery(qu);
                while(res.next())
                {
                    c.add(res.getString("full_name"));
                }
            }
            catch(SQLException q){}
            this.c.addItemListener((ItemEvent i) -> {
                Conn c=new Conn();
                this.n=(String)this.c.getSelectedItem();
                
                //                        this.c_id=i.getSource();
                ResultSet res=null;
                String query = "select * from customer where full_name='" +(String) this.c.getSelectedItem() + "'";
                try {
                    res=c.s.executeQuery(query);
                    while (res.next()) {
                        fname.setText(res.getString("full_name"));
                        this.gender.setText(res.getString("gender"));
                        this.age.setText(res.getString("age"));
                        this.addr.setText(res.getString("address"));
                        this.contact.setText(res.getString("contact"));
                        this.id.setText(res.getString("email"));
                        this.pswd.setText(res.getString("password"));
                        this.city.setText(res.getString("city"));
                        if(this.n.equals(res.getString("full_name")))
                            this.c_id=Integer.parseInt(res.getString("customer_id"));
                    }
                    }
                catch(Exception e)
                {}
            
        });
              p.add(this.c);
    }

      

    public void actionPerformed(ActionEvent ac)
    {
        if(ac.getSource()==update)
        {
            String sql_query=null;
            Conn c=new Conn();
            sql_query="update customer set age="+Integer.parseInt(this.age.getText())+",address='"+this.addr.getText()+"',contact="+Long.parseLong(this.contact.getText())+",city='"+this.city.getText()+"' where customer_id="+this.c_id;
            try
            {
                c.s.executeUpdate(sql_query);
                JOptionPane.showMessageDialog(null,"Customer details updated successfully!");
            }
            catch(Exception exceptn){}
        }
        else if(ac.getSource()==cancel)
        {
            this.setVisible(false);
        }
            
    }
    public static void main(String args[])
    {
        new UpdateCustomerDetails();
    }
}
