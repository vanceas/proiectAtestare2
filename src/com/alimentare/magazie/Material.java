package com.alimentare.magazie;

import java.sql.SQLException;
import java.sql.Statement;

public class Material {
    int nr;
    String Material;
    String Cumparator;
    int nrColi;

    public Material(int nr, String Material, String Cumparator, int nrColi) {
        this.nr = nr;
        this.Material = Material;
        this.Cumparator = Cumparator;
        this.nrColi = nrColi;
    }
    public void saveMaterial(Statement myStmt){
        String insertDb = "insert into depozit"
                + "(nr,Material,Cumparator,nrColi)"
                + "values ("
                + this.nr + ", '" + this.Material + "','" +
                this.Cumparator + "'," + this.nrColi + ")";
        try {
            myStmt.executeUpdate(insertDb);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
