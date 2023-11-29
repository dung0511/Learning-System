/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Date;

/**
 *
 * @author acer
 */
public class FormatDate {
    public String formatDateSQL(Date d) {
        String t = d.toString();
        String a = "";
        String date = "", month = "", year = "";
        int k = 0, count = 0;
        for (int i = 0; i < t.length(); i++) {
            if(t.charAt(i) == '-' && count == 0) {
                 year = t.substring(k, i);
                 count++;
                 k = i;
            }
            else if(t.charAt(i) == '-' && count == 1) {
                 month = t.substring(k + 1, i);
                 count++;
                 k = i;
            }
            else if(i == t.length() - 1) {
                date = t.substring(k + 1, i + 1);
            }
        }
        return date + "-" + month+ "-" + year;
    }
    
    public Date formatStringToDateSQL(String d) {
        String t = d;
        String a = "";
        String date = "", month = "", year = "";
        int k = 0, count = 0;
        for (int i = 0; i < t.length(); i++) {
            if(t.charAt(i) == '-' && count == 0) {
                 year = t.substring(k, i);
                 count++;
                 k = i;
            }
            else if(t.charAt(i) == '-' && count == 1) {
                 month = t.substring(k + 1, i);
                 count++;
                 k = i;
            }
            else if(i == t.length() - 1) {
                date = t.substring(k + 1, i + 1);
            }
        }
        return Date.valueOf(date + "-" + month + "-" + year);
    }
}
