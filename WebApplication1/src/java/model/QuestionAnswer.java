/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quany
 */
public class QuestionAnswer {

    private Question question;
    private String answerOption;
    private String answerContent;
    private int isKey;

    public QuestionAnswer() {
    }

    public QuestionAnswer(Question question, String answerOption, String answerContent, int isKey) {
        this.question = question;
        this.answerOption = answerOption;
        this.answerContent = answerContent;
        this.isKey = isKey;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public int getIsKey() {
        return isKey;
    }

    public void setIsKey(int isKey) {
        this.isKey = isKey;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" + "question=" + question + ", answerOption=" + answerOption + ", answerContent=" + answerContent + ", isKey=" + isKey + '}';
    }




    
}
