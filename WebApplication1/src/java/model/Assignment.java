/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import model.Account;

import java.sql.Timestamp;

/**
 *
 * @author acer
 */
public class Assignment {
    private int asmID;
    private String asmName;
    private String asmDes;
    private Subject subject;
    private Chapter chapter;
    private Lesson lesson;
    private Timestamp dl;
    private Account createdBy;
    private Timestamp createdAt;
    private Account updatedBy;
    private Timestamp updatedAt;
    private int status;
    private int total_students;
    private int submitted_students;
    private int remaining_students;
    
    public int getTotal_students() {
        return total_students;
    }

    public void setTotal_students(int total_students) {
        this.total_students = total_students;
    }

    public int getSubmitted_students() {
        return submitted_students;
    }

    public void setSubmitted_students(int submitted_students) {
        this.submitted_students = submitted_students;
    }

    public int getRemaining_students() {
        return remaining_students;
    }

    public void setRemaining_students(int remaining_students) {
        this.remaining_students = remaining_students;
    }

    public Assignment() {
    }

    public int getAsmID() {
        return asmID;
    }

    public void setAsmID(int asmID) {
        this.asmID = asmID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    
    

    public String getAsmName() {
        return asmName;
    }

    public void setAsmName(String asmName) {
        this.asmName = asmName;
    }

    public String getAsmDes() {
        return asmDes;
    }

    public void setAsmDes(String asmDes) {
        this.asmDes = asmDes;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject s) {
        this.subject = s;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter c) {
        this.chapter = c;
    }

    public Timestamp getDl() {
        return dl;
    }

    public void setDl(Timestamp dl) {
        this.dl = dl;
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
    
    @Override
    public String toString() {
        return "Assignment{" + "asmID=" + asmID + ", asmName=" + asmName + ", asmDes=" + asmDes + ", subject=" + subject + ", chapter=" + chapter + ", lesson=" + lesson + ", dl=" + dl + ", createdBy=" + createdBy + ", createdAt=" + createdAt + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + ", status=" + status + ", total_students=" + total_students + ", submitted_students=" + submitted_students + ", remaining_students=" + remaining_students + '}';
    }

   
    
    
}
