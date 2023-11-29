/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author acer
 */
public class Notification {
    private int ID;
    private Account from;
    private Account to;
    private String type;
    private Timestamp date;
    private ClassDiscussion cd;
    private DiscussionComment dc;
    private int page;
    private int classID;

    public Notification() {
    }

    public Notification(int ID, Account from, Account to, String type, Timestamp date, ClassDiscussion cd, DiscussionComment dc) {
        this.ID = ID;
        this.from = from;
        this.to = to;
        this.type = type;
        this.date = date;
        this.cd = cd;
        this.dc = dc;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }
    
    
}
