package model;

import java.sql.Timestamp;

/**
 *
 * @author quany
 */
public class ClassAssignment {
    
    private int asmID;
    private String asmName;
    private String asmDes;
    private Subject subject;
    private SubjectSetting subjectsetting;
    private Class clas;
    private Lesson lesson;
    private Timestamp dl;
    private Account createdBy;
    private Timestamp createdAt;
    private Account updatedBy;
    private Timestamp updatedAt;
    private int status;

    public ClassAssignment() {
    }
    
    

    public ClassAssignment(int asmID, String asmName, String asmDes, Subject subject, SubjectSetting subjectsetting, Class clas, Lesson lesson, Timestamp dl, Account createdBy, Timestamp createdAt, Account updatedBy, Timestamp updatedAt, int status) {
        this.asmID = asmID;
        this.asmName = asmName;
        this.asmDes = asmDes;
        this.subject = subject;
        this.subjectsetting = subjectsetting;
        this.clas = clas;
        this.lesson = lesson;
        this.dl = dl;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public ClassAssignment(String asmName, String asmDes, Subject subject, SubjectSetting subjectsetting, Class clas, Lesson lesson, Account createdBy, Timestamp createdAt, int status) {
        this.asmName = asmName;
        this.asmDes = asmDes;
        this.subject = subject;
        this.subjectsetting = subjectsetting;
        this.clas = clas;
        this.lesson = lesson;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.status = status;
    }

    public ClassAssignment(String asmName, String asmDes, Subject subject, SubjectSetting subjectsetting, Class clas, Lesson lesson, Timestamp dl, Account createdBy, Timestamp createdAt, int status) {
        this.asmName = asmName;
        this.asmDes = asmDes;
        this.subject = subject;
        this.subjectsetting = subjectsetting;
        this.clas = clas;
        this.lesson = lesson;
        this.dl = dl;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.status = status;
    }

    public ClassAssignment(String asmName, String asmDes, Subject subject, SubjectSetting subjectsetting, Class clas, Timestamp dl, Account createdBy, Timestamp createdAt, int status) {
        this.asmName = asmName;
        this.asmDes = asmDes;
        this.subject = subject;
        this.subjectsetting = subjectsetting;
        this.clas = clas;
        this.dl = dl;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.status = status;
    }

    public ClassAssignment(int asmID, String asmName, String asmDes, Subject subject, SubjectSetting subjectsetting, Class clas, Timestamp dl, int status) {
        this.asmID = asmID;
        this.asmName = asmName;
        this.asmDes = asmDes;
        this.subject = subject;
        this.subjectsetting = subjectsetting;
        this.clas = clas;
        this.dl = dl;
        this.status = status;
    }











    
    
    

    public int getAsmID() {
        return asmID;
    }

    public void setAsmID(int asmID) {
        this.asmID = asmID;
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

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SubjectSetting getSubjectsetting() {
        return subjectsetting;
    }

    public void setSubjectsetting(SubjectSetting subjectsetting) {
        this.subjectsetting = subjectsetting;
    }

    public Class getClas() {
        return clas;
    }

    public void setClas(Class clas) {
        this.clas = clas;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassAssignment{" + "asmID=" + asmID + ", asmName=" + asmName + ", asmDes=" + asmDes + ", subject=" + subject + ", subjectsetting=" + subjectsetting + ", clas=" + clas + ", lesson=" + lesson + ", dl=" + dl + ", createdBy=" + createdBy + ", createdAt=" + createdAt + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + ", status=" + status + '}';
    }

    
  

  
}
