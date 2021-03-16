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
//import net.proteanit.sql.DbUtils;
import java.awt.event.*;
public class EmployeeDetails extends JFrame implements ActionListener{
    JTable tb;
    JButton back;
    String email_id,password;
    public EmployeeDetails(String email,String pswd)
    {
        this.email_id=email;
        password=pswd;
        setLayout(null); 
        JLabel top=new JLabel("View Employee Details");
        top.setBounds(400,10,250,30);
        top.setFont(new Font("Tahoma",Font.BOLD,20));
        add(top);
        String column_names[]= {"Full Name","Gender","Age","Address","Contact","Date of Joining","Email"};
        String records[][]=new String[22][column_names.length];
        Conn c=new Conn();
        try {
            int row=0,column=0;
            ResultSet rs=c.s.executeQuery("select full_name,gender,age,address,contact,doj,email from admin");
            TableColumn col=null;
            while(rs.next())
            {
                records[row][column++]=rs.getString("full_name");
//                col=tb.getColumnModel().getColumn(column);
                records[row][column++]=rs.getString("gender");
//                col=tb.getColumnModel().getColumn(column);
                records[row][column++]=rs.getString("age");
//                col=tb.getColumnModel().getColumn(column);
                records[row][column++]=rs.getString("address");
//                col=tb.getColumnModel().getColumn(column);
                
                records[row][column++]=rs.getString("contact");
                records[row][column++]=rs.getString("doj");
                records[row][column++]=rs.getString("email");
                row++;
                column=0;
                
            }
            tb=new JTable(records,column_names);
            tb.setRowHeight(30);
            for(int i=0;i<column_names.length;i++)
            {
                col=tb.getColumnModel().getColumn(i);
                if(i==3)
                    col.setPreferredWidth(290);
                else
                    col.setPreferredWidth(80);
            }
            this.getContentPane().setBackground(Color.WHITE);
//            tb.setBounds(80,50,800,)
            JScrollPane jsp=new JScrollPane(tb,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jsp.setBounds(40,100,1000,405);
            JTableHeader header = tb.getTableHeader();
            header.setBackground(Color.blue);
            header.setForeground(Color.white);
            header.setFont(new Font("Tahoma",Font.PLAIN,16));
            add(jsp);
            back=new JButton("Back");
            back.setBounds(440,550,200,30);
            back.setForeground(Color.WHITE);
            back.setBackground(Color.blue);
            add(back);
            back.setFont(new Font("Tahoma",Font.PLAIN,17));
            back.addActionListener(this);
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
        setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON/_CLOSE);
        setBounds(240,105,1100,700);
        
    }
    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource()==back)
        {
            this.setVisible(false);
        }
        
    }
    public static void main(String args[])
    {
        new EmployeeDetails("ritik@gmail.com","ritik");
    }
}
