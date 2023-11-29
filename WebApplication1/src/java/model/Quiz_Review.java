/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Quiz_Review {

    private Quiz_Question question;
    private Question_Answer correctAnswer;
    private String studentAnswer;

    public Quiz_Review() {
    }

    public Quiz_Review(Quiz_Question question, String studentAnswer) {
        this.question = question;
        this.studentAnswer = studentAnswer;
    }

    public Quiz_Question getQuestion() {
        return question;
    }

    public void setQuestion(Quiz_Question question) {
        this.question = question;
    }

    public Question_Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Question_Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }
    
}
