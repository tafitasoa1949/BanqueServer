/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class Compte implements Serializable{
    int numerobancaire;
    int cin;
    Banque banque;
    float solde;

    public int getNumerobancaire() {
        return numerobancaire;
    }

    public void setNumerobancaire(int numerobancaire) {
        this.numerobancaire = numerobancaire;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public Compte(int numerobancaire, int cin, Banque banque, float solde) {
        this.numerobancaire = numerobancaire;
        this.cin = cin;
        this.banque = banque;
        this.solde = solde;
    }

    public Compte() {
    }
}
