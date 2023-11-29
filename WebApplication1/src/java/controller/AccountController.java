/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAL;
import model.User;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author ADMIN
 */
public class AccountController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AccountController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            String username = a.getUser();
            String action = " ";
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            System.out.println(username + " " + action + " " + a.getPass());
            AccountDAL util = new AccountDAL();
            Account account;
            account = util.getAccountByUsername(username);
            request.setAttribute("accountDetail", account);
            if ("edit".equalsIgnoreCase(action)) {
                request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                return;
            }
            //request.getRequestDispatcher("view/AccountDetail.jsp").forward(request, response);
            request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      try {
            System.out.println("1");
            AccountDAL util = new AccountDAL();
            UserDAO ud = new UserDAO();
            HttpSession session = request.getSession();
            Account ab = (Account) session.getAttribute("account");
            String username = request.getParameter("username");
            Account a = util.getAccountByUsername(username);
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobileNumber");
            int accountId = a.getID();

            if (fullname == null || email == null || mobile == null) {
                request.setAttribute("errorMessage", "Vui lòng không để trống bất kỳ trường nào.");
                Account account = util.getAccountByUsername(username);
                request.setAttribute("accountDetail", account);
                request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                request.setAttribute("errorMessage", "Email không hợp lệ.");
                Account account = util.getAccountByUsername(username);
                request.setAttribute("accountDetail", account);
                request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                return;
            }
            if (ud.checkEmailExist(email,accountId)) {
                request.setAttribute("errorMessage", "Email đã tồn tại.");
                Account account = util.getAccountByUsername(username);
                request.setAttribute("accountDetail", account);
                request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                return;
            }

            boolean result = util.updateAccountInfo(fullname, email, mobile, accountId);
            System.out.println(result + "doPost");
            if (result) {
                Account account = util.getAccountByUsername(username);
                if (account != null) {
                    request.setAttribute("accountDetail", account);
                    request.setAttribute("successMessage", "Cập nhật thông tin tài khoản thành công.");
                    //request.getRequestDispatcher("view/AccountDetail.jsp").forward(request, response);
                    request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                } else {
                    //request.getRequestDispatcher("view/AccountDetail.jsp").forward(request, response);
                    request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật thông tin tài khoản. Vui lòng thử lại.");
                Account account = util.getAccountByUsername(username);
                if (account != null) {
                    request.setAttribute("accountDetail", account);
                    request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                } else {
                    //request.getRequestDispatcher("view/AccountDetail.jsp").forward(request, response);
                    request.getRequestDispatcher("view/EditAccount.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
