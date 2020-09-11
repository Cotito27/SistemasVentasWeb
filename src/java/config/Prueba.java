/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ProDesk 2019
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
        try {
        String sql="select * from empleado";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("User"));
                System.out.println(rs.getString("Dni"));
            }
        } catch (Exception e) {
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
