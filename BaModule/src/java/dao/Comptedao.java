/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Banque;
import dto.Compte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class Comptedao {
    public static boolean checkSolde(Connection con,int numerobancaire,float volaAlaina)throws Exception{
        boolean result = false;
        float vola = 0;
        float boby = 0;
        PreparedStatement stmt = null;
        String sql =" select solde from compte where numerobancaire=?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, numerobancaire);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                boby = rs.getFloat("solde");
            }
            vola = boby - volaAlaina;
            if(vola >= 0){
                result = true;
            }
        } catch (Exception e) {
            throw new Exception("Erreur : " + e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
        return result;
    }
    public static float getVola(Connection con,int numerobancaire)throws Exception{
        float solde = 0;
        PreparedStatement stmt = null;
        String sql = "SELECT solde FROM compte WHERE numerobancaire = ?";
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, numerobancaire);
            rs = stmt.executeQuery();
            if (rs.next()) {
                solde = rs.getFloat("solde");
            }
        } catch (Exception e) {
            throw new Exception("Erreur : " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return solde;
    }
    public static List<Compte> getListCompte(Connection con,int cin)throws Exception{
        List<Compte> list = new ArrayList<>();
        PreparedStatement stmt = null;
        String sql = " select * from compte where cin=?";
        try {
            con = Connexion.getconnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cin);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Compte compte = new Compte();
                compte.setNumerobancaire(rs.getInt("numerobancaire"));
                compte.setCin(cin);
                String idBanque = rs.getString("idbanque");
                Banque banque = new Banquedao().getById(con,idBanque);
                compte.setBanque(banque);
                compte.setSolde(rs.getFloat("solde"));
                list.add(compte);
            }
        } catch (Exception e) {
            throw new Exception("Erreur : " + e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
        return list;
    }
    public static boolean checkCompte(Connection con,int numerobancaire)throws Exception{
        boolean result = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM compte WHERE numerobancaire = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, numerobancaire);
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            throw new Exception("Erreur : " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return result;
    }
}
