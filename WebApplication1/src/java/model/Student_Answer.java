/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Student_Answer {

    private int student_id, quiz_id, count, question_id;
    private String answer_option;

    public Student_Answer() {
    }

    public Student_Answer(int student_id, int quiz_id, int count, int question_id, String answer_option) {
        this.student_id = student_id;
        this.quiz_id = quiz_id;
        this.count = count;
        this.question_id = question_id;
        this.answer_option = answer_option;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getAnswer_option() {
        return answer_option;
    }

    public void setAnswer_option(String answer_option) {
        this.answer_option = answer_option;
    }

}
