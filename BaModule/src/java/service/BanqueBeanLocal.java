/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package service;
import dto.Banque;
import dto.Compte;
import dto.Transaction;
import java.sql.Connection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tafitasoa-P15B-140
 */
@Local
public interface BanqueBeanLocal {
    public String test();
    public List<Compte> getLiscompte(Connection con,int cin)throws Exception;
    public Banque getBanque(Connection con,String idbanque)throws Exception;
    public float convrertionAr(float montant,float coursAr);
    public void checkCompteBancaire(Connection con,int numeroBancaire)throws Exception;
    public void faireTransaction(Connection con,Transaction transaction)throws Exception;
    public List<Transaction> getListTransactions(Connection con,int numerobancaire)throws Exception;
}
