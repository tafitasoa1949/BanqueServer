/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package service;

import dao.Banquedao;
import dao.Comptedao;
import dao.Transactiondao;
import dto.Banque;
import dto.Compte;
import dto.Transaction;
import java.sql.Connection;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Tafitasoa-P15B-140
 */
@Stateless
public class BanqueBean implements BanqueBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String test() {
        return "hello";
    }

    @Override
    public List<Compte> getLiscompte(Connection con,int cin) throws Exception {
        return Comptedao.getListCompte(con,cin);
    }

    @Override
    public Banque getBanque(Connection con,String idbanque) throws Exception {
        return Banquedao.getById(con,idbanque);
    }

    @Override
    public float convrertionAr(float montant, float coursAr) {
        return Transactiondao.convertAr(montant, coursAr);
    }

    @Override
    public void checkCompteBancaire(Connection con, int numeroBancaire) throws Exception {
        if(Comptedao.checkCompte(con, numeroBancaire)){

        }else{
            throw new Exception("Numero bancaire :"+numeroBancaire+" invalide");
        }
    }
    
    @Override
    public void faireTransaction(Connection con, Transaction transaction) throws Exception {
        try {
            Transactiondao.FaireTransaction(con, transaction);
        } catch (Exception e) {
            throw new Exception("solde insuffisant");
        }
    }

    @Override
    public List<Transaction> getListTransactions(Connection con, int numerobancaire) throws Exception {
        return Transactiondao.getListTransaction(con, numerobancaire);
    }

    
}
