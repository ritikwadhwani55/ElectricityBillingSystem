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
public class Homepage extends JFrame implements ActionListener{
    public Homepage()
    {
        super("Electricity Billing System");
        setBounds(320,80,900,600);
        setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("electricitybillingsystem/images/electricity.jpg"));
        Image i1=img.getImage().getScaledInstance(900,600,Image.SCALE_DEFAULT);
        ImageIcon resize=new ImageIcon(i1);
        
        JLabel l=new JLabel(resize);
        l.setBounds(0,0,900,600);
        add(l);
        JLabel d=new JLabel("Maharashtra State Electricity Distribution Co. Ltd.");
        
        d.setFont(new Font("sans-serif",Font.BOLD,20));
        d.setForeground(Color.WHITE);
        d.setBounds(370,250,490,35);
        l.add(d);
        d.setVisible(false);
        JButton b1=new JButton("Next");
        b1.setBounds(520,350,100,35);
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);
        add(b1);
        b1.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        while(true)
        {
            d.setVisible(true);
            try
            {
                Thread.sleep(1000);
                
            }
            catch(InterruptedException ie){}
            d.setVisible(false);
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ae){}
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.setVisible(false);
        new Login();
    }
    public static void main(String args[])
    {
        new Homepage();
    }
}
