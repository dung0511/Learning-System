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
import java.util.List;
import model.Question_Answer;
import model.Quiz_Question;
import model.Quiz_Review;
import model.Student_Answer;

/**
 *
 * @author ADMIN
 */
public class StudentAnswerDAO extends DBContext implements Serializable {

    public ArrayList<Student_Answer> getAnswersByStudentIdAndQuizIdAndCount(int studentId, int quizId, int count)
            throws SQLException, ClassNotFoundException {

        ArrayList<Student_Answer> listStuAnswer = new ArrayList<Student_Answer>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "select * from student_ans "
                    + "where student_id = ? "
                    + "and quiz_id = ? "
                    + "and count = ? ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, studentId);
            stm.setInt(2, quizId);
            stm.setInt(3, count);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            while (rs.next()) {
                Student_Answer sa = new Student_Answer();
                sa.setStudent_id(studentId);
                sa.setQuiz_id(quizId);
                sa.setAnswer_option(rs.getString("answer_option"));
                sa.setCount(rs.getInt("count"));
                sa.setQuestion_id(rs.getInt("question_id"));
                listStuAnswer.add(sa);
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
        return listStuAnswer;
    }

    public double calculateScore(int studentId, int quizId, int count) throws SQLException, ClassNotFoundException {
        double score = 0;
        QuestionAnswerDAO qad = new QuestionAnswerDAO();
        QuizDAL qd = new QuizDAL();
        List<Student_Answer> studentAnswers = getAnswersByStudentIdAndQuizIdAndCount(studentId, quizId, count);
        for (Student_Answer studentAnswer : studentAnswers) {
            List<Question_Answer> correctAnswers = qad.getQuestionAnsByQuestionId(studentAnswer.getQuestion_id());
            for (Question_Answer correctAnswer : correctAnswers) {
                if (correctAnswer.isIs_key() && correctAnswer.getAnswer_option().equals(studentAnswer.getAnswer_option())) {
                    score++;
                    break;
                }
            }
        }
        int noQ = qd.getQuizByID(quizId).getNoQ();
        score = score / noQ;
        return score;
    }

    public void saveStudentQuiz(List<Student_Answer> answers)
            throws SQLException, ClassNotFoundException {
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        try {
            for (Student_Answer answer : answers) {
                int studentId = answer.getStudent_id();
                int quizId = answer.getQuiz_id();
                int questionId = answer.getQuestion_id();
                String answerOption = answer.getAnswer_option();
                int count = answer.getCount();
                String sql = "INSERT INTO student_ans(student_id, quiz_id, question_id, answer_option, count) VALUES (?, ?, ?, ?, ?)";
                stm = DBContext().prepareStatement(sql);
                stm.setInt(1, studentId);
                stm.setInt(2, quizId);
                stm.setInt(3, questionId);
                stm.setString(4, answerOption);
                stm.setInt(5, count);
                stm.executeUpdate();
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
    }

    public ArrayList<Quiz_Review> getReviewsByStudentAns(int studentId, int quizId, int count)
            throws SQLException, ClassNotFoundException {

        ArrayList<Quiz_Review> listReview = new ArrayList<Quiz_Review>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        QuizQuestionDAO qqd = new QuizQuestionDAO();
        QuestionAnswerDAO qad = new QuestionAnswerDAO();
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "select * from student_ans "
                    + "where student_id = ? "
                    + "and quiz_id = ? "
                    + "and count = ? ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, studentId);
            stm.setInt(2, quizId);
            stm.setInt(3, count);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            while (rs.next()) {
                int questionId = rs.getInt("question_id");
                Quiz_Question question = qqd.getQuizQuestionByQuestionId(questionId);
                String studentAns = rs.getString("answer_option");
                Quiz_Review qr = new Quiz_Review();
                qr.setQuestion(question);
                qr.setStudentAnswer(studentAns);
                Question_Answer answer = qad.findCorrectAnswer(question.getAnswer());
                qr.setCorrectAnswer(answer);
                listReview.add(qr);
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
        return listReview;
    }

}
