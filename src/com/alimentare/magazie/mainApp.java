package com.alimentare.magazie;


import java.sql.*;

public class mainApp {
    static  int materialCount = 1;
    public static void main(String[] args) {
        String dbUrl = "jdbc:postgresql:depozit";
        String user = "postgres";
        String password = "cristal";

        try {
            Connection myConn = DriverManager.getConnection(dbUrl, user, password);
            Statement myStmt = myConn.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from depozit");
            while (myRs.next()) {
                materialCount++;
            }
            new workWindow(myStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
