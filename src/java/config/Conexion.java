/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ProDesk 2019
 */
public class Conexion {
    Connection con = null;
   int port = 3306;
String user="usermysql27";
String password="DatabaseCoti_27.022001";
String host="SG-bdventas-3073-master.servers.mongodirector.com";
String db="db_ventas";
String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false", host, port, db);

public Connection Conexion() throws SQLException {
    con = DriverManager.getConnection(url, user, password);
    return con;
}


}
