/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.StudentIClass;
import model.Subject;
import model.SystemSetting;
import util.FormatDate;
import util.FormatTime;

/**
 *
 * @author acer
 */
public class ClassDAL extends DBContext {

    public List<model.Class> getAllClassThatTraineeIn(int student_id, Date d) {
        List<model.Class> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        FormatDate fd = new FormatDate();
        FormatTime ft = new FormatTime();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT class.* FROM student_class JOIN class ON student_class.class_id = class.class_id "
                    + "AND student_class.subject_Id = class.subject_id "
                    + "WHERE student_id = ? "
                    + "AND (class.start_date <= ? AND ? <= class.end_date "
                    + ")";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, student_id);
            st.setDate(2, d);
            st.setDate(3, d);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class();
                c.setActivate(rs.getInt("status"));
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));

                c.setStartDate(rs.getDate("start_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                c.setTrainer(ad.getAccountByAccID(rs.getInt("trainer")));
                c.setStartS(fd.formatDateSQL(c.getStartDate()));
                c.setEndS(fd.formatDateSQL(c.getEndDate()));
                c.setDisplayOrder(rs.getInt("display_order"));
                list.add(c);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
        return list;
    }

    public int getNumOfStudentByClassID(int classID) {
        try {
            String sql = "SELECT COUNT(StudentID) AS T FROM class JOIN student_class ON class.class_id = student_class.class_id "
                    + "WHERE class.class_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("1");
        }
        return 0;
    }

    public Account getTrainerByClassID(int classID) {

        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT trainer FROM class WHERE class_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = ad.getAccountByAccID(rs.getInt("trainer"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<model.Class> getAllClass() {
        SystemSettingDAL ssd = new SystemSettingDAL();
        List<model.Class> list = new ArrayList<>();
        FormatDate fd = new FormatDate();
        FormatTime ft = new FormatTime();
        try {
            String sql = "SELECT class.*  FROM class ";
            SubjectDAL sd = new SubjectDAL();
            SystemSettingDAL ss = new SystemSettingDAL();
            AccountDAL ad = new AccountDAL();
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class();
                c.setActivate(rs.getInt("status"));
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));

                c.setStartDate(rs.getDate("start_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                c.setTrainer(ad.getAccountByAccID(rs.getInt("trainer")));
                c.setStartS(fd.formatDateSQL(c.getStartDate()));
                c.setEndS(fd.formatDateSQL(c.getEndDate()));
                c.setDisplayOrder(rs.getInt("display_order"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public model.Class getClassByID(int id) {
        SystemSettingDAL ssd = new SystemSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        FormatDate fd = new FormatDate();
        FormatTime ft = new FormatTime();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM class WHERE class.class_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class();
                c.setActivate(rs.getInt("status"));
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));

                c.setStartDate(rs.getDate("start_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                c.setTrainer(ad.getAccountByAccID(rs.getInt("trainer")));
                c.setStartS(fd.formatDateSQL(c.getStartDate()));
                c.setEndS(fd.formatDateSQL(c.getEndDate()));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setDescription(rs.getString("description"));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<model.Class> getClassByPage(int start, int end, List<model.Class> list) {
        List<model.Class> list2 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list2.add(list.get(i));
        }
        return list2;
    }

    public List<model.Class> getClassBySearch(String search) {
        List<model.Class> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();
        FormatTime ft = new FormatTime();

        try {
            String sql = "SELECT class.* FROM class  WHERE class_name LIKE '%" + search + "%'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                model.Class c = new model.Class();
                c.setActivate(rs.getInt("status"));
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));

                c.setStartDate(rs.getDate("start_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                c.setTrainer(ad.getAccountByAccID(rs.getInt("trainer")));
                c.setStartS(fd.formatDateSQL(c.getStartDate()));
                c.setEndS(fd.formatDateSQL(c.getEndDate()));
                c.setDisplayOrder(rs.getInt("display_order"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<model.Class> getClassBySearchAFilter(String search, String subjectID, int semester, String condition) {
        List<model.Class> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();
        FormatTime ft = new FormatTime();
        try {
            String sql;
            PreparedStatement st;
            if (subjectID.equals("0") && semester != 0) {
                sql = "SELECT class.*  FROM class WHERE class_name LIKE '%" + search + "%' AND semester = ? AND " + condition;
                st = DBContext().prepareStatement(sql);
                st.setInt(1, semester);
            } else if (semester == 0 && !(subjectID.equals("0"))) {
                sql = "SELECT class.* FROM class WHERE class_name LIKE '%" + search + "%' AND subject_id = ? AND " + condition;
                st = DBContext().prepareStatement(sql);
                st.setString(1, subjectID);
            } else if ((semester == 0 && subjectID.equals("0"))) {
                sql = "SELECT class.* FROM class WHERE class_name LIKE '%" + search + "%' AND " + condition;
                st = DBContext().prepareStatement(sql);
            } else {
                sql = "SELECT class.* FROM class  WHERE class_name LIKE '%" + search + "%' AND subject_id = ? AND semester = ? AND " + condition;
                st = DBContext().prepareStatement(sql);
                st.setString(1, subjectID);
                st.setInt(2, semester);
            }
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class();
                c.setActivate(rs.getInt("status"));
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));

                c.setStartDate(rs.getDate("start_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                c.setTrainer(ad.getAccountByAccID(rs.getInt("trainer")));
                c.setStartS(fd.formatDateSQL(c.getStartDate()));
                c.setEndS(fd.formatDateSQL(c.getEndDate()));
                c.setDisplayOrder(rs.getInt("display_order"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("dada" + e);
        }
        return list;
    }

    public void activateClass(int classID, int subjectID) {
        try {
            String sql = "UPDATE class SET status = 1 WHERE class_id = ? AND subject_id = ? ";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setInt(2, subjectID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deactivateClass(int classID, int subjectID) {
        try {
            String sql = "UPDATE class SET status = 0 WHERE class_id = ? AND subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setInt(2, subjectID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insClass(String className, int semester, int subjectID, int status, Date start, Date end, Time startT, Time endT, int trainerID, int order, String description) {
        try {
            String sql = "INSERT INTO class (class_name, subject_id, semester, status, start_date, start_time, end_date, end_time, trainer, display_order, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, className);
            st.setInt(2, subjectID);
            st.setInt(3, semester);
            st.setInt(4, status);
            st.setDate(5, start);
            st.setTime(6, startT);
            st.setDate(7, end);
            st.setTime(8, endT);
            st.setInt(9, trainerID);
            st.setInt(10, order);
            st.setString(11, description);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void insStudentIClassByClassIDASemASj(String studentID, int classID, int sem, int subjectID, Date start, Date end, Time startT, Time endT) {
        try {
            String sql = "INSERT INTO student_class (username, class_id, semester, subject_id, start_date, end_date, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, studentID);
            st.setInt(2, classID);
            st.setInt(3, sem);
            st.setInt(4, subjectID);
            st.setDate(5, start);
            st.setDate(6, end);
            st.setTime(7, startT);
            st.setTime(8, endT);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<StudentIClass> getStudentIClassByClassIDASemASj(int classID, int subjectID) {
        List<StudentIClass> list = new ArrayList<>();
        try {
            String sql = "SELECT *, account.username FROM student_class JOIN account ON student_class.student_id = account.account_id WHERE class_id = ? AND subject_id = ? ";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setInt(2, subjectID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StudentIClass sic = new StudentIClass(rs.getString("username"), classID, rs.getString("semester"), subjectID, rs.getDate("start_date"), rs.getDate("end_date"), rs.getTime("start_time"), rs.getTime("end_time"));
                list.add(sic);
            }
        } catch (Exception e) {
            System.out.println("get" + e);
        }
        return list;
    }

    public void dltStudentIClassByClassIDASemASj(int classID, int subjectID) {
        try {
            String sql = "DELETE FROM student_class WHERE class_id = ? AND subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setInt(2, subjectID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("dlt" + e);
        }
    }

    public model.Class getClassByNameASj(String name, int subjectID) {
        try {
            String sql = "SELECT * FROM class WHERE class_name = ? AND subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, subjectID);
            AccountDAL ad = new AccountDAL();
            FormatDate fd = new FormatDate();
            FormatTime ft = new FormatTime();
            SubjectDAL sd = new SubjectDAL();
            SystemSettingDAL ssd = new SystemSettingDAL();
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                model.Class c = new model.Class();
                c.setActivate(rs.getInt("status"));
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));

                c.setStartDate(rs.getDate("start_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                c.setTrainer(ad.getAccountByAccID(rs.getInt("trainer")));
                c.setStartS(fd.formatDateSQL(c.getStartDate()));
                c.setEndS(fd.formatDateSQL(c.getEndDate()));
                c.setDisplayOrder(rs.getInt("display_order"));
                return c;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updClass(int classID, String className, int subjectIDOld, int activate, int subjectID, int semester, Date start, Date end, Time startT, Time endT, int trainerID, int order, String description) {
        try {
            List<StudentIClass> listSIC = getStudentIClassByClassIDASemASj(classID, subjectIDOld);
            String sql = "UPDATE class Set class_name = ?, subject_id = ?, semester = ?, status = ?, start_date = ?, start_time = ?, end_date = ?, end_time = ?, trainer = ?, display_order = ?, description = ? WHERE class_id = ? ";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, className);
            st.setInt(2, subjectID);
            st.setInt(3, semester);
            st.setInt(4, activate);
            st.setDate(5, start);
            st.setTime(6, startT);
            st.setDate(7, end);
            st.setTime(8, endT);
            st.setInt(9, trainerID);
            st.setInt(10, order);
            st.setString(11, description);
            st.setInt(12, classID);
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println("DA" + e);
        }
    }

    public model.Class getClassByIDASjASem(int classID, int subjectID) {
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();
        FormatTime ft = new FormatTime();
        try {
            String sql = "SELECT * FROM class  WHERE class_id= ? AND subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setInt(2, subjectID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                model.Class c = new model.Class();
                c.setActivate(rs.getInt("status"));
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));

                c.setStartDate(rs.getDate("start_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                c.setTrainer(ad.getAccountByAccID(rs.getInt("trainer")));
                c.setStartS(fd.formatDateSQL(c.getStartDate()));
                c.setEndS(fd.formatDateSQL(c.getEndDate()));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setDescription(rs.getString("description"));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public model.Class getClassBySubjectASem(int subjectID, int semester) {
        try {
            SubjectDAL sd = new SubjectDAL();
            SystemSettingDAL ssd = new SystemSettingDAL();
            AccountDAL ad = new AccountDAL();
            FormatDate fd = new FormatDate();
            FormatTime ft = new FormatTime();
            String sql = "SELECT * FROM class WHERE subject_id = ? AND semester = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, subjectID);
            st.setInt(2, semester);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                model.Class c = new model.Class();
                c.setActivate(rs.getInt("status"));
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));

                c.setStartDate(rs.getDate("start_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                c.setTrainer(ad.getAccountByAccID(rs.getInt("trainer")));
                c.setStartS(fd.formatDateSQL(c.getStartDate()));
                c.setEndS(fd.formatDateSQL(c.getEndDate()));
                c.setDisplayOrder(rs.getInt("display_order"));

                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<model.Class> getClassByFilter(String subjectID, int semester, String condition) {
        List<model.Class> list = new ArrayList<>();
        SystemSettingDAL ssd = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        SubjectDAL sd = new SubjectDAL();
        FormatDate fd = new FormatDate();
        FormatTime ft = new FormatTime();
        try {
            String sql;
            PreparedStatement st;
            if (subjectID.equals("0") && semester != 0) {
                sql = "SELECT class.* FROM class WHERE semester = ? AND " + condition;
                st = DBContext().prepareStatement(sql);
                st.setInt(1, semester);
            } else if (semester == 0 && !(subjectID.equals("0"))) {
                sql = "SELECT class.* FROM class WHERE subject_id = ? AND " + condition;
                st = DBContext().prepareStatement(sql);
                st.setString(1, subjectID);
            } else if ((semester == 0 && (subjectID.equals("0")))) {
                sql = "SELECT class.* FROM class WHERE " + condition;
                st = DBContext().prepareStatement(sql);
            } else {
                sql = "SELECT class.* FROM class WHERE subject_id = ? AND semester = ? AND " + condition;
                st = DBContext().prepareStatement(sql);
                st.setString(1, subjectID);
                st.setInt(2, semester);

            }
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class();
                c.setActivate(rs.getInt("status"));
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));

                c.setStartDate(rs.getDate("start_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                c.setTrainer(ad.getAccountByAccID(rs.getInt("trainer")));
                c.setStartS(fd.formatDateSQL(c.getStartDate()));
                c.setEndS(fd.formatDateSQL(c.getEndDate()));
                c.setDisplayOrder(rs.getInt("display_order"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("da" + e);
        }
        return list;
    }
}
