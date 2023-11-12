/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Banque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class Banquedao {
     public static Banque getById(Connection con,String id)throws Exception{
        PreparedStatement stmt = null;
        String sql = "select * from banque where id=?";
        Banque banque = null;
        try {
            con = Connexion.getconnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                banque = new Banque();
                banque.setId(id);
                banque.setNom(rs.getString("nom"));
                banque.setEmail(rs.getString("email"));
                banque.setAddresse(rs.getString("addresse"));
            }
        } catch (Exception e) {
            throw new Exception("erreur"+e.getMessage());
        }finally{
            if (stmt != null) stmt.close();
        }
        return banque;
    }
}
