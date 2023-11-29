/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dal.NotificationDAL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Account;
import model.Notification;

/**
 *
 * @author acer
 */
public class Menu {

    public void getNotification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account)session.getAttribute("account");
        if(a != null) {
            NotificationDAL nd = new NotificationDAL();
            List<Notification> list = nd.getTop5NotiByDate(a.getID());
            for (int i = 0; i < list.size(); i++) {
            }
            request.setAttribute("noti", list);
        }
    }
}
