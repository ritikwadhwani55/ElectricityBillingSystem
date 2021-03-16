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
import java.util.*;
import java.sql.*;
public class CreateBill extends JFrame implements ActionListener, ItemListener{
    JPanel jp;
    Choice choice,months,year;
    int meter_number;
//    JComboBox months,year;
    ArrayList<String> month_arr;
    JTextField units_consumed;
    JButton submit,cancel;
    int c_id;
    public CreateBill()
    {
        super("Create a Bill");
        setVisible(true);
        setBounds(190,150,900,400);
        this.getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/electricity-bill-png.png"));
        
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image imageResize=img.getImage().getScaledInstance(370,400,Image.SCALE_DEFAULT);
        ImageIcon adjusted=new ImageIcon(imageResize);
        JLabel imgLabel=new JLabel(adjusted);
        imgLabel.setBounds(0,0,370,400);
        add(imgLabel);
        
        //Panel
        jp=new JPanel();
        jp.setBounds(371,0,630,400);
        jp.setBackground(Color.WHITE);
        jp.setLayout(null);
        add(jp);
        
        
        JLabel top=new JLabel("Calculate a Bill");
        top.setBounds(225,5,320,30);
        top.setFont(new Font("Tahoma",Font.BOLD,20));
        jp.add(top);
        
        JLabel meter=new JLabel("Select Meter Number");
        meter.setBounds(20,75,250,30);
        meter.setFont(new Font("Tahoma",Font.PLAIN,20));
        jp.add(meter);
        jp.setLayout(null);
        Conn c=new Conn();
        int m_no=0;
        choice=new Choice();
        choice.add("");
        try
        {
            String q="select meter_number from connection";
            ResultSet rs=c.s.executeQuery(q);
            while(rs.next())
            {
                choice.add(rs.getString("meter_number"));
            }
            jp.add(choice);
            choice.setBounds(280,75,150,30);
        }
        catch(SQLException exc){}
        choice.addItemListener((ItemEvent ievent) -> {
                this.meter_number = Integer.parseInt((String)this.choice.getSelectedItem());
                System.out.print(this.meter_number);
            }
        );
        JLabel month=new JLabel("Month: ");
        month.setBounds(20,155,180,30);
        month.setFont(new Font("Tahoma",Font.PLAIN,20));
        jp.add(month);
        this.month_arr=new ArrayList<String>();
        this.months=new Choice();
        String month_arr[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
        for(int j=0;j<month_arr.length;j++)
            this.months.add(month_arr[j]);
        for(int k=0;k<month_arr.length;k++)
            this.month_arr.add(month_arr[k]);
        int y=Calendar.getInstance().get(Calendar.YEAR);
        int year1[]=new int[2];
        year1[0]=y;
        year1[1]=y-1;
       
        this.year=new Choice();
        this.year.add(Integer.toString(y));
        this.year.add(Integer.toString(y-1));
        this.year.setBounds(280,115,180,30);
        jp.add(this.year);
         this.year.addItemListener(this);
        JLabel year=new JLabel("Year: ");
        year.setBounds(20,115,180,30);
        year.setFont(new Font("Tahoma",Font.PLAIN,20));
        jp.add(year);
//        this.months.addItemListener(this);
        int cal=Calendar.getInstance().get(Calendar.MONTH);
//      System.out.print(cal);
        String month_arr1[]=new String[month_arr.length];
        this.months=new Choice();
        for(int i=0;i<cal;i++)
            this.months.add(month_arr[i]);
        
        this.months.setBounds(280,155,180,30);
        jp.add(this.months);
        JLabel units=new JLabel("Units: ");
        units.setBounds(20,195,180,30);
        units.setFont(new Font("Tahoma",Font.PLAIN,20));
        jp.add(units);
        
        units_consumed=new JTextField();
        units_consumed.setBounds(280,195,150,30);
        units_consumed.setFont(new Font("Tahoma",Font.PLAIN,20));
        jp.add(units_consumed);
        
        //submit
        cancel=new JButton("Cancel");
        this.cancel.setBounds(280,265,150,30);
        jp.add(this.cancel);
        cancel.setBackground(Color.blue);
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Tahoma",Font.PLAIN,16));
        cancel.addActionListener(this);
        
        submit=new JButton("Submit");
        this.submit.setBounds(20,265,150,30);
        jp.add(this.submit);
        submit.setBackground(Color.blue);
        submit.setForeground(Color.white);
        submit.setFont(new Font("Tahoma",Font.PLAIN,16));
        submit.addActionListener(this);
    }
    public void itemStateChanged(ItemEvent ie)
    {
        int i;
        
        String y=(String)this.year.getSelectedItem();
        int y1=Integer.parseInt(y);
        if(ie.getSource()==this.year)
        {
            
            if(y1==Calendar.getInstance().get(Calendar.YEAR))
            {
                this.months.setVisible(false);
                this.months=new Choice();
                this.months.setVisible(true);
                for(i=0;i<Calendar.getInstance().get(Calendar.MONTH);i++)
                    this.months.add(month_arr.get(i));
                this.months.setBounds(280,155,180,30);
                jp.add(this.months);
            }
            else
            {
                this.months.setVisible(false);
                    this.months=new Choice();
                    this.months.setVisible(true);
                for(i=0;i<month_arr.size();i++)
                    this.months.add(month_arr.get(i));
                this.months.setBounds(280,155,180,30);
                jp.add(this.months);
            }
        }
        
    }
    public void actionPerformed(ActionEvent acevent)
    {
       if(acevent.getSource()==cancel)
       {
           this.setVisible(false);
       }
       else if(acevent.getSource()==submit)
       {
           float units=Integer.parseInt(this.units_consumed.getText());
           Conn c=new Conn();
           String sql=null;
           float bill=0;
           try
           {
               sql="select * from tax";
               ResultSet r=c.s.executeQuery(sql);
               if(r.next())
               {
                    bill=units*Integer.parseInt(r.getString("cost_per_unit"))+Integer.parseInt(r.getString("meter_rent"))+Integer.parseInt(r.getString("service_charge"))+Integer.parseInt(r.getString("service_rate"))+Integer.parseInt(r.getString("fixed_rate"));
                    System.out.print(bill);
//                    this.c_id=0;
                    sql="select connection_id from connection where meter_number="+this.meter_number;
                    System.out.print(this.meter_number);
                    r=c.s.executeQuery(sql);
                    if(r.next())
                    {
                        this.c_id=Integer.parseInt(r.getString("connection_id"));
                    }
                    sql="insert into bill(connection_id,month,year,total_bill,payment_status,units_consumed) values("+this.c_id+",'"+(String)this.months.getSelectedItem()+"','"+(String)this.year.getSelectedItem()+"',"+bill+",'Unpaid',"+Integer.parseInt(this.units_consumed.getText())+")";
                    c.s.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "The total calculated bill is "+bill);
                    this.setVisible(false);
                }
               
           }
           catch(Exception e){
               e.printStackTrace();
           }
       }
    }
    public static void main(String args[])
    {
        new CreateBill();
    }
}
