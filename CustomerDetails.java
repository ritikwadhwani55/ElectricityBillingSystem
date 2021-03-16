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
import java.awt.event.*;
public class CustomerDetails extends JFrame{
    JTable tb;
    JButton back;
    
    public CustomerDetails()
    {
        super("View Customer Details");
        setVisible(true);
        setBounds(196,110,1160,620);
        
        setLayout(null); 
        JLabel top=new JLabel("View Customer Details");
        top.setBounds(450,10,250,30);
        top.setFont(new Font("Tahoma",Font.BOLD,20));
        add(top);
        String column_names[]= {"Full Name","Gender","Age","Address","Contact","Email","Password"};
        String records[][]=new String[22][column_names.length];
        Conn c=new Conn();
        try {
            int row=0,column=0;
            ResultSet rs=c.s.executeQuery("select full_name,gender,age,address,contact,email,password from customer");
            TableColumn col=null;
            while(rs.next())
            {
                records[row][column++]=rs.getString("full_name");
                records[row][column++]=rs.getString("gender");
                records[row][column++]=rs.getString("age");
                records[row][column++]=rs.getString("address");
                records[row][column++]=rs.getString("contact");
                records[row][column++]=rs.getString("email");
                records[row][column++]=rs.getString("password");
                row++;
                column=0;
                
            }
            tb=new JTable(records,column_names);
            tb.setRowHeight(30);
//            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            for(int i=0;i<column_names.length;i++)
            {
                col=tb.getColumnModel().getColumn(i);
                if(i==3)
                    col.setPreferredWidth(200);
                else
                    col.setPreferredWidth(50);
            }
            this.getContentPane().setBackground(Color.WHITE);
//            tb.setBounds(80,50,800,)
            JScrollPane jsp=new JScrollPane(tb,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jsp.setBounds(72,70,1010,405);
            JTableHeader header = tb.getTableHeader();
            header.setBackground(Color.blue);
            header.setForeground(Color.white);
            header.setFont(new Font("Tahoma",Font.BOLD,16));
            add(jsp);
            back=new JButton("Back");
            back.setBounds(497,510,150,28);
            back.setForeground(Color.WHITE);
            back.setBackground(Color.blue);
            add(back);
            back.setFont(new Font("Tahoma",Font.PLAIN,17));
            back.addActionListener((ActionEvent act) -> this.setVisible(false));
//            back.addActionListener(this);
//            ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/Customer-Services.jpg"));
//    //        setLayout(null);
//            Image imageResize=img.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
//            ImageIcon adjusted=new ImageIcon(imageResize);
//            JLabel imgLabel=new JLabel(adjusted);
//            imgLabel.setBounds(300,600,300,300);
//            add(imgLabel);
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
    
    public static void main(String args[])
    {
        new CustomerDetails();
    }
}
