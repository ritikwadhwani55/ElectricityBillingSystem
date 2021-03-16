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
import java.sql.*;
public class Conn {
    Statement s;
    Connection c;
    public Conn()
    {
        try {
            
        Class.forName("com.mysql.cj.jdbc.Driver");
        c=DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_system","root","");
        s=c.createStatement();
         }catch(Exception ex){
            
         }
     }
}
