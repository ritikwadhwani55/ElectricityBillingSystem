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
public class ViewProfile extends JFrame implements ActionListener{
    JButton back;
    String username,password;
    public ViewProfile(String email_id,String pswd)
    {
        
        super("View Profile");this.username=email_id;
        password=pswd;
        setBounds(280,150,920,520);
        JLabel view=new JLabel("View Profile");
        setVisible(true);

        this.setLayout(null);
        this.getContentPane().setBackground(Color.WHITE);
        JLabel profile=new JLabel("View Profile");
        profile.setBounds(350,40,280,20);
        profile.setFont(new Font("Tahoma",Font.BOLD,21));
        add(profile);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel name=new JLabel("Full Name: ");
//        name.setForeground(Color.BLUE);
        name.setBounds(50,100,150,20);
        add(name);
        Conn c=new Conn();
        try {
                ResultSet res=c.s.executeQuery("select * from admin where email='"+username+"'");
                if(res.next())
                {
                    JLabel name_val=new JLabel(res.getString("full_name"));
                    
                    name_val.setBounds(400,100,150,20);
                    name.setFont(new Font("Tahoma",Font.PLAIN,17));
                    name_val.setFont(new Font("Tahoma",Font.PLAIN,17));
                    add(name_val);
                    JLabel gender=new JLabel("Gender: ");
                    JLabel gender_val=new JLabel(res.getString("gender"));
                    JLabel age=new JLabel("Age: ");
                    JLabel age_val=new JLabel(res.getString("age"));
                    JLabel salary=new JLabel("Salary");
                    JLabel salary_val=new JLabel(res.getString("salary"));
                    JLabel add=new JLabel("Address: ");
                    JLabel address_val=new JLabel(res.getString("address"));
                    JLabel contact=new JLabel("Contact: ");
                    JLabel contact_val=new JLabel(res.getString("contact"));
                    JLabel doj=new JLabel("Date of Joining: ");
                    JLabel doj_val=new JLabel(res.getString("doj"));
                    JLabel email=new JLabel("Email ID: ");
                    JLabel email_val=new JLabel(res.getString("email"));
                    JLabel password=new JLabel("Password: ");
                    JLabel pswd_val=new JLabel(res.getString("password"));
                    gender.setBounds(50,140,150,20);
                    add(gender);
                    gender.setFont(new Font("Tahoma",Font.PLAIN,17));
                    gender_val.setFont(new Font("Tahoma",Font.PLAIN,17));
                    gender_val.setBounds(400,140,150,20);
                    add(gender_val);
                    age.setBounds(50,180,150,20);
                    add(age);
                    age_val.setBounds(400,180,150,20);
                    add(age_val);
                    age.setFont(new Font("Tahoma",Font.PLAIN,17));
                    age_val.setFont(new Font("Tahoma",Font.PLAIN,17));
                    salary.setBounds(50,220,150,20);
                    add(salary);
                    salary_val.setBounds(400,220,150,20);
                    add(salary_val);
                    salary.setFont(new Font("Tahoma",Font.PLAIN,17));
                    salary_val.setFont(new Font("Tahoma",Font.PLAIN,17));
                    add.setBounds(50,260,150,20);
                    add(add);
                    address_val.setBounds(400,260,450,20);
                    add(address_val);
                    add.setFont(new Font("Tahoma",Font.PLAIN,17));
                    address_val.setFont(new Font("Tahoma",Font.PLAIN,17));
                    add(contact);
                    contact.setBounds(50,300,150,20);
                    contact_val.setBounds(400,300,150,20);
                    add(contact_val);
                    contact.setFont(new Font("Tahoma",Font.PLAIN,17));
                    contact_val.setFont(new Font("Tahoma",Font.PLAIN,17));
                    add(doj);
                    add(doj_val);
                    doj.setBounds(50,340,150,20);
                    doj_val.setBounds(400,340,150,20);
                    doj.setFont(new Font("Tahoma",Font.PLAIN,17));
                    doj_val.setFont(new Font("Tahoma",Font.PLAIN,17));
                    add(email);
                    add(email_val);
                    email.setBounds(50,380,100,20);
                    email_val.setBounds(400,380,150,20);
                    email.setFont(new Font("Tahoma",Font.PLAIN,17));
                    email_val.setFont(new Font("Tahoma",Font.PLAIN,17));
                    add(password);
                    add(pswd_val);
                    password.setBounds(50,420,150,20);
                    pswd_val.setBounds(400,420,150,20);
                    password.setFont(new Font("Tahoma",Font.PLAIN,17));
                    pswd_val.setFont(new Font("Tahoma",Font.PLAIN,17));
                    back=new JButton("Back");
                    back.setBounds(190,450,200,25);
                    back.setForeground(Color.WHITE);
                    back.setBackground(Color.blue);
                    add(back);
                    back.setFont(new Font("Tahoma",Font.BOLD,17));
                    back.addActionListener(this);
                }
//                this.setLayout(new BorderLayout());
//                add(profile,"North");
//                add(p1,"West");
                
            }catch(SQLException sqlExc)
            {
                sqlExc.printStackTrace();
            }
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        this.setVisible(false);
    }
    public static void main(String args[])
    {
        new ViewProfile("ritik@gmail.com","ritik");
    }
}
