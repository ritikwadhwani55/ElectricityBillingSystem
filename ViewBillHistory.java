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
public class ViewBillHistory extends JFrame{
    String email,password;
    JTable tb;
    public ViewBillHistory(String username,String password)
    {
        super("View Bill History");
        this.email=username;
        this.password=password;
        setVisible(true);
        setBounds(150,80,1225,620);
//        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/user.png"));
        setLayout(null);

        this.getContentPane().setBackground(Color.white);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel lab=new JLabel("View Bills");
        lab.setBounds(520,8,280,20);
        lab.setFont(new Font("Tahoma",Font.BOLD,23));
        add(lab);
        
        
        String col_names[]={"Bill ID","Meter Number","City","Month","Year","Total Bill","Payment Status","Units Consumed"};
        String tuples[][]=new String[20][col_names.length];
        Conn c=new Conn();
        String sql=null;
        TableColumn col=null;
        ResultSet re;
        try
        {
            sql="select customer_id from customer where email='"+this.email+"' and password='"+this.password+"'";
            re=c.s.executeQuery(sql);
            int id=0;
            if(re.next())
                id=Integer.parseInt(re.getString("customer_id"));
            sql="select b.*,c.meter_number,c.city from bill b inner join connection c on b.connection_id=c.connection_id where c.customer_id="+id;
            re=c.s.executeQuery(sql);
            int r=0,c1=0;
            while(re.next())
            {
                
                tuples[r][c1++]=re.getString("bill_id");
//                tuples[r][c1++]=re.getString("connection_id");
                 tuples[r][c1++]=re.getString("meter_number");
                tuples[r][c1++]=re.getString("city");
                tuples[r][c1++]=re.getString("month");
                tuples[r][c1++]=re.getString("year");
                tuples[r][c1++]=re.getString("total_bill");
//                tuples[r][c1++]=re.getString("payment_mode");
                tuples[r][c1++]=re.getString("payment_status");
                tuples[r][c1++]=re.getString("units_consumed");
                
                r++;
                c1=0;
            }
            tb=new JTable(tuples,col_names);
            JScrollPane jp1=new JScrollPane(tb,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jp1.setBounds(85,70,1050,480);
            add(jp1);
            JTableHeader header = tb.getTableHeader();
            header.setBackground(Color.blue);
            header.setForeground(Color.white);
            header.setFont(new Font("Tahoma",Font.BOLD,16));
            tb.setRowHeight(30);
            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            for(int i1=0;i1<col_names.length;i1++)
            {
                col=tb.getColumnModel().getColumn(i1);
                col.setPreferredWidth(150);
            }
        }catch(SQLException sqle){}
    }
     public static void main(String args[])
    {
        new ViewBillHistory("kamal@gmail.com","kamal");
    }
}
