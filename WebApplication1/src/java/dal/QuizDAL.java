/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Chapter;
import model.GradeItem;
import model.Lesson;
import model.Quiz;
import model.Subject;
import model.Class;

/**
 *
 * @author acer
 */
public class QuizDAL extends DBContext {

    public Quiz getQuizByID(int quiz) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        AccountDAL ad = new AccountDAL();
        SubjectDAL sd = new SubjectDAL();
        try {
            String sql = "SELECT * FROM quiz WHERE quiz_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, quiz);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Quiz q = new Quiz();
                q.setChapter(ssd.getChapterByID(rs.getInt("chapter_id")));
                q.setCreatedAt((rs.getTimestamp("created_at")));
                q.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                q.setUpdatedAt((rs.getTimestamp("updated_at")));
                q.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                q.setDisplayOrder(rs.getInt("display_order"));
                q.setNoQ(rs.getInt("noQ"));
                q.setQuizID(rs.getInt("quiz_id"));
                q.setQuizName(rs.getString("quiz_name"));
                q.setTimeLimit(rs.getDouble("time_limit"));
                q.setDescription(rs.getString("description"));
                q.setType(rs.getString("quiz_type"));
                Subject subject = sd.getSubjectByID(rs.getInt("subject_id"));
                q.setSubject(subject);
                return q;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Quiz getQuizByLessonID(int lesson) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT quiz.* FROM lesson JOIN quiz ON lesson.quiz_id = quiz.quiz_id WHERE lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, lesson);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Quiz q = new Quiz();
                q.setChapter(ssd.getChapterByID(rs.getInt("chapter_id")));
                q.setCreatedAt((rs.getTimestamp("created_at")));
                q.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                q.setUpdatedAt((rs.getTimestamp("updated_at")));
                q.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                q.setDisplayOrder(rs.getInt("display_order"));
                q.setNoQ(rs.getInt("noQ"));
                q.setQuizID(rs.getInt("quiz_id"));
                q.setQuizName(rs.getString("quiz_name"));
                q.setTimeLimit(rs.getDouble("time_limit"));
                q.setDescription(rs.getString("description"));
                return q;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<GradeItem> getQuizGradeByAccAQuiz(int accID, int quizID) {
        List<GradeItem> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        AsmDAL asd = new AsmDAL();
        QuizDAL qd = new QuizDAL();
        try {
            String sql = "SELECT * FROM grade_item WHERE student_id = ? AND quiz_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, accID);
            st.setInt(2, quizID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                GradeItem gi = new GradeItem();
                //gi.setAsm(asd.getAsmByID(rs.getInt("asm_id")));
                gi.setCount(rs.getInt("count"));
                gi.setDateTaken(rs.getTimestamp("date_taken"));
                gi.setItemID(rs.getInt("item_id"));
                gi.setItemName(rs.getString("item_name"));
                gi.setItemType(rs.getString("item_type"));
                gi.setNotes(rs.getString("notes"));
                gi.setQuiz(qd.getQuizByID(rs.getInt("quiz_id")));
                gi.setGrade(rs.getDouble("grade"));
                gi.setStudent(ad.getAccountByAccID(rs.getInt("student_id")));
                gi.setTimeTaken(rs.getDouble("time_taken"));
                gi.setPass(rs.getBoolean("pass"));
                list.add(gi);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Quiz> getAllQuiz() {
        ArrayList<Quiz> quizs = new ArrayList<>();
        try {
            String sql = "SELECT  quiz_id,quiz_name,chapter_id,lesson_id,noQ,time_limit,display_order,full_name,quiz.status "
                    + "FROM quiz join account on quiz.created_by = account.account_id"
                    + "WHERE class_id = null";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();

                q.setQuizID(rs.getInt("quiz_id"));
                q.setQuizName(rs.getString("quiz_name"));
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("chapter_id"));
                q.setChapter(c);
                Subject sub = new Subject();
                sub.setSubjectID(rs.getInt("subject_id"));
                q.setSubject(sub);
                q.setNoQ(rs.getInt("noQ"));
                q.setTimeLimit(rs.getInt("time_limit"));
                q.setDisplayOrder(rs.getInt("display_order"));
                Account a = new Account();
                a.setFullName(rs.getString("full_name"));
                q.setCreatedBy(a);
                q.setStatus(rs.getBoolean("status"));
                quizs.add(q);
            }
        } catch (Exception e) {
        }
        return quizs;
    }

    public ArrayList<Quiz> searchQuizByName(String quiz_name) {
        ArrayList<Quiz> quizls = new ArrayList<>();
        try {
            String sql = "select * from quiz where quiz_name LIKE ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, "%" + quiz_name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setQuizID(rs.getInt("quiz_id"));
                q.setQuizName(rs.getString("quiz_name"));
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("chapter_id"));
                q.setChapter(c);
                Subject sub = new Subject();
                sub.setSubjectID(rs.getInt("subject_id"));
                q.setSubject(sub);
                q.setNoQ(rs.getInt("noQ"));
                q.setTimeLimit(rs.getInt("time_limit"));
                q.setDisplayOrder(rs.getInt("display_order"));
                Account a = new Account();
                Account updated_by = new Account();
                a.setUser(rs.getString("created_by"));
                updated_by.setUser(rs.getString("updated_at"));
                q.setCreatedBy(a);
                q.setCreatedAt(rs.getTimestamp("created_at"));
                q.setUpdatedBy(updated_by);
                q.setUpdatedAt(rs.getTimestamp("updated_at"));
                q.setStatus(rs.getBoolean("status"));
                quizls.add(q);
            }
        } catch (Exception e) {
        }
        return quizls;
    }

    public void insertQuiz(Quiz q) {
        try {
            String sql = "INSERT INTO quiz\n"
                    + "(quiz_name,\n"
                    + "chapter_id,\n"
                    + "lesson_id,\n"
                    + "noQ,\n"
                    + "time_limit,\n"
                    + "display_order,\n"
                    + "created_by,\n"
                    + "created_at,\n"
                    + "status)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?);";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, q.getQuizName());
            st.setInt(2, q.getChapter().getChapterID());
            st.setInt(3, q.getSubject().getSubjectID());
            st.setInt(4, q.getNoQ());
            st.setDouble(5, q.getTimeLimit());
            st.setInt(6, q.getDisplayOrder());
            st.setInt(7, q.getCreatedBy().getID());
            st.setTimestamp(8, q.getCreatedAt());
            st.setBoolean(9, q.isStatus());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public void insertPracticeQuiz(Quiz q) {
        try {
            String sql = "INSERT INTO quiz\n"
                    + "(quiz_name,\n"
                    + "chapter_id,\n"
                    + "noQ,\n"
                    + "created_by,\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?);";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, q.getQuizName());
            st.setInt(2, q.getChapter().getChapterID());
            st.setInt(3, q.getNoQ());
            st.setInt(4, q.getCreatedBy().getID());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Chapter> getAllChapterQuiz() {
        ArrayList<Chapter> chaps = new ArrayList<>();
        try {
            String sql = "SELECT setting_id,setting_name FROM subject_setting";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("setting_id"));
                c.setChapterName(rs.getString("setting_name"));
                chaps.add(c);
            }
        } catch (Exception e) {
        }
        return chaps;
    }

    public ArrayList<Lesson> getAllLessonQuiz() {
        ArrayList<Lesson> less = new ArrayList<>();
        try {
            String sql = "SELECT lesson_id,lesson_name FROM lesson";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson les = new Lesson();
                les.setLessonID(rs.getInt("lesson_id"));
                les.setLessonName(rs.getString("lesson_name"));
                less.add(les);
            }
        } catch (Exception e) {
        }
        return less;
    }

    public static void main(String[] args) {
        QuizDAL qd = new QuizDAL();
        System.out.println(qd.getAllChapterQuiz());
    }

    public ArrayList<Quiz> getListQuizBySubjectID(int id)
            throws SQLException, ClassNotFoundException {
        ArrayList<Quiz> listQuiz = new ArrayList<Quiz>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ChapterDAO cd = new ChapterDAO();
        SubjectDAL sd = new SubjectDAL();
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "SELECT * FROM quiz WHERE subject_id = ? ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, id);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setQuizID(rs.getInt("quiz_id"));
                quiz.setQuizName(rs.getString("quiz_name"));
                Chapter c = cd.getChapterByID(rs.getInt("chapter_id"));
                quiz.setChapter(c);
                Subject subject = sd.getSubjectByID(rs.getInt("subject_id"));
                quiz.setSubject(subject);
                quiz.setType(rs.getString("quiz_type"));
                quiz.setNoQ(rs.getInt("noQ"));
                quiz.setTimeLimit(rs.getDouble("time_limit"));
                quiz.setDisplayOrder(rs.getInt("display_order"));
                quiz.setDescription(rs.getString("description"));
                quiz.setStatus(rs.getBoolean("status"));
                if (quiz.isStatus()) {
                    listQuiz.add(quiz);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listQuiz;
    }

    public ArrayList<Quiz> getPracticeQuizzesByStudentId(int studentId, int subjectID)
            throws SQLException, ClassNotFoundException {
        ArrayList<Quiz> listQuiz = new ArrayList<Quiz>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM quiz WHERE quiz_type = 'Practice' AND created_by = ? AND subject_id = ? ";
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, studentId);
            stm.setInt(2, subjectID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Quiz quiz = getQuizByID(rs.getInt("quiz_id"));
                listQuiz.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listQuiz;
    }
   public List<Quiz> GetAllQuizByChap(int chapterID)
            throws SQLException, ClassNotFoundException {
        ArrayList<Quiz> listQuiz = new ArrayList<Quiz>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ChapterDAO cd = new ChapterDAO();
        SubjectDAL sd = new SubjectDAL();
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "SELECT * FROM quiz WHERE chapter_id = ? ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, chapterID);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setQuizID(rs.getInt("quiz_id"));
                quiz.setQuizName(rs.getString("quiz_name"));
                Chapter c = cd.getChapterByID(rs.getInt("chapter_id"));
                quiz.setChapter(c);
                Subject subject = sd.getSubjectByID(rs.getInt("subject_id"));
                quiz.setSubject(subject);
                quiz.setType(rs.getString("quiz_type"));
                quiz.setNoQ(rs.getInt("noQ"));
                quiz.setTimeLimit(rs.getDouble("time_limit"));
                quiz.setDisplayOrder(rs.getInt("display_order"));
                quiz.setDescription(rs.getString("description"));
                quiz.setStatus(rs.getBoolean("status"));
                if (quiz.isStatus()) {
                    listQuiz.add(quiz);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listQuiz;
    }

    public ArrayList<Class> getAllClasses()
            throws SQLException, ClassNotFoundException {
        ArrayList<Class> listClass = new ArrayList<Class>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ChapterDAO cd = new ChapterDAO();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "SELECT * FROM class ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            while (rs.next()) {
                Class cl = new Class();
                cl.setClassID(rs.getInt("class_id"));
                cl.setClassName(rs.getString("class_name"));
                cl.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                cl.setTrainer(ad.getAccountByAccID(rs.getInt("trainer")));
                cl.setStartDate(rs.getDate("start_date"));
                cl.setEndDate(rs.getDate("end_date"));
                cl.setStartTime(rs.getTime("start_time"));
                cl.setEndTime(rs.getTime("end_time"));
                cl.setActivate(rs.getInt("status"));
                cl.setSemester(ssd.getSettingByID(rs.getInt("semester")));
                listClass.add(cl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listClass;
    }

    public ArrayList<Subject> getAllSubject() {
        ArrayList<Subject> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM subject";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"), rs.getString("subject_des"), rs.getInt("status"),ad.getAccountByAccID(rs.getInt("manager")));          
                s.setSubjectCode(rs.getString("subject_code"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}
