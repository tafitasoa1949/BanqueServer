/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class Transaction implements Serializable{
    int numerobancairedepart;
    int numerobancairedestinateur;
    int code;
    float montant;
    Date date_transaction;
    float volaVente;
    int ar = 0;

    public int getNumerobancairedepart() {
        return numerobancairedepart;
    }

    public void setNumerobancairedepart(int numerobancairedepart) {
        this.numerobancairedepart = numerobancairedepart;
    }

    public int getNumerobancairedestinateur() {
        return numerobancairedestinateur;
    }

    public void setNumerobancairedestinateur(int numerobancairedestinateur) {
        this.numerobancairedestinateur = numerobancairedestinateur;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDate_transaction() {
        return date_transaction;
    }

    public void setDate_transaction(Date date_transaction) {
        this.date_transaction = date_transaction;
    }

    public float getVolaVente() {
        return volaVente;
    }

    public void setVolaVente(float volaVente) {
        this.volaVente = volaVente;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }
    
    public Transaction(int numerobancairedepart, int numerobancairedestinateur, float montant,int code,Date date,float volaVente,int ar) {
        this.numerobancairedepart = numerobancairedepart;
        this.numerobancairedestinateur = numerobancairedestinateur;
        this.montant = montant;
        this.code = code;
        this.date_transaction = date;
        this.volaVente = volaVente;
        this.ar = ar;
    }

    public Transaction() {
    }
    
}
