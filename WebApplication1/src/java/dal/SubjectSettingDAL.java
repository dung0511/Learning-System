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
import model.Chapter;
import model.Dimension;
import model.Lesson;
import model.Subject;
import model.SubjectSetting;
import model.SystemSetting;
import util.GetFileName;

/**
 *
 * @author acer
 */
public class SubjectSettingDAL extends DBContext {

    public List<SubjectSetting> getAllSettingBySubject(String code) {
        List<SubjectSetting> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE subject_code = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SubjectSetting ss = new SubjectSetting(rs.getInt("setting_id"), rs.getString("subject_code"), rs.getString("setting_group"), rs.getString("setting_name"), rs.getString("setting_value"), rs.getInt("display_order"), rs.getString("description"), sd.getSubjectByCode(rs.getString("subject_code")), rs.getInt("status"));
                list.add(ss);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public SubjectSetting getAllSettingBySetting(int id) {
        List<SubjectSetting> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE setting_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SubjectSetting ss = new SubjectSetting(rs.getInt("setting_id"), rs.getString("subject_code"), rs.getString("setting_group"), rs.getString("setting_name"), rs.getString("setting_value"), rs.getInt("display_order"), rs.getString("description"), sd.getSubjectByCode(rs.getString("subject_code")), rs.getInt("status"));
                return ss;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<SubjectSetting> getListByPage(int start, int end, List<SubjectSetting> list) {
        List<SubjectSetting> list2 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list2.add(list.get(i));
        }
        return list2;
    }

    public List<Lesson> getLessonByPage(int start, int end, List<Lesson> list) {
        List<Lesson> list1 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }

    public void activateSST(int id) {
        try {
            String sql = "UPDATE subject_setting SET status = 1 WHERE setting_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("da" + e);
        }
    }

    public void deactivateSST(int id) {
        try {
            String sql = "UPDATE subject_setting SET status = 0 WHERE setting_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("da" + e);
        }
    }

    public List<Chapter> getAllChapterFromClassAndSubject(int subjectID) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        List<Chapter> list = new ArrayList<>();
        AsmDAL asd = new AsmDAL();
        try {
            String sql = "SELECT * FROM subject_setting LEFT JOIN\n"
                    + "					(SELECT subject_setting.setting_id, SUM(cnt) AS cnt FROM subject_setting LEFT JOIN \n"
                    + "                    (SELECT subject_setting.setting_id, COUNT(DISTINCT subject_setting.setting_id, lesson.lesson_id) AS cnt FROM subject_setting\n"
                    + "                     LEFT JOIN lesson ON subject_setting.setting_id = lesson.chapter_id\n"
                    + "                    LEFT JOIN video_tracking ON lesson.lesson_id = video_tracking.lesson_id\n"
                    + "                    LEFT JOIN quiz ON lesson.quiz_id = quiz.quiz_id\n"
                    + "                    LEFT JOIN grade_item ON quiz.quiz_id = grade_item.quiz_id\n"
                    + "                    WHERE subject_setting.subject_id = ? AND (grade_item.student_id > 0 OR video_tracking.status = 1)\n"
                    + "                    GROUP BY subject_setting.setting_id, lesson.lesson_id) AS T\n"
                    + "                    ON subject_setting.setting_id = T.setting_id\n"
                    + "                    GROUP BY subject_setting.setting_id) AS C\n"
                    + "                    ON subject_setting.setting_id = C.setting_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, subjectID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("setting_id"));
                c.setChapterName(rs.getString("setting_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                c.setAsm(asd.getAllAsmByChapter(rs.getInt("setting_id")));
                c.setNumAchieve(rs.getInt("cnt"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Chapter> getAllChapter() {
        List<Chapter> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE setting_type = 'Chapter'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("setting_id"));
                c.setChapterName(rs.getString("setting_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Chapter> getAllChapterBySubject(int subjectID) {
        List<Chapter> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE setting_type = 'Chapter' AND subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, subjectID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("setting_id"));
                c.setChapterName(rs.getString("setting_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Chapter getChapterByID(int id) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE setting_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("setting_id"));
                c.setChapterName(rs.getString("setting_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Lesson> getAllLessonByChapter(int chapterID, int classID) {
        AccountDAL ad = new AccountDAL();
        List<Lesson> list = new ArrayList<>();
        QuizDAL qd = new QuizDAL();
        AsmDAL asd = new AsmDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT lesson.*, grade_item.student_id, quiz.quiz_id, assignment.asm_id, grade, video_tracking.status AS vist FROM lesson LEFT JOIN quiz ON lesson.quiz_id = quiz.quiz_id "
                    + "LEFT JOIN video_tracking ON lesson.lesson_id = video_tracking.lesson_id"
                    + " LEFT JOIN assignment ON lesson.asm_id = assignment.asm_id"
                    + " LEFT JOIN grade_item ON quiz.quiz_id = grade_item.quiz_id WHERE lesson.chapter_id = ? AND lesson.status = 1 AND (lesson.class_id IS NULL || lesson.class_id = ?) AND (count = 1 || count IS NULL)\n";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, chapterID);
            st.setInt(2, classID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLessonID(rs.getInt("lesson_id"));
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                l.setQuiz(qd.getQuizByLessonID(rs.getInt("lesson_id")));
                l.setAsm(asd.getAllAsmByLesson(rs.getInt("asm_id")));
                l.setVideoLink(rs.getString("video_link"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setDescription(rs.getString("description"));
                if (rs.getInt("student_id") > 0 || rs.getInt("vist") == 1) {
                    l.setAchieve(true);
                } else {
                    l.setAchieve(false);
                }
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println("2" + e);
        }
        return list;
    }

    public Dimension getDimensionByID(int dimensionID) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE setting_id = ? AND setting_type = 'Dimension'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, dimensionID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Dimension d = new Dimension();
                d.setCreatedAt(rs.getTimestamp("created_at"));
                d.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                d.setUpdatedAt(rs.getTimestamp("updated_at"));
                d.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                d.setDescription(rs.getString("description"));
                d.setDimensionID(rs.getInt("dimension_id"));
                d.setDimensionName(rs.getString("dimension_name"));
                d.setDisplayOrder(rs.getInt("display_order"));
                d.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                d.setStatus(rs.getInt("status"));
                d.setSsType(ssd.getSettingByID(rs.getInt("dimension_type")));
                return d;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Lesson getLessonByID(int ID) {
        AccountDAL ad = new AccountDAL();
        QuizDAL qd = new QuizDAL();
        AsmDAL asd = new AsmDAL();
        ClassDAL cd = new ClassDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        System.out.println("kdoa" + ID);
        try {
            String sql = "SELECT lesson.* FROM lesson WHERE lesson.lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, ID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setStatus(rs.getInt("status"));
                l.setVideoLink(rs.getString("video_link"));
                l.setAttatchedFile(rs.getString("attatched_file"));
                if (rs.getInt("quiz_id") != 0) {
                    l.setQuiz(qd.getQuizByID(rs.getInt("quiz_id")));
                }
                System.out.println("5");
                if (rs.getInt("asm_id") != 0) {
                    l.setAsm(asd.getAsmByID(rs.getInt("asm_id")));
                }
                System.out.println("da");
                if (rs.getInt("class_id") != 0) {
                    l.setCls(cd.getClassByID(rs.getInt("class_id")));
                }
                GetFileName gfn = new GetFileName();
                if (l.getAttatchedFile() != null && !l.getAttatchedFile().equals("")) {
                    l.setFileName(gfn.getFileName(l.getAttatchedFile()));
                }
                System.out.println("o0d");
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                System.out.println("kdoa");
                l.setDescription(rs.getString("description"));
                return l;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Lesson> getAllLesson() {
        List<Lesson> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM lesson ORDER BY display_order, lesson_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Lesson> getLessonBySearch(String more, String order) {
        List<Lesson> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lesson " + more
                    + order;
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            SystemSettingDAL ssd = new SystemSettingDAL();
            AccountDAL ad = new AccountDAL();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Lesson> getLessonByKey(String key, List<Lesson> list, String more) {
        List<Lesson> list1 = new ArrayList<>();
        List<Lesson> list3 = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM lesson WHERE lesson_name LIKE '%" + key + "%'"
                    + more
                    + " ORDER BY display_order, lesson_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                list1.add(l);
            }
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (list1.get(i).getChapter().getChapterID() == list.get(j).getChapter().getChapterID()) {
                        list3.add(list1.get(i));
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list3;
    }

    public List<Lesson> getLessonByType(int id, List<Lesson> list) {
        List<Lesson> list1 = new ArrayList<>();
        List<Lesson> list3 = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM lesson WHERE lesson_type = ? "
                    + " ORDER BY display_order, lesson_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                list1.add(l);
            }
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (list1.get(i).getChapter().getChapterID() == list.get(j).getChapter().getChapterID()) {
                        list3.add(list1.get(i));
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list3;
    }

    public List<Lesson> getLessonByStatus(int status, List<Lesson> list) {
        List<Lesson> list1 = new ArrayList<>();
        List<Lesson> list3 = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM lesson WHERE status = ? "
                    + " ORDER BY display_order, lesson_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, status);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                list1.add(l);
            }
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (list1.get(i).getChapter().getChapterID() == list.get(j).getChapter().getChapterID()) {
                        list3.add(list1.get(i));
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list3;
    }

    public String getSortedLessonAsc(String id) {
        String order = "";
        switch (id) {
            case "ID": {
                order = "ORDER BY lesson_id ASC";
                break;
            }
            case "Name": {
                order = "ORDER BY lesson_name ASC";
                break;
            }
            case "Chapter": {
                order = "ORDER BY chapter_id ASC";
                break;
            }
            case "Type": {
                order = "ORDER BY lesson_type ASC";
                break;
            }
            case "Order": {
                order = "ORDER BY display_order ASC";
                break;
            }
            case "Status": {
                order = "ORDER BY status ASC";
                break;
            }
        }
        return order;
    }

    public String getSortedLessonDesc(String id) {
        String order = "";
        switch (id) {
            case "ID": {
                order = "ORDER BY lesson_id DESC";
                break;
            }
            case "Name": {
                order = "ORDER BY lesson_name DESC";
                break;
            }
            case "Chapter": {
                order = "ORDER BY chapter_id DESC";
                break;
            }
            case "Type": {
                order = "ORDER BY lesson_type DESC";
                break;
            }
            case "Order": {
                order = "ORDER BY display_order DESC";
                break;
            }
            case "Status": {
                order = "ORDER BY status DESC";
                break;
            }
        }
        return order;
    }

    public void actLesson(int n, int id) {

        try {
            String sql = "UPDATE Lesson SET status = ? WHERE lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, n);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("da" + e);
        }
    }

    public Lesson getLessonByIDnew(int ID) {
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM lesson";
            PreparedStatement st = DBContext().prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Lesson l = new Lesson();
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));

                return l;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public SubjectSetting getAllSettingBySettingID(int id) {
        List<SubjectSetting> list = new ArrayList<>();
        SubjectDAL subjectDAL = new SubjectDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE setting_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                return ss;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Lesson> getAllLessonQuiz(int chapterID) {
        List<Lesson> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        QuizDAL qd = new QuizDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM lesson WHERE quiz_id IS NOT NULL AND chapter_id = ? ORDER BY display_order, lesson_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, chapterID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                l.setQuiz(qd.getQuizByID(rs.getInt("quiz_id")));
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Lesson> getAllLessonAsm(int chapterID) {
        List<Lesson> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        AsmDAL asd = new AsmDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM lesson WHERE asm_id IS NOT NULL AND chapter_id = ? ORDER BY display_order, lesson_id";
            System.out.println(sql + " " + chapterID);
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, chapterID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                l.setAsm(asd.getAsmByID(rs.getInt("asm_id")));
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void addLessonVideoWithFile(Lesson l) {
        try {
            String sql = "INSERT INTO lesson"
                    + " (chapter_id, lesson_name, lesson_type, display_order, video_link, description, class_id, quiz_id, asm_id, created_by, created_at, status, attatched_file)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, l.getChapter().getChapterID());
            st.setString(2, l.getLessonName());
            st.setInt(3, l.getLessonType().getSetting_id());
            st.setInt(4, l.getDisplayOrder());
            st.setString(5, l.getVideoLink());
            st.setString(6, l.getDescription());
            if (l.getCls() != null) {
                st.setInt(7, l.getCls().getClassID());
            } else {
                st.setNull(7, java.sql.Types.INTEGER);
            }

            if (l.getQuiz() != null) {
                st.setInt(8, l.getQuiz().getQuizID());
            } else {
                st.setNull(8, java.sql.Types.INTEGER);
            }
            if (l.getAsm() != null) {
                st.setInt(9, l.getAsm().getAsmID());
            } else {
                st.setNull(9, java.sql.Types.INTEGER);
            }

            st.setInt(10, l.getCreatedBy().getID());

            st.setTimestamp(11, l.getCreatedAt());
            st.setInt(12, l.getStatus());
            st.setString(13, l.getAttatchedFile());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("da" + e);
        }
    }

    public void updLessonVideoWithFile(Lesson l) {
        System.out.println(l.getLessonID());
        try {
            String sql = "UPDATE lesson SET"
                    + " chapter_id = ?, lesson_name = ?, lesson_type = ?, display_order = ?, video_link = ?, description = ?, class_id = ?, quiz_id = ?, asm_id = ?, created_by = ?, created_at = ?, status = ?, attatched_file = ?, updated_by = ?, updated_at = ?"
                    + " WHERE lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, l.getChapter().getChapterID());
            System.out.println(1);
            st.setString(2, l.getLessonName());
            System.out.println(2);
            st.setInt(3, l.getLessonType().getSetting_id());
            System.out.println(3);
            st.setInt(4, l.getDisplayOrder());
            System.out.println(4);
            st.setString(5, l.getVideoLink());
            System.out.println(5);
            st.setString(6, l.getDescription());
            System.out.println(6);
            if (l.getCls() != null) {
                st.setInt(7, l.getCls().getClassID());
            } else {
                st.setNull(7, java.sql.Types.INTEGER);
            }
            System.out.println(7);
            if (l.getQuiz() != null) {
                st.setInt(8, l.getQuiz().getQuizID());
            } else {
                st.setNull(8, java.sql.Types.INTEGER);
            }
            System.out.println(8);
            if (l.getAsm() != null) {
                st.setInt(9, l.getAsm().getAsmID());
            } else {
                st.setNull(9, java.sql.Types.INTEGER);
            }
            System.out.println(9);
            st.setInt(10, l.getCreatedBy().getID());
            System.out.println(10);
            st.setTimestamp(11, l.getCreatedAt());
            System.out.println(11);
            st.setInt(12, l.getStatus());
            System.out.println(12);
            st.setString(13, l.getAttatchedFile());
            System.out.println(13);
            st.setInt(14, l.getUpdatedBy().getID());
            System.out.println(14);
            st.setTimestamp(15, l.getUpdatedAt());
            System.out.println(15);
            st.setInt(16, l.getLessonID());
            System.out.println(16);

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("da" + e);
        }
    }

    public List<SubjectSetting> getAllSettingBySubject(int id, int start, int total) {
        List<SubjectSetting> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        DimensionDAO dd = new DimensionDAO();
        try {
            String sql = "SELECT * FROM subject_setting WHERE subject_id = ? LIMIT ?, ? ";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, start);
            st.setInt(3, total);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SubjectSetting ss = new SubjectSetting();
                ss.setSettingID(rs.getInt("setting_id"));
                ss.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                ss.setSettingName(rs.getString("setting_name"));
                ss.setStatus(rs.getInt("status"));
                ss.setDisplayOrder(rs.getInt("display_order"));
                ss.setSettingType(rs.getString("setting_type"));
                ss.setDescription(rs.getString("description"));
                ss.setDimen(dd.getDimensionByID(rs.getInt("setting_id")));
                list.add(ss);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getNoOfRecords(int id) throws SQLException, ClassNotFoundException {
        int noOfRecords = 0;
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "SELECT COUNT(*) FROM subject_setting WHERE subject_id = ?";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, id);
            //4. Execute Query
            rs = stm.executeQuery();
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
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
        return noOfRecords;
    }

    public boolean addSubjectSettingChapter(SubjectSetting ss) {
        try {
            String sql = "INSERT INTO subject_setting (setting_name, description, display_order, subject_id, status, setting_type) VALUES (?, ?, ?, ?, ?, 'Chapter')";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, ss.getSettingName());
            st.setString(2, ss.getDescription());
            st.setInt(3, ss.getDisplayOrder());
            int subjectID = ss.getS().getSubjectID();
            st.setInt(4, subjectID);
            st.setInt(5, ss.getStatus());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean addSubjectSettingDimension(SubjectSetting ss) {
        try {
            String sql = "INSERT INTO subject_setting (setting_name, description, display_order, subject_id, status, setting_type, dimension_type) VALUES (? , ? , ? , ? , ? , 'Dimension', ? )";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, ss.getSettingName());
            st.setString(2, ss.getDescription());
            st.setInt(3, ss.getDisplayOrder());
            int subjectID = ss.getS().getSubjectID();
            st.setInt(4, subjectID);
            st.setInt(5, ss.getStatus());
            DimensionDAO dd = new DimensionDAO();
            st.setInt(6, dd.getDimensionTypeByName(ss.getDimen().getType(), ss.getS().getSubjectID()).getId());
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updSubjectSetting(SubjectSetting ss) {
        try {
            String sql = "UPDATE subject_setting SET setting_name= ?,"
                    + " description = ? ,"
                    + " display_order = ? ,"
                    + " status = ? "
                    + "WHERE setting_id = ? ";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, ss.getSettingName());
            st.setString(2, ss.getDescription());
            st.setInt(3, ss.getDisplayOrder());
            st.setInt(4, ss.getStatus());
            st.setInt(5, ss.getSettingID());
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
