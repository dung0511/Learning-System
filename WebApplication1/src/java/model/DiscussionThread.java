/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author acer
 */
public class DiscussionThread {
    private ClassDiscussion cd;
    private List<DiscussionComment> dd;

    public DiscussionThread() {
    }

    public DiscussionThread(ClassDiscussion cd, List<DiscussionComment> dd) {
        this.cd = cd;
        this.dd = dd;
    }

    public ClassDiscussion getCd() {
        return cd;
    }

    public void setCd(ClassDiscussion cd) {
        this.cd = cd;
    }

    public List<DiscussionComment> getDd() {
        return dd;
    }

    public void setDd(List<DiscussionComment> dd) {
        this.dd = dd;
    }
    
    
}
