/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.QuestionAnswer;

/**
 *
 * @author quany
 */
public class QuesAsnDAL extends DBContext {

    public void addAsnwer(int question, String answerOption, String answerContent, int isKey) {
        try {
            String sql = "INSERT INTO question_ans (question_id, answer_option, answer_content, is_key) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = DBContext().prepareStatement(sql);
            ps.setInt(1, question);
            ps.setString(2, answerOption);
            ps.setString(3, answerContent);
            ps.setInt(4,  isKey);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuesAsnDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
