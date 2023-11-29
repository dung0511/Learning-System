/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ClassDiscussion;
import model.DiscussionComment;
import model.Notification;

/**
 *
 * @author acer
 */
public class NotificationDAL extends DBContext {

    public List<Notification> getAllNotification(int accID) {
        List<Notification> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        DiscussionDAL dd = new DiscussionDAL();
        try {
            String sql = "SELECT * FROM notification";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Notification n = new Notification();
                n.setCd(dd.getDiscussionByID(rs.getInt("discussion_id"), accID));
                n.setDate(rs.getTimestamp("date"));
                n.setDc(dd.getDCByID(rs.getInt("comment_id"), accID));
                n.setFrom(ad.getAccountByAccID(rs.getInt("from_id")));
                n.setID(rs.getInt("id"));
                n.setTo(ad.getAccountByAccID(rs.getInt("to_id")));
                n.setType(rs.getString("type"));
                n.setPage(rs.getInt("page"));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Notification> getTop5NotiByDate(int accID) {
        List<Notification> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        DiscussionDAL dd = new DiscussionDAL();
        try {
            String sql = "SELECT * FROM notification \n "
                    + "WHERE to_id = ? "
                    + "ORDER BY date ASC LIMIT 5";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, accID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Notification n = new Notification();
                n.setCd(dd.getDiscussionByID(rs.getInt("discussion_id"), accID));
                n.setDate(rs.getTimestamp("date"));
                n.setDc(dd.getDCByID(rs.getInt("comment_id"), accID));
                n.setFrom(ad.getAccountByAccID(rs.getInt("from_id")));
                n.setID(rs.getInt("id"));
                n.setTo(ad.getAccountByAccID(rs.getInt("to_id")));
                n.setType(rs.getString("type"));
                n.setPage(dd.getCommentPage(dd.getAllDiscussionComByDID(n.getCd().getDiscussionID(), accID)));
                n.setClassID(dd.getDiscussionByID(rs.getInt("discussion_id"), accID).getCls().getClassID());
                n.setPage(rs.getInt("page"));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertN(Notification n) {
        try {
            String sql = "INSERT INTO notification "
                    + "(from_id, to_id, type, date, discussion_id, comment_id, page) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, n.getFrom().getID());
            st.setInt(2, n.getTo().getID());
            st.setString(3, n.getType());
            st.setTimestamp(4, n.getDate());
            st.setInt(5, n.getCd().getDiscussionID());
            st.setInt(6, n.getDc().getCommentID());
            st.setInt(7, n.getPage());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
