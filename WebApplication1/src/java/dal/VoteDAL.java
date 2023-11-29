/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import model.AccountVote;
import model.DiscussionComment;
import util.FormatDate;

/**
 *
 * @author acer
 */
public class VoteDAL extends DBContext{
    public void insertVoteDiscussion(int accID, int discussionID, int noVote, Timestamp date) {
        try {
            String sql = "INSERT INTO account_vote " +
                    "(account_id, discussion_id, noVote, date) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, accID);
            st.setInt(2, discussionID);
            st.setInt(3, noVote);
            st.setTimestamp(4, date);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertVoteComment(int accID, int discussionID, int commentID, int noVote, Timestamp date) {
        try {
            String sql = "INSERT INTO account_vote " +
                    "(account_id, discussion_id, comment_id, noVote, date) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, accID);
            st.setInt(2, discussionID);
            st.setInt(3, commentID);
            st.setInt(4, noVote);
            st.setTimestamp(5, date);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("d" + e);
        }
    }
    
    public AccountVote getVoteDiscussionByDiscussAAcc(int accID, int discussionID) {
        AccountDAL ad = new AccountDAL();
        DiscussionDAL dd = new DiscussionDAL();
        FormatDate fd = new FormatDate();
        try {
            try {
                String sql = "SELECT * FROM account_vote WHERE "
                        + "account_id = ? AND "
                        + "discussion_id = ? AND "
                        + "comment_id IS NULL";
                
                PreparedStatement st = DBContext().prepareStatement(sql);
                st.setInt(1, accID);
                st.setInt(2, discussionID);
                ResultSet rs = st.executeQuery();
                if(rs.next()) {
                    AccountVote av = new AccountVote();
                    av.setAccount(ad.getAccountByAccID(rs.getInt("account_id")));
                    av.setCd(dd.getDiscussionByID(discussionID, accID));
                    av.setDate(rs.getTimestamp("date"));
                    av.setDc(dd.getDCByID(rs.getInt("comment_id"), accID));
                    av.setDte(rs.getDate("date"));
                    av.setNoVote(rs.getInt("noVote"));
                    av.setSTT(rs.getInt("stt"));
                    av.setdS(fd.formatDateSQL(av.getDte()));
                    return av;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public AccountVote getVoteDiscussionByComAAcc(int accID, int commentID) {
        AccountDAL ad = new AccountDAL();
        DiscussionDAL dd = new DiscussionDAL();
        FormatDate fd = new FormatDate();
        try {
            try {
                String sql = "SELECT * FROM account_vote WHERE "
                        + "account_id = ? AND "
                        + "comment_id = ?";
                System.out.println(sql + " " + accID + " " + commentID);
                PreparedStatement st = DBContext().prepareStatement(sql);
                st.setInt(1, accID);
                st.setInt(2, commentID);
                ResultSet rs = st.executeQuery();
                if(rs.next()) {
                    DiscussionComment dc = dd.getDCByID(commentID, accID);
                    AccountVote av = new AccountVote();
                    av.setAccount(ad.getAccountByAccID(rs.getInt("account_id")));
                    av.setCd(dd.getDiscussionByID(dc.getCd().getDiscussionID(), accID));
                    av.setDate(rs.getTimestamp("date"));
                    av.setDc(dd.getDCByID(rs.getInt("comment_id"), accID));
                    av.setDte(rs.getDate("date"));
                    av.setNoVote(rs.getInt("noVote"));
                    av.setSTT(rs.getInt("stt"));
                    av.setdS(fd.formatDateSQL(av.getDte()));
                    return av;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void updateVoteComment(AccountVote av) {
        try {
            String sql = "UPDATE account_vote SET "
                    + "account_id = ?, "
                    + "discussion_id = ?, "
                    + "comment_id = ?, "
                    + "noVote = ?, "
                    + "date = ? "
                    + "WHERE stt = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, av.getAccount().getID());
            st.setInt(2, av.getCd().getDiscussionID());
            st.setInt(3, av.getDc().getCommentID());
            st.setInt(4, av.getNoVote());
            st.setTimestamp(5, av.getDate());
            st.setInt(6, av.getSTT());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void deleteVoteDiscussion(AccountVote av) {
        try {
            String sql = "DELETE FROM account_vote WHERE"
                    + " stt = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, av.getSTT());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
