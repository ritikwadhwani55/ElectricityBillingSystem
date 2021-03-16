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
public class EditUserProfile extends JFrame implements ActionListener{
    JPanel p;
    String username,password;
    JTextField age,contact,passwd;
    JTextArea address;
    JButton edit,cancel;
    public EditUserProfile(String username,String password)
    {
        super("Edit Profile");
        this.username=username;
        this.password=password;
        setVisible(true);
        setBounds(100,100,925,600);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/user.png"));
        setLayout(null);
        Image imageResize=img.getImage().getScaledInstance(350,400,Image.SCALE_DEFAULT);
        ImageIcon adjusted=new ImageIcon(imageResize);
        JLabel imgLabel=new JLabel(adjusted);
        imgLabel.setBounds(0,0,350,400);
        add(imgLabel);
        this.getContentPane().setBackground(Color.white);
        p=new JPanel();
        p.setBounds(350,0,910-350,600);
        p.setBackground(Color.YELLOW);
        add(p);
        p.setLayout(null);
        p.setBorder(new TitledBorder(new LineBorder(Color.BLUE),"Edit Profile",TitledBorder.CENTER,TitledBorder.DEFAULT_JUSTIFICATION,new Font("sans-serif",Font.BOLD,19)));
        JLabel name=new JLabel("Full Name: ");
        name.setBounds(20,60,100,20);
        name.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(name);
        JLabel gender=new JLabel("Gender: ");
        gender.setBounds(20,100,100,20);
        gender.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(gender);
        JLabel age=new JLabel("Age: ");
        age.setBounds(20,140,100,20);
        age.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(age);
        
        JLabel cont=new JLabel("Contact: ");
        cont.setBounds(20,180,100,20);
        cont.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(cont);
        
        JLabel address=new JLabel("Address: ");
        address.setBounds(20,220,100,20);
        address.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(address);
        
        JLabel city=new JLabel("City: ");
        city.setBounds(20,300,100,20);
        city.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(city);
        
        JLabel email=new JLabel("Email ID: ");
        email.setBounds(20,340,110,20);
        email.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(email);
        JLabel pass=new JLabel("Password: ");
        pass.setBounds(20,380,100,20);
        pass.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(pass);
        
        //Database connection 
        Conn conn=new Conn();
        try {
                ResultSet rs=conn.s.executeQuery("select full_name,gender,age,address,contact,email,password from customer where email='"+this.username+"' and password='"+this.password+"'");
                if(rs.next())
                {
                    JLabel full_name=new JLabel(rs.getString("full_name"));
                    full_name.setBounds(200,60,150,20);
                    full_name.setFont(new Font("Tahoma",Font.PLAIN,16));
                    p.add(full_name);
                    
                    JLabel gender1=new JLabel(rs.getString("gender"));
                    gender1.setBounds(200,100,150,20);
                    gender1.setFont(new Font("Tahoma",Font.PLAIN,16));
                    p.add(gender1);
                    
                    this.contact=new JTextField();
                    this.contact.setBounds(200,180,150,20);
                    p.add(this.contact);
                    this.contact.setFont(new Font("Tahoma",Font.PLAIN,16));
                    this.contact.setText(rs.getString("contact"));
                    
                    this.age=new JTextField();
                    this.age.setBounds(200,140,150,20);
                    p.add(this.age);
                    this.age.setFont(new Font("Tahoma",Font.PLAIN,16));
                    this.age.setText(rs.getString("age"));
                    
                    
                    
                    this.address=new JTextArea();
                    this.address.setBounds(200,220,350,90);
                    p.add(this.address);
                    this.address.setFont(new Font("Tahoma",Font.PLAIN,16));
                    this.address.setText(rs.getString("address"));
                    
                    JLabel email1=new JLabel(rs.getString("email"));
                    email1.setBounds(200,320,150,20);
                    email1.setFont(new Font("Tahoma",Font.PLAIN,16));
                    p.add(email1);
                    
                    JLabel passwd=new JLabel();
                    passwd.setBounds(200,360,150,20);
                    p.add(passwd);
                    passwd.setFont(new Font("Tahoma",Font.PLAIN,16));
                    passwd.setText(rs.getString("password"));
                    
                    edit=new JButton("Edit");
                    this.edit.setBounds(20,410,150,30);
                    p.add(this.edit);
                    edit.setBackground(Color.blue);
                    edit.setForeground(Color.white);
                    edit.setFont(new Font("Tahoma",Font.PLAIN,16));
                    edit.addActionListener(this);
                    cancel=new JButton("Cancel");
                    this.cancel.setBounds(200,410,150,30);
                    p.add(this.cancel);
                    cancel.setForeground(Color.white);
                    cancel.setBackground(Color.blue);
                    cancel.setFont(new Font("Tahoma",Font.PLAIN,16));
                    cancel.addActionListener(this);                    
                }
        }
        catch(SQLException exc){}
        getContentPane().setBackground(Color.WHITE);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent action)
    {
        if(action.getSource()==edit)
        {
             Conn conn=new Conn();
            try {
                String qu="update customer set age="+Integer.parseInt(this.age.getText())+",contact="+Long.parseLong(this.contact.getText())+",address='"+this.address.getText()+"' where email='"+this.username+"' and password='"+this.password+"'";
    //            this.password=this.passwd.getText();
                conn.s.executeUpdate(qu);

                JOptionPane.showMessageDialog(null,"User Details updated Successfully!");
                this.setVisible(false);
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        else if(action.getSource()==cancel)
        {
            this.setVisible(false);
        }
    }
    public static void main(String args[])
    {
        new EditUserProfile("kamal@gmail.com","kamal");
    }
}
