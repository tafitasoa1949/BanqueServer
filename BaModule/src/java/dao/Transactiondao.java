/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Banque;
import dto.Compte;
import dto.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class Transactiondao {
    public static float convertAr(float montant,float coursAr){
        return montant * coursAr;
    }
    public static void insertTransaction(Connection con,Transaction transaction)throws Exception{
        String sql = "INSERT INTO transaction VALUES(?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, transaction.getNumerobancairedepart());
            stmt.setInt(2, transaction.getNumerobancairedestinateur());
            stmt.setInt(3, transaction.getCode());
            stmt.setFloat(4, transaction.getMontant());
            stmt.setDate(5, transaction.getDate_transaction());
            stmt.executeUpdate(); 
        } catch (Exception e) {
            throw new Exception("Erreur : " + e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
    }
    public static void addSolde(Connection con,int numerobancaire,float newVola,float montantVente,int ar)throws Exception{
        String sql = " update compte set solde=? where numerobancaire=?";
        float soldeApres = 0;
        float solde_avant = 0;
        float volaVente = 0;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            solde_avant = Comptedao.getVola(con, numerobancaire);
            if(ar == 1){
                //volaVente = newVola * montantVente;
                soldeApres = solde_avant + montantVente;
            }else{
                soldeApres = solde_avant + newVola;
            } 
            stmt.setFloat(1, soldeApres);
            stmt.setInt(2, numerobancaire);
            stmt.executeUpdate(); 
        } catch (Exception e) {
            throw new Exception("Erreur : " + e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
    }
    public static void diminueSolde(Connection con,int numerobancaire,float vola)throws Exception{
        String sql = " update compte set solde=? where numerobancaire=?";
        float soldeApres = 0;
        float solde_avant = 0;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            solde_avant = Comptedao.getVola(con, numerobancaire);
            soldeApres = solde_avant - vola;
            stmt.setFloat(1, soldeApres);
            stmt.setInt(2, numerobancaire);
            stmt.executeUpdate(); 
        } catch (Exception e) {
            throw new Exception("Erreur : " + e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
    }
    public static void FaireTransaction(Connection con,Transaction transaction)throws Exception{
        try {
            if(Comptedao.checkSolde(con, transaction.getNumerobancairedepart(),transaction.getMontant())){
                con.setAutoCommit(false);
                insertTransaction(con, transaction);
                diminueSolde(con, transaction.getNumerobancairedepart(), transaction.getMontant());
                addSolde(con, transaction.getNumerobancairedestinateur(), transaction.getMontant(),transaction.getVolaVente(),transaction.getAr());
                con.commit();
                //return "vita";
            }else{
                con.rollback();
                throw new Exception("Solde insuffisant");      
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public static List<Transaction> getListTransaction(Connection con,int numerobancaire)throws Exception{
        List<Transaction> list = new ArrayList<>();
        PreparedStatement stmt = null;
        String sql = "select t.* from transaction as t join compte as c on " + 
                "c.numerobancaire=t.numerobancairedepart or c.numerobancaire=t.numerobancairedestinateur where numerobancaire=?";
        try {
            con = Connexion.getconnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, numerobancaire);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Transaction transaction = new Transaction();
                transaction.setNumerobancairedepart(rs.getInt("numerobancairedepart"));
                transaction.setNumerobancairedestinateur(rs.getInt("numerobancairedestinateur"));
                transaction.setCode(rs.getInt("code"));
                transaction.setMontant(rs.getFloat("montant"));
                transaction.setDate_transaction(rs.getDate("date_transaction"));
                list.add(transaction);
            }
        } catch (Exception e) {
            throw new Exception("Erreur : " + e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
        return list;
    }
}
