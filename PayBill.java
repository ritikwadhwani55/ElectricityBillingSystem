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
import java.util.*;
import java.sql.*;
import java.awt.event.*;
public class PayBill extends JFrame implements ItemListener,ActionListener{
    JPanel jp;
    JButton pay,cancel;
    Choice meter_num,unpaid_months;
    ArrayList radio;
    float cost_per_unit;
    int connctn_id,bill_id;
    JLabel units_consumed,meter_rent,service_charge,service_rate,fixed_rate,bill,total_bill;
    public PayBill()
    {
        super("Pay a Bill");
        setVisible(true);
        setBounds(190,150,970,520);
        this.getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/unnamed.png"));
        
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image imageResize=img.getImage().getScaledInstance(370,520,Image.SCALE_DEFAULT);
        ImageIcon adjusted=new ImageIcon(imageResize);
        JLabel imgLabel=new JLabel(adjusted);
        imgLabel.setBounds(0,0,370,520);
        add(imgLabel);
        
        
        //Panel
        jp=new JPanel();
        jp.setBounds(371,0,710,520);
        jp.setBackground(Color.WHITE);
        jp.setLayout(null);
        add(jp);
        
        JLabel top=new JLabel("Pay a Bill");
        top.setBounds(225,5,320,30);
        top.setFont(new Font("Tahoma",Font.BOLD,20));
        jp.add(top);
        
        JLabel m_no=new JLabel("Meter Number: ");
        m_no.setBounds(20,50,150,30);
        m_no.setFont(new Font("Tahoma",Font.PLAIN,16));
        jp.add(m_no);
        
        JLabel month=new JLabel("Select the Month: ");
        month.setBounds(20,90,150,30);
        month.setFont(new Font("Tahoma",Font.PLAIN,16));
        jp.add(month);
        
        JLabel units=new JLabel("Units Consumed: ");
        units.setBounds(20,150,150,30);
        units.setFont(new Font("Tahoma",Font.PLAIN,16));
        jp.add(units);
        
        JLabel bill1=new JLabel("Bill: ");
        bill1.setBounds(20,190,150,30);
        bill1.setFont(new Font("Tahoma",Font.PLAIN,16));
        jp.add(bill1);
        
        JLabel meter_rent=new JLabel("Meter rent: ");
        meter_rent.setBounds(20,230,150,30);
        meter_rent.setFont(new Font("Tahoma",Font.PLAIN,16));
        jp.add(meter_rent);
        
        JLabel service_charge=new JLabel("Service Charge: ");
        service_charge.setBounds(20,270,150,30);
        service_charge.setFont(new Font("Tahoma",Font.PLAIN,16));
        jp.add(service_charge);
        
        JLabel service_rate=new JLabel("Service Rate: ");
        service_rate.setBounds(280,270,150,30);
        service_rate.setFont(new Font("Tahoma",Font.PLAIN,16));
        jp.add(service_rate);
        
        JLabel fixed_rate=new JLabel("Fixed Rate: ");
        fixed_rate.setBounds(20,310,150,30);
        fixed_rate.setFont(new Font("Tahoma",Font.PLAIN,16));
        jp.add(fixed_rate);
        
        JLabel total=new JLabel("Total Bill: ");
        total.setBounds(20,350,150,30);
        total.setFont(new Font("Tahoma",Font.PLAIN,16));
        jp.add(total);
        
        
        pay=new JButton("Pay");
            this.pay.setBounds(20,410,150,30);
            jp.add(this.pay);
            pay.setBackground(Color.blue);
            pay.setForeground(Color.white);
            pay.setFont(new Font("Tahoma",Font.PLAIN,16));
            pay.addActionListener(this);

        cancel=new JButton("Cancel");
            this.cancel.setBounds(280,410,150,30);
            jp.add(this.cancel);
            cancel.setBackground(Color.blue);
            cancel.setForeground(Color.white);
            cancel.setFont(new Font("Tahoma",Font.PLAIN,16));
            cancel.addActionListener(this);
        this.meter_num=new Choice();
        this.meter_num.add("");
        Conn c=new Conn();
        String query=null;
        ResultSet rs;
        try
        {
            query="select meter_number from connection";
            rs=c.s.executeQuery(query);
            while(rs.next())
            {
                this.meter_num.add(rs.getString("meter_number"));
            }
            this.meter_num.setBounds(250,50,180,30);
            jp.add(this.meter_num);
            this.meter_num.addItemListener(this);
//            query="SELECT b.*,c.meter_number from connection c inner join bill b on b.connection_id=c.connection_id where c.meter_number="+Integer.parseInt((String)this.meter_num.getSelectedItem());
//            rs=c.s.executeQuery(query);
//            btn=new ButtonGroup();
//            unpaid_months=new Choice();
//            unpaid_months.add("");
//            while(rs.next())
//            {
//                this.connctn_id=Integer.parseInt(rs.getString("connection_id"));
//                
//                unpaid_months.add(rs.getString("month")+" " +rs.getString("year"));
//                
//            }
            
            unpaid_months=new Choice();
            unpaid_months.add("");
            unpaid_months.setBounds(250,90,180,30);
            jp.add(this.unpaid_months);
            this.unpaid_months.setVisible(true);
            this.unpaid_months.addItemListener(this);
        }
        catch(Exception excptn){
            excptn.printStackTrace();
        }
        query="select * from tax";
            try
            {
                rs=c.s.executeQuery(query);
                if(rs.next())
                {
                    this.meter_rent=new JLabel(rs.getString("meter_rent"));
                    this.meter_rent.setBounds(160,230,150,30);
                    this.meter_rent.setFont(new Font("Tahoma",Font.PLAIN,16));
                    jp.add(this.meter_rent);
                    
                    this.service_charge=new JLabel(rs.getString("service_charge"));
                    this.service_charge.setBounds(160,270,150,30);
                    this.service_charge.setFont(new Font("Tahoma",Font.PLAIN,16));
                    jp.add(this.service_charge);
                    
                    this.service_rate=new JLabel(rs.getString("service_rate"));
                    this.service_rate.setBounds(400,270,150,30);
                    this.service_rate.setFont(new Font("Tahoma",Font.PLAIN,16));
                    jp.add(this.service_rate);
                    
                    this.fixed_rate=new JLabel(rs.getString("fixed_rate"));
                    this.fixed_rate.setBounds(160,310,150,30);
                    this.fixed_rate.setFont(new Font("Tahoma",Font.PLAIN,16));
                    jp.add(this.fixed_rate);
                    
                    this.cost_per_unit=Float.parseFloat(rs.getString("cost_per_unit"));
                    
                    //creating empty label to dynamically update the bill
                    this.bill=new JLabel();
                    this.bill.setBounds(160,190,150,30);
                    this.bill.setFont(new Font("Tahoma",Font.PLAIN,16));
                    jp.add(this.bill);
                    
                    this.total_bill=new JLabel();
                    this.total_bill.setBounds(160,350,150,30);
                    this.total_bill.setFont(new Font("Tahoma",Font.PLAIN,16));
                    jp.add(this.total_bill);
                    
                    this.units_consumed=new JLabel();
                    this.units_consumed.setBounds(160,150,150,30);
                    this.units_consumed.setFont(new Font("Tahoma",Font.PLAIN,16));
                    jp.add(this.units_consumed);
                }
            }
            catch(SQLException dbexcept){}
    }
    public void actionPerformed(ActionEvent actnEvent)
    {
        if(actnEvent.getSource()==pay)
        {
            if(!((String)this.unpaid_months.getSelectedItem()).equals(""))
            {
                String db_query="update bill set payment_status='Paid',payment_date=CURRENT_DATE where bill_id="+this.bill_id;
                Conn c=new Conn();

                try
                {
                    c.s.executeUpdate(db_query);
                    JOptionPane.showMessageDialog(null, "Bill paid!");
                    this.setVisible(false);
                }
                catch(SQLException db_except){}
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Your Bills are already paid, Thankyou!");
            }
        }
            else if(actnEvent.getSource()==cancel)
            {
                this.setVisible(false);
            }
        
        
        
    }
    public void itemStateChanged(ItemEvent ie)
    {
        String query=null;
            ResultSet rs;
            Conn c=new Conn();
        if(ie.getSource()==this.meter_num)
        {
            
            try {
                String m=(String)this.meter_num.getSelectedItem();
            if(!m.equals(""))
            {
                query="SELECT b.* from connection c inner join bill b on b.connection_id=c.connection_id where c.meter_number="+Integer.parseInt((String)this.meter_num.getSelectedItem())+" and (c.disconnect_date is null or b.payment_status='Unpaid')";
                rs=c.s.executeQuery(query);
    //            btn=new ButtonGroup();
                this.unpaid_months.setVisible(false);
                unpaid_months=new Choice();
                unpaid_months.add("");
                unpaid_months.setVisible(true);
                unpaid_months.addItemListener(this);
                while(rs.next())
                {
                    if(rs.getString("payment_status").toLowerCase().equals("unpaid"))
                    {
                        this.connctn_id=Integer.parseInt(rs.getString("connection_id"));

                        unpaid_months.add(rs.getString("month")+" " +rs.getString("year"));
                    }
                }
            
            unpaid_months.setBounds(250,90,180,30);
            jp.add(unpaid_months);
            }
        }
            catch(Exception e){}
        }
        else if(ie.getSource()==this.unpaid_months)
        {
            query="select b.* from bill b inner join connection c on b.connection_id=c.connection_id where c.connection_id="+this.connctn_id+" and b.month='"+((String)this.unpaid_months.getSelectedItem()).substring(0,((String)this.unpaid_months.getSelectedItem()).indexOf(" "))+"' and b.year="+Integer.parseInt(((String)this.unpaid_months.getSelectedItem()).substring(this.unpaid_months.getSelectedItem().indexOf(" ")+1,((String)this.unpaid_months.getSelectedItem()).length()))+"";
            try {
                rs=c.s.executeQuery(query);
                if(rs.next())
                {
                    float bill=Float.parseFloat(rs.getString("units_consumed"));
                    bill=bill*this.cost_per_unit;
                    this.bill.setVisible(false);
                    this.bill=new JLabel(Float.toString(bill));
                    this.bill.setBounds(160,190,150,30);
                    this.bill.setFont(new Font("Tahoma",Font.PLAIN,16));
                    jp.add(this.bill);
                    
                    this.total_bill.setVisible(false);
                    this.total_bill=new JLabel(rs.getString("total_bill"));
                    this.total_bill.setBounds(160,350,150,30);
                    this.total_bill.setFont(new Font("Tahoma",Font.PLAIN,16));
                    jp.add(this.total_bill);
                    
                    this.units_consumed.setVisible(false);
                    this.units_consumed=new JLabel(rs.getString("units_consumed"));
                    this.units_consumed.setBounds(160,150,150,30);
                    this.units_consumed.setFont(new Font("Tahoma",Font.PLAIN,16));
                    jp.add(this.units_consumed);
                    this.bill_id=Integer.parseInt(rs.getString("bill_id"));
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[])
    {
        new PayBill();
    }
}
