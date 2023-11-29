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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClassAssignment;
import model.Lesson;
import model.Subject;
import model.Class;
import model.SubjectSetting;

/**
 *
 * @author quany
 */
public class ClassAssignmentDAL extends DBContext {

    public ArrayList<ClassAssignment> getAll(int accountID) {
        AccountDAL ad = new AccountDAL();
        SubjectDAL subjectDAL = new SubjectDAL();
        ClassDAL classDAL = new ClassDAL();
        SubjectSettingDAL subjectsettingDAL = new SubjectSettingDAL();
        try {

            String sql = "SELECT a.*"
                    + "FROM assignment a "
                    + "INNER JOIN class c ON a.class_id = c.class_id "
                    + "WHERE c.trainer = ? ";
            ArrayList<ClassAssignment> list;
            try ( PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ps.setInt(1, accountID);
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    ClassAssignment ca = new ClassAssignment();
                    ca.setAsmID(rs.getInt("asm_id"));
                    ca.setAsmName(rs.getString("asm_name"));
                    ca.setAsmDes(rs.getString("asm_des"));

                    Subject s;
                    s = subjectDAL.getSubjectByID(rs.getInt("subject_id"));
                    ca.setSubject(s);

                    SubjectSetting ss;
                    ss = subjectsettingDAL.getAllSettingBySettingID(rs.getInt("chapter_id"));
                    ca.setSubjectsetting(ss);

                    Class c;
                    c = classDAL.getClassByID(rs.getInt("class_id"));
                    ca.setClas(c);

                    ca.setDl(rs.getTimestamp("deadline"));
                    ca.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                    ca.setCreatedAt(rs.getTimestamp("created_at"));
                    ca.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                    ca.setUpdatedAt(rs.getTimestamp("updated_at"));
                    ca.setStatus(rs.getInt("status"));

                    list.add(ca);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ClassAssignmentDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<ClassAssignment> getasmID(int asmID) {
        AccountDAL ad = new AccountDAL();
        SubjectDAL subjectDAL = new SubjectDAL();
        ClassDAL classDAL = new ClassDAL();
        SubjectSettingDAL subjectsettingDAL = new SubjectSettingDAL();
        try {
            String sql = "SELECT * FROM assignment where asm_id = ?";
            ArrayList<ClassAssignment> list;
            try ( PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ps.setInt(1, asmID);
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    ClassAssignment ca = new ClassAssignment();
                    ca.setAsmID(rs.getInt("asm_id"));
                    ca.setAsmName(rs.getString("asm_name"));
                    ca.setAsmDes(rs.getString("asm_des"));

                    Subject s;
                    s = subjectDAL.getSubjectByID(rs.getInt("subject_id"));
                    ca.setSubject(s);

                    SubjectSetting ss;
                    ss = subjectsettingDAL.getAllSettingBySettingID(rs.getInt("chapter_id"));
                    ca.setSubjectsetting(ss);

                    Class c;
                    c = classDAL.getClassByID(rs.getInt("class_id"));
                    ca.setClas(c);

//                    Lesson lesson;
//                    lesson = subjectsettingDAL.getLessonByIDnew(rs.getInt("lesson_id"));
//                    ca.setLesson(lesson);
                    ca.setDl(rs.getTimestamp("deadline"));
                    ca.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                    ca.setCreatedAt(rs.getTimestamp("created_at"));
                    ca.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                    ca.setUpdatedAt(rs.getTimestamp("updated_at"));
                    ca.setStatus(rs.getInt("status"));

                    list.add(ca);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ClassAssignmentDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ClassAssignment getAsmID(int asmID) {
        AccountDAL ad = new AccountDAL();
        SubjectDAL subjectDAL = new SubjectDAL();
        ClassDAL classDAL = new ClassDAL();
        SubjectSettingDAL subjectsettingDAL = new SubjectSettingDAL();
        try {
            String sql = "SELECT * FROM assignment where asm_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, asmID);
            ResultSet rs = ps.executeQuery();
            rs.next();

            ClassAssignment classassignment = new ClassAssignment();
            classassignment.setAsmID(rs.getInt("asm_id"));
            classassignment.setAsmName(rs.getString("asm_name"));
            classassignment.setAsmDes(rs.getString("asm_des"));

            Subject s;
            s = subjectDAL.getSubjectByID(rs.getInt("subject_id"));
            classassignment.setSubject(s);

            SubjectSetting ss;
            ss = subjectsettingDAL.getAllSettingBySettingID(rs.getInt("chapter_id"));
            classassignment.setSubjectsetting(ss);

            Class c;
            c = classDAL.getClassByID(rs.getInt("class_id"));
            classassignment.setClas(c);

//            Lesson lesson;
//            lesson = subjectsettingDAL.getLessonByIDnew(rs.getInt("lesson_id"));
//            classassignment.setLesson(lesson);
            classassignment.setDl(rs.getTimestamp("deadline"));
            classassignment.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
            classassignment.setCreatedAt(rs.getTimestamp("created_at"));
            classassignment.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
            classassignment.setUpdatedAt(rs.getTimestamp("updated_at"));
            classassignment.setStatus(rs.getInt("status"));
            return classassignment;
        } catch (SQLException ex) {
            Logger.getLogger(ClassAssignmentDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void add(String asmName, String asmDes, int subject, int subjectsetting, int clas, Timestamp dl, int createdBy, Timestamp createdAt, int status) {
        try {
            String sql = "INSERT INTO assignment (asm_name, asm_des, subject_id, chapter_id, class_id, deadline, created_by, created_at, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBContext().prepareStatement(sql);
            ps.setString(1, asmName);
            ps.setString(2, asmDes);
            ps.setInt(3, subject);
            ps.setInt(4, subjectsetting);
            ps.setInt(5, clas);
            ps.setTimestamp(6, dl);
            ps.setInt(7, createdBy);
            ps.setTimestamp(8, createdAt);
            ps.setInt(9, status);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassAssignmentDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<SubjectSetting> getAllSettingBySettingID(int id) {
        ArrayList<SubjectSetting> list = new ArrayList<>();
        SubjectDAL subjectDAL = new SubjectDAL();
        try {
            String sql = "SELECT * FROM swp_prj.subject_setting where subject_id = ?";
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

    public ArrayList<Lesson> getLessonByIDnew(int ID) {
        ArrayList<Lesson> list = new ArrayList<>();
        SubjectDAL subjectDAL = new SubjectDAL();

        try {
            String sql = "SELECT * FROM swp_prj.lesson where subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, ID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println("Lap ");

                Lesson l = new Lesson();
                l.setLessonID(rs.getInt("lesson_id"));

                Subject s;
                s = subjectDAL.getSubjectByID(rs.getInt("subject_id"));
                l.setS(s);

                l.setLessonName(rs.getString("lesson_name"));
                list.add(l);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void update(ClassAssignment classassignment) {
        try {
            String sql = "UPDATE assignment SET asm_name = ?, asm_des = ?, subject_id = ?, chapter_id = ?, class_id = ?, deadline = ?, status = ? WHERE asm_id = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, classassignment.getAsmName());
            ps.setString(2, classassignment.getAsmDes());
            ps.setInt(3, classassignment.getSubject().getSubjectID());
            ps.setInt(4, classassignment.getSubjectsetting().getSettingID());
            ps.setInt(5, classassignment.getClas().getClassID());
            ps.setTimestamp(6, classassignment.getDl());
            ps.setInt(7, classassignment.getStatus());
            ps.setInt(8, classassignment.getAsmID());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClassAssignmentDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<ClassAssignment> getByPageSize(int page, int size) {
        AccountDAL ad = new AccountDAL();
        SubjectDAL subjectDAL = new SubjectDAL();
        ClassDAL classDAL = new ClassDAL();
        SubjectSettingDAL subjectsettingDAL = new SubjectSettingDAL();
        try {
            page -= 1;
            String sql = "SELECT *  FROM swp_prj.assignment WHERE class_id = ? limit ? offset ? ";
            ArrayList<ClassAssignment> list;
            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, 1);
                ps.setInt(2, size);
                ps.setInt(3, page * size);
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    ClassAssignment ca = new ClassAssignment();
                    ca.setAsmID(rs.getInt("asm_id"));
                    ca.setAsmName(rs.getString("asm_name"));
                    ca.setAsmDes(rs.getString("asm_des"));

                    Subject s;
                    s = subjectDAL.getSubjectByID(rs.getInt("subject_id"));
                    ca.setSubject(s);

                    SubjectSetting ss;
                    ss = subjectsettingDAL.getAllSettingBySettingID(rs.getInt("chapter_id"));
                    ca.setSubjectsetting(ss);

                    Class c;
                    c = classDAL.getClassByID(rs.getInt("class_id"));
                    ca.setClas(c);

                    ca.setDl(rs.getTimestamp("deadline"));
                    ca.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                    ca.setCreatedAt(rs.getTimestamp("created_at"));
                    ca.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                    ca.setUpdatedAt(rs.getTimestamp("updated_at"));
                    ca.setStatus(rs.getInt("status"));
                    list.add(ca);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ClassAssignmentDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<ClassAssignment> getByPageSize(int page, int size, int accountID) {
        AccountDAL ad = new AccountDAL();
        SubjectDAL subjectDAL = new SubjectDAL();
        ClassDAL classDAL = new ClassDAL();
        SubjectSettingDAL subjectsettingDAL = new SubjectSettingDAL();
        try {
            page -= 1;
            String sql = "SELECT a.* "
                    + "FROM assignment a "
                    + "INNER JOIN class c ON a.class_id = c.class_id "
                    + "WHERE c.trainer = ? "
                    + "LIMIT ? OFFSET ?";
            ArrayList<ClassAssignment> list;
            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, accountID);
                ps.setInt(2, size);
                ps.setInt(3, page * size);

                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();

                while (rs.next()) {
                    ClassAssignment ca = new ClassAssignment();
                    ca.setAsmID(rs.getInt("asm_id"));
                    ca.setAsmName(rs.getString("asm_name"));
                    ca.setAsmDes(rs.getString("asm_des"));

                    Subject s;
                    s = subjectDAL.getSubjectByID(rs.getInt("subject_id"));
                    ca.setSubject(s);

                    SubjectSetting ss;
                    ss = subjectsettingDAL.getAllSettingBySettingID(rs.getInt("chapter_id"));
                    ca.setSubjectsetting(ss);

                    Class c;
                    c = classDAL.getClassByID(rs.getInt("class_id"));
                    ca.setClas(c);

                    ca.setDl(rs.getTimestamp("deadline"));
                    ca.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                    ca.setCreatedAt(rs.getTimestamp("created_at"));
                    ca.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                    ca.setUpdatedAt(rs.getTimestamp("updated_at"));
                    ca.setStatus(rs.getInt("status"));
                    list.add(ca);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ClassAssignmentDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
        public void delete(int asmID) {
        try {
            String sql = "DELETE FROM assignment WHERE asm_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, asmID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassAssignmentDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
