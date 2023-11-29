/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Question_Answer {
    private int questionId;
    private String answer_option;
    private String answer_content;
    private boolean is_key;

    public Question_Answer() {
    }

    public Question_Answer(int questionId, String answer_option, String answer_content, boolean is_key) {
        this.questionId = questionId;
        this.answer_option = answer_option;
        this.answer_content = answer_content;
        this.is_key = is_key;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer_option() {
        return answer_option;
    }

    public void setAnswer_option(String answer_option) {
        this.answer_option = answer_option;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public boolean isIs_key() {
        return is_key;
    }

    public void setIs_key(boolean is_key) {
        this.is_key = is_key;
    }

    
}
