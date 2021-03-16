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
public class ViewBillDetails extends JFrame implements ItemListener{
    JTable tb;
    Choice choose_city;
    public ViewBillDetails()
    {
        super("View Bills");
        setVisible(true);
        setBounds(150,80,1225,750);
//        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/user.png"));
        setLayout(null);

        this.getContentPane().setBackground(Color.white);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel lab=new JLabel("View Bills");
        lab.setBounds(520,8,280,20);
        lab.setFont(new Font("Tahoma",Font.BOLD,23));
        add(lab);
        
        JLabel city_label=new JLabel("View Bills of a City");
        city_label.setBounds(160,60,220,20);
        city_label.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(city_label);
        
        
        choose_city=new Choice();
        choose_city.add("");
        choose_city.addItemListener(this);
        
        String col_names[]={"Bill ID","Connection ID","Meter Number","City","Month","Year","Total Bill","Payment Status","Units Consumed"};
        String tuples[][]=new String[20][col_names.length];
        Conn c=new Conn();
        String sql=null;
        TableColumn col=null;
        ResultSet re;
        try
        {
            sql="select city from connection";
            re=c.s.executeQuery(sql);
            while(re.next())
                choose_city.add(re.getString("city"));
            choose_city.add("all");
            choose_city.setFont(new Font("Tahoma",Font.PLAIN,22));
            choose_city.setBounds(400,60,180,35);
            add(choose_city);
            sql="select b.*,c.meter_number,c.city from bill b inner join connection c on b.connection_id=c.connection_id";
            re=c.s.executeQuery(sql);
            int r=0,c1=0;
            while(re.next())
            {
                
                tuples[r][c1++]=re.getString("bill_id");
                tuples[r][c1++]=re.getString("connection_id");
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
            jp1.setBounds(85,160,1050,480);
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
    public void itemStateChanged(ItemEvent ie)
    {
        String city_name=choose_city.getSelectedItem();
            TableColumn col1=null;
            int r2=0,c2=0;
            Conn c=new Conn();
            if(!city_name.equals("all"))
            {
                    try {
                            ResultSet r=c.s.executeQuery("select b.*,c.meter_number,c.city from bill b inner join connection c on b.connection_id=c.connection_id where c.city='"+city_name+"'");
                        String col_name[]={"Bill ID","Connection ID","Meter Number","Month","Year","Total Bill","Payment Status","Units Consumed"};
                        String tuple[][]=new String[20][col_name.length];
        
                        while(r.next())
                        {

                            tuple[r2][c2++]=r.getString("bill_id");
                            tuple[r2][c2++]=r.getString("connection_id");
                             tuple[r2][c2++]=r.getString("meter_number");
                            
                            tuple[r2][c2++]=r.getString("month");
                            tuple[r2][c2++]=r.getString("year");
                            tuple[r2][c2++]=r.getString("total_bill");
//                            tuple[r2][c2++]=r.getString("payment_mode");
                            tuple[r2][c2++]=r.getString("payment_status");
                            tuple[r2][c2++]=r.getString("units_consumed");

                            r2++;
                            c2=0;
                        }
                        tb=new JTable(tuple,col_name);
                        JScrollPane jp1=new JScrollPane(tb,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                        jp1.setBounds(85,160,1050,480);
                        add(jp1);
                        JTableHeader header = tb.getTableHeader();
                        header.setBackground(Color.blue);
                        header.setForeground(Color.white);
                        header.setFont(new Font("Tahoma",Font.BOLD,16));
                        tb.setRowHeight(30);
                        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        for(int i1=0;i1<col_name.length;i1++)
                        {
                            col1=tb.getColumnModel().getColumn(i1);
                            col1.setPreferredWidth(150);
                        }
                    
                    }catch(SQLException e){}
                }
                else 
                {
                    try {
                            ResultSet r=c.s.executeQuery("select b.*,c.meter_number,c.city from bill b inner join connection c on b.connection_id=c.connection_id");
                        String col_name[]={"Bill ID","Connection ID","Meter Number","City","Month","Year","Total Bill","Payment Status","Units Consumed"};
                        String tuple[][]=new String[20][col_name.length];
        
                        while(r.next())
                        {

                            tuple[r2][c2++]=r.getString("bill_id");
                            tuple[r2][c2++]=r.getString("connection_id");
                             tuple[r2][c2++]=r.getString("meter_number");
                            tuple[r2][c2++]=r.getString("city");
                            tuple[r2][c2++]=r.getString("month");
                            tuple[r2][c2++]=r.getString("year");
                            tuple[r2][c2++]=r.getString("total_bill");
//                            tuple[r2][c2++]=r.getString("payment_mode");
                            tuple[r2][c2++]=r.getString("payment_status");
                            tuple[r2][c2++]=r.getString("units_consumed");

                            r2++;
                            c2=0;
                        }
                        tb=new JTable(tuple,col_name);
                        JScrollPane jp1=new JScrollPane(tb,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                        jp1.setBounds(85,160,1050,480);
                        add(jp1);
                        JTableHeader header = tb.getTableHeader();
                        header.setBackground(Color.blue);
                        header.setForeground(Color.white);
                        header.setFont(new Font("Tahoma",Font.BOLD,16));
                        tb.setRowHeight(30);
                        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        for(int i1=0;i1<col_name.length;i1++)
                        {
                            col1=tb.getColumnModel().getColumn(i1);
                            col1.setPreferredWidth(150);
                        }
                    
                    }catch(SQLException e){}
                    
                }
            }
    
    public static void main(String args[])
    {
        new ViewBillDetails();
    }
}
