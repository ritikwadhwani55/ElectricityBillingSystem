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
public class ViewTax extends JFrame{
    JTable table;
    JButton back;
   public ViewTax()
   {
        setVisible(true);
        setBounds(100,100,1000,540);
         JLabel top=new JLabel("Update Customer Details");
        top.setBounds(400,5,320,30);
        top.setFont(new Font("Tahoma",Font.BOLD,20));
        add(top);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);
        Conn c=new Conn();
        try
        {
            String db_query="select * from tax";
            String column_names[]={"Tax Id","Cost per unit","Meter rent","Service Charge","Service Rate","Fixed Rate","Electricity Type"};
            String records[][]=new String[20][column_names.length];
            ResultSet rslt=c.s.executeQuery(db_query);
            int i=0,j=0;
            while(rslt.next())
            {
                records[i][j++]=rslt.getString("tax_id");
                records[i][j++]=rslt.getString("cost_per_unit");
                records[i][j++]=rslt.getString("meter_rent");
                records[i][j++]=rslt.getString("service_charge");
                records[i][j++]=rslt.getString("service_rate");
                records[i][j++]=rslt.getString("fixed_rate");
                records[i][j++]=rslt.getString("electricity_type");
                j=0;
                i++;
                
            }
            table=new JTable(records,column_names);
            JScrollPane jspane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            table.setRowHeight(30);
            jspane.setBounds(100,100,750,300);
            add(jspane);
            JTableHeader header = table.getTableHeader();
            header.setBackground(Color.blue);
            header.setForeground(Color.white);
            header.setFont(new Font("Tahoma",Font.BOLD,16));
            table.setRowHeight(30);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableColumn colmn=null;
            for(int col=0;col<column_names.length;col++)
            {
                colmn=table.getColumnModel().getColumn(col);
                colmn.setPreferredWidth(100);
                if(col==1 || col==3 || col==4 || col==6)
                    colmn.setPreferredWidth(155);
            }
            back=new JButton("Back");
            this.back.setBounds(390,430,150,30);
            add(this.back);
            back.setBackground(Color.blue);
            back.setForeground(Color.white);
            back.setFont(new Font("Tahoma",Font.PLAIN,16));
            back.addActionListener((ActionEvent actEvent) -> {
                this.setVisible(false);
            });
            
        }
        catch(SQLException exc){}
   }
   public static void main(String args[])
   {
       new ViewTax();
   }
}
