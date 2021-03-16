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
public class ViewConnectionDetails extends JFrame{
    JPanel p;
    JTable tb;
    JButton b;
    public ViewConnectionDetails()
    {
        setVisible(true);
        setBounds(60,60,1450,700);
        setLayout(null);
        ImageIcon ic=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/tdworld.png"));
        Image i=ic.getImage().getScaledInstance(320,695,Image.SCALE_DEFAULT);
        ImageIcon icon=new ImageIcon(i);
        JLabel l=new JLabel(icon);
        l.setBounds(0,0,320,700);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(l);
        p=new JPanel();
        p.setLayout(null);
        p.setBounds(321,0,1160,700);
        add(p);
        p.setBackground(Color.white);
        
        JLabel lab=new JLabel("View Connection Details");
        lab.setBounds(420,20,280,20);
        lab.setFont(new Font("Tahoma",Font.BOLD,21));
        p.add(lab);
        String col_names[]={"Connection ID","Customer ID","Customer Name","Meter Number","Connection Address","City","Meter Location","Installation Date","Disconnection Date"};
        String tuples[][]=new String[20][col_names.length];
        Conn c=new Conn();
        String sql=null;
        TableColumn col=null;
        try
        {
            sql="select c.connection_id,c.customer_id,cust.full_name,c.meter_number,c.conn_address,c.city,c.meter_location,c.installation_date,c.disconnect_date from connection c inner join customer cust on c.customer_id=cust.customer_id";
            ResultSet re=c.s.executeQuery(sql);
            int r=0,c1=0;
            while(re.next())
            {
                tuples[r][c1++]=re.getString("connection_id");
                tuples[r][c1++]=re.getString("customer_id");
                tuples[r][c1++]=re.getString("full_name");
                tuples[r][c1++]=re.getString("meter_number");
                tuples[r][c1++]=re.getString("conn_address");
                tuples[r][c1++]=re.getString("city");
                tuples[r][c1++]=re.getString("meter_location");
                tuples[r][c1++]=re.getString("installation_date");
                tuples[r][c1++]=re.getString("disconnect_date");
                r++;
                c1=0;
            }
            tb=new JTable(tuples,col_names);
            JScrollPane jp1=new JScrollPane(tb,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jp1.setBounds(20,100,1050,500);
            p.add(jp1);
            JTableHeader header = tb.getTableHeader();
            header.setBackground(Color.blue);
            header.setForeground(Color.white);
            header.setFont(new Font("Tahoma",Font.BOLD,16));
            tb.setRowHeight(30);
            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            for(int i1=0;i1<col_names.length;i1++)
            {
                col=tb.getColumnModel().getColumn(i1);
                if(i1==4)
                    col.setPreferredWidth(280);
                else if(i1==5)
                    col.setPreferredWidth(115);
                else
                    col.setPreferredWidth(166);
            }
            b=new JButton("Back");
            b.setBounds(475,610,150,28);
            b.setForeground(Color.WHITE);
            b.setBackground(Color.blue);
            p.add(b);
            b.setFont(new Font("Tahoma",Font.PLAIN,17));
            b.addActionListener((ActionEvent act) -> this.setVisible(false));
        
        }
        catch(SQLException eqlexcept){}
    }
    public static void main(String args[])
    {
        new ViewConnectionDetails();
    }
}
