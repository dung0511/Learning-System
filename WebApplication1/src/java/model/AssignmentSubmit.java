/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class AssignmentSubmit {
    private int submitID;
    private Account accID;
    private Account accName;
    private Account email;
    private String Status;
    private double Grade;
    private String FileURL;

    public AssignmentSubmit() {
    }

    public int getSubmitID() {
        return submitID;
    }

    public void setSubmitID(int submitID) {
        this.submitID = submitID;
    }

    public Account getAccID() {
        return accID;
    }

    public void setAccID(Account accID) {
        this.accID = accID;
    }

    public Account getAccName() {
        return accName;
    }

    public void setAccName(Account accName) {
        this.accName = accName;
    }

    public Account getEmail() {
        return email;
    }

    public void setEmail(Account email) {
        this.email = email;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public double getGrade() {
        return Grade;
    }

    public void setGrade(double Grade) {
        this.Grade = Grade;
    }

    public String getFileURL() {
        return FileURL;
    }

    public void setFileURL(String FileURL) {
        this.FileURL = FileURL;
    }

    @Override
    public String toString() {
        return "AssignmentSubmit{" + "submitID=" + submitID + ", accID=" + accID + ", accName=" + accName + ", email=" + email + ", Status=" + Status + ", Grade=" + Grade + ", FileURL=" + FileURL + '}';
    }
    
}
