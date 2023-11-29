/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Quiz_Question {
   private int question_id, subject_id, chapter_id ;
   private String topic;
   private int display_order; //từ bảng quiz_question
   private boolean status;
   private List<Question_Answer> answer;

    public Quiz_Question() {
    }

    public Quiz_Question(int question_id, int subject_id, int chapter_id, String topic, int display_order, boolean status, List<Question_Answer> answer) {
        this.question_id = question_id;
        this.subject_id = subject_id;
        this.chapter_id = chapter_id;
        this.topic = topic;
        this.display_order = display_order;
        this.status = status;
        this.answer = answer;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Question_Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Question_Answer> answer) {
        this.answer = answer;
    }
   
}
