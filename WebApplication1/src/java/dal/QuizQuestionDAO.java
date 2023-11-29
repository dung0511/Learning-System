/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Question_Answer;
import model.Quiz_Question;

/**
 *
 * @author ADMIN
 */
public class QuizQuestionDAO extends DBContext implements Serializable {

    public Quiz_Question getQuizQuestionByQuestionId(int questionId)
            throws SQLException, ClassNotFoundException {

        QuestionAnswerDAO qad = new QuestionAnswerDAO();
        Quiz_Question qq = new Quiz_Question();
        ArrayList<Question_Answer> answerList = new ArrayList<Question_Answer>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "select * from question where question_id = ? ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, questionId);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            if (rs.next()) {
                qq.setQuestion_id(rs.getInt("question_id"));
                qq.setSubject_id(rs.getInt("subject_id"));
                qq.setChapter_id(rs.getInt("chapter_id"));
                qq.setTopic(rs.getString("topic"));
                qq.setDisplay_order(rs.getInt("display_order"));
                qq.setStatus(rs.getBoolean("status"));
                answerList = qad.getQuestionAnsByQuestionId(questionId);
                qq.setAnswer(answerList);
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
        return qq;
    }

    public ArrayList<Quiz_Question> getQuizQuestionsByQuizId(int quizId)
            throws SQLException, ClassNotFoundException {

        ArrayList<Quiz_Question> listQuestion = new ArrayList<Quiz_Question>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "select * from quiz_question where quiz_id = ? ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, quizId);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            while (rs.next()) {
                Quiz_Question qq = getQuizQuestionByQuestionId(rs.getInt("question_id"));
                qq.setDisplay_order(rs.getInt("display_order")); //ghi đè display order cho đúng với order trong bảng quiz_question
                listQuestion.add(qq);
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
        return listQuestion;
    }

    public ArrayList<Quiz_Question> getRandomQuestionsByChapter(int chapterId, int limit)
            throws SQLException, ClassNotFoundException {
        ArrayList<Quiz_Question> listQuestion = new ArrayList<Quiz_Question>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM question WHERE chapter_id = ? ORDER BY RAND() LIMIT ?";
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, chapterId);
            stm.setInt(2, limit);
            rs = stm.executeQuery();
            while (rs.next()) {
                Quiz_Question qq = getQuizQuestionByQuestionId(rs.getInt("quesID"));
                listQuestion.add(qq);
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
        return listQuestion;
    }

    public ArrayList<Quiz_Question> getRandomQuestionsByDimensionType(int dimensionTypeId, int limit)
            throws SQLException, ClassNotFoundException {
        ArrayList<Quiz_Question> listQuestion = new ArrayList<Quiz_Question>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM question_dimension WHERE dimension_id = ? ORDER BY RAND() LIMIT ?";
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, dimensionTypeId);
            stm.setInt(2, limit);
            rs = stm.executeQuery();
            while (rs.next()) {
                Quiz_Question qq = getQuizQuestionByQuestionId(rs.getInt("quesID"));
                listQuestion.add(qq);
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
        return listQuestion;
    }
}
