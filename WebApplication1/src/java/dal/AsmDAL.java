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
import model.Account;
import model.Assignment;
import model.AssignmentSubmit;
import model.Chapter;
import model.Subject;

/**
 *
 * @author acer
 */
public class AsmDAL extends DBContext {

    public Assignment getAllAsmByLesson(int lesson) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM assignment JOIN lesson ON lesson.asm_id = assignment.asm_id WHERE lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, lesson);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                a.setAsmDes(rs.getString("asm_des"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setChapter(ssd.getChapterByID(rs.getInt("chapter_id")));
                a.setCreatedAt(rs.getTimestamp("created_at"));
                a.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                a.setUpdatedAt(rs.getTimestamp("updated_at"));
                a.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                a.setDl(rs.getTimestamp("deadline"));
                a.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Assignment> getAllAsmByChapter(int chapter) {
        List<Assignment> list = new ArrayList<>();
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM assignment WHERE chapter_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, chapter);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                a.setAsmDes(rs.getString("asm_des"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setCreatedAt(rs.getTimestamp("created_at"));
                a.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                a.setUpdatedAt(rs.getTimestamp("updated_at"));
                a.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                a.setDl(rs.getTimestamp("deadline"));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Assignment> pagingAssignment(int index) {
        List<Assignment> list = new ArrayList<>();
        String query = "select Row_number() over (order by asm_id asc) as no, a.asm_id,a.asm_name,a.asm_des,sj.subject_code,a.deadline \n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n"
                + "order by no asc Limit 5 offset ?";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setInt(1, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                Subject s = new Subject();
                Chapter c = new Chapter();
                s.setSubjectCode(rs.getString("subject_code"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setAsmDes(rs.getString("asm_des"));
                a.setSubject(s);
                a.setDl(rs.getTimestamp("deadline"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalAssignment() {
        int count = 0;
        String query = "select count(*)\n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    public int getTotalAssignmentBySubject(String subject) {
        String query = "select count(*)\n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n"
                + "join chapter ct on a.chapter_id=ct.chapter_id\n"
                + "where sj.subject_code like ?";
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, "%" + subject + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public List<Assignment> paggingAccountBySubject(String subject, int index) {
        List<Assignment> list = new ArrayList<>();
        String query = "select Row_number() over (order by asm_id asc) as no, a.asm_id,a.asm_name,a.asm_des,sj.subject_code,ct.chapter_name \n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n"
                + "join chapter ct on a.chapter_id=ct.chapter_id\n"
                + "where sj.subject_code like ?\n"
                + "order by no asc Limit 5 offset ?";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, "%" + subject + "%");
            stm.setInt(2, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                Subject s = new Subject();
                Chapter c = new Chapter();
                s.setSubjectCode(rs.getString("subject_code"));
                c.setChapterName(rs.getString("chapter_name"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setAsmDes(rs.getString("asm_des"));
                a.setSubject(s);
                a.setChapter(c);
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Assignment> paggingAccountByName(String subject, int index) {
        List<Assignment> list = new ArrayList<>();
        String query = "select Row_number() over (order by asm_id asc) as no, a.asm_id,a.asm_name,a.asm_des,sj.subject_code,ct.chapter_name \n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n"
                + "join chapter ct on a.chapter_id=ct.chapter_id\n"
                + "where a.asm_name like ?\n"
                + "order by no asc Limit 5 offset ?";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, "%" + subject + "%");
            stm.setInt(2, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                Subject s = new Subject();
                Chapter c = new Chapter();
                s.setSubjectCode(rs.getString("subject_code"));
                c.setChapterName(rs.getString("chapter_name"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setAsmDes(rs.getString("asm_des"));
                a.setSubject(s);
                a.setChapter(c);
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalAssignmentBySubjectName(String subject) {
        String query = "select count(*)\n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n"
                + "join chapter ct on a.chapter_id=ct.chapter_id\n"
                + "where a.asm_name like ?";
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, "%" + subject + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public Assignment getAsmByID(int asmID) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM assignment WHERE asm_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, asmID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Assignment a = new Assignment();
                a.setAsmDes(rs.getString("asm_des"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setChapter(ssd.getChapterByID(rs.getInt("chapter_id")));
                a.setCreatedAt(rs.getTimestamp("created_at"));
                a.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                a.setUpdatedAt(rs.getTimestamp("updated_at"));
                a.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                a.setDl(rs.getTimestamp("deadline"));
                a.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int getTotalAssForTrainer(int class1, int subject) {
        String query = "Select count(*)\n"
                + "from(\n"
                + "	SELECT \n"
                + "		Row_number() over (order by a.asm_id asc) as no,\n"
                + "		a.asm_id AS IDAssignment,\n"
                + "		a.asm_name AS AssignmentName,\n"
                + "		a.deadline AS Deadline,\n"
                + "		COUNT(DISTINCT c.student_id) AS total_students,\n"
                + "		COUNT(DISTINCT s.submission_id) AS submitted_students,\n"
                + "		(COUNT(DISTINCT c.student_id) - COUNT(DISTINCT s.submission_id)) AS remaining_students\n"
                + "	FROM \n"
                + "		assignment a\n"
                + "	LEFT JOIN \n"
                + "		assignment_submit s ON a.asm_id = s.asm_id\n"
                + "	LEFT JOIN \n"
                + "		student_class c ON a.class_id = c.class_id\n"
                + "	Where a.class_id = ? and a.subject_id = ?\n"
                + "	GROUP BY \n"
                + "		a.asm_id, a.asm_name, a.deadline\n"
                + ") as subquery";
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setInt(1, class1);
            stm.setInt(2, subject);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public List<Assignment> pagggingAssForTrainer(int class1, int subject, int index) {
        List<Assignment> list = new ArrayList<>();
        String query = "SELECT \n"
                + "	Row_number() over (order by a.asm_id asc) as no,\n"
                + "    a.asm_id AS IDAssignment,\n"
                + "    a.asm_name AS AssignmentName,\n"
                + "    a.deadline AS Deadline,\n"
                + "    COUNT(DISTINCT c.student_id) AS total_students,\n"
                + "    COUNT(DISTINCT s.submission_id) AS submitted_students,\n"
                + "    (COUNT(DISTINCT c.student_id) - COUNT(DISTINCT s.submission_id)) AS remaining_students\n"
                + "FROM \n"
                + "    assignment a\n"
                + "LEFT JOIN \n"
                + "    assignment_submit s ON a.asm_id = s.asm_id\n"
                + "LEFT JOIN \n"
                + "    student_class c ON a.class_id = c.class_id\n"
                + "Where a.class_id = ? and a.subject_id = ?\n"
                + "GROUP BY \n"
                + "    a.asm_id, a.asm_name, a.deadline\n"
                + "order by no asc Limit 5 offset ?";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setInt(1, class1);
            stm.setInt(2, subject);
            stm.setInt(3, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                a.setAsmID(rs.getInt("IDAssignment"));
                a.setAsmName(rs.getString("AssignmentName"));
                a.setDl(rs.getTimestamp("Deadline"));
                a.setTotal_students(rs.getInt("total_students"));
                a.setSubmitted_students(rs.getInt("submitted_students"));
                a.setRemaining_students(rs.getInt("remaining_students"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalStInAss(int class1, int subject) {
        String query = "Select count(*)\n"
                + "FROM(\n"
                + "	SELECT \n"
                + "	asb.submission_id as SubmitID,\n"
                + "	ac.account_id as ID,\n"
                + "    ac.full_name AS StudentName,\n"
                + "    ac.email,\n"
                + "    CASE \n"
                + "        WHEN asb.update_at IS NULL THEN 'Chưa nộp'\n"
                + "        WHEN gi.grade IS NULL THEN 'Đã nộp, chưa chấm điểm'\n"
                + "        WHEN asb.update_at <= a.deadline THEN 'Đã nộp đúng hạn'\n"
                + "        ELSE 'Nộp quá hạn'\n"
                + "    END AS Status,\n"
                + "    gi.grade AS Grade,\n"
                + "    asb.file_url AS FileURL\n"
                + "FROM \n"
                + "    assignment a\n"
                + "JOIN \n"
                + "    student_class sc ON a.class_id = sc.class_id\n"
                + "JOIN \n"
                + "    account ac ON sc.student_id = ac.account_id\n"
                + "LEFT JOIN \n"
                + "    assignment_submit asb ON a.asm_id = asb.asm_id AND ac.account_id = asb.account_id\n"
                + "LEFT JOIN \n"
                + "    grade_item gi ON asb.submission_id = gi.submit_id\n"
                + "WHERE \n"
                + "    a.asm_id = ? and a.class_id=?\n"
                + ") as subquery";
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setInt(1, class1);
            stm.setInt(2, subject);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public List<AssignmentSubmit> pagggingStInAss(int class1, int subject, int index) {
        List<AssignmentSubmit> list = new ArrayList<>();
        String query = "SELECT \n"
                + "	Row_number() over (order by a.asm_id asc) as no,\n"
                + "	asb.submission_id as SubmitID,\n"
                + "	ac.account_id as ID,\n"
                + "    ac.full_name AS StudentName,\n"
                + "    ac.email,\n"
                + "    CASE \n"
                + "        WHEN asb.update_at IS NULL THEN 'Chưa nộp'\n"
                + "        WHEN gi.grade IS NULL THEN 'Đã nộp, chưa chấm điểm'\n"
                + "        WHEN asb.update_at <= a.deadline THEN 'Đã nộp đúng hạn'\n"
                + "        ELSE 'Nộp quá hạn'\n"
                + "    END AS Status,\n"
                + "    gi.grade AS Grade,\n"
                + "    asb.file_url AS FileURL\n"
                + "FROM \n"
                + "    assignment a\n"
                + "JOIN \n"
                + "    student_class sc ON a.class_id = sc.class_id\n"
                + "JOIN \n"
                + "    account ac ON sc.student_id = ac.account_id\n"
                + "LEFT JOIN \n"
                + "    assignment_submit asb ON a.asm_id = asb.asm_id AND ac.account_id = asb.account_id\n"
                + "LEFT JOIN \n"
                + "    grade_item gi ON asb.submission_id = gi.submit_id\n"
                + "WHERE \n"
                + "    a.asm_id = ? and a.class_id=?\n"
                + "order by no asc Limit 5 offset ?";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setInt(1, class1);
            stm.setInt(2, subject);
            stm.setInt(3, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                AssignmentSubmit as = new AssignmentSubmit();
                as.setSubmitID(rs.getInt("SubmitID"));
                acc.setID(rs.getInt("ID"));
                as.setAccID(acc);
                acc.setFullName(rs.getString("StudentName"));
                as.setAccName(acc);
                acc.setEmail(rs.getString("email"));
                as.setEmail(acc);
                as.setStatus(rs.getString("Status"));
                as.setGrade(rs.getDouble("Grade"));
                as.setFileURL(rs.getString("FileURL"));
                list.add(as);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void InsertGrade(String grade, String submit_id){
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            
            String query = "insert into grade_item(grade,submit_id) values(?,?)";
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, grade);
            stm.setString(2, submit_id);
        }catch(Exception e){
            
        }
        
    }
    public static void main(String[] args) {
        AsmDAL dao = new AsmDAL();
        List<AssignmentSubmit> asmList = dao.pagggingStInAss(1, 1, 1);
        dao.InsertGrade("7", "5");
        int count = dao.getTotalStInAss(1, 1);
        System.out.println(count);
        for (AssignmentSubmit a : asmList) {
            System.out.println(a);
        }
    }
}
