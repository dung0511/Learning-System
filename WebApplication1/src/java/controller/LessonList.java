/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AsmDAL;
import dal.ClassDAL;
import dal.DiscussionDAL;
import dal.QuizDAL;
import dal.SubjectDAL;
import dal.SubjectSettingDAL;
import dal.SystemSettingDAL;
import dal.VoteDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import model.Account;
import model.Assignment;
import model.Lesson;
import model.Quiz;
import util.Paging;
import util.ValidInput;

/**
 *
 * @author acer
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1000, // 2MB
        maxFileSize = 1024 * 1024 * 1000, // 50MB
        maxRequestSize = 1024 * 1024 * 1000)
public class LessonList extends HttpServlet {

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
            out.println("<title>Servlet LessonList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LessonList at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("act") == null || request.getParameter("act").equals("")) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            switch (request.getParameter("act")) {
                case "list": {
                    listLessonGet(request, response);
                    break;
                }
                case "add": {
                    addLesson(request, response);
                    break;
                }
                case "upd": {
                    updLesson(request, response);
                    break;
                }
                case "act": {
                    activateDeactivateLesson(request, response);
                    break;
                }
            }
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
        Instant instant = Instant.now();
        Timestamp now = Timestamp.from(instant);
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        System.out.println(request.getParameter("act"));
        if (request.getParameter("act").equals("list")) {
            BufferedReader reader = request.getReader();
            String text = reader.readLine();
            DiscussionDAL dd = new DiscussionDAL();
            VoteDAL vd = new VoteDAL();
            while (text != null) {
                String id_raw = "";
                System.out.println("duako" + text);
                for (int i = 0; i < text.length(); i++) {
                    if (text.substring(i, i + 3).equals("id=")) {
                        id_raw = text.substring(i + 3);
                        break;
                    }
                }
                int id = Integer.parseInt(id_raw);
                SubjectSettingDAL ssd = new SubjectSettingDAL();
                if (ssd.getLessonByID(id).getStatus() == 1) {
                    ssd.actLesson(0, id);
                } else {
                    ssd.actLesson(1, id);
                }
                text = reader.readLine();
            }
        } else if (request.getParameter("act") != null && request.getParameter("act").equals("add")) {
            if (request.getParameter("add") != null) {
                if (request.getParameter("type2").equals("1")) {
                    String subject_raw = request.getParameter("subject");
                    String quiz_raw = request.getParameter("quiz");
                    String asm_raw = request.getParameter("asm");
                    int subjectID = 0, quizID = 0, asmID = 0, chapterID = 0;
                    try {
                        if (subject_raw != null
                                && !subject_raw.equals("")) {
                            subjectID = Integer.parseInt(subject_raw);
                        }
                        if (quiz_raw != null
                                && !quiz_raw.equals("")) {
                            quizID = Integer.parseInt(quiz_raw);
                        }
                        if (asm_raw != null
                                && !asm_raw.equals("")) {
                            asmID = Integer.parseInt(asm_raw);
                        }
                        if (request.getParameter("chapter") != null
                                && !request.getParameter("chapter").equals("")) {
                            chapterID = Integer.parseInt(request.getParameter("chapter"));
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    SubjectSettingDAL ssd = new SubjectSettingDAL();
                    SubjectDAL sd = new SubjectDAL();
                    ClassDAL cd = new ClassDAL();

                    request.setAttribute("sID", subjectID);
                    request.setAttribute("listS", sd.getAllSubject(" 1 = 1 "));
                    if (subjectID == 0) {
                        request.setAttribute("listC", ssd.getAllChapterBySubject(1));
                        if (ssd.getChapterByID(chapterID).getSubject().getSubjectID() != subjectID) {
                            chapterID = ssd.getAllChapterBySubject(1).get(0).getChapterID();
                        }

                    } else {
                        request.setAttribute("listC", ssd.getAllChapterBySubject(subjectID));
                        request.setAttribute("listQ", ssd.getAllLessonQuiz(1));
                        request.setAttribute("listA", ssd.getAllLessonAsm(1));
                        if (ssd.getChapterByID(chapterID).getSubject().getSubjectID() != subjectID) {
                            chapterID = ssd.getAllChapterBySubject(subjectID).get(0).getChapterID();
                        }
                    }
                    if (chapterID == 0) {
                        if (subjectID == 0) {
                            request.setAttribute("listQ", ssd.getAllLessonQuiz(ssd.getAllChapterBySubject(1).get(0).getChapterID()));
                            request.setAttribute("listA", ssd.getAllLessonAsm(ssd.getAllChapterBySubject(1).get(0).getChapterID()));
                        } else {
                            request.setAttribute("listQ", ssd.getAllLessonQuiz(ssd.getAllChapterBySubject(subjectID).get(0).getChapterID()));
                            request.setAttribute("listA", ssd.getAllLessonAsm(ssd.getAllChapterBySubject(subjectID).get(0).getChapterID()));
                        }
                    } else {
                        request.setAttribute("listQ", ssd.getAllLessonQuiz(chapterID));
                        request.setAttribute("listA", ssd.getAllLessonAsm(chapterID));
                    }
                    request.setAttribute("chapterID", chapterID);
                    request.setAttribute("quizID", quizID);
                    request.setAttribute("asmID", asmID);
                    request.setAttribute("add", 1);
                    request.setAttribute("listCls", cd.getAllClass());
                    request.getRequestDispatcher("view/NewLesson.jsp").forward(request, response);
                } else {
                    String subject_raw = request.getParameter("subject");
                    String chapter_raw = request.getParameter("chapter");
                    String name = request.getParameter("name");
                    String order_raw = request.getParameter("order");
                    String status_raw = request.getParameter("status");
                    String class_raw = request.getParameter("class");
                    String description = request.getParameter("summerNoteText");
                    int subjectID = 0, chapterID = 0, order = 0, status = 0, classID = 0;
                    SystemSettingDAL sstd = new SystemSettingDAL();
                    try {
                        subjectID = Integer.parseInt(subject_raw);
                        chapterID = Integer.parseInt(chapter_raw);
                        order = Integer.parseInt(order_raw);
                        status = Integer.parseInt(status_raw);
                        classID = Integer.parseInt(class_raw);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    try {
                        if (request.getParameter("type").equals("video")) {
                            Part filePart = request.getPart("file1");
                            String video = request.getParameter("link");
                            if (filePart.getSubmittedFileName() != null
                                    && !filePart.getSubmittedFileName().equals("")) {
                                String fileD = "";
                                for (int i = 0; i < filePart.getSubmittedFileName().length(); i++) {
                                    if (filePart.getSubmittedFileName().charAt(i) == '.') {
                                        fileD = filePart.getSubmittedFileName().substring(i);
                                    }
                                }
                                String htai = now.toString().replaceAll(" ", "");

                                htai = htai.replaceAll("\\.", "");
                                htai = htai.replaceAll("\\:", "");
                                htai = htai.replaceAll("\\-", "");
                                //   String uploadDirectory = request.getServletContext().getRealPath("img");
                                filePart.write(("C:\\SWP\\file\\" + htai + fileD));
                                String file = "C:\\SWP\\file\\" + htai + fileD;
                                // System.out.println(uploadDirectory + File.separator + htai + fileD);
                                // filePart.write(uploadDirectory + File.separator + htai + fileD);
                                //response.getWriter().print("file/" + htai + fileD);
                                SubjectDAL sd = new SubjectDAL();
                                ClassDAL cd = new ClassDAL();
                                SubjectSettingDAL ssd = new SubjectSettingDAL();
                                Lesson l = new Lesson();
                                l.setS(sd.getSubjectByID(subjectID));
                                l.setChapter(ssd.getChapterByID(chapterID));
                                l.setLessonName(name);
                                l.setDisplayOrder(order);
                                l.setStatus(status);
                                if (classID != 0) {
                                    l.setCls(cd.getClassByID(classID));
                                }
                                l.setVideoLink(video);
                                l.setAttatchedFile(file);
                                l.setLessonType(sstd.getSetting("Video"));
                                l.setDescription(description);
                                l.setCreatedAt(now);
                                l.setCreatedBy(a);
                                ssd.addLessonVideoWithFile(l);

                                listLessonGet(l, request, response);

                            } else {
                                SubjectDAL sd = new SubjectDAL();
                                ClassDAL cd = new ClassDAL();
                                SubjectSettingDAL ssd = new SubjectSettingDAL();
                                Lesson l = new Lesson();
                                l.setS(sd.getSubjectByID(subjectID));
                                l.setChapter(ssd.getChapterByID(chapterID));
                                l.setLessonName(name);
                                l.setDisplayOrder(order);
                                l.setStatus(status);
                                if (classID != 0) {
                                    l.setCls(cd.getClassByID(classID));
                                }
                                l.setLessonType(sstd.getSetting("Video"));
                                l.setAttatchedFile("");
                                l.setVideoLink(video);
                                l.setDescription(description);
                                l.setCreatedAt(now);
                                l.setCreatedBy(a);
                                ssd.addLessonVideoWithFile(l);

                                listLessonGet(l, request, response);

                            }
                        } else if (request.getParameter("type").equals("quiz")) {
                            String quiz_raw = request.getParameter("quiz");
                            int quizID = 0;
                            try {
                                quizID = Integer.parseInt(quiz_raw);
                                System.out.println(subjectID + " " + chapterID + " " + name + " " + order + " " + status + " " + classID + " " + quizID + " " + description);

                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            SubjectDAL sd = new SubjectDAL();
                            ClassDAL cd = new ClassDAL();
                            QuizDAL qd = new QuizDAL();
                            SubjectSettingDAL ssd = new SubjectSettingDAL();
                            Lesson l = new Lesson();
                            l.setS(sd.getSubjectByID(subjectID));
                            l.setChapter(ssd.getChapterByID(chapterID));
                            l.setLessonName(name);
                            l.setDisplayOrder(order);
                            l.setStatus(status);
                            l.setAttatchedFile("");
                            l.setVideoLink("");
                            l.setCreatedAt(now);
                            l.setCreatedBy(a);
                            l.setLessonType(sstd.getSetting("Quiz"));

                            if (classID != 0) {
                                l.setCls(cd.getClassByID(classID));
                            }
                            l.setDescription(description);
                            if (quizID != 0) {
                                l.setQuiz(qd.getQuizByID(quizID));
                            }
                            ssd.addLessonVideoWithFile(l);

                            listLessonGet(l, request, response);
                        } else if (request.getParameter("type").equals("asm")) {
                            String asm_raw = request.getParameter("asm");
                            int asmID = 0;
                            try {
                                asmID = Integer.parseInt(asm_raw);
                                System.out.println(subjectID + " " + chapterID + " " + name + " " + order + " " + status + " " + classID + " " + asmID + " " + description);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            SubjectDAL sd = new SubjectDAL();
                            ClassDAL cd = new ClassDAL();
                            AsmDAL qd = new AsmDAL();

                            SubjectSettingDAL ssd = new SubjectSettingDAL();
                            Lesson l = new Lesson();
                            l.setS(sd.getSubjectByID(subjectID));
                            l.setChapter(ssd.getChapterByID(chapterID));
                            l.setLessonName(name);
                            l.setDisplayOrder(order);
                            l.setCreatedAt(now);
                            l.setCreatedBy(a);
                            l.setStatus(status);
                            l.setAttatchedFile("");
                            l.setVideoLink("");
                            l.setLessonType(sstd.getSetting("Assignment"));

                            if (classID != 0) {
                                l.setCls(cd.getClassByID(classID));
                            }
                            l.setDescription(description);
                            if (asmID != 0) {
                                l.setAsm(qd.getAsmByID(asmID));
                            }
                            ssd.addLessonVideoWithFile(l);

                            listLessonGet(l, request, response);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            } else {
                Part filePart = request.getPart("file1");
                String htai = now.toString().replaceAll(" ", "");

                htai = htai.replaceAll("\\.", "");
                htai = htai.replaceAll("\\:", "");
                htai = htai.replaceAll("\\-", "");
                String uploadDirectory = request.getServletContext().getRealPath("img");
                filePart.write(("C:\\SWP\\Git\\g1\\Project\\WebApplication1\\web\\img\\" + htai + ".png"));
                //filePart.write(("C:\\SWP\\Git\\g1\\Project\\WebApplication1\\build\\web\\img\\" + htai + ".png"));
                filePart.write(uploadDirectory + File.separator + htai + ".png");
                response.getWriter().print("img/" + htai + ".png");
            }

        } else if (request.getParameter("upd") != null) {
            String page = request.getParameter("page");
            if (request.getParameter("type2").equals("1")) {
                String id_raw = request.getParameter("id");
                String subject_raw = request.getParameter("subject");
                String quiz_raw = request.getParameter("quiz");
                String asm_raw = request.getParameter("asm");
                int subjectID = 0, quizID = 0, asmID = 0, chapterID = 0, id = 0;
                try {
                    id = Integer.parseInt(id_raw);
                    if (subject_raw != null
                            && !subject_raw.equals("")) {
                        subjectID = Integer.parseInt(subject_raw);
                    }
                    if (quiz_raw != null
                            && !quiz_raw.equals("")) {
                        quizID = Integer.parseInt(quiz_raw);
                    }
                    if (asm_raw != null
                            && !asm_raw.equals("")) {
                        asmID = Integer.parseInt(asm_raw);
                    }
                    if (request.getParameter("chapter") != null
                            && !request.getParameter("chapter").equals("")) {
                        chapterID = Integer.parseInt(request.getParameter("chapter"));
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                SubjectSettingDAL ssd = new SubjectSettingDAL();
                SubjectDAL sd = new SubjectDAL();
                QuizDAL qd = new QuizDAL();
                AsmDAL ad = new AsmDAL();
                ClassDAL cd = new ClassDAL();

                Lesson l = ssd.getLessonByID(id);

                request.setAttribute("listS", sd.getAllSubject(" 1 = 1 "));
                if (subjectID == 0) {
                    request.setAttribute("listC", ssd.getAllChapterBySubject(1));
                    if (ssd.getChapterByID(chapterID).getSubject().getSubjectID() != subjectID) {
                        chapterID = ssd.getAllChapterBySubject(1).get(0).getChapterID();
                    }

                } else {
                    request.setAttribute("listC", ssd.getAllChapterBySubject(subjectID));
                    request.setAttribute("listQ", ssd.getAllLessonQuiz(1));
                    request.setAttribute("listA", ssd.getAllLessonAsm(1));
                    if (ssd.getChapterByID(chapterID).getSubject().getSubjectID() != subjectID) {
                        chapterID = ssd.getAllChapterBySubject(subjectID).get(0).getChapterID();
                    }
                }
                if (chapterID == 0) {
                    if (subjectID == 0) {
                        request.setAttribute("listQ", ssd.getAllLessonQuiz(ssd.getAllChapterBySubject(1).get(0).getChapterID()));
                        request.setAttribute("listA", ssd.getAllLessonAsm(ssd.getAllChapterBySubject(1).get(0).getChapterID()));
                    } else {
                        request.setAttribute("listQ", ssd.getAllLessonQuiz(ssd.getAllChapterBySubject(subjectID).get(0).getChapterID()));
                        request.setAttribute("listA", ssd.getAllLessonAsm(ssd.getAllChapterBySubject(subjectID).get(0).getChapterID()));
                    }
                } else {
                    request.setAttribute("listQ", ssd.getAllLessonQuiz(chapterID));
                    request.setAttribute("listA", ssd.getAllLessonAsm(chapterID));
                }
                l.setChapter(ssd.getChapterByID(chapterID));
                l.setQuiz(qd.getQuizByID(quizID));
                l.setAsm(ad.getAsmByID(asmID));

                request.setAttribute("l", l);
                request.setAttribute("id", id);
                request.setAttribute("upd", 1);
                request.setAttribute("page", page);
                request.setAttribute("listCls", cd.getAllClass());
                request.getRequestDispatcher("view/LessonDetail.jsp").forward(request, response);
            } else {
                String id_raw = request.getParameter("id");
                String subject_raw = request.getParameter("subject");
                String chapter_raw = request.getParameter("chapter");
                String name = request.getParameter("name");
                String order_raw = request.getParameter("order");
                String status_raw = request.getParameter("status");
                String class_raw = request.getParameter("class");
                String description = request.getParameter("summerNoteText");
                int subjectID = 0, chapterID = 0, order = 0, status = 0, classID = 0, id = 0;
                SystemSettingDAL sstd = new SystemSettingDAL();
                try {
                    id = Integer.parseInt(id_raw);
                    subjectID = Integer.parseInt(subject_raw);
                    chapterID = Integer.parseInt(chapter_raw);
                    order = Integer.parseInt(order_raw);
                    status = Integer.parseInt(status_raw);
                    classID = Integer.parseInt(class_raw);
                } catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    if (request.getParameter("type").equals("video")) {
                        Part filePart = request.getPart("file1");
                        String video = request.getParameter("link");
                        if (filePart.getSubmittedFileName() != null
                                && !filePart.getSubmittedFileName().equals("")) {
                            String fileD = "";
                            for (int i = 0; i < filePart.getSubmittedFileName().length(); i++) {
                                if (filePart.getSubmittedFileName().charAt(i) == '.') {
                                    fileD = filePart.getSubmittedFileName().substring(i);
                                }
                            }
                            String htai = now.toString().replaceAll(" ", "");

                            htai = htai.replaceAll("\\.", "");
                            htai = htai.replaceAll("\\:", "");
                            htai = htai.replaceAll("\\-", "");
                            //   String uploadDirectory = request.getServletContext().getRealPath("img");
                            filePart.write(("C:\\SWP\\file\\" + htai + fileD));
                            String file = "C:\\SWP\\file\\" + htai + fileD;
                            // System.out.println(uploadDirectory + File.separator + htai + fileD);
                            // filePart.write(uploadDirectory + File.separator + htai + fileD);
                            //response.getWriter().print("file/" + htai + fileD);
                            SubjectDAL sd = new SubjectDAL();
                            ClassDAL cd = new ClassDAL();
                            SubjectSettingDAL ssd = new SubjectSettingDAL();
                            Account createdBy = ssd.getLessonByID(id).getCreatedBy();
                            Timestamp createdAt = ssd.getLessonByID(id).getCreatedAt();
                            Lesson l = new Lesson();
                            l.setLessonID(id);
                            l.setS(sd.getSubjectByID(subjectID));
                            l.setChapter(ssd.getChapterByID(chapterID));
                            l.setLessonName(name);
                            l.setDisplayOrder(order);
                            l.setCreatedAt(createdAt);
                            l.setCreatedBy(createdBy);
                            l.setUpdatedAt(now);
                            l.setUpdatedBy(a);
                            l.setStatus(status);
                            if (classID != 0) {
                                l.setCls(cd.getClassByID(classID));
                            }
                            l.setVideoLink(video);
                            l.setAttatchedFile(file);
                            l.setLessonType(sstd.getSetting("Video"));
                            l.setDescription(description);

                            ssd.updLessonVideoWithFile(l);

                            listLessonGet(page, request, response);

                        } else {
                            SubjectDAL sd = new SubjectDAL();
                            ClassDAL cd = new ClassDAL();
                            SubjectSettingDAL ssd = new SubjectSettingDAL();
                            Account createdBy = ssd.getLessonByID(id).getCreatedBy();
                            Timestamp createdAt = ssd.getLessonByID(id).getCreatedAt();
                            Lesson l = new Lesson();
                            l.setCreatedAt(createdAt);
                            l.setCreatedBy(createdBy);
                            l.setLessonID(id);
                            l.setS(sd.getSubjectByID(subjectID));
                            l.setChapter(ssd.getChapterByID(chapterID));
                            l.setLessonName(name);
                            l.setDisplayOrder(order);
                            l.setStatus(status);
                            if (classID != 0) {
                                l.setCls(cd.getClassByID(classID));
                            }
                            l.setLessonType(sstd.getSetting("Video"));
                            l.setAttatchedFile("");
                            l.setVideoLink(video);
                            l.setDescription(description);
                            l.setUpdatedAt(now);
                            l.setUpdatedBy(a);
                            ssd.updLessonVideoWithFile(l);

                            listLessonGet(page, request, response);

                        }
                    } else if (request.getParameter("type").equals("quiz")) {
                        String quiz_raw = request.getParameter("quiz");
                        int quizID = 0;
                        try {
                            quizID = Integer.parseInt(quiz_raw);
                            System.out.println(subjectID + " " + chapterID + " " + name + " " + order + " " + status + " " + classID + " " + quizID + " " + description);

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        SubjectDAL sd = new SubjectDAL();
                        ClassDAL cd = new ClassDAL();
                        QuizDAL qd = new QuizDAL();
                        SubjectSettingDAL ssd = new SubjectSettingDAL();
                        Account createdBy = ssd.getLessonByID(id).getCreatedBy();
                        Timestamp createdAt = ssd.getLessonByID(id).getCreatedAt();
                        Lesson l = new Lesson();
                        l.setCreatedAt(createdAt);
                        l.setCreatedBy(createdBy);
                        l.setLessonID(id);
                        l.setS(sd.getSubjectByID(subjectID));
                        l.setChapter(ssd.getChapterByID(chapterID));
                        l.setLessonName(name);
                        l.setDisplayOrder(order);
                        l.setStatus(status);
                        l.setAttatchedFile("");
                        l.setVideoLink("");
                        l.setUpdatedAt(now);
                        l.setUpdatedBy(a);
                        l.setLessonType(sstd.getSetting("Quiz"));

                        if (classID != 0) {
                            l.setCls(cd.getClassByID(classID));
                        }
                        l.setDescription(description);
                        if (quizID != 0) {
                            l.setQuiz(qd.getQuizByID(quizID));
                        }
                        ssd.updLessonVideoWithFile(l);

                        listLessonGet(page, request, response);
                    } else if (request.getParameter("type").equals("asm")) {
                        String asm_raw = request.getParameter("asm");
                        int asmID = 0;
                        try {
                            asmID = Integer.parseInt(asm_raw);
                            System.out.println(subjectID + " " + chapterID + " " + name + " " + order + " " + status + " " + classID + " " + asmID + " " + description);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        SubjectDAL sd = new SubjectDAL();
                        ClassDAL cd = new ClassDAL();
                        AsmDAL qd = new AsmDAL();

                        SubjectSettingDAL ssd = new SubjectSettingDAL();
                        Account createdBy = ssd.getLessonByID(id).getCreatedBy();
                        Timestamp createdAt = ssd.getLessonByID(id).getCreatedAt();
                        Lesson l = new Lesson();
                        l.setCreatedAt(createdAt);
                        l.setCreatedBy(createdBy);
                        l.setLessonID(id);
                        l.setS(sd.getSubjectByID(subjectID));
                        l.setChapter(ssd.getChapterByID(chapterID));
                        l.setLessonName(name);
                        l.setDisplayOrder(order);

                        l.setStatus(status);
                        l.setAttatchedFile("");
                        l.setVideoLink("");
                        l.setLessonType(sstd.getSetting("Assignment"));

                        if (classID != 0) {
                            l.setCls(cd.getClassByID(classID));
                        }
                        l.setDescription(description);
                        if (asmID != 0) {
                            l.setAsm(qd.getAsmByID(asmID));
                        }
                        l.setUpdatedAt(now);
                        l.setUpdatedBy(a);
                        ssd.updLessonVideoWithFile(l);

                        listLessonGet(page, request, response);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } else {
            Part filePart = request.getPart("file1");
            String htai = now.toString().replaceAll(" ", "");

            htai = htai.replaceAll("\\.", "");
            htai = htai.replaceAll("\\:", "");
            htai = htai.replaceAll("\\-", "");
            String uploadDirectory = request.getServletContext().getRealPath("img");
            filePart.write(("C:\\SWP\\Git\\g1\\Project\\WebApplication1\\web\\img\\" + htai + ".png"));
            //filePart.write(("C:\\SWP\\Git\\g1\\Project\\WebApplication1\\build\\web\\img\\" + htai + ".png"));
            filePart.write(uploadDirectory + File.separator + htai + ".png");
            response.getWriter().print("img/" + htai + ".png");
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

    private void listLessonGet(Lesson l, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SystemSettingDAL stsd = new SystemSettingDAL();
        String order = "";
        String sql = "";
        Paging p = new Paging();
        int numPerPage = 5;
        String page_raw = request.getParameter("page");
        List<Lesson> list = ssd.getLessonBySearch(sql, order);
        int page = (list.size() % numPerPage == 0) ? (list.size() / numPerPage) : (list.size() / numPerPage + 1);
        request.setAttribute("chapter", ssd.getAllChapter());
        request.setAttribute("type", stsd.getAllLessonType());
        if (!list.isEmpty()) {
            request.setAttribute("totalEntity", list.size());
            request.setAttribute("totalPage", p.getTotalPage(page, list.size(), numPerPage));
            list = pagination(list, page, numPerPage);
            request.setAttribute("size", list.size());
            request.setAttribute("list", 1);
            request.setAttribute("listL", list);
            request.setAttribute("page", page);
            String msg = "Add successfully";
            request.setAttribute("msg", msg);
        } else {
            request.setAttribute("ms", "Don't have any lessons");
        }
        request.getRequestDispatcher("view/LessonList.jsp").forward(request, response);
    }

    private void listLessonGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SystemSettingDAL stsd = new SystemSettingDAL();
        String order = sort(request, response);
        String sql = "";
        String moreC = searchByChapter(request, response);
        String moreS = searchByStatus(request, response);
        String moreT = searchByType(request, response);
        String moreN = searchByNameOrID(request, response);
        sql += "WHERE ";
        sql += moreC;
        sql += "AND";
        sql += moreS;
        sql += "AND";
        sql += moreT;
        sql += "AND";
        sql += moreN;
        Paging p = new Paging();
        int numPerPage = 5;
        String page_raw = request.getParameter("page");
        int page = 1;
        if (page_raw == null || page_raw.equals("")) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(page_raw);
            } catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("view/error.jsp");
            }
        }
        List<Lesson> list = ssd.getLessonBySearch(sql, order);
        request.setAttribute("chapter", ssd.getAllChapter());
        request.setAttribute("type", stsd.getAllLessonType());
        if (!list.isEmpty()) {
            request.setAttribute("totalEntity", list.size());
            request.setAttribute("totalPage", p.getTotalPage(page, list.size(), numPerPage));
            list = pagination(list, page, numPerPage);
            request.setAttribute("size", list.size());
            request.setAttribute("list", 1);
            request.setAttribute("listL", list);
            request.setAttribute("page", page);
        } else {
            request.setAttribute("ms", "Don't have any lessons");
        }
        request.getRequestDispatcher("view/LessonList.jsp").forward(request, response);
    }

    private String sort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String o = "";
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (request.getParameter("sort") == null || request.getParameter("sort").equals("")
                || request.getParameter("order") == null || request.getParameter("order").equals("")) {
            o = "ORDER BY display_order, lesson_id";
        } else {
            if (!request.getParameter("sort").equals("ID") && !request.getParameter("sort").equals("Name")
                    && !request.getParameter("sort").equals("Chapter") && !request.getParameter("sort").equals("Type")
                    && !request.getParameter("sort").equals("Order") && !request.getParameter("sort").equals("status")) {
                o = "ORDER BY display_order, lesson_id";
            } else {
                int order = 0;
                try {
                    order = Integer.parseInt(request.getParameter("order"));
                    if (order != 0 && order != 1) {
                        throw new Exception("L");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                }
                if (order == 0) {
                    request.setAttribute("sort", request.getParameter("sort"));
                    request.setAttribute("order", 0);
                    o = ssd.getSortedLessonDesc(request.getParameter("sort"));
                } else {
                    request.setAttribute("sort", request.getParameter("sort"));
                    request.setAttribute("order", 1);
                    o = ssd.getSortedLessonAsc(request.getParameter("sort"));
                }
            }
        }
        return o;
    }

    public List<Lesson> pagination(List<Lesson> list, int page, int numPerPage) {
        Paging p = new Paging();
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        int start = p.getStart(page, list.size(), numPerPage);
        int end = p.getEnd(page, list.size(), numPerPage);
        list = ssd.getLessonByPage(start, end, list);
        return list;
    }

    private String searchByChapter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        String more = " 1 = 1 ";
        String id_raw = request.getParameter("chapter");
        if (request.getParameter("chapter") == null
                || request.getParameter("chapter").equals("")) {
            more = " 1 = 1 ";
        } else {
            try {
                id = Integer.parseInt(id_raw);

            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
            request.setAttribute("c", id);
            if (id == 0) {
                more = " 1 = 1 ";
            } else {
                more = " chapter_id = " + id + " ";
            }
        }
        return more;
    }

    private String searchByStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        String more = " 1 = 1 ";
        if (request.getParameter("status") == null
                || request.getParameter("status").equals("")) {
            request.setAttribute("s", -1);
            more = " 1 = 1 ";
        } else {
            int status = 0;
            try {
                status = Integer.parseInt(request.getParameter("status"));
                if (status != 1 && status != 0 && status != -1) {
                    throw new Exception("L");
                }
            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
            request.setAttribute("s", status);
            if (status == -1) {
                more = " 1 = 1 ";
            } else {
                more = " status = " + status + " ";
            }
        }
        return more;
    }

    private String searchByType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        String more = " 1 = 1 ";
        String id_raw = request.getParameter("type");
        if (request.getParameter("type") == null
                || request.getParameter("type").equals("")) {
            more = " 1 = 1 ";
        } else {
            try {
                id = Integer.parseInt(id_raw);

            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
            request.setAttribute("t", id);
            if (id == 0) {
                more = " 1 = 1 ";
            } else {
                more = " lesson_type = " + id + " ";
            }
        }
        return more;
    }

    private String searchByNameOrID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = "", more = "";
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (request.getParameter("search") == null
                || request.getParameter("search").equals("")) {
            request.setAttribute("search", key);
            more = " 1 = 1 ";
        } else {
            key = request.getParameter("search");
            request.setAttribute("search", key);
            more = " lesson_name LIKE '%" + key + "%' ";
        }
        return more;
    }

    private void addLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        ClassDAL cd = new ClassDAL();
        request.setAttribute("listS", sd.getAllSubject(" 1 = 1 "));
        request.setAttribute("listC", ssd.getAllChapterBySubject(1));
        request.setAttribute("listQ", ssd.getAllLessonQuiz(ssd.getAllChapterBySubject(1).get(0).getChapterID()));
        request.setAttribute("listA", ssd.getAllLessonAsm(ssd.getAllChapterBySubject(1).get(0).getChapterID()));
        request.setAttribute("listCls", cd.getAllClass());
        request.setAttribute("add", 1);
        request.getRequestDispatcher("view/NewLesson.jsp").forward(request, response);
    }

    private void updLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("upd", 1);
        String id_raw = request.getParameter("id");
        String page_raw = request.getParameter("page");
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
        } catch (Exception e) {
            System.out.println(e);
        }
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        Lesson l = ssd.getLessonByID(id);
        SubjectDAL sd = new SubjectDAL();
        ClassDAL cd = new ClassDAL();
        request.setAttribute("l", l);
        request.setAttribute("id", id);
        request.setAttribute("page", page_raw);
        request.setAttribute("listS", sd.getAllSubject(" 1 = 1 "));
        request.setAttribute("listC", ssd.getAllChapterBySubject(1));
        request.setAttribute("listQ", ssd.getAllLessonQuiz(ssd.getAllChapterBySubject(1).get(0).getChapterID()));
        request.setAttribute("listA", ssd.getAllLessonAsm(ssd.getAllChapterBySubject(1).get(0).getChapterID()));
        request.setAttribute("listCls", cd.getAllClass());
        request.getRequestDispatcher("view/LessonDetail.jsp").forward(request, response);
    }

    private void activateDeactivateLesson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ValidInput v = new ValidInput();
        String id_raw = request.getParameter("id");
        String page_raw = request.getParameter("page");
        int id = v.validInt(id_raw, request, response);
        int page = v.validInt(page_raw, request, response);
        System.out.println(id + " " + page);
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (ssd.getLessonByID(id) != null) {
            if (ssd.getLessonByID(id).getStatus() == 1) {
                ssd.actLesson(0, id);
            } else {
                ssd.actLesson(1, id);
            }
            //   listLessonPage(page, request, response);
        }

    }

    private void listLessonPage(int page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SystemSettingDAL stsd = new SystemSettingDAL();
        String order = sort(request, response);
        String sql = "";
        String moreC = searchByChapter(request, response);
        String moreS = searchByStatus(request, response);
        String moreT = searchByType(request, response);
        String moreN = searchByNameOrID(request, response);
        sql += "WHERE ";
        sql += moreC;
        sql += "AND";
        sql += moreS;
        sql += "AND";
        sql += moreT;
        sql += "AND";
        sql += moreN;
        Paging p = new Paging();
        int numPerPage = 5;

        List<Lesson> list = ssd.getLessonBySearch(sql, order);
        request.setAttribute("chapter", ssd.getAllChapter());
        request.setAttribute("type", stsd.getAllLessonType());
        if (!list.isEmpty()) {
            request.setAttribute("totalEntity", list.size());
            request.setAttribute("totalPage", p.getTotalPage(page, list.size(), numPerPage));
            list = pagination(list, page, numPerPage);
            request.setAttribute("size", list.size());
            request.setAttribute("list", 1);
            request.setAttribute("listL", list);
            request.setAttribute("page", page);
        } else {
            request.setAttribute("ms", "Don't have any lessons");
        }
        request.getRequestDispatcher("view/LessonList.jsp").forward(request, response);
    }

    private void listLessonGet(String page1, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("kdoa");
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SystemSettingDAL stsd = new SystemSettingDAL();
        String order = "";
        String sql = "";
        int page = Integer.parseInt(page1);
        Paging p = new Paging();
        int numPerPage = 5;

        List<Lesson> list = ssd.getLessonBySearch(sql, order);
        request.setAttribute("chapter", ssd.getAllChapter());
        request.setAttribute("type", stsd.getAllLessonType());
        if (!list.isEmpty()) {
            request.setAttribute("totalEntity", list.size());
            request.setAttribute("totalPage", p.getTotalPage(page, list.size(), numPerPage));
            list = pagination(list, page, numPerPage);
            request.setAttribute("size", list.size());
            request.setAttribute("list", 1);
            request.setAttribute("listL", list);
            request.setAttribute("page", page);
            request.setAttribute("msg", "Update successfully");
        } else {
            request.setAttribute("ms", "Don't have any lessons");
        }
        request.getRequestDispatcher("view/LessonList.jsp").forward(request, response);
    }

}
