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
import model.Account;
import model.Chapter;
import model.Dimension;
import model.DimensionType;
import model.Subject;

/**
 *
 * @author ADMIN
 */
public class DimensionDAO extends DBContext implements Serializable {

    public ArrayList<DimensionType> getDimenTypeBySubjectID(int id)
            throws SQLException, ClassNotFoundException {
        ArrayList<DimensionType> listDimenType = new ArrayList<DimensionType>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "Select *"
                    + "FROM dimension_type "
                    + "WHERE subject_id = ? "
                    + "ORDER BY display_order ASC";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, id);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            if (rs.next()) {
                SubjectDAL SubjectDAO = new SubjectDAL();
                Subject subject = SubjectDAO.getSubjectByID(rs.getInt("subject_id"));
                DimensionType dt = new DimensionType();
                dt.setId(rs.getInt("type_id"));
                dt.setName(rs.getString("type_name"));
                dt.setSubject(subject);
                dt.setStatus(rs.getBoolean("status"));
                listDimenType.add(dt);
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
        return listDimenType;
    }

    public DimensionType getDimensionTypeByName(String name, int sId) {
        DimensionType dt = new DimensionType();
        SubjectDAL sd = new SubjectDAL();
        try {
            String sql = "Select * From dimension_type where type_name = ? and subject_id = ? ";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, sId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                dt.setId(rs.getInt("type_id"));
                dt.setName(rs.getString("type_name"));
                dt.setStatus(rs.getBoolean("status"));
                dt.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return dt;
    }

    public Dimension getDimensionByID(int dimensionID) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE setting_id = ?";
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
                d.setDimensionID(dimensionID);
                d.setDimensionName(rs.getString("setting_name"));
                d.setDisplayOrder(rs.getInt("display_order"));
                d.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                d.setStatus(rs.getInt("status"));
                d.setType(getDimensionTypeByID(dimensionID));
                return d;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String getDimensionTypeByID(int dimensionID) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        String type = null;
        try {
            String sql = "SELECT * FROM dimension_type WHERE type_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, dimensionID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                type = rs.getString("type_name");
            }
            return type;
        } catch (Exception e) {
            System.out.println(e);
        }
        return type;
    }

    public int getDimensionTypeIdByType(String type) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        int typeID = 0;
        try {
            String sql = "SELECT * FROM dimension_type WHERE type_name = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, type);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                typeID = rs.getInt("type_id");
            }
            return typeID;
        } catch (Exception e) {
            System.out.println(e);
        }
        return typeID;
    }

    public ArrayList<Dimension> getDimenBySubjectID(int id)
            throws SQLException, ClassNotFoundException {
        ArrayList<Dimension> listDimen = new ArrayList<Dimension>();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "Select *"
                    + "FROM dimension "
                    + "WHERE subject_id = ? "
                    + "ORDER BY display_order ASC";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setInt(1, id);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            if (rs.next()) {
                SubjectDAL SubjectDAO = new SubjectDAL();
                AccountDAL AccountDAO = new AccountDAL();
                SystemSettingDAL SSDAO = new SystemSettingDAL();
                Account creater;
                Account updater;
                creater = AccountDAO.getAccountByAccID(rs.getInt("created_by"));
                updater = AccountDAO.getAccountByAccID(rs.getInt("updated_by"));
                Subject subject = SubjectDAO.getSubjectByID(rs.getInt("subject_id"));

                Chapter c = new Chapter(rs.getInt("chapter_id"),
                        subject,
                        rs.getString("chapter_name"),
                        rs.getInt("display_order"),
                        rs.getString("description"),
                        creater,
                        rs.getTimestamp("created_at"),
                        updater,
                        rs.getTimestamp("updated_at"),
                        rs.getInt("status"));
                Dimension d = new Dimension(rs.getInt("dimension_id"),
                        subject,
                        rs.getString("dimension_name"),
                        rs.getInt("display_order"),
                        rs.getString("description"),
                        creater,
                        rs.getTimestamp("created_at"),
                        updater,
                        rs.getTimestamp("updated_at"),
                        rs.getInt("status"));

                listDimen.add(d);
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
        return listDimen;
    }
//
//    public boolean updateDimensionByID(Dimension dimen) {
//        SubjectDAL sd = new SubjectDAL();
//        AccountDAL ad = new AccountDAL();
//        SystemSettingDAL ssd = new SystemSettingDAL();
//        try {
//            String sql = "UPDATE dimension SET created_at = ?,"
//                    + " created_by = ?,"
//                    + " updated_at = ?, "
//                    + "updated_by = ?, "
//                    + "description = ?,"
//                    + " dimension_name = ?,"
//                    + " display_order = ?, "
//                    + "subject_id = ?, "
//                    + "status = ?, "
//                    + "dimension_type = ? "
//                    + "WHERE dimension_id = ?";
//            PreparedStatement st = DBContext().prepareStatement(sql);
//            st.setInt(1, dimen.getDimensionID());
//            st.setTimestamp(1, dimen.getCreatedAt());
//            int createrID = dimen.getCreatedBy().getID();
//            st.setInt(2, createrID);
//            st.setTimestamp(3, dimen.getUpdatedAt());
//            int updaterID = dimen.getUpdatedBy().getID();
//            st.setInt(4, updaterID);
//            st.setString(5, dimen.getDescription());
//            st.setString(6, dimen.getDimensionName());
//            st.setInt(7, dimen.getDisplayOrder());
//            int subjectID = sd.GetSubjectID(dimen.getSubject());
//            st.setInt(8, subjectID);
//            st.setInt(9, dimen.getStatus());
//            int settingID = ssd.getIDBySetting(dimen.getType());
//            st.setInt(10, settingID);
//            st.setInt(11, dimen.getDimensionID());
//            int rowsUpdated = st.executeUpdate();
//            return rowsUpdated > 0;
//        } catch (Exception e) {
//            System.out.println("updateDimen: "+e);
//        }
//        return false;
//    }

    public void activateDimension(int id) {
        try {
            String sql = "UPDATE dimension SET status = 1 WHERE dimension_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deactivateDimension(int id) {
        try {
            String sql = "UPDATE dimension SET status = 0 WHERE dimension_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean addDimensionType(DimensionType dt) {
        try {
            String sql = "INSERT INTO dimension_type (type_name, subject_id , status) VALUES (? , ? , ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, dt.getName());
            st.setInt(2,dt.getSubject().getSubjectID());
            st.setBoolean(3, dt.isStatus());
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updDimensionType(DimensionType dt) {
        try {
            String sql = "UPDATE dimension_type SET type_name = ? AND status = ? WHERE type_id = ? ";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, dt.getName());
            st.setBoolean(2, dt.isStatus());
            st.setInt(3, dt.getId());
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

//    public boolean addDimension(Dimension dimension) {
//        SubjectDAL sd = new SubjectDAL();
//        AccountDAL ad = new AccountDAL();
//        SystemSettingDAL ssd = new SystemSettingDAL();
//        try {
//            String sql = "INSERT INTO dimension "
//                    + "(created_at, created_by, updated_at, updated_by, description, dimension_name, display_order, subject_id, status, dimension_type) "
//                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement st = DBContext().prepareStatement(sql);
//            st.setTimestamp(1, dimension.getCreatedAt());
//            int createrID = dimension.getCreatedBy().getID();
//            st.setInt(2, createrID);
//            st.setTimestamp(3, dimension.getUpdatedAt());
//            int updaterID = dimension.getUpdatedBy().getID();
//            st.setInt(4, updaterID);
//            st.setString(5, dimension.getDescription());
//            st.setString(6, dimension.getDimensionName());
//            st.setInt(7, dimension.getDisplayOrder());
//            int subjectID = sd.GetSubjectID(dimension.getSubject());
//            st.setInt(8, subjectID);
//            st.setInt(9, dimension.getStatus());
//            int settingID = ssd.getIDBySetting(dimension.getType());
//            st.setInt(10, settingID);
//
//            int rowsInserted = st.executeUpdate();
//            return rowsInserted > 0;
//        } catch (Exception e) {
//            System.out.println("addDimen: "+e);
//        }
//        return false;
//    }
}
