/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author quany
 */
public class Question {

    private int quesID;
    private Subject subject;
    private SubjectSetting subjectsetting;
    private String topic;
    private int displayOrder;
    private Account createdBy;
    private Timestamp createdAt;
    private Account updatedBy;
    private Timestamp updatedAt;
    private int status;

    public Question() {
    }

    public Question(int quesID, Subject subject, SubjectSetting subjectsetting, String topic, int displayOrder, Account createdBy, Timestamp createdAt, Account updatedBy, Timestamp updatedAt, int status) {
        this.quesID = quesID;
        this.subject = subject;
        this.subjectsetting = subjectsetting;
        this.topic = topic;
        this.displayOrder = displayOrder;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Question(Subject subject, SubjectSetting subjectsetting, String topic, int displayOrder, Account createdBy, Timestamp createdAt, int status) {
        this.subject = subject;
        this.subjectsetting = subjectsetting;
        this.topic = topic;
        this.displayOrder = displayOrder;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.status = status;
    }
    
    

    public int getQuesID() {
        return quesID;
    }

    public void setQuesID(int quesID) {
        this.quesID = quesID;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SubjectSetting getSubjectsetting() {
        return subjectsetting;
    }

    public void setSubjectsetting(SubjectSetting subjectsetting) {
        this.subjectsetting = subjectsetting;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Account getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Account updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Question{" + "quesID=" + quesID + ", subject=" + subject + ", subjectsetting=" + subjectsetting + ", topic=" + topic + ", displayOrder=" + displayOrder + ", createdBy=" + createdBy + ", createdAt=" + createdAt + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + ", status=" + status + '}';
    }


    

}
