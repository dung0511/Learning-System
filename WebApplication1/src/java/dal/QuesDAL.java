package dal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Chapter;
import model.Question;
import model.Subject;
import model.SubjectSetting;

/**
 *
 * @author quany
 */
public class QuesDAL extends DBContext {

    public ArrayList<Question> getAll() {
        AccountDAL ad = new AccountDAL();
        SubjectDAL subjectDAL = new SubjectDAL();
        SubjectSettingDAL subjectsettingDAL = new SubjectSettingDAL();
        try {
            String sql = "SELECT * FROM question";
            ArrayList<Question> list;
            try ( PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    Question question = new Question();
                    question.setQuesID(rs.getInt("question_id"));

                    Subject s;
                    s = subjectDAL.getSubjectByID(rs.getInt("subject_id"));
                    question.setSubject(s);

                    SubjectSetting ss;
                    ss = subjectsettingDAL.getAllSettingBySettingID(rs.getInt("chapter_id"));
                    question.setSubjectsetting(ss);

                    question.setTopic(rs.getString("topic"));
                    question.setDisplayOrder(rs.getInt("display_order"));
                    question.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                    question.setCreatedAt(rs.getTimestamp("created_at"));
                    question.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                    question.setUpdatedAt(rs.getTimestamp("updated_at"));
                    question.setStatus(rs.getInt("status"));
                    list.add(question);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(QuesDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Question getQuesByID(int id) {
        try {
            AccountDAL ad = new AccountDAL();
            SubjectDAL subjectDAL = new SubjectDAL();
            SubjectSettingDAL subjectsettingDAL = new SubjectSettingDAL();
            String sql = "SELECT * FROM question WHERE question_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Question q = new Question();

                Subject s;
                s = subjectDAL.getSubjectByID(rs.getInt("subject_id"));
                q.setSubject(s);

                SubjectSetting ss;
                ss = subjectsettingDAL.getAllSettingBySettingID(rs.getInt("chapter_id"));
                q.setSubjectsetting(ss);

                q.setTopic(rs.getString("topic"));
                q.setDisplayOrder(rs.getInt("display_order"));
                q.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                q.setCreatedAt(rs.getTimestamp("created_at"));
                q.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                q.setUpdatedAt(rs.getTimestamp("updated_at"));
                q.setStatus(rs.getInt("status"));
                return q;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<SubjectSetting> getAllSettingBySettingID(int id) {
        ArrayList<SubjectSetting> list = new ArrayList<>();
        SubjectDAL subjectDAL = new SubjectDAL();
        try {
            String sql = "SELECT * FROM subject_setting where subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println("Lap ");
                SubjectSetting ss = new SubjectSetting();
                ss.setSettingID(rs.getInt("setting_id"));

                Subject s;
                s = subjectDAL.getSubjectByID(rs.getInt("subject_id"));
                ss.setS(s);

                ss.setSettingName(rs.getString("setting_name"));
                ss.setDisplayOrder(rs.getInt("display_order"));
                ss.setDescription(rs.getString("description"));
                ss.setSettingType(rs.getString("setting_type"));
                list.add(ss);

            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
        public ArrayList<Question> getByPageSize(int page, int size) {
        AccountDAL ad = new AccountDAL();
        SubjectDAL subjectDAL = new SubjectDAL();
        SubjectSettingDAL subjectsettingDAL = new SubjectSettingDAL();
        try {
            page -= 1;
            String sql = "SELECT * FROM question LIMIT ? OFFSET ?";
            ArrayList<Question> list;
            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, size);
                ps.setInt(2, page * size);

                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();

                while (rs.next()) {
                    Question question = new Question();
                    question.setQuesID(rs.getInt("question_id"));

                    Subject s;
                    s = subjectDAL.getSubjectByID(rs.getInt("subject_id"));
                    question.setSubject(s);

                    SubjectSetting ss;
                    ss = subjectsettingDAL.getAllSettingBySettingID(rs.getInt("chapter_id"));
                    question.setSubjectsetting(ss);

                    question.setTopic(rs.getString("topic"));
                    question.setDisplayOrder(rs.getInt("display_order"));
                    question.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                    question.setCreatedAt(rs.getTimestamp("created_at"));
                    question.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                    question.setUpdatedAt(rs.getTimestamp("updated_at"));
                    question.setStatus(rs.getInt("status"));
                    list.add(question);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ClassAssignmentDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public void addQues(int subject, int subjectsetting, String topic, int displayOrder, int createdBy, Timestamp createdAt, int status ) {
//        try {
//            String sql = "INSERT INTO question (subject_id, chapter_id, topic, display_order, created_by, created_at, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement ps = DBContext().prepareStatement(sql);
//            ps.setInt(1, subject);
//            ps.setInt(2, subjectsetting);
//            ps.setString(3, topic);
//            ps.setInt(4, displayOrder);
//            ps.setInt(5, createdBy);
//            ps.setTimestamp(6, createdAt);
//            ps.setInt(7, status);
//            ps.execute();
//            ps.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(QuesDAL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public int addQues(int subject, int subjectsetting, String topic, int displayOrder, int createdBy, Timestamp createdAt, int status) {
        int quesID = -1; // Khởi tạo một giá trị mặc định, có thể là -1 hoặc giá trị mặc định khác

        try {
            String sql = "INSERT INTO question (subject_id, chapter_id, topic, display_order, created_by, created_at, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBContext().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, subject);
            ps.setInt(2, subjectsetting);
            ps.setString(3, topic);
            ps.setInt(4, displayOrder);
            ps.setInt(5, createdBy);
            ps.setTimestamp(6, createdAt);
            ps.setInt(7, status);
            ps.execute();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                quesID = generatedKeys.getInt(1); // Lấy ID được sinh tự động
            }

            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuesDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return quesID; // Trả về ID của câu hỏi sau khi thêm vào cơ sở dữ liệu
    }

    public void delete(int quesID) {
        try {
            String sql = "DELETE FROM question WHERE question_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quesID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuesDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


//    public void update(Question question) {
//        try {
//            String sql = "UPDATE question SET topic = ?, answer = ?, display_order = ?, status = ? WHERE question_id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, question.getTopic());
//            ps.setString(2, question.getAnswer());
//            ps.setInt(3, question.getDisplayOrder());
//            ps.setInt(4, question.getStatus());
//            ps.setInt(5, question.getQuesID());
//            ps.execute();
//        } catch (SQLException ex) {
//            Logger.getLogger(QuesDAL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
