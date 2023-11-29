/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.GradeItem;
import model.Quiz;

import util.FormatDate;

/**
 *
 * @author acer
 */
public class GradeDAL extends DBContext {

    public List<GradeItem> getAllGradeByStudent(int studentID, int classID) {
        List<GradeItem> list = new ArrayList<>();
        AsmDAL asd = new AsmDAL();
        QuizDAL qd = new QuizDAL();
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();

        try {
            String sql = "SELECT * FROM grade_item WHERE student_id = ? AND class_id = ? ORDER BY item_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, studentID);
            st.setInt(2, classID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                GradeItem gi = new GradeItem();
                gi.setAsm(asd.getAsmByID(rs.getInt("asm_id")));
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
                gi.setDate(rs.getDate("date_taken"));
                gi.setDateS(fd.formatDateSQL(gi.getDate()));
                list.add(gi);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<GradeItem> getAllGradeByStudentASearch(int studentID, int classID, String condition, String o) {
        List<GradeItem> list = new ArrayList<>();
        AsmDAL asd = new AsmDAL();
        QuizDAL qd = new QuizDAL();
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();
        try {
            String sql = "SELECT * FROM grade_item WHERE student_id = ? AND class_id = ?" + condition + o;
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, studentID);
            st.setInt(2, classID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                GradeItem gi = new GradeItem();
                gi.setAsm(asd.getAsmByID(rs.getInt("asm_id")));
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
                gi.setDate(rs.getDate("date_taken"));
                gi.setDateS(fd.formatDateSQL(gi.getDate()));
                gi.setPass(rs.getBoolean("pass"));
                list.add(gi);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<GradeItem> getGradeByItem(List<GradeItem> list, int start, int end) {
        List<GradeItem> list1 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }

    public String order(String sort, String order) {
        String o = "";
        switch (sort) {
            case "ID": {
                if (order.equals("0")) {
                    o = " ORDER BY item_id DESC";
                } else {
                    o = " ORDER BY item_id ASC";
                }
                break;
            }
            case "Name": {
                if (order.equals("0")) {
                    o = " ORDER BY item_name DESC";
                } else {
                    o = " ORDER BY item_name ASC";
                }
                break;
            }
            case "Type": {
                if (order.equals("0")) {
                    o = " ORDER BY item_type DESC";
                } else {
                    o = " ORDER BY item_type ASC";
                }
                break;
            }
            case "Taken": {
                if (order.equals("0")) {
                    o = " ORDER BY count DESC";
                } else {
                    o = " ORDER BY count ASC";
                }
                break;
            }
            case "Grade": {
                if (order.equals("0")) {
                    o = " ORDER BY grade DESC";
                } else {
                    o = " ORDER BY grade ASC";
                }
                break;
            }
            case "Note": {
                if (order.equals("0")) {
                    o = " ORDER BY notes DESC";
                } else {
                    o = " ORDER BY notes ASC";
                }
                break;
            }
            case "Time": {
                if (order.equals("0")) {
                    o = " ORDER BY time_taken DESC";
                } else {
                    o = " ORDER BY time_taken ASC";
                }
                break;
            }
            case "Date": {
                if (order.equals("0")) {
                    o = " ORDER BY date_taken DESC";
                } else {
                    o = " ORDER BY date_taken ASC";
                }
                break;
            }
            default: {
                o = " ORDER BY item_id";
                break;
            }
        }
        return o;
    }

    public int getMaxCount(int studentId, int quizId) throws SQLException, ClassNotFoundException {
        int maxCount = 0;
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT MAX(count) FROM grade_item WHERE student_id = ? AND quiz_id = ?";
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, studentId);
            stm.setInt(2, quizId);
            rs = stm.executeQuery();
            if (rs.next()) {
                maxCount = rs.getInt(1);
                if (rs.wasNull()) {
                    maxCount = 0;
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
        return maxCount;
    }

    public boolean saveStudentQuizGrade(int studentId, int quizId, int count, double grade, Double time_taken)
            throws SQLException, ClassNotFoundException {
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        QuizDAL qd = new QuizDAL();
        try {
            String sql = "INSERT INTO grade_item(item_name,student_id,item_type,count,grade,date_taken,time_taken,quiz_id) VALUES (?,?,'Quiz',?,?,?,?,?)";
            stm = DBContext().prepareStatement(sql);
            String item_name = qd.getQuizByID(quizId).getQuizName();
            stm.setString(1, item_name);
            stm.setInt(2, studentId);
            stm.setInt(3, count);
            stm.setDouble(4, grade);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            stm.setTimestamp(5, timestamp);
            if (time_taken != null) {
                stm.setDouble(6, time_taken);
            } else {
                stm.setNull(6, java.sql.Types.DOUBLE);
            }
            stm.setInt(7, quizId);
            //stm.executeUpdate();
            int rowsInserted = stm.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public GradeItem GetGradeByQuizIdAndCount(int studentId, int quizId, int count)
            throws SQLException, ClassNotFoundException {
        GradeItem grade = new GradeItem();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        QuizDAL qd = new QuizDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "Select * from grade_item "
                    + "where student_id = ? "
                    + "and count = ? "
                    + "and quiz_id = ? ";
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, studentId);
            stm.setInt(2, count);
            stm.setInt(3, quizId);
            rs = stm.executeQuery();
            if (rs.next()) {
                Quiz quiz = qd.getQuizByID(quizId);
                Account a = ad.getAccountByAccID(rs.getInt("student_id"));
                grade.setQuiz(quiz);
                grade.setCount(count);
                grade.setItemName(rs.getString("item_name"));
                grade.setStudent(a);
                grade.setItemType(rs.getString("item_type"));
                grade.setGrade(rs.getDouble("grade"));
                grade.setDateTaken(rs.getTimestamp("date_taken"));
                grade.setTimeTaken(rs.getDouble("time_taken"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return grade;
    }


   
}
