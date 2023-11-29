<%-- 
    Document   : ClassDiscussion
    Created on : Oct 24, 2023, 5:49:20 PM
    Author     : acer
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Director | Dashboard</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Morris chart -->
        <link href="css/morris/morris.css" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
        <!-- Date Picker -->
        <link href="css/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
        <!-- fullCalendar -->
        <!-- <link href="css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" /> -->
        <!-- Daterange picker -->
        <link href="css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- iCheck for checkboxes and radio inputs -->
        <link href="css/iCheck/all.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <!-- <link href="css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" /> -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link href="css/discussion.css" rel="stylesheet" type="text/css" />

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"> -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

        <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
        <style>
            .timeline-body img{
                max-width: 600px;
            }

            .note-editable img{
                max-height: 100px;
                max-width: 600px;
            }
        </style>
        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .footer-main{
                left: 0%;
            }

            .ttle{
                font-size: 25px;
                font-weight: bold;
                margin-left: 50px;
                margin-top: 20px;
            }

            .content{
                margin-left: 70px;
                margin-top: 20px;
            }

            .in4{
                display: flex;
                flex-direction: row;
                align-items: flex-start;
                width: 100%;
            }

            .im{
                width: 100%;
                height: 100%;
            }

            .img{
                width: 40px;
                height: 40px;
            }
            .otherIN4{
                margin-left: 10px;
                display: flex;
                flex-direction: column;
                font-size: 18px;
            }

            .topic{
                font-weight: bold;
                margin-bottom: 10px;
                display: flex;
                flex-direction: row;
            }


            .topicN {
                width: 600px;
            }
            .dcontent{
                width:800px;
                white-space: wrap;
                overflow: hidden;
            }

            .in4{
                margin-bottom: 20px;
                padding: 20px;
            }

            .uIN4{
                display: flex;
                flex-direction: row;
            }

            .uname{
                margin-right: 25px;
            }

            .blk{
                font-weight: bold;
            }

            .topicO{
                display: flex;
                flex-direction: row;
                text-align: right;
            }

            .com{
                display: flex;
                flex-direction: row;
                margin-right: 25px;
            }

            .rate{
                display: flex;
                flex-direction: row;
            }

            .oIN4Img{
                width: 25px;
                margin-right: 10px;
            }

            .topicN{
                margin-right: 335px;
            }

            .topicN:hover{
                cursor: pointer;
            }

            .clearfix{
                display: flex;
                flex-direction: row;
                align-items: center;
            }

            .pagination{
                margin-left: 830px;
            }


            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                background-color: black;
                color: white;
            }
            .pagination li.active a, .pagination li.active a.page-link {
                background-color: black;
                color: white;
            }
            .pagination li.active a:hover {
                background-color: black;
                color: white;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .page-item .active {
                background-color: black;
                color: white;
            }

            .search{
                margin-top: 20px;
                margin-left: 50px;
                font-size: 18px;
                display: flex;
                flex-direction: column;
            }

            .uname1{
                display: flex;
                flex-direction: column;
                justify-content: flex-start;
                width: 200px;
                margin-right: 720px;
            }
            .imgS{
                width: 35px;
                filter: drop-shadow(0px 1000px 0 white);
                transform: translateY(-1000px);
            }

            .searchArea{
                display: flex;
                flex-direction: row;
                align-items: center;
            }
            #search{
                width: 1100px;
                height: 50px;
                padding: 20px;
            }

            .IM{
                border: 1px solid black;
                height: 50px;
                padding: 10px;
                background-color: black;
                color: white;
            }

            .filter{
                display: flex;
                flex-direction: row;
                margin-top: 10px;
            }

            .week{
                display: flex;
                flex-direction: column;
                margin-right: 20px;
            }
            .text{
                font-weight: bold;
            }

            select{
                margin-top: 10px;
                padding: 10px;
            }

            select:hover{
                cursor: pointer;
            }

            .w{
                width: 300px;
            }

            .o{
                width: 450px;
            }

            .q{
                width: 312px;
            }

            .ms{
                color: red;
            }
            .ms{
                margin-top: 20px;
                font-size: 30px;
                text-align: center;
                margin-top: 20px;
                margin-bottom: 20px;
            }
            .com:hover{
                cursor: pointer;
            }

            .oIN4Img1{
                width: 25px;
                margin-right: 10px;
                filter: drop-shadow(0px 1000px 0 black);
                transform: translateY(-1000px);
            }

            #topic{
                width: 85%;
            }

            .ttLE{
                margin-bottom: 20px;
                text-align: center;
                margin-top: 20px;
                font-size: 25px;
                font-weight: bold;
                color: black;
            }

            body{
                margin-left: 100px;
            }



            .new{

                display: none;
            }

            #a{
                margin-left: 70px;
                font-size: 20px;
                color: black;
                font-weight: bold;
                margin-bottom: 50px;
                margin-top: 100px;
            }

            #a:hover{
                cursor: pointer;
            }

            .btn{
                font-size: 20px;
                text-align: center;
                padding-top: 10px;
                padding-bottom: 10px;
                padding-left: 20px;
                padding-right: 20px;
                background-color: #0088cc;
                color: white;
            }

            .hint-text{
                margin-right: 690px;
                margin-left: 25px;
            }
            .pnq{
                margin-top: 20px;
                margin-bottom: 20px;
            }

        </style>

        <!--Toast up-->
        <style>


            /* Modifier */
            .btn--size-l {
                padding: 16px 56px;
            }

            .btn--size-s {
                padding: 8px 32px;
            }

            .btn:hover {
                opacity: 0.8;
            }

            .btn + .btn {
                margin-left: 16px;
            }

            .btn--success {
                background-color: #71be34;
            }

            .btn--warn {
                background-color: #ffb702;
            }

            .btn--danger {
                background-color: #ff623d;
            }

            .btn--disabled {
                opacity: 0.5 !important;
                cursor: default;
            }

            /* ======= Toast message ======== */

            #toast {
                position: fixed;
                top: 32px;
                right: 32px;
                z-index: 999999;
                background-color: green;
            }

            .toast {
                display: flex;
                align-items: center;
                background-color: green;
                border-radius: 2px;
                padding: 20px 0;
                min-width: 400px;
                max-width: 450px;
                border-left: 4px solid;
                box-shadow: 0 5px 8px rgba(0, 0, 0, 0.08);
                transition: all linear 0.3s;
            }

            @keyframes slideInLeft {
                from {
                    opacity: 0;
                    transform: translateX(calc(100% + 32px));
                }
                to {
                    opacity: 1;
                    transform: translateX(0);
                }
            }

            @keyframes fadeOut {
                to {
                    opacity: 0;
                }
            }

            .toast--success {
                border-color: #47d864;
            }

            .toast--success .toast__icon {
                color: #47d864;
            }

            .toast--info {
                border-color: #2f86eb;
            }

            .toast--info .toast__icon {
                color: #2f86eb;
            }

            .toast--warning {
                border-color: #ffc021;
            }

            .toast--warning .toast__icon {
                color: #ffc021;
            }

            .toast--error {
                border-color: #ff623d;
            }

            .toast--error .toast__icon {
                color: #ff623d;
            }

            .toast + .toast {
                margin-top: 24px;
            }

            .toast__icon {
                font-size: 24px;
            }

            .toast__icon,
            .toast__close {
                padding: 0 16px;
            }

            .toast__body {
                flex-grow: 1;
            }

            .toast__title {
                font-size: 16px;
                font-weight: 600;
                color: #333;
            }

            .toast__msg {
                font-size: 14px;
                color: #888;
                margin-top: 6px;
                line-height: 1.5;
            }

            .toast__close {
                font-size: 20px;
                color: rgba(0, 0, 0, 0.3);
                cursor: pointer;
            }
            .pnq{
                text-align: left;
            }

            .a{
                margin-top: 15px;
                margin-left: 15px;
            }

            .lnk{
                color: #0088cc;
                margin-left: 25px;
            }

            .a:hover{
                cursor: pointer;
            }

            .bt{
                display: flex;
                flex-direction: row;
                margin-top: 10px;
            }

            .bt div{
                margin-right: 20px;
                font-weight: bold;
            }

            .bt div{
                cursor: pointer;
            }

            .txtA1{
                margin-top: 20px;
                margin-left: 43px;
            }

            .dCom{
                display: none;
            }

            .footer-main{
                background-color: black;
            }

            .left-side sidebar-offcanvas{
                height: 170%;
            }

            .content{
                background: white;
                position: absolute;
                height: 100%;
                margin-bottom: 10px;
            }


            body{
                background-color: white;
            }

        </style>

        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <%@include file = "menufl.jsp" %>

        <aside  class="left-side sidebar-offcanvas" style = "height:150%">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- Sidebar user panel -->
                <div class="user-panel">
                    <div class="pull-left image">
                        <img src="img/26115.jpg" class="img-circle" alt="User Image" />
                    </div>
                    <div class="pull-left info">
                        <p>Hello, Jane</p>

                        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                    </div>
                </div>
                <!-- search form -->

                <!-- /.search form -->
                <!-- sidebar menu: : style can be found in sidebar.less -->
                <ul class="sidebar-menu">
                    <li>
                        <a href="DashboardController">
                            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-list"></i> <span>Assignment List</span>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <i class="fa fa-list"></i> <span>Quiz List</span>
                        </a>
                    </li>

                    <li>
                        <a href="cg?class=${requestScope.cls}">
                            <i class="fa fa-list"></i> <span>Class Grade</span>
                        </a>
                    </li>

                    <li>
                        <a href="cds?class=${requestScope.cls}">
                            <i class="fa fa-list"></i> <span>Class Discussion</span>
                        </a>
                    </li>

                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
        <aside class="right-side">

            <!-- Main content -->
            <div class="container-xl"> 
                <select id = "slt" hidden>
                    <c:forEach var="i" items="${requestScope.listAcc}" varStatus="loop">
                        <option>${i.getUser()} - ${i.getFullName()} (${i.getRoleName()})</option>
                    </c:forEach>
                </select>
                <form action = "cds" id = "form">
                    <div class = "search">

                        <input type ="hidden" name ="class" value ="${requestScope.cls}"/>
                        <div class = "searchArea">
                            <div>
                                <input type ="text" name ="search" id = "search" placeholder = "Tìm kiếm tất cả các câu hỏi trong lớp" value = "${requestScope.key}"/>
                            </div>
                            <div class = "IM">
                                <img class ="imgS" src ="img/searchIcon.png" onclick = "sbmit()"/>
                            </div>
                        </div>  
                        <div class = "filter">
                            <div class = "week">
                                <div class = "text">
                                    Tuần:
                                </div>
                                <div class = "fweek">
                                    <select name = "w" class = "w" onchange="sbmit()">
                                        <option value = "0">Tất cả các tuần</option>
                                        <c:forEach var="i" items="${requestScope.listW}" varStatus="loop">
                                            <option ${requestScope.d.toString().equals(i.getStartDate().toString())?"selected":""} value = "${i.getDateS()}">${i.getDateS()} to ${i.getDateE()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class = "week">
                                <div class = "text">
                                    Sắp xếp theo:
                                </div>
                                <div class = "forder">
                                    <select onchange ="sbmit()" name = "order" class = "o">
                                        <option value = "0" ${requestScope.order == 0?"selected":""}>Sắp xếp theo thứ tự đề xuất</option>
                                        <option value = "1" ${requestScope.order == 1?"selected":""}>Sắp xếp theo thứ tự gần đây nhất</option>
                                        <option value = "2" ${requestScope.order == 2?"selected":""}>Sắp xếp theo thứ tự có nhiều người tán thành nhất</option>
                                    </select>
                                </div>
                            </div>
                            <div class = "week">
                                <div class = "text">
                                    Câu hỏi:
                                </div>
                                <div class = "fq">
                                    <select onchange ="sbmit()" name = "q" class = "q">
                                        <option value = "0" ${requestScope.q == 0?"selected":""}>Tất cả các câu hỏi</option>
                                        <option value = "1" ${requestScope.q == 1?"selected":""}>Câu hỏi tôi đã hỏi</option>
                                        <option value = "2" ${requestScope.q == 2?"selected":""}>Câu hỏi không có câu trả lời</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                </form>
                <c:if test = "${requestScope.msg != null}">
                    <div class = "ms" id = "ms" style = "display: none">
                        ${requestScope.msg}
                    </div>
                    <div id="toast"></div>
                </c:if>
                <c:if test = "${requestScope.list != null}">
                    <div class = "ttle">All questions in this class (${requestScope.totalEntity})</div>
                    <div class = "pnq">
                        <a id = "a"  onclick = "show()">Post New Question</a>
                    </div>
                    <div class = "content">
                        <div class = "new" id = "new">
                            <%@include file = "CKEditor.jsp" %>
                        </div>

                        <c:forEach var="i" items="${requestScope.list}" varStatus="loop">
                            <div class = "in4">
                                <div class = "img">
                                    <img class ="im" src ="img/userIcon.png" />
                                </div>
                                <div class = "otherIN4">
                                    <div class = "topic">
                                        <div class = "topicN">
                                            ${i.getCd().getDiscussionTopic()}
                                        </div>
                                        <div class = "topicO">
                                            <div class = "com" >
                                                <div class = "cImg">
                                                    <img class ="oIN4Img" src ="img/commentIcon.png"/>
                                                </div>
                                                <div class = "cN">
                                                    ${i.getCd().getNoCom()}
                                                </div>
                                            </div>
                                            <div class = "rate">
                                                <c:if test = "${i.getCd().isOnV() == false}">
                                                    <div class = "rImg">
                                                        <img id ="v${i.getCd().getDiscussionID()}" class ="oIN4Img" src ="img/voteIcon.png" onclick = "vote1('${requestScope.cls}', '${i.getCd().getDiscussionID()}')"/>
                                                    </div>
                                                </c:if>
                                                <c:if test = "${i.getCd().isOnV() == true}">
                                                    <div class = "rImg">
                                                        <img id ="v${i.getCd().getDiscussionID()}"  class ="oIN4Img" src ="img/blackVoteIcon.png" onclick = "vote1('${requestScope.cls}', '${i.getCd().getDiscussionID()}')"/>
                                                    </div>
                                                </c:if>
                                                <div class = "rN" id = "vote${i.getCd().getDiscussionID()}">
                                                    ${i.getCd().getNoVote()}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class = "dcontent">
                                        ${i.getCd().getDiscussionContent()}
                                    </div>

                                    <div class = "uIN4">
                                        <c:if test = "${sessionScope.account.getID() == i.getCd().getAccount().getID()}">
                                            <div class = "uname">
                                                <span class = "blk">Author:</span> ${i.getCd().getAccount().getFullName()} (me)
                                            </div>
                                        </c:if>
                                        <c:if test = "${sessionScope.account.getID() != i.getCd().getAccount().getID()}">
                                            <div class = "uname">
                                                <span class = "blk">Author:</span> ${i.getCd().getAccount().getFullName()}
                                            </div>
                                        </c:if>
                                        <div class = "udate">
                                            <span class = "blk">Date:</span> ${i.getCd().getdS()}
                                        </div>

                                    </div>
                                    <div class = "bt">
                                        <div>
                                            Vote
                                        </div>
                                        <div onclick = "showT('${i.getCd().getDiscussionID()}')">
                                            Reply
                                        </div>
                                    </div>
                                    <div class = "a">
                                        <c:if test = "${i.getCd().getNoCom() > 0}">
                                            <span class = "lnk" onclick = "cmt('${i.getCd().getDiscussionID()}')">View  ${i.getCd().getNoCom()} responses</span>
                                        </c:if>
                                    </div>
                                    <div>
                                        <div class = "txtA1" id ="txtA${i.getCd().getDiscussionID()}" style = "display: none">
                                            <textarea id ="summernote1${i.getCd().getDiscussionID()}" onchange ="go1()"  name="editordata">
                                            </textarea>
                                            <form id = "frm1" method="post" action = "cds">
                                                <input type ="hidden" name ="page" value ="${requestScope.page}"/>

                                                <input type ="hidden" id ="cls" name ="cls" value ="${requestScope.cls}"/>
                                                <input type ="hidden" name ="id" value ="${i.getCd().getDiscussionID()}"/>
                                                <input type ="submit" id ="sbmit" value ="Post"/>
                                                <input type ="hidden" name ="reply" value = "1"/>
                                                <textarea style ="display:none" id ="txt1${i.getCd().getDiscussionID()}" name ="summerNoteTextReply"></textarea>
                                            </form>
                                        </div>
                                    </div>
                                    <div class = "a">

                                        <div class = "dCom" id = "cmt${i.getCd().getDiscussionID()}">
                                            <c:forEach var="j" items="${i.getDd()}" varStatus="loop">
                                                <div class = "in4" >
                                                    <div class = "img">
                                                        <img class ="im" src ="img/userIcon.png" />
                                                    </div>
                                                    <div class = "otherIN4">
                                                        <div class = "topic">

                                                            <div class = "topicO1">
                                                                <c:if test = "${sessionScope.account.getID() == j.getAccount().getID()}">
                                                                    <div class = "uname1">
                                                                        <div>
                                                                            ${j.getAccount().getFullName()} (me)
                                                                        </div>
                                                                        <div class = "udate1">
                                                                            ${j.getdS()}
                                                                        </div>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test = "${sessionScope.account.getID() != j.getAccount().getID()}">
                                                                    <div class = "uname1">
                                                                        <div>
                                                                            ${j.getAccount().getFullName()}
                                                                        </div>
                                                                        <div class = "udate1">
                                                                            ${j.getdS()}
                                                                        </div>
                                                                    </div>
                                                                </c:if>

                                                                <div class = "rate">
                                                                    <div class = "rImg">
                                                                        <img class ="oIN4Img1" src ="img/emptyStar.png"/>
                                                                    </div>
                                                                    <div class = "rN" id = "vte${j.getCommentID()}">
                                                                        ${j.getNoVote()}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class = "dcontentD">
                                                            ${j.getComment()}
                                                        </div>
                                                        <div class = "btnV">
                                                            <div class="dropdown">

                                                                <div>
                                                                    <c:if test = "${j.getsVote() == 5}">
                                                                        <button class = "dropbtn5" id = "${j.getCommentID()}">
                                                                            Vote
                                                                        </button>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() == 4}">
                                                                        <button class = "dropbtn4" id = "${j.getCommentID()}">
                                                                            Vote
                                                                        </button>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() == 3}">
                                                                        <button class = "dropbtn3" id = "${j.getCommentID()}">
                                                                            Vote
                                                                        </button>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() == 2}">

                                                                        <button class = "dropbtn2" id = "${j.getCommentID()}">
                                                                            Vote
                                                                        </button>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() == 1}">

                                                                        <button class = "dropbtn1" id = "${j.getCommentID()}">
                                                                            Vote
                                                                        </button>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() == 0}">

                                                                        <button class = "dropbtn" id = "${j.getCommentID()}">
                                                                            Vote
                                                                        </button>
                                                                    </c:if>
                                                                </div>
                                                                <div class = "dropdown-content">
                                                                    <c:if test = "${j.getsVote() == 5}">
                                                                        <div id ="s51" class = "insideDC" onclick = "vote('5', '${j.getCommentID()}', ' ${j.getNoVote()}', '${j.getsVote()}', '${j.getCd().getDiscussionID()}')">
                                                                            <div  class = "DCimg" id = "img5${j.getCommentID()}">
                                                                                <img src ="img/emptyStar.png"/>
                                                                            </div>
                                                                            <div class = "DCtext" id = "text5${j.getCommentID()}">
                                                                                5 star
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() != 5}">
                                                                        <div id ="s5" class = "insideDC" onclick = "vote('5', '${j.getCommentID()}', ' ${j.getNoVote()}', '${j.getsVote()}', '${j.getCd().getDiscussionID()}')">
                                                                            <div  class = "DCimg" id = "img5${j.getCommentID()}">
                                                                                <img src ="img/emptyStar.png"/>
                                                                            </div>
                                                                            <div class = "DCtext" id = "text5${j.getCommentID()}">
                                                                                5 star
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() == 4}">
                                                                        <div id ="s41" class = "insideDC" onclick = "vote('4', '${j.getCommentID()}', ' ${j.getNoVote()}', '${j.getsVote()}', '${j.getCd().getDiscussionID()}')">
                                                                            <div class = "DCimg" id = "img4${j.getCommentID()}">
                                                                                <img src ="img/emptyStar.png"/>
                                                                            </div>
                                                                            <div class = "DCtext" id = "text4${j.getCommentID()}">
                                                                                4 star
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() != 4}">
                                                                        <div id ="s4" class = "insideDC" onclick = "vote('4', '${j.getCommentID()}', ' ${j.getNoVote()}', '${j.getsVote()}', '${j.getCd().getDiscussionID()}')">
                                                                            <div class = "DCimg" id = "img4${j.getCommentID()}">
                                                                                <img src ="img/emptyStar.png"/>
                                                                            </div>
                                                                            <div class = "DCtext" id = "text4${j.getCommentID()}">
                                                                                4 star
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() == 3}">
                                                                        <div id ="s31" class = "insideDC" onclick = "vote('3', '${j.getCommentID()}', ' ${j.getNoVote()}', '${j.getsVote()}', '${j.getCd().getDiscussionID()}')">
                                                                            <div class = "DCimg" id = "img3${j.getCommentID()}">
                                                                                <img src ="img/emptyStar.png"/>
                                                                            </div>
                                                                            <div class = "DCtext" id = "text3${j.getCommentID()}">
                                                                                3 star
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() != 3}">
                                                                        <div id ="s3" class = "insideDC" onclick = "vote('3', '${j.getCommentID()}', ' ${j.getNoVote()}', '${j.getsVote()}', '${j.getCd().getDiscussionID()}')">
                                                                            <div class = "DCimg" id = "img3${j.getCommentID()}">
                                                                                <img src ="img/emptyStar.png"/>
                                                                            </div>
                                                                            <div class = "DCtext" id = "text3${j.getCommentID()}">
                                                                                3 star
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() == 2}">
                                                                        <div id ="s21" class = "insideDC" onclick = "vote('2', '${j.getCommentID()}', ' ${j.getNoVote()}', '${j.getsVote()}', '${j.getCd().getDiscussionID()}')">
                                                                            <div class = "DCimg" id = "img2${j.getCommentID()}">
                                                                                <img src ="img/emptyStar.png"/>
                                                                            </div>
                                                                            <div class = "DCtext" id = "text2${j.getCommentID()}">
                                                                                2 star
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() != 2}">
                                                                        <div id ="s2" class = "insideDC" onclick = "vote('2', '${j.getCommentID()}', ' ${j.getNoVote()}', '${j.getsVote()}', '${j.getCd().getDiscussionID()}')">
                                                                            <div class = "DCimg" id = "img2${j.getCommentID()}">
                                                                                <img src ="img/emptyStar.png"/>
                                                                            </div>
                                                                            <div class = "DCtext" id = "text2${j.getCommentID()}">
                                                                                2 star
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() == 1}">
                                                                        <div id ="s11" class = "insideDC" onclick = "vote('1', '${j.getCommentID()}', ' ${j.getNoVote()}', '${j.getsVote()}', '${j.getCd().getDiscussionID()}')">
                                                                            <div class = "DCimg" id = "img1${j.getCommentID()}">
                                                                                <img src ="img/emptyStar.png"/>
                                                                            </div>
                                                                            <div class = "DCtext" id = "text1${j.getCommentID()}">
                                                                                1 star
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test = "${j.getsVote() != 1}">
                                                                        <div id ="s1" class = "insideDC" onclick = "vote('1', '${j.getCommentID()}', ' ${j.getNoVote()}', '${j.getsVote()}', '${j.getCd().getDiscussionID()}')">
                                                                            <div class = "DCimg" id = "img1${j.getCommentID()}">
                                                                                <img src ="img/emptyStar.png"/>
                                                                            </div>
                                                                            <div class = "DCtext" id = "text1${j.getCommentID()}">
                                                                                1 star
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </div>
                                                            </div>

                                                            <div class = "sVote">

                                                            </div>
                                                        </div>

                                                        <div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </c:forEach>
                        <div class="clearfix">
                            <div class="hint-text">Showing <b>${requestScope.size}</b> out of <b>${requestScope.totalEntity}</b> entries</div>
                            <ul class="pagination">

                                <li class="page-item"><a href ="cds?page=${(page - 1) < 1?(1):(page-1)}&class=${requestScope.cls}&search=${requestScope.key}&w=${requestScope.d}&order=${requestScope.order}&q=${requestScope.q}">Previous</a></li>
                                    <c:forEach begin = "${1}" end = "${totalPage}" var = "i">
                                    <li class="page-item"><a class ="${i == page ? "active":"noActive"}" href ="cds?page=${i}&class=${requestScope.cls}&search=${requestScope.key}&w=${requestScope.d}&order=${requestScope.order}&q=${requestScope.q}">${i}</a></li>
                                    </c:forEach>
                                <li class="page-item"><a href ="cds?page=${(page + 1) > totalPage?(1):(page+1)}&class=${requestScope.cls}&search=${requestScope.key}&w=${requestScope.d}&order=${requestScope.order}&q=${requestScope.q}">Next</a></li>
                            </ul>
                        </div>
                    </div>
                </c:if>

                <%@include file = "footer.jsp" %>
                <!-- jQuery 2.0.2 -->
            </div>
        </aside>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>

        <!-- jQuery UI 1.10.3 -->
        <script src="js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <!-- daterangepicker -->
        <script src="js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>

        <script src="js/plugins/chart.js" type="text/javascript"></script>

        <!-- datepicker
        <script src="js/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>-->
        <!-- Bootstrap WYSIHTML5
        <script src="js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>-->
        <!-- iCheck -->
        <script src="js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <!-- calendar -->
        <script src="js/plugins/fullcalendar/fullcalendar.js" type="text/javascript"></script>

        <!-- Director App -->
        <script src="js/Director/app.js" type="text/javascript"></script>

        <!-- Director dashboard demo (This is only for demo purposes) -->
        <script src="js/Director/dashboard.js" type="text/javascript"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
        <script>
                                                                            function show() {
                                                                                if ((String) (document.getElementById("a").innerHTML) == 'Post New Question') {
                                                                                    document.getElementById("a").innerHTML = 'Hide';
                                                                                    document.getElementById("new").style.display = 'block';
                                                                                }
                                                                                else {
                                                                                    document.getElementById("a").innerHTML = 'Post New Question';
                                                                                    document.getElementById("new").style.display = 'none';
                                                                                }
                                                                            }

                                                                            function cmt(id) {
                                                                                if (String(document.getElementById("cmt" + id).style.display) == 'block') {
                                                                                    document.getElementById("cmt" + id).style.display = 'none';
                                                                                } else {
                                                                                    document.getElementById("cmt" + id).style.display = 'block';
                                                                                }
                                                                            }
                                                                            var a = 0;
                                                                            var b = 2;
                                                                            const selectElement = document.getElementById("slt");
                                                                            var arr1 = Array.from(selectElement.options);
                                                                            var arr = [];
                                                                            var arr2 = [];
                                                                            for (var i = 0; i < arr1.length; i++) {
                                                                                var a = arr1[i].innerHTML, b = "";

                                                                                arr.push(a);
                                                                            }
                                                                            function showT(id) {
                                                                                if (document.getElementById("txtA" + a) != null && a != id) {
                                                                                    document.getElementById("txtA" + a).style.display = 'none';
                                                                                }
                                                                                console.log(String(document.getElementById("txtA" + id).style.display));
                                                                                if (String(document.getElementById("txtA" + id).style.display) == 'none') {
                                                                                    document.getElementById("txtA" + id).style.display = 'block';

                                                                                } else {
                                                                                    document.getElementById("txtA" + id).style.display = 'none';
                                                                                }
                                                                                b = 2;
                                                                                a = id;
                                                                                $('#summernote1' + id).summernote({
                                                                                    height: 450,
                                                                                    fontsize: 20,
                                                                                    placeholder: 'Description',
                                                                                    callbacks: {
                                                                                        onImageUpload: function (files) {
                                                                                            for (var i = 0; i < files.length; i++) {
                                                                                                sendFile1(files[i]);
                                                                                            }
                                                                                        }
                                                                                    },
                                                                                    hint: {
                                                                                        mentions: arr,
                                                                                        match: /\B@(\w*)$/,
                                                                                        search: function (keyword, callback) {
                                                                                            callback($.grep(this.mentions, function (item) {
                                                                                                var cnt = 0;
                                                                                                for (var i = 0; i < arr.length; i++) {
                                                                                                    var a = (String)(document.getElementById("summernote1" + id).value);
                                                                                                    if (a.includes("@" + arr[i])) {
                                                                                                        arr.splice(i, 1);
                                                                                                    }
                                                                                                }
                                                                                                for (var i = 0; i < arr2.length; i++) {
                                                                                                    var a = (String)(document.getElementById("summernote1" + id).value);
                                                                                                    if (!a.includes("@" + arr2[i])) {
                                                                                                        for (var j = 0; j < arr.length; j++) {
                                                                                                            if (arr[j] == arr2[i]) {
                                                                                                                cnt++;
                                                                                                            }
                                                                                                        }
                                                                                                        if (cnt == 0) {
                                                                                                            arr.push(arr2[i]);
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                return item.indexOf(keyword) == 0;
                                                                                            }));
                                                                                        },
                                                                                        content: function (item) {
                                                                                            arr2.push(item);
                                                                                            var b;
                                                                                            for (var j = 0; j < item.length; j++) {
                                                                                                if (item[j] == '-') {
                                                                                                    b = item.substring(0, j - 1);
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                            var a = document.getElementById("cls").value;
                                                                                            const el = `<a id = "usertag" href = "http://localhost:9999/prj/cds?class=` + a + `" target="_blank">` + "@" + b + `</a>`;
                                                                                            return document.execCommand("insertHTML", false, el);
                                                                                        }
                                                                                    },
                                                                                });
                                                                                document.getElementsByName("summerNoteTextReply").value = document.getElementById("summernote1" + id).value;
                                                                                console.log(document.getElementById("txt1" + id).value);
                                                                                $('#frm1').submit(function () {
                                                                                    document.getElementById("txt1" + id).value = document.getElementById("summernote1" + id).value;
                                                                                    console.log(document.getElementById("txt1" + id).value);
                                                                                    // $('textarea[name=summerNoteText]').val($('#summernote')).summernote('code');
                                                                                });

                                                                            }
        </script>

        <script>
            $(document).ready(function () {
                $('#summernote').summernote({
                    height: 450,
                    fontsize: 20,
                    placeholder: 'Description',
                    callbacks: {
                        onImageUpload: function (files) {
                            for (var i = 0; i < files.length; i++) {
                                sendFile(files[i]);
                            }
                        }
                    }
                });
                document.getElementsByName("summerNoteText").value = document.getElementById("summernote").value;

                $('#frm').submit(function () {
                    document.getElementById("ko").value = document.getElementById("topic").value;
                    document.getElementById("txt").value = document.getElementById("summernote").value;
                    // $('textarea[name=summerNoteText]').val($('#summernote')).summernote('code');
                });
            });

            function sendFile1(file) {
                var data = new FormData();
                data.append("file", file);
                $.ajax({
                    data: data,
                    encType: 'multipart/form-data',
                    type: "POST",
                    url: "http://localhost:9999/prj/cds",
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (url) {
                        var src_url = '${pageContext.request.contextPath}/' + url;

                        var image = $('<img>').attr({src: src_url, class: 'img_comment'});
                        $('#summernote1' + a).summernote("insertNode", image[0]);
                        document.getElementById("txt").value = document.getElementById("summernote1" + a).value;
                        console.log(document.getElementById("ko1").value);
                    },
                    error: function (data) {
                        console.log("Error");
                    }
                });
            }

            function sendFile(file) {
                var data = new FormData();
                data.append("file", file);
                $.ajax({
                    data: data,
                    encType: 'multipart/form-data',
                    type: "POST",
                    url: "http://localhost:9999/prj/cds",
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (url) {
                        var src_url = '${pageContext.request.contextPath}/' + url;

                        var image = $('<img>').attr({src: src_url, class: 'img_comment'});
                        $('#summernote').summernote("insertNode", image[0]);
                        document.getElementById("txt").value = document.getElementById("summernote").value;
                        console.log(document.getElementById("ko1").value);
                    },
                    error: function (data) {
                        console.log("Error");
                    }
                });
            }

            function go1() {
                document.getElementById("txt").value = document.getElementById("summernote1");
                console.log(document.getElementById("summernote1"));
            }



        </script>

        <script>
            if (document.getElementById("ms") !== null) {
                console.log("1");
                var duration = 3000;
                const main = document.getElementById("toast");
                if (main) {
                    const toast = document.createElement("div");

                    // Auto remove toast
                    const autoRemoveId = setTimeout(function () {
                        main.removeChild(toast);
                    }, duration + 1000);

                    // Remove toast when clicked
                    toast.onclick = function (e) {
                        if (e.target.closest(".toast__close")) {
                            main.removeChild(toast);
                            clearTimeout(autoRemoveId);
                        }
                    };

                    const icons = {
                        success: "fas fa-check-circle",
                        info: "fas fa-info-circle",
                        warning: "fas fa-exclamation-circle",
                        error: "fas fa-exclamation-circle"
                    };
                    const delay = (duration / 1000).toFixed(2);

                    toast.classList.add("toast", `toast--${type}`);
                    toast.style.animation = `slideInLeft ease .3s, fadeOut linear 1s ${delay}s forwards`;

                    toast.innerHTML = `
                    <div class="toast__icon">
                        <i class="${icons}"></i>
                    </div>
                    <div class="toast__body">
                        <h3 class="toast__title" style = 'background-color: green; color: white'">` + document.getElementById("ms").innerHTML + `</h3>
                    </div>
                    <div class="toast__close">
                        <i class="fas fa-times"></i>
                    </div>
                `;
                    main.appendChild(toast);
                }
            }


        </script>

        <!-- Director for demo purposes -->
        <script type="text/javascript">
            $('input').on('ifChecked', function (event) {
                // var element = $(this).parent().find('input:checkbox:first');
                // element.parent().parent().parent().addClass('highlight');
                $(this).parents('li').addClass("task-done");
                console.log('ok');
            });
            $('input').on('ifUnchecked', function (event) {
                // var element = $(this).parent().find('input:checkbox:first');
                // element.parent().parent().parent().removeClass('highlight');
                $(this).parents('li').removeClass("task-done");
                console.log('not');
            });

        </script>
        <script>
            $('#noti-box').slimScroll({
                height: '400px',
                size: '5px',
                BorderRadius: '5px'
            });

            $('input[type="checkbox"].flat-grey, input[type="radio"].flat-grey').iCheck({
                checkboxClass: 'icheckbox_flat-grey',
                radioClass: 'iradio_flat-grey'
            });
        </script>
        <script type="text/javascript">
            $(function () {
                "use strict";
                //BAR CHART
                var data = {
                    labels: ["January", "February", "March", "April", "May", "June", "July"],
                    datasets: [
                        {
                            label: "My First dataset",
                            fillColor: "rgba(220,220,220,0.2)",
                            strokeColor: "rgba(220,220,220,1)",
                            pointColor: "rgba(220,220,220,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(220,220,220,1)",
                            data: [65, 59, 80, 81, 56, 55, 40]
                        },
                        {
                            label: "My Second dataset",
                            fillColor: "rgba(151,187,205,0.2)",
                            strokeColor: "rgba(151,187,205,1)",
                            pointColor: "rgba(151,187,205,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(151,187,205,1)",
                            data: [28, 48, 40, 19, 86, 27, 90]
                        }
                    ]
                };
                new Chart(document.getElementById("linechart").getContext("2d")).Line(data, {
                    responsive: true,
                    maintainAspectRatio: false,
                });

            });
            // Chart.defaults.global.responsive = true;
        </script>
        <script type = "text/javascript">
            function go(id, cls) {
                window.location = "dd?id=" + id + "&class=" + cls;
            }

            function sbmit() {
                document.getElementById("form").submit();
            }

            function vote1(cls, id) {
                console.log("da");
                var url = "http://localhost:9999/prj/cds?class=" + cls;
                let http = new XMLHttpRequest();
                http.open('POST', url, true);
                http.setRequestHeader("Access-Control-Allow-Origin", "*");
                http.setRequestHeader("Access-Control-Allow-Origin", "POST, GET, PUT");
                http.setRequestHeader("Access-Control-Allow-Origin", "Content-Type");
                http.onload = function () {
                };
                //http.send("time=5");
                if (document.getElementById("v" + id).src === "http://localhost:9999/prj/img/voteIcon.png") {
                    http.send("vote=" + 1 + "&id=" + id);
                    document.getElementById("vote" + id).innerHTML = (Number)(document.getElementById("vote" + id).innerHTML) + 1;
                    document.getElementById("v" + id).src = "img/blackVoteIcon.png";
                } else {
                    http.send("vote=" + 0 + "&id=" + id);
                    document.getElementById("vote" + id).innerHTML = (Number)(document.getElementById("vote" + id).innerHTML) - 1;
                    document.getElementById("v" + id).src = "img/voteIcon.png";
                }
            }

        </script>

        <script>
            function normal5(id) {
                document.getElementById("img5" + id).classList.remove("DCimg5");
                document.getElementById("img5" + id).classList.add("DCimg");
                document.getElementById("text5" + id).classList.remove("DCtext5");
                document.getElementById("text5" + id).classList.add("DCtext");
            }

            function normal4(id) {
                document.getElementById("img4" + id).classList.remove("DCimg4");
                document.getElementById("img4" + id).classList.add("DCimg");
                document.getElementById("text4" + id).classList.remove("DCtext4");
                document.getElementById("text4" + id).classList.add("DCtext");
            }
            function normal3(id) {
                document.getElementById("img3" + id).classList.remove("DCimg3");
                document.getElementById("img3" + id).classList.add("DCimg");
                document.getElementById("text3" + id).classList.remove("DCtext3");
                document.getElementById("text3" + id).classList.add("DCtext");
            }

            function normal2(id) {
                document.getElementById("img2" + id).classList.remove("DCimg2");
                document.getElementById("img2" + id).classList.add("DCimg");
                document.getElementById("text2" + id).classList.remove("DCtext2");
                document.getElementById("text2" + id).classList.add("DCtext");
            }
            var startTime, endTime;
            function start() {
                startTime = new Date();
            }
            ;

            function end() {
                endTime = new Date();
                var timeDiff = endTime - startTime; //in ms
                // strip the ms
                timeDiff /= 1000;

                // get seconds 
                var seconds = Math.round(timeDiff);
                return seconds;
            }

            function normal1(id) {
                document.getElementById("img1" + id).classList.remove("DCimg1");
                document.getElementById("img1" + id).classList.add("DCimg");
                document.getElementById("text1" + id).classList.remove("DCtext1");
                document.getElementById("text1" + id).classList.add("DCtext");
            }

            function vote(num, id, vote, sVote, cd) {
                var second = end();
                start();

                var a = "", b = "";
                var url = "http://localhost:9999/prj/dd?id=" + cd;
                let http = new XMLHttpRequest();
                http.open('POST', url, true);
                http.setRequestHeader("Access-Control-Allow-Origin", "*");
                http.setRequestHeader("Access-Control-Allow-Origin", "POST, GET, PUT");
                http.setRequestHeader("Access-Control-Allow-Origin", "Content-Type");
                if ((String)(document.getElementById(id).style.color) === "red") {
                    if (Number(num) == 5) {
                        document.getElementById(id).style.fontWeight = "normal";
                        document.getElementById(id).style.color = "#566787";
                        document.getElementById("img5" + id).classList.remove("DCimg5");
                        document.getElementById("img5" + id).classList.add("DCimg");
                        document.getElementById("text5" + id).classList.remove("DCtext5");
                        document.getElementById("text5" + id).classList.add("DCtext");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) - (Number)(sVote);
                        http.send("vote=" + 0 + "&id=" + id);
                    } else if (Number(num) == 4) {
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "blue";
                        document.getElementById("img4" + id).classList.remove("DCimg");
                        document.getElementById("img4" + id).classList.add("DCimg4");
                        document.getElementById("text4" + id).classList.remove("DCtext");
                        document.getElementById("text4" + id).classList.add("DCtext4");
                        normal5(id);
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 4 - (Number)(sVote);
                        http.send("vote=" + 4 + "&id=" + id);
                    } else if (Number(num) == 3) {
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "yellow";
                        document.getElementById("img3" + id).classList.remove("DCimg");

                        document.getElementById("img3" + id).classList.add("DCimg3");
                        document.getElementById("text3" + id).classList.remove("DCtext");
                        document.getElementById("text3" + id).classList.add("DCtext3");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 3 - (Number)(sVote);
                        http.send("vote=" + 3 + "&id=" + id);
                        normal5(id);
                    } else if (Number(num) == 2) {
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "orange";
                        document.getElementById("img2" + id).classList.remove("DCimg");
                        document.getElementById("img2" + id).classList.add("DCimg2");
                        document.getElementById("text2" + id).classList.remove("DCtext");
                        document.getElementById("text2" + id).classList.add("DCtext2");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 2 - (Number)(sVote);
                        http.send("vote=" + 2 + "&id=" + id);
                        normal5(id);
                    } else if (Number(num) == 1) {
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "purple";
                        document.getElementById("img1" + id).classList.remove("DCimg");
                        document.getElementById("img1" + id).classList.add("DCimg1");
                        document.getElementById("text1" + id).classList.remove("DCtext");
                        document.getElementById("text1" + id).classList.add("DCtext1");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 1 - (Number)(sVote);
                        normal5(id);
                        http.send("vote=" + 1 + "&id=" + id);
                    }
                } else if ((String)(document.getElementById(id).style.color) === "blue") {
                    if (Number(num) == 5) {
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "red";
                        document.getElementById("img5" + id).classList.remove("DCimg");
                        document.getElementById("img5" + id).classList.add("DCimg5");
                        document.getElementById("text5" + id).classList.remove("DCtext");
                        document.getElementById("text5" + id).classList.add("DCtext5");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 5 - (Number)(sVote);
                        normal4(id);
                        http.send("vote=" + 5 + "&id=" + id);
                    } else if (Number(num) == 4) {
                        document.getElementById(id).style.fontWeight = "normal";
                        document.getElementById(id).style.color = "#566787";
                        document.getElementById("img4" + id).classList.remove("DCimg4");
                        document.getElementById("img4" + id).classList.add("DCimg");
                        document.getElementById("text4" + id).classList.remove("DCtext4");
                        document.getElementById("text4" + id).classList.add("DCtext");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) - (Number)(sVote);
                        http.send("vote=" + 0 + "&id=" + id);
                    } else if (Number(num) == 3) {
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "yellow";
                        document.getElementById("img3" + id).classList.remove("DCimg");
                        document.getElementById("img3" + id).classList.add("DCimg3");
                        document.getElementById("text3" + id).classList.remove("DCtext");
                        document.getElementById("text3" + id).classList.add("DCtext3");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 3 - (Number)(sVote);
                        normal4(id);
                        http.send("vote=" + 3 + "&id=" + id);
                    } else if (Number(num) == 2) {
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "orange";
                        document.getElementById("img2" + id).classList.remove("DCimg");
                        document.getElementById("img2" + id).classList.add("DCimg2");
                        document.getElementById("text2" + id).classList.remove("DCtext");
                        document.getElementById("text2" + id).classList.add("DCtext2");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 2 - (Number)(sVote);
                        normal4(id);
                        http.send("vote=" + 2 + "&id=" + id);
                    } else if (Number(num) == 1) {
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "purple";
                        document.getElementById("img1" + id).classList.remove("DCimg");
                        document.getElementById("img1" + id).classList.add("DCimg1");
                        document.getElementById("text1" + id).classList.remove("DCtext");
                        document.getElementById("text1" + id).classList.add("DCtext1");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 1 - (Number)(sVote);
                        normal4(id);
                        http.send("vote=" + 1 + "&id=" + id);
                    }
                } else if ((String)(document.getElementById(id).style.color) === "yellow") {
                    if (Number(num) == 5) {
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "red";
                        document.getElementById("img5" + id).classList.remove("DCimg");
                        document.getElementById("img5" + id).classList.add("DCimg5");
                        document.getElementById("text5" + id).classList.remove("DCtext");
                        document.getElementById("text5" + id).classList.add("DCtext5");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 5 - (Number)(sVote);
                        normal3(id);
                        http.send("vote=" + 5 + "&id=" + id);
                    } else if (Number(num) == 4) {
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "blue";
                        document.getElementById("img4" + id).classList.remove("DCimg");
                        document.getElementById("img4" + id).classList.add("DCimg4");
                        document.getElementById("text4" + id).classList.remove("DCtext");
                        document.getElementById("text4" + id).classList.add("DCtext4");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 4 - (Number)(sVote);
                        normal3(id);
                        http.send("vote=" + 4 + "&id=" + id);
                    } else if (Number(num) == 3) {
                        document.getElementById(id).style.fontWeight = "normal";
                        document.getElementById(id).style.color = "#566787";
                        document.getElementById("img3" + id).classList.remove("DCimg3");
                        document.getElementById("img3" + id).classList.add("DCimg");
                        document.getElementById("text3" + id).classList.remove("DCtext3");
                        document.getElementById("text3" + id).classList.add("DCtext");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) - (Number)(sVote);
                        http.send("vote=" + 0 + "&id=" + id);
                    } else if (Number(num) == 2) {

                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "orange";
                        document.getElementById("img2" + id).classList.remove("DCimg");
                        document.getElementById("img2" + id).classList.add("DCimg2");
                        document.getElementById("text2" + id).classList.remove("DCtext");
                        document.getElementById("text2" + id).classList.add("DCtext2");
                        normal3(id);
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 2 - (Number)(sVote);
                        http.send("vote=" + 2 + "&id=" + id);
                    } else if (Number(num) == 1) {

                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "purple";
                        document.getElementById("img1" + id).classList.remove("DCimg");
                        document.getElementById("img1" + id).classList.add("DCimg1");
                        document.getElementById("text1" + id).classList.remove("DCtext");
                        document.getElementById("text1" + id).classList.add("DCtext1");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 1 - (Number)(sVote);
                        normal3(id);
                        http.send("vote=" + 1 + "&id=" + id);
                    }
                } else if ((String)(document.getElementById(id).style.color) === "orange") {
                    if (Number(num) == 5) {

                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "red";
                        document.getElementById("img5" + id).classList.remove("DCimg");
                        document.getElementById("img5" + id).classList.add("DCimg5");
                        document.getElementById("text5" + id).classList.remove("DCtext");
                        document.getElementById("text5" + id).classList.add("DCtext5");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 5 - (Number)(sVote);
                        normal2(id);
                        http.send("vote=" + 5 + "&id=" + id);
                    } else if (Number(num) == 4) {

                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "blue";
                        document.getElementById("img4" + id).classList.remove("DCimg");
                        document.getElementById("img4" + id).classList.add("DCimg4");
                        document.getElementById("text4" + id).classList.remove("DCtext");
                        document.getElementById("text4" + id).classList.add("DCtext4");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 4 - (Number)(sVote);
                        normal2(id);
                        http.send("vote=" + 4 + "&id=" + id);
                    } else if (Number(num) == 3) {

                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "yellow";
                        document.getElementById("img3" + id).classList.remove("DCimg");
                        document.getElementById("img3" + id).classList.add("DCimg3");
                        document.getElementById("text3" + id).classList.remove("DCtext");
                        document.getElementById("text3" + id).classList.add("DCtext3");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 3 - (Number)(sVote);
                        normal2(id);
                        http.send("vote=" + 3 + "&id=" + id);
                    } else if (Number(num) == 2) {

                        document.getElementById(id).style.fontWeight = "normal";
                        document.getElementById(id).style.color = "#566787";
                        document.getElementById("img2" + id).classList.remove("DCimg2");
                        document.getElementById("img2" + id).classList.add("DCimg");
                        document.getElementById("text2" + id).classList.remove("DCtext2");
                        document.getElementById("text2" + id).classList.add("DCtext");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) - (Number)(sVote);
                        http.send("vote=" + 0 + "&id=" + id);
                    } else if (Number(num) == 1) {

                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "purple";
                        document.getElementById("img1" + id).classList.remove("DCimg");
                        document.getElementById("img1" + id).classList.add("DCimg1");
                        document.getElementById("text1" + id).classList.remove("DCtext");
                        document.getElementById("text1" + id).classList.add("DCtext1");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 1 - (Number)(sVote);
                        normal2(id);
                        http.send("vote=" + 1 + "&id=" + id);
                    }
                } else if ((String)(document.getElementById(id).style.color) === "purple") {
                    if (Number(num) == 5) {

                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "red";
                        document.getElementById("img5" + id).classList.remove("DCimg");
                        document.getElementById("img5" + id).classList.add("DCimg5");
                        document.getElementById("text5" + id).classList.remove("DCtext");
                        document.getElementById("text5" + id).classList.add("DCtext5");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 5 - (Number)(sVote);
                        normal1(id);
                        http.send("vote=" + 5 + "&id=" + id);
                    } else if (Number(num) == 4) {

                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "blue";
                        document.getElementById("img4" + id).classList.remove("DCimg");
                        document.getElementById("img4" + id).classList.add("DCimg4");
                        document.getElementById("text4" + id).classList.remove("DCtext");
                        document.getElementById("text4" + id).classList.add("DCtext4");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 4 - (Number)(sVote);
                        normal1(id);
                        http.send("vote=" + 4 + "&id=" + id);
                    } else if (Number(num) == 3) {

                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "yellow";
                        document.getElementById("img3" + id).classList.remove("DCimg");
                        document.getElementById("img3" + id).classList.add("DCimg3");
                        document.getElementById("text3" + id).classList.remove("DCtext");
                        document.getElementById("text3" + id).classList.add("DCtext3");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 3 - (Number)(sVote);
                        normal1(id);
                        http.send("vote=" + 3 + "&id=" + id);
                    } else if (Number(num) == 2) {

                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "orange";
                        document.getElementById("img2" + id).classList.remove("DCimg");
                        document.getElementById("img2" + id).classList.add("DCimg2");
                        document.getElementById("text2" + id).classList.remove("DCtext");
                        document.getElementById("text2" + id).classList.add("DCtext2");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) + 2 - (Number)(sVote);
                        normal1(id);
                        http.send("vote=" + 2 + "&id=" + id);
                    } else if (Number(num) == 1) {

                        document.getElementById(id).style.fontWeight = "normal";
                        document.getElementById(id).style.color = "#566787";
                        document.getElementById("img1" + id).classList.remove("DCimg1");
                        document.getElementById("img1" + id).classList.add("DCimg");
                        document.getElementById("text1" + id).classList.remove("DCtext1");
                        document.getElementById("text1" + id).classList.add("DCtext");
                        document.getElementById("vte" + id).innerHTML = (Number)(vote) - (Number)(sVote);
                        http.send("vote=" + 0 + "&id=" + id);
                    }
                } else {
                    console.log("1");
                    if (Number(num) == 5) {
                        if (document.getElementById("s51") !== null) {
                            document.getElementById("s51").setAttribute("id", "s5");
                        }
                        if (document.getElementById("s41") !== null) {
                            document.getElementById("s41").setAttribute("id", "s4");
                        }
                        if (document.getElementById("s31") !== null) {
                            document.getElementById("s31").setAttribute("id", "s3");
                        }
                        if (document.getElementById("s21") !== null) {
                            document.getElementById("s21").setAttribute("id", "s2");
                        }
                        if (document.getElementById("s11") !== null) {
                            document.getElementById("s11").setAttribute("id", "s2");
                        }
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "red";
                        if ((String)(document.getElementById(id).className) == 'dropbtn5') {
                            document.getElementById(id).classList.remove("dropbtn5");
                            document.getElementById(id).classList.add("dropbtn");
                            document.getElementById(id).style.fontWeight = "normal";
                            document.getElementById(id).style.color = "#566787";
                            document.getElementById("vte" + id).innerHTML = (Number)(vote) - (Number)(sVote);
                            http.send("vote=" + 0 + "&id=" + id);
                        } else {
                            document.getElementById("img5" + id).classList.remove("DCimg");
                            document.getElementById("img5" + id).classList.add("DCimg5");
                            document.getElementById("text5" + id).classList.remove("DCtext");
                            document.getElementById("text5" + id).classList.add("DCtext5");
                            document.getElementById("vte" + id).innerHTML = (Number)(vote) + 5 - (Number)(sVote);
                            http.send("vote=" + 5 + "&id=" + id);
                        }


                    } else if (Number(num) == 4) {
                        if (document.getElementById("s51") !== null) {
                            document.getElementById("s51").setAttribute("id", "s5");
                        }
                        if (document.getElementById("s41") !== null) {
                            document.getElementById("s41").setAttribute("id", "s4");
                        }
                        if (document.getElementById("s31") !== null) {
                            document.getElementById("s31").setAttribute("id", "s3");
                        }
                        if (document.getElementById("s21") !== null) {
                            document.getElementById("s21").setAttribute("id", "s2");
                        }
                        if (document.getElementById("s11") !== null) {
                            document.getElementById("s11").setAttribute("id", "s2");
                        }
                        document.getElementById(id).style.color = "blue";
                        if ((String)(document.getElementById(id).className) == 'dropbtn4') {
                            document.getElementById(id).classList.remove("dropbtn4");
                            document.getElementById(id).classList.add("dropbtn");
                            document.getElementById(id).style.fontWeight = "normal";
                            document.getElementById(id).style.color = "#566787";

                            document.getElementById("vte" + id).innerHTML = (Number)(vote) - (Number)(sVote);
                            http.send("vote=" + 0 + "&id=" + id);
                        } else {
                            document.getElementById("img4" + id).classList.remove("DCimg");
                            document.getElementById("img4" + id).classList.add("DCimg4");
                            document.getElementById("text4" + id).classList.remove("DCtext");
                            document.getElementById("text4" + id).classList.add("DCtext4");
                            document.getElementById("vte" + id).innerHTML = (Number)(vote) + 4 - (Number)(sVote);
                            http.send("vote=" + 4 + "&id=" + id);
                        }
                    } else if (Number(num) == 3) {
                        if (document.getElementById("s51") !== null) {
                            document.getElementById("s51").setAttribute("id", "s5");
                        }
                        if (document.getElementById("s41") !== null) {
                            document.getElementById("s41").setAttribute("id", "s4");
                        }
                        if (document.getElementById("s31") !== null) {
                            document.getElementById("s31").setAttribute("id", "s3");
                        }
                        if (document.getElementById("s21") !== null) {
                            document.getElementById("s21").setAttribute("id", "s2");
                        }
                        if (document.getElementById("s11") !== null) {
                            document.getElementById("s11").setAttribute("id", "s2");
                        }
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "yellow";
                        if ((String)(document.getElementById(id).className) == 'dropbtn3') {
                            document.getElementById(id).classList.remove("dropbtn3");
                            document.getElementById(id).classList.add("dropbtn");
                            document.getElementById(id).style.fontWeight = "normal";
                            document.getElementById(id).style.color = "#566787";
                            document.getElementById("vte" + id).innerHTML = (Number)(vote) - (Number)(sVote);
                            http.send("vote=" + 0 + "&id=" + id);
                        } else {
                            document.getElementById("img3" + id).classList.remove("DCimg");
                            document.getElementById("img3" + id).classList.add("DCimg3");
                            document.getElementById("text3" + id).classList.remove("DCtext");
                            document.getElementById("text3" + id).classList.add("DCtext3");
                            document.getElementById("vte" + id).innerHTML = (Number)(vote) + 3 - (Number)(sVote);
                            http.send("vote=" + 3 + "&id=" + id);
                        }

                    } else if (Number(num) == 2) {
                        if (document.getElementById("s51") !== null) {
                            document.getElementById("s51").setAttribute("id", "s5");
                        }
                        if (document.getElementById("s41") !== null) {
                            document.getElementById("s41").setAttribute("id", "s4");
                        }
                        if (document.getElementById("s31") !== null) {
                            document.getElementById("s31").setAttribute("id", "s3");
                        }
                        if (document.getElementById("s21") !== null) {
                            document.getElementById("s21").setAttribute("id", "s2");
                        }
                        if (document.getElementById("s11") !== null) {
                            document.getElementById("s11").setAttribute("id", "s2");
                        }
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "orange";
                        if ((String)(document.getElementById(id).className) == 'dropbtn2') {
                            document.getElementById(id).classList.remove("dropbtn2");
                            document.getElementById(id).classList.add("dropbtn");
                            document.getElementById(id).style.fontWeight = "normal";
                            document.getElementById(id).style.color = "#566787";
                            document.getElementById("vte" + id).innerHTML = (Number)(vote) - (Number)(sVote);
                            http.send("vote=" + 0 + "&id=" + id);
                        } else {
                            document.getElementById("img2" + id).classList.remove("DCimg");
                            document.getElementById("img2" + id).classList.add("DCimg2");
                            document.getElementById("text2" + id).classList.remove("DCtext");
                            document.getElementById("text2" + id).classList.add("DCtext2");
                            document.getElementById("vte" + id).innerHTML = (Number)(vote) + 2 - (Number)(sVote);
                            http.send("vote=" + 2 + "&id=" + id);
                        }

                    } else if (Number(num) == 1) {
                        if (document.getElementById("s51") !== null) {
                            document.getElementById("s51").setAttribute("id", "s5");
                        }
                        if (document.getElementById("s41") !== null) {
                            document.getElementById("s41").setAttribute("id", "s4");
                        }
                        if (document.getElementById("s31") !== null) {
                            document.getElementById("s31").setAttribute("id", "s3");
                        }
                        if (document.getElementById("s21") !== null) {
                            document.getElementById("s21").setAttribute("id", "s2");
                        }
                        if (document.getElementById("s11") !== null) {
                            document.getElementById("s11").setAttribute("id", "s2");
                        }
                        document.getElementById(id).style.fontWeight = "bold";
                        document.getElementById(id).style.color = "purple";
                        if ((String)(document.getElementById(id).className) == 'dropbtn1') {
                            document.getElementById(id).classList.remove("dropbtn1");
                            document.getElementById(id).classList.add("dropbtn");
                            document.getElementById(id).style.fontWeight = "normal";
                            document.getElementById(id).style.color = "#566787";
                            document.getElementById("vte" + id).innerHTML = (Number)(vote) - (Number)(sVote);
                            http.send("vote=" + 0 + "&id=" + id);
                        } else {
                            document.getElementById("img1" + id).classList.remove("DCimg");
                            document.getElementById("img1" + id).classList.add("DCimg1");
                            document.getElementById("text1" + id).classList.remove("DCtext");
                            document.getElementById("text1" + id).classList.add("DCtext1");

                            document.getElementById("vte" + id).innerHTML = (Number)(vote) + 1 - (Number)(sVote);
                            http.send("vote=" + 1 + "&id=" + id);
                        }

                    }
                }

            }
        </script>
    </body>
</html>
