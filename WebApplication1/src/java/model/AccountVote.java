/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author acer
 */
public class AccountVote {
    private int STT;
    private Account account;
    private ClassDiscussion cd;
    private DiscussionComment dc;
    private int noVote;
    private Timestamp date;
    private Date dte;
    private String dS;

    public AccountVote() {
    }

    public AccountVote(int STT, Account account, ClassDiscussion cd, DiscussionComment dc, int noVote, Timestamp date, Date dte, String dS) {
        this.STT = STT;
        this.account = account;
        this.cd = cd;
        this.dc = dc;
        this.noVote = noVote;
        this.date = date;
        this.dte = dte;
        this.dS = dS;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ClassDiscussion getCd() {
        return cd;
    }

    public void setCd(ClassDiscussion cd) {
        this.cd = cd;
    }

    public DiscussionComment getDc() {
        return dc;
    }

    public void setDc(DiscussionComment dc) {
        this.dc = dc;
    }

    public int getNoVote() {
        return noVote;
    }

    public void setNoVote(int noVote) {
        this.noVote = noVote;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Date getDte() {
        return dte;
    }

    public void setDte(Date dte) {
        this.dte = dte;
    }

    public String getdS() {
        return dS;
    }

    public void setdS(String dS) {
        this.dS = dS;
    }
    
    
}
