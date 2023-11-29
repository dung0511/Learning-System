/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.ClassDiscussion;
import model.DiscussionComment;
import model.DiscussionThread;
import util.FormatDate;

/**
 *
 * @author acer
 */
public class DiscussionDAL extends DBContext {

    public int addComment(DiscussionComment dc) {
        int id = 0;
        try {
            String sql = "INSERT INTO discussion_comment "
                    + "(discussion_id, account_id, comment, comment_date, noVote) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, dc.getCd().getDiscussionID());
            st.setInt(2, dc.getAccount().getID());
            st.setString(3, dc.getComment());
            st.setTimestamp(4, dc.getDate());
            st.setInt(5, dc.getNoVote());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public int addDiscussion(ClassDiscussion cd) {
        int id = 0;
        try {
            String sql = "INSERT INTO class_discussion "
                    + "(account_id, class_id, discussion_topic, discussion_content, discussion_date, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, cd.getAccount().getID());
            st.setInt(2, cd.getCls().getClassID());
            st.setString(3, cd.getDiscussionTopic());
            st.setString(4, cd.getDiscussionContent());
            st.setTimestamp(5, cd.getDiscussionDate());
            st.setBoolean(6, cd.isStatus());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public void updateComment(DiscussionComment dc) {
        try {
            String sql = "UPDATE discussion_comment SET "
                    + "discussion_id = ?, "
                    + "account_id = ?, "
                    + "comment = ?, "
                    + "reply = ?, "
                    + "comment_date = ?, "
                    + "noVote = ? "
                    + "WHERE comment_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, dc.getCd().getDiscussionID());
            st.setInt(2, dc.getAccount().getID());
            st.setString(3, dc.getComment());
            if (dc.getReply() != null) {
                st.setInt(4, dc.getReply().getID());
            } else {
                st.setNull(4, Types.INTEGER);
            }
            st.setTimestamp(5, dc.getDate());
            st.setInt(6, dc.getNoVote());
            st.setInt(7, dc.getCommentID());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateDiscussion(ClassDiscussion cd) {
        try {
            String sql = "UPDATE class_discussion SET "
                    + "account_id = ?, "
                    + "class_id = ?, "
                    + "discussion_topic = ?, "
                    + "discussion_content = ?, "
                    + "discussion_date = ?, "
                    + "status = ?, "
                    + "noVote = ? "
                    + "WHERE discussion_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, cd.getAccount().getID());
            st.setInt(2, cd.getCls().getClassID());
            st.setString(3, cd.getDiscussionTopic());
            st.setString(4, cd.getDiscussionContent());
            st.setTimestamp(5, cd.getDiscussionDate());
            st.setBoolean(6, cd.isStatus());
            st.setInt(7, cd.getNoVote());
            st.setInt(8, cd.getDiscussionID());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<ClassDiscussion> getAllDiscussionByClass(int classID) {
        ClassDAL cd = new ClassDAL();
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();
        List<ClassDiscussion> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM class_discussion \n"
                    + "					JOIN\n"
                    + "                    (SELECT class_discussion.discussion_id, COUNT(discussion_comment.discussion_id) AS noC FROM class_discussion \n"
                    + "					LEFT JOIN discussion_comment \n"
                    + "                    ON class_discussion.discussion_id = discussion_comment.discussion_id\n"
                    + "                    GROUP BY class_discussion.discussion_id)\n"
                    + "                    AS C\n"
                    + "                    ON class_discussion.discussion_id = C.discussion_id"
                    + " WHERE class_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ClassDiscussion cds = new ClassDiscussion();
                cds.setAccount(ad.getAccountByAccID(rs.getInt("account_id")));
                cds.setCls(cd.getClassByID(rs.getInt("class_id")));
                cds.setDiscussionContent(rs.getString("discussion_content"));
                cds.setDiscussionDate(rs.getTimestamp("discussion_date"));
                cds.setDiscussionID(rs.getInt("discussion_id"));
                cds.setDiscussionTopic(rs.getString("discussion_topic"));
                cds.setNoVote(rs.getInt("noVote"));
                cds.setStatus(rs.getBoolean("status"));
                cds.setD(rs.getDate("discussion_date"));
                cds.setdS(fd.formatDateSQL(cds.getD()));
                cds.setNoCom(rs.getInt("noC"));
                list.add(cds);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ClassDiscussion getDiscussionByID(int discussionID, int accID) {
        ClassDAL cd = new ClassDAL();
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();
        try {
            String sql = "SELECT class_discussion.*, C.*, T1.account_id AS Acc FROM class_discussion \n"
                    + "LEFT JOIN\n"
                    + "					(SELECT account_vote.* FROM class_discussion \n"
                    + "					LEFT JOIN account_vote \n"
                    + "                    ON class_discussion.discussion_id = account_vote.discussion_id\n"
                    + "					WHERE account_vote.account_id = ?)\n"
                    + "                    AS T1 \n"
                    + "                    ON class_discussion.discussion_id = T1.discussion_id"
                    + "					JOIN\n"
                    + "                    (SELECT class_discussion.discussion_id, COUNT(discussion_comment.discussion_id) AS noC FROM class_discussion \n"
                    + "					LEFT JOIN discussion_comment \n"
                    + "                    ON class_discussion.discussion_id = discussion_comment.discussion_id\n"
                    + "                    GROUP BY class_discussion.discussion_id)\n"
                    + "                    AS C\n"
                    + "                    ON class_discussion.discussion_id = C.discussion_id"
                    + " WHERE class_discussion.discussion_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, accID);
            st.setInt(2, discussionID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ClassDiscussion cds = new ClassDiscussion();
                cds.setAccount(ad.getAccountByAccID(rs.getInt("account_id")));
                cds.setCls(cd.getClassByID(rs.getInt("class_id")));
                cds.setDiscussionContent(rs.getString("discussion_content"));
                cds.setDiscussionDate(rs.getTimestamp("discussion_date"));
                cds.setDiscussionID(rs.getInt("discussion_id"));
                cds.setDiscussionTopic(rs.getString("discussion_topic"));
                cds.setNoVote(rs.getInt("noVote"));
                cds.setStatus(rs.getBoolean("status"));
                cds.setD(rs.getDate("discussion_date"));
                cds.setdS(fd.formatDateSQL(cds.getD()));
                cds.setNoCom(rs.getInt("noC"));
                if (rs.getInt("Acc") == accID) {
                    cds.setOnV(true);
                } else {
                    cds.setOnV(false);
                }
                return cds;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ClassDiscussion getDiscussionByDate(Timestamp d) {
        ClassDAL cd = new ClassDAL();
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();
        try {
            String sql = "SELECT * FROM class_discussion WHERE discussion_date = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setTimestamp(1, d);
            System.out.println(sql + " " + d);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ClassDiscussion cds = new ClassDiscussion();
                cds.setAccount(ad.getAccountByAccID(rs.getInt("account_id")));
                cds.setCls(cd.getClassByID(rs.getInt("class_id")));
                cds.setDiscussionContent(rs.getString("discussion_content"));
                cds.setDiscussionDate(rs.getTimestamp("discussion_date"));
                cds.setDiscussionID(rs.getInt("discussion_id"));
                cds.setDiscussionTopic(rs.getString("discussion_topic"));
                cds.setNoVote(rs.getInt("noVote"));
                cds.setStatus(rs.getBoolean("status"));
                cds.setD(rs.getDate("discussion_date"));
                cds.setdS(fd.formatDateSQL(cds.getD()));
                return cds;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public DiscussionComment getDCByID(int commentID, int accID) {
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();
        try {
            String sql = "SELECT * FROM discussion_comment WHERE comment_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, commentID);
            System.out.println(sql + " " + commentID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                DiscussionComment dc = new DiscussionComment();
                dc.setAccount(ad.getAccountByAccID(rs.getInt("account_id")));
                dc.setCd(getDiscussionByID(rs.getInt("discussion_id"), accID));
                dc.setD(rs.getDate("comment_date"));
                dc.setComment(rs.getString("comment"));
                dc.setDate(rs.getTimestamp("comment_date"));
                dc.setNoVote(rs.getInt("noVote"));
                dc.setReply(ad.getAccountByAccID(rs.getInt("reply")));
                dc.setdS(fd.formatDateSQL(dc.getD()));
                dc.setCommentID(rs.getInt("comment_id"));
                return dc;
            }
        } catch (Exception e) {
            System.out.println("p" + e);
        }
        return null;
    }

    public List<DiscussionComment> getAllDiscussionComByDID(int discussionID, int accID) {
        List<DiscussionComment> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();
        try {
            String sql = "SELECT * FROM discussion_comment\n"
                    + "			LEFT JOIN\n"
                    + "			(SELECT account_vote.account_id AS rep, account_vote.comment_id AS k, account_vote.noVote AS sVote FROM account_vote \n"
                    + "			JOIN discussion_comment \n"
                    + "            ON account_vote.comment_id = discussion_comment.comment_id\n"
                    + "            WHERE account_vote.account_id = ?\n"
                    + "			) AS T\n"
                    + "            ON discussion_comment.comment_id = T.k"
                    + " WHERE discussion_id = ? "
                    + "ORDER BY comment_date";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, accID);
            st.setInt(2, discussionID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                DiscussionComment dc = new DiscussionComment();
                dc.setAccount(ad.getAccountByAccID(rs.getInt("account_id")));
                dc.setCd(getDiscussionByID(rs.getInt("discussion_id"), accID));
                dc.setComment(rs.getString("comment"));
                dc.setCommentID(rs.getInt("comment_id"));
                dc.setD(rs.getDate("comment_date"));
                dc.setDate(rs.getTimestamp("comment_date"));
                dc.setNoVote(rs.getInt("noVote"));
                dc.setReply(ad.getAccountByAccID(rs.getInt("reply")));
                dc.setdS(fd.formatDateSQL(dc.getD()));
                if (rs.getInt("rep") != 0) {
                    dc.setsVote(rs.getInt("sVote"));
                } else {
                    dc.setsVote(0);
                }
                list.add(dc);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ClassDiscussion> getAllDiscussionByClassASearch(int classID, int accID, String condition, String order) {
        ClassDAL cd = new ClassDAL();
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();
        List<ClassDiscussion> list = new ArrayList<>();
        try {
            String sql = "SELECT class_discussion.*, C.*, T1.account_id AS Acc FROM class_discussion \n"
                    + "LEFT JOIN\n"
                    + "					(SELECT account_vote.* FROM class_discussion \n"
                    + "					LEFT JOIN account_vote \n"
                    + "                    ON class_discussion.discussion_id = account_vote.discussion_id\n"
                    + "					WHERE account_vote.account_id = ? AND comment_id IS NULL)\n"
                    + "                    AS T1 \n"
                    + "                    ON class_discussion.discussion_id = T1.discussion_id"
                    + "					JOIN\n"
                    + "                    (SELECT class_discussion.discussion_id, COUNT(discussion_comment.discussion_id) AS noC FROM class_discussion \n"
                    + "					LEFT JOIN discussion_comment \n"
                    + "                    ON class_discussion.discussion_id = discussion_comment.discussion_id\n"
                    + "                    GROUP BY class_discussion.discussion_id)\n"
                    + "                    AS C\n"
                    + "                    ON class_discussion.discussion_id = C.discussion_id"
                    + " WHERE class_id = ? " + condition + order;
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, accID);
            st.setInt(2, classID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ClassDiscussion cds = new ClassDiscussion();
                cds.setAccount(ad.getAccountByAccID(rs.getInt("account_id")));
                cds.setCls(cd.getClassByID(rs.getInt("class_id")));
                cds.setDiscussionContent(rs.getString("discussion_content"));
                cds.setDiscussionDate(rs.getTimestamp("discussion_date"));
                cds.setDiscussionID(rs.getInt("discussion_id"));
                cds.setDiscussionTopic(rs.getString("discussion_topic"));
                cds.setNoVote(rs.getInt("noVote"));
                cds.setStatus(rs.getBoolean("status"));
                cds.setD(rs.getDate("discussion_date"));
                cds.setdS(fd.formatDateSQL(cds.getD()));
                cds.setNoCom(rs.getInt("noC"));
                if (rs.getInt("Acc") != accID) {
                    cds.setOnV(false);
                } else {
                    cds.setOnV(true);
                }
                list.add(cds);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<DiscussionComment> getDCByPage(List<DiscussionComment> list, int start, int end) {
        List<DiscussionComment> list1 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }

    public int getCommentPage(List<DiscussionComment> list) {
        int numPerPage = 3;
        int page = (list.size() % numPerPage == 0) ? (list.size() / numPerPage) : (list.size() / numPerPage + 1);
        return page;
    }

    public Date getSundayByMonday(Date d) {
        try {
            String sql = "SELECT\n"
                    + "  DATE_ADD(?, INTERVAL 6 DAY) AS sunday\n"
                    + "FROM\n"
                    + "  (\n"
                    + "    SELECT\n"
                    + "      ?\n"
                    + "  ) AS t";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setDate(1, d);
            st.setDate(2, d);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDate("sunday");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<ClassDiscussion> getCDByPage(List<ClassDiscussion> list, int start, int end) {
        List<ClassDiscussion> list1 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }
    
     public List<DiscussionThread> getDTByPage(List<DiscussionThread> list, int start, int end) {
        List<DiscussionThread> list1 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }
}
