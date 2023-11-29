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
import model.Question;
import model.Question_Answer;

/**
 *
 * @author ADMIN
 */
public class QuestionAnswerDAO extends DBContext implements Serializable {

    public ArrayList<Question_Answer> getQuestionAnsByQuestionId(int id)
            throws SQLException, ClassNotFoundException {
        ArrayList<Question_Answer> listAnswer = new ArrayList<Question_Answer>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "select * from question_ans where question_id = ? ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, id);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            while (rs.next()) {
                Question_Answer qa = new Question_Answer(
                        id,
                        rs.getString("answer_option"),
                        rs.getString("answer_content"),
                        rs.getBoolean("is_key")
                );
                listAnswer.add(qa);
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
        return listAnswer;
    }

    public Question_Answer findCorrectAnswer(List<Question_Answer> answers) {
        for (Question_Answer answer : answers) {
            if (answer.isIs_key()) {
                return answer;
            }
        }
        return null;
    }
}
