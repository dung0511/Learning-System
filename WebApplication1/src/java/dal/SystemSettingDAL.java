/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.SystemSetting;

/**
 *
 * @author acer
 */
public class SystemSettingDAL extends DBContext {

    public SystemSetting getSettingByName(String name) {
        try {
            String sql = "SELECT * FROM system_setting WHERE setting_name = '" + name + "'";
         
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                SystemSetting ss = new SystemSetting();
                ss.setDisplay_order(rs.getInt("display_order"));
                ss.setSetting_id(rs.getInt("setting_id"));
                ss.setSetting_name(rs.getString("setting_name"));
                ss.setSetting_status(rs.getBoolean("setting_status"));
                ss.setSetting_type(rs.getString("setting_type"));
                return ss;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public SystemSetting getSettingByID(int id) {
        try {
            String sql = "SELECT * FROM system_setting WHERE setting_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                SystemSetting ss = new SystemSetting();
                ss.setDisplay_order(rs.getInt("display_order"));
                ss.setSetting_id(rs.getInt("setting_id"));
                ss.setSetting_name(rs.getString("setting_name"));
                ss.setSetting_status(rs.getBoolean("setting_status"));
                ss.setSetting_type(rs.getString("setting_type"));
                return ss;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int getIDBySetting(SystemSetting s) {
        try {
            String sql = "SELECT setting_id FROM system_setting WHERE setting_tyoe = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, s.getSetting_type());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("setting_id");
                return id;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public List<SystemSetting> getAllSemesterByO() {
        List<SystemSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM system_setting WHERE setting_type = 'Semester'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SystemSetting ss = new SystemSetting(rs.getString("setting_name"), rs.getString("setting_type"), rs.getBoolean("setting_status"));
                ss.setSetting_id(rs.getInt("setting_id"));
                list.add(ss);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<String> getAllSemester() {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM system_setting WHERE setting_type = 'Semester'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("setting_name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public SystemSetting getSetting(String s) {
        try {
            String sql = "SELECT * FROM system_setting WHERE setting_name = '" + s + "'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SystemSetting ss = new SystemSetting();
                ss.setSetting_id(rs.getInt("setting_id"));
                ss.setSetting_name(rs.getString("setting_name"));
                ss.setSetting_status(rs.getBoolean("setting_status"));
                ss.setSetting_type(rs.getString("setting_type"));
                ss.setDisplay_order(rs.getInt("display_order"));
                return ss;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<SystemSetting> getAllLessonType() {
        List<SystemSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM system_setting WHERE setting_type = 'Lesson Type'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SystemSetting ss = new SystemSetting();
                ss.setSetting_id(rs.getInt("setting_id"));
                ss.setSetting_name(rs.getString("setting_name"));
                ss.setSetting_status(rs.getBoolean("setting_status"));
                ss.setSetting_type(rs.getString("setting_type"));
                ss.setDisplay_order(rs.getInt("display_order"));
                list.add(ss);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
