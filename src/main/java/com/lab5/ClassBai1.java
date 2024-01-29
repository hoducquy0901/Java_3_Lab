/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.lab5;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author acer
 */
public class ClassBai1 {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String connectionSql = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=STUDENTS";
    private static String acc = "sa";
    private static String pass = "123456";

    private static Connection conn;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("Lỗi Driver");
        }
    }
    
   
    public static void main(String[] args) {
        try{
            conn = DriverManager.getConnection(connectionSql,acc,pass);
            String sql = "Select * from STUDENTS";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                System.out.print(rs.getString("MaSV")+", ");
                System.out.print(rs.getString("HoTen")+", ");
                System.out.print(rs.getString("Email")+", ");
                System.out.print(rs.getString("SoDT")+", ");
                System.out.println(rs.getString("GioiTinh"));
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
