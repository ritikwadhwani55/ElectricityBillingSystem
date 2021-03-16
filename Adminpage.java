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
import java.awt.event.*;
public class Adminpage extends JFrame implements ActionListener{
    JMenuBar m;
    JButton logout;
    String username,password;
    JMenu check,edit,bill,connection;
    JMenuItem tax,customer_details,profile,admin_details,add_admin,edit_profile,view_conn_request,
    update_cust_details,update_tax_values,bill_details,add_bill,update_bill_details,changePswd;
    public Adminpage(String username,String password)
    {
        this.username=username;
        this.password=password;
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/MahaVitaran-logo.jpg"));
//        setLayout(null);
        Image imageResize=img.getImage().getScaledInstance(1000,500,Image.SCALE_DEFAULT);
        ImageIcon adjusted=new ImageIcon(imageResize);
        JLabel imgLabel=new JLabel(adjusted);
        imgLabel.setBounds(0,0,1100,600);
        add(imgLabel);
        m=new JMenuBar();
        m.setBounds(0,0,1580,30);
        m.setBackground(Color.BLACK);
        m.setForeground(Color.WHITE);
        imgLabel.add(m);
        //Check Menu 
        check=new JMenu("Check");
        profile=new JMenuItem("View Profile");
        admin_details=new JMenuItem("Employee Details");
        //JMenuItem add_admin=new JMenuItem("Add Admin");
        customer_details=new JMenuItem("Customer Details");
        tax=new JMenuItem("Tax");
        check.setForeground(Color.WHITE);
        check.setFont(new Font("Tahoma",Font.PLAIN,17));
        check.add(profile);
        check.add(admin_details);
        check.add(customer_details);
        check.add(tax);
        m.add(check);
        
        //Edit Menu
        edit=new JMenu("Edit");
        add_admin=new JMenuItem("Add Admin");
        edit_profile=new JMenuItem("Edit Profile");
        update_cust_details=new JMenuItem("Update Customer Details");
//      update_tax_values=new JMenuItem("Update Tax Values");
        tax.addActionListener(this);
        edit.add(add_admin);
        edit.add(edit_profile);
        edit.add(update_cust_details);
        edit.setFont(new Font("Tahoma",Font.PLAIN,17));
        edit.setForeground(Color.WHITE);
        m.add(edit);
        changePswd=new JMenuItem("Change Password");
        edit.add(changePswd);
        changePswd.addActionListener(this);
        
//      jm2.add();

        //Bill Menu
        bill=new JMenu("Bill");
        add_bill=new JMenuItem("Create a Bill");
        bill_details=new JMenuItem("View Bill Details");
        bill_details.addActionListener(this);
        update_bill_details=new JMenuItem("Pay Bill");
        bill.add(add_bill);
        bill.add(bill_details);
        bill.add(update_bill_details);
        m.add(bill);
        bill.setForeground(Color.white);
        bill.setFont(new Font("Tahoma",Font.PLAIN,17));
        
        //Connection Menu
        connection=new JMenu("Connection");
        JMenuItem add_conn=new JMenuItem("Add a Connection");
        JMenuItem update_conn_details=new JMenuItem("Update Connection Details");
        update_conn_details=new JMenuItem("Update Connection Details");
        update_conn_details.addActionListener(this);
        view_conn_request=new JMenuItem("View Connection Requests");
        JMenuItem view_connections=new JMenuItem("View Connection Details");
        connection.add(add_conn);
        connection.add(view_connections);
        connection.add(update_conn_details);
        connection.add(this.view_conn_request);
        
        m.add(connection);
        connection.setForeground(Color.white);
        connection.setFont(new Font("Tahoma",Font.PLAIN,17));
        check.addActionListener(this);
        edit.addActionListener(this);
        connection.addActionListener(this);
        this.view_conn_request.addActionListener(this);
        
        bill.addActionListener(this);
        setBounds(10,0,1500,800);
        getContentPane().setBackground(Color.white);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel top=new JLabel("MSEDCL Team Welcomes You!");
        top.setBounds(600,40,400,20);
        top.setFont(new Font("sans-serif",Font.BOLD,21));
        top.setForeground(Color.GRAY);
        imgLabel.add(top);
        admin_details.addActionListener(this);
        customer_details.addActionListener(this);
        tax.addActionListener(this);
        profile.addActionListener(this);
        add_admin.addActionListener(this);
        edit_profile.addActionListener(this);
        update_cust_details.addActionListener(this);
//        update_tax_values.addActionListener(this);
        add_bill.addActionListener(this);
        update_bill_details.addActionListener(this);
        add_conn.addActionListener(this);
        view_connections.addActionListener(this);
        
        //logout
        logout=new JButton("Logout");
        m.add(logout);
        logout.setFont(new Font("sans-serif",Font.PLAIN,21));
        logout.setForeground(Color.white);
        logout.setBackground(Color.black);
//        imgLabel.add(top);
        logout.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ace)
    {
        
        String menuItem=ace.getActionCommand();
        if(menuItem.equals("View Profile"))
        {
            new ViewProfile(username,password);
        }
        else if(menuItem.equals("Employee Details"))
        {
            new EmployeeDetails(username,password);
        }
        else if(menuItem.equals("Customer Details"))
        {
            new CustomerDetails();
        }
        else if(menuItem.equals("Tax"))
        {
            new ViewTax();
        }
        else if(menuItem.equals("Add Admin"))
        {
            new AddAdmin();
        }
        else if(menuItem.equals("Edit Profile"))
        {
            new EditProfile(username,password);
        }
        else if(menuItem.equals("Update Customer Details"))
        {
            new UpdateCustomerDetails();
        }
        else if(menuItem.equals("Create a Bill"))
        {
            new CreateBill();
        }
        else if(menuItem.equals("Pay Bill"))
        {
            new PayBill();
        }
        else if(menuItem.equals("View Bill Details"))
        {
            new ViewBillDetails();
        }
        else if(menuItem.equals("Add a Connection"))
        {
            new AddCustomer();
        }
        else if(menuItem.equals("View Connection Details"))
        {
            new ViewConnectionDetails();
        }
        else if(menuItem.equals("Update Connection Details"))
        {
            new UpdateConnectionDetails();
        }
        else if(menuItem.equals("Change Password"))
        {
           new ChangePswd(username,password,this);
        }
        else if(menuItem.equals("View Connection Requests"))
        {
           new ViewConnectionRequest();
        }
        else if(ace.getSource()==logout)
        {
            System.exit(0);
//            new Homepage();
        }
    }
    public static void main(String args[])
    {
        new Adminpage("ritik@gmail.com","ritik");
    }
}
