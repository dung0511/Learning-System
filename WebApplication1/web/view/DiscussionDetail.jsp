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
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"> -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

        <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">

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
                width: 450px;
            }
            .dcontent{
                width:550px;
                text-overflow: ellipsis;
                white-space: nowrap;
                overflow: hidden;
            }

            .dcontentD{
                width:700px;

            }

            .in4{
                margin-left: 100px;
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

            .topicO1{
                display: flex;
                flex-direction: row;
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
                height: 25px;
            }

            .oIN4Img1{
                width: 25px;
                margin-right: 10px;
                height: 25px;
            }

            .topicN{
                margin-right: 300px;
            }

            .in4:hover{
                cursor: pointer;
            }

            .clearfix{
                display: flex;
                flex-direction: row;
                align-items: center;
            }

            .pagination{
                margin-left: 625px;
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
                margin-top: 100px;
                font-size: 30px;
                text-align: center;
                margin-bottom: 400px;
            }

            .navTtle{
                display: flex;
                font-weight: bold;
                font-size: 20px;
                justify-content: space-between;
            }

            .allR{
                margin-left: 130px;
            }

            .uname1{
                display: flex;
                flex-direction: column;
                justify-content: flex-start;
                width: 200px;
                margin-right: 550px;
            }

            .udate1{
                font-size: 12px;
            }

            .content{
                overflow-x: hidden;
            }

            .in4{
                overflow-x: hidden;
            }

            .btnV{
                margin-top: 10px;
            }

            /* Dropdown Button */
            .dropbtn {

                padding-right: 12px;
                padding-left: 12px;
                padding-top: 10px;
                padding-bottom: 10px;
                width: 100px;
                font-size: 16px;
                border: none;
            }

            .dropbtn5 {

                padding-right: 12px;
                padding-left: 12px;
                padding-top: 10px;
                padding-bottom: 10px;
                width: 100px;
                font-size: 16px;
                border: none;
                font-weight: bold;
                color: red;
            }



            .dropbtn4 {

                padding-right: 12px;
                padding-left: 12px;
                padding-top: 10px;
                padding-bottom: 10px;
                width: 100px;
                font-size: 16px;
                border: none;
                font-weight: bold;
                color: blue;
            }

            .dropbtn3 {

                padding-right: 12px;
                padding-left: 12px;
                padding-top: 10px;
                padding-bottom: 10px;
                width: 100px;
                font-size: 16px;
                border: none;
                font-weight: bold;
                color: yellow;
            }

            .dropbtn2 {

                padding-right: 12px;
                padding-left: 12px;
                padding-top: 10px;
                padding-bottom: 10px;
                width: 100px;
                font-size: 16px;
                border: none;
                font-weight: bold;
                color: orange;
            }

            .dropbtn1 {

                padding-right: 12px;
                padding-left: 12px;
                padding-top: 10px;
                padding-bottom: 10px;
                width: 100px;
                font-size: 16px;
                border: none;
                font-weight: bold;
                color: purple;
            }

            .dropbtn5:hover #s5 .DCimg img{
                filter: drop-shadow(0px 1000px 0 red);
                transform: translateY(-1000px);
            }

            .dropbtn5:hover #s5 .DCtext{
                color: black;
                font-weight: bold;
            }

            .dropbtn5:hover .dropdown-content {
                display: flex;
                flex-direction: row;
            }

            .dropbtn4:hover #s4 .DCimg img{
                filter: drop-shadow(0px 1000px 0 blue);
                transform: translateY(-1000px);
            }

            .dropbtn4:hover #s4 .DCtext{
                color: black;
                font-weight: bold;
            }

            .dropbtn4:hover .dropdown-content {
                display: flex;
                flex-direction: row;
            }

            .dropbtn3:hover #s3 .dropdown-content img{
                filter: drop-shadow(0px 1000px 0 yellow);
                transform: translateY(-1000px);
            }

            .dropbtn3:hover #s3 .DCtext{
                color: black;
                font-weight: bold;
            }


            .dropbtn3:hover .dropdown-content {
                display: flex;
                flex-direction: row;
            }
            .dropbtn2:hover #s2 .DCimg img{
                filter: drop-shadow(0px 1000px 0 orange);
                transform: translateY(-1000px);
            }

            .dropbtn2:hover #s2 .DCtext{
                color: black;
                font-weight: bold;
            }

            .dropbtn2:hover .dropdown-content {
                display: flex;
                flex-direction: row;
            }

            .dropbtn1:hover #s1 .DCimg img{
                filter: drop-shadow(0px 1000px 0 purple);
                transform: translateY(-1000px);
            }

            .dropbtn1:hover #s1 .DCtext{
                color: black;
                font-weight: bold;
            }

            .dropbtn1:hover .dropdown-content {
                display: flex;
                flex-direction: row;
            }

            /* The container <div> - needed to position the dropdown content */
            .dropdown {
                position: relative;
                display: flex;
                flex-direction: row;
            }

            /* Dropdown Content (Hidden by Default) */
            .dropdown-content {
                display: none;
                margin-left: 120px;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            /* Links inside the dropdown */
            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            /* Change color of dropdown links on hover */


            /* Show the dropdown menu on hover */
            .dropdown:hover .dropdown-content {
                display: flex;
                flex-direction: row;
            }


            .insideDC{
                display: flex;
                flex-direction: row;
                align-items: center;
                border-right: 1px solid black;
                border-left: 1px solid black;
                padding-right: 10px;
                padding-left: 10px;

            }

            .DCimg{
                width: 40px;
                height: 42px;
                padding-top: 4px;
                padding-right: 2px;
                padding-left: 2px;
                padding-bottom: 0px;
            }

            .DCimg img{
                width: 50%;
                height: 50%;
            }

            .DCtext{
                padding-bottom: 4px;
            }

            .DCimg5{
                width: 40px;
                height: 42px;
                padding-top: 4px;
                padding-right: 2px;
                padding-left: 2px;
                padding-bottom: 0px;
            }

            .DCimg5 img{
                width: 50%;
                height: 50%;
            }

            .DCtext5{
                padding-bottom: 4px;
            }

            .DCimg4{
                width: 40px;
                height: 42px;
                padding-top: 4px;
                padding-right: 2px;
                padding-left: 2px;
                padding-bottom: 0px;
            }

            .DCimg4 img{
                width: 50%;
                height: 50%;
            }

            .DCtext4{
                padding-bottom: 4px;
            }

            .DCimg3{
                width: 40px;
                height: 42px;
                padding-top: 4px;
                padding-right: 2px;
                padding-left: 2px;
                padding-bottom: 0px;
            }

            .DCimg3 img{
                width: 50%;
                height: 50%;
            }

            .DCtext3{
                padding-bottom: 4px;
            }

            .DCimg2{
                width: 40px;
                height: 42px;
                padding-top: 4px;
                padding-right: 2px;
                padding-left: 2px;
                padding-bottom: 0px;
            }

            .DCimg2 img{
                width: 50%;
                height: 50%;
            }

            .DCtext2{
                padding-bottom: 4px;
            }

            .DCimg1{
                width: 40px;
                height: 42px;
                padding-top: 4px;
                padding-right: 2px;
                padding-left: 2px;
                padding-bottom: 0px;
            }

            .DCimg1 img{
                width: 50%;
                height: 50%;
            }

            .DCtext1{
                padding-bottom: 4px;
            }
            #s5:hover .DCimg img{
                filter: drop-shadow(0px 1000px 0 red);
                transform: translateY(-1000px);
            }
            .DCimg5 img {
                filter: drop-shadow(0px 1000px 0 red);
                transform: translateY(-1000px);
            }
            .DCtext5 {
                color: black;
                font-weight: bold;
            }
            .DCimg4 img {
                filter: drop-shadow(0px 1000px 0 blue);
                transform: translateY(-1000px);
            }
            .DCtext4 {
                color: black;
                font-weight: bold;
            }
            .DCimg3 img {
                filter: drop-shadow(0px 1000px 0 yellow);
                transform: translateY(-1000px);
            }
            .DCtext3 {
                color: black;
                font-weight: bold;
            }
            .DCimg2 img {
                filter: drop-shadow(0px 1000px 0 orange);
                transform: translateY(-1000px);
            }
            .DCtext2 {
                color: black;
                font-weight: bold;
            }
            .DCimg1 img {
                filter: drop-shadow(0px 1000px 0 purple);
                transform: translateY(-1000px);
            }
            .DCtext1 {
                color: black;
                font-weight: bold;
            }
            #s5:hover .DCtext{
                color: black;
                font-weight: bold;
            }



            #s4:hover .DCimg img{
                filter: drop-shadow(0px 1000px 0 blue);
                transform: translateY(-1000px);
            }

            #s4:hover .DCtext{
                color: black;
                font-weight: bold;
            }

            #s3:hover .DCimg img{
                filter: drop-shadow(0px 1000px 0 yellow);
                transform: translateY(-1000px);
            }

            #s3:hover .DCtext{
                color: black;
                font-weight: bold;
            }

            #s2:hover .DCimg img{
                filter: drop-shadow(0px 1000px 0 orange);
                transform: translateY(-1000px);
            }

            #s2:hover .DCtext{
                color: black;
                font-weight: bold;
            }

            #s1:hover .DCimg img{
                filter: drop-shadow(0px 1000px 0 purple);
                transform: translateY(-1000px);
            }

            #s1:hover .DCtext{
                color: black;
                font-weight: bold;
            }

            /*POP UP*/
            .popup {
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.4);
                display: none;
            }
            .popup-content {
                background-color: white;
                margin: 10% auto;
                padding: 20px;
                border: 1px solid #888888;
                width: 30%;
                font-weight: bolder;
            }
            .popup-content button {
                display: block;
                margin: 0 auto;
            }
            .show {
                display: block;
            }
            h1 {
                color: green;
            }
            .popup-content{
                font-size: 20px;
            }
            body {
                font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
                font-size: 14px;
                line-height: 1.42857143;
                color: #333;
                background-color: #fff;
            }
            .popupBtn{
                display: flex;
                flex-direction: row;
                justify-content: center;
            }

            .popupBtn button{
                width: 80px;
                border: 1px solid black;
                border-radius: 12px;
                padding: 5px;
            }

            .popupBtn button:hover{
                background-color: black;
                color: white;
            }

            #s51 .DCimg img{
                filter: drop-shadow(0px 1000px 0 red);
                transform: translateY(-1000px);
            }

            #s51 .DCtext{
                color: black;
                font-weight: bold;
            }

            #s41 .DCimg img{
                filter: drop-shadow(0px 1000px 0 blue);
                transform: translateY(-1000px);
            }

            #s41 .DCtext{
                color: black;
                font-weight: bold;
            }

            #s31 .DCimg img{
                filter: drop-shadow(0px 1000px 0 yellow);
                transform: translateY(-1000px);
            }

            #s31 .DCtext{
                color: black;
                font-weight: bold;
            }

            #s21 .DCimg img{
                filter: drop-shadow(0px 1000px 0 orange);
                transform: translateY(-1000px);
            }

            #s21 .DCtext{
                color: black;
                font-weight: bold;
            }

            #s11 .DCimg img{
                filter: drop-shadow(0px 1000px 0 purple);
                transform: translateY(-1000px);
            }

            #s11 .DCtext{
                color: black;
                font-weight: bold;
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



            .hint-text{
                margin-right: 0px;
                margin-left: 25px;
            }
            .pnq{
                margin-top: 20px;
                margin-bottom: 20px;
            }

            .timeline-body img{
                max-width: 600px;
            }

            .note-editable img{
                max-height: 100px;
                max-width: 600px;
            }

            #topic{
                width: 100%;
            }

            .txtA{
                margin-top: 20px;
                margin-left: 70px;
                margin-bottom: 20px;
                width: 80%;
                font-size: 20px;
            }

            .inp{
                margin-top: 20px;
                margin-left: 70px;
                width: 80%;
                font-size: 20px;
            }

            .inp input{
                padding: 10px;

            }

            #sbmit{
                background-color: #006dcc;
                color: white;
                padding-top: 5px;
                padding-bottom: 5px;
                padding-left: 25px;
                padding-right: 25px;
            }

            .new{
                display: none;
            }
        </style>
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

        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body class="skin-black"> 
        <c:if test = "${requestScope.discussion != null}">
            <div class = "in4">
                <div class = "img">
                    <img class ="im" src ="img/userIcon.png" />
                </div>
                <c:if test = "${requestScope.msg != null}">
                    <div class = "ms" id = "ms" style = "display: none">
                        ${requestScope.msg}
                    </div>
                    <div id="toast"></div>
                </c:if>
                <div class = "otherIN4">
                    <div class = "topic">
                        <div class = "topicN">
                            ${discussion.getDiscussionTopic()}
                        </div>
                        <div class = "topicO">
                            <div class = "com">
                                <div class = "cImg">
                                    <img class ="oIN4Img" src ="img/commentIcon.png"/>
                                </div>
                                <div class = "cN">
                                    ${discussion.getNoCom()}
                                </div>
                            </div>
                            <div class = "rate">
                                <div class = "rImg">
                                    <img class ="oIN4Img" src ="img/voteIcon.png"/>
                                </div>
                                <div class = "rN">
                                    ${discussion.getNoVote()}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class = "dcontentD">
                        ${discussion.getDiscussionContent()}
                    </div>
                    <div class = "uIN4">
                        <c:if test = "${sessionScope.account.getID() == discussion.getAccount().getID()}">
                            <div class = "uname">
                                <span class = "blk">Author:</span> ${discussion.getAccount().getFullName()} (me)
                            </div>
                        </c:if>
                        <c:if test = "${sessionScope.account.getID() != discussion.getAccount().getID()}">
                            <div class = "uname">
                                <span class = "blk">Author:</span> ${discussion.getAccount().getFullName()}
                            </div>
                        </c:if>
                        <div class = "udate">
                            <span class = "blk">Date:</span> ${discussion.getdS()}
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <div class = "navTtle">
            <div class = "allR">
                ${discussion.getNoCom()} response
            </div>

        </div>
        <c:if test = "${requestScope.list == null}">
            <div class = "ms">
                ${requestScope.ms}
            </div>
        </c:if>
        <div class = "pnq">
            <a id = "a"  onclick = "show()">Post New Comment</a>
        </div>
        <div class = "new" id = "new">
            <div class = "txtA">

                <textarea id ="summernote" onchange ="go1()"  class="form-control hint2mention"  name="editordata" placeholder = "Description">
                
                </textarea>

                <form id = "frm" method="post" action = "dd">
                    <input type ="hidden" id ="cls" name ="cls" value ="${requestScope.cls}"/>
                    <input type ="hidden" id ="id" name ="cds" value ="${requestScope.id}"/>
                    <input type ="submit" id ="sbmit" value ="Post"/>
                    <input type ="hidden" name ="topic" id = "ko"/>
                    <input type ="hidden" name ="ko1" id = "ko1" value =""/>
                    <textarea style ="display:none" id ="txt" name ="summerNoteText"></textarea>
                </form>
                <select id = "slt" hidden>
                    <c:forEach var="i" items="${requestScope.listAcc}" varStatus="loop">
                        <option>${i.getUser()}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <c:if test = "${requestScope.list != null}">
            <div class = "content">
                <c:forEach var="i" items="${requestScope.list}" varStatus="loop">
                    <div class = "in4" >
                        <div class = "img">
                            <img class ="im" src ="img/userIcon.png" />
                        </div>
                        <div class = "otherIN4">
                            <div class = "topic">

                                <div class = "topicO1">
                                    <c:if test = "${sessionScope.account.getID() == i.getAccount().getID()}">
                                        <div class = "uname1">
                                            <div>
                                                ${i.getAccount().getFullName()} (me)
                                            </div>
                                            <div class = "udate1">
                                                ${i.getdS()}
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test = "${sessionScope.account.getID() != i.getAccount().getID()}">
                                        <div class = "uname1">
                                            <div>
                                                ${i.getAccount().getFullName()}
                                            </div>
                                            <div class = "udate1">
                                                ${i.getdS()}
                                            </div>
                                        </div>
                                    </c:if>

                                    <div class = "rate">
                                        <div class = "rImg">
                                            <img class ="oIN4Img1" src ="img/emptyStar.png"/>
                                        </div>
                                        <div class = "rN" id = "vte${i.getCommentID()}">
                                            ${i.getNoVote()}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class = "dcontentD">
                                ${i.getComment()}
                            </div>
                            <div class = "btnV">
                                <div class="dropdown">

                                    <div>
                                        <c:if test = "${i.getsVote() == 5}">
                                            <button class = "dropbtn5" id = "${i.getCommentID()}">
                                                Vote
                                            </button>
                                        </c:if>
                                        <c:if test = "${i.getsVote() == 4}">
                                            <button class = "dropbtn4" id = "${i.getCommentID()}">
                                                Vote
                                            </button>
                                        </c:if>
                                        <c:if test = "${i.getsVote() == 3}">
                                            <button class = "dropbtn3" id = "${i.getCommentID()}">
                                                Vote
                                            </button>
                                        </c:if>
                                        <c:if test = "${i.getsVote() == 2}">

                                            <button class = "dropbtn2" id = "${i.getCommentID()}">
                                                Vote
                                            </button>
                                        </c:if>
                                        <c:if test = "${i.getsVote() == 1}">

                                            <button class = "dropbtn1" id = "${i.getCommentID()}">
                                                Vote
                                            </button>
                                        </c:if>
                                        <c:if test = "${i.getsVote() == 0}">

                                            <button class = "dropbtn" id = "${i.getCommentID()}">
                                                Vote
                                            </button>
                                        </c:if>
                                    </div>
                                    <div class = "dropdown-content">
                                        <c:if test = "${i.getsVote() == 5}">
                                            <div id ="s51" class = "insideDC" onclick = "vote('5', '${i.getCommentID()}', ' ${i.getNoVote()}', '${i.getsVote()}', '${i.getCd().getDiscussionID()}')">
                                                <div  class = "DCimg" id = "img5${i.getCommentID()}">
                                                    <img src ="img/emptyStar.png"/>
                                                </div>
                                                <div class = "DCtext" id = "text5${i.getCommentID()}">
                                                    5 star
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test = "${i.getsVote() != 5}">
                                            <div id ="s5" class = "insideDC" onclick = "vote('5', '${i.getCommentID()}', ' ${i.getNoVote()}', '${i.getsVote()}', '${i.getCd().getDiscussionID()}')">
                                                <div  class = "DCimg" id = "img5${i.getCommentID()}">
                                                    <img src ="img/emptyStar.png"/>
                                                </div>
                                                <div class = "DCtext" id = "text5${i.getCommentID()}">
                                                    5 star
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test = "${i.getsVote() == 4}">
                                            <div id ="s41" class = "insideDC" onclick = "vote('4', '${i.getCommentID()}', ' ${i.getNoVote()}', '${i.getsVote()}', '${i.getCd().getDiscussionID()}')">
                                                <div class = "DCimg" id = "img4${i.getCommentID()}">
                                                    <img src ="img/emptyStar.png"/>
                                                </div>
                                                <div class = "DCtext" id = "text4${i.getCommentID()}">
                                                    4 star
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test = "${i.getsVote() != 4}">
                                            <div id ="s4" class = "insideDC" onclick = "vote('4', '${i.getCommentID()}', ' ${i.getNoVote()}', '${i.getsVote()}', '${i.getCd().getDiscussionID()}')">
                                                <div class = "DCimg" id = "img4${i.getCommentID()}">
                                                    <img src ="img/emptyStar.png"/>
                                                </div>
                                                <div class = "DCtext" id = "text4${i.getCommentID()}">
                                                    4 star
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test = "${i.getsVote() == 3}">
                                            <div id ="s31" class = "insideDC" onclick = "vote('3', '${i.getCommentID()}', ' ${i.getNoVote()}', '${i.getsVote()}', '${i.getCd().getDiscussionID()}')">
                                                <div class = "DCimg" id = "img3${i.getCommentID()}">
                                                    <img src ="img/emptyStar.png"/>
                                                </div>
                                                <div class = "DCtext" id = "text3${i.getCommentID()}">
                                                    3 star
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test = "${i.getsVote() != 3}">
                                            <div id ="s3" class = "insideDC" onclick = "vote('3', '${i.getCommentID()}', ' ${i.getNoVote()}', '${i.getsVote()}', '${i.getCd().getDiscussionID()}')">
                                                <div class = "DCimg" id = "img3${i.getCommentID()}">
                                                    <img src ="img/emptyStar.png"/>
                                                </div>
                                                <div class = "DCtext" id = "text3${i.getCommentID()}">
                                                    3 star
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test = "${i.getsVote() == 2}">
                                            <div id ="s21" class = "insideDC" onclick = "vote('2', '${i.getCommentID()}', ' ${i.getNoVote()}', '${i.getsVote()}', '${i.getCd().getDiscussionID()}')">
                                                <div class = "DCimg" id = "img2${i.getCommentID()}">
                                                    <img src ="img/emptyStar.png"/>
                                                </div>
                                                <div class = "DCtext" id = "text2${i.getCommentID()}">
                                                    2 star
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test = "${i.getsVote() != 2}">
                                            <div id ="s2" class = "insideDC" onclick = "vote('2', '${i.getCommentID()}', ' ${i.getNoVote()}', '${i.getsVote()}', '${i.getCd().getDiscussionID()}')">
                                                <div class = "DCimg" id = "img2${i.getCommentID()}">
                                                    <img src ="img/emptyStar.png"/>
                                                </div>
                                                <div class = "DCtext" id = "text2${i.getCommentID()}">
                                                    2 star
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test = "${i.getsVote() == 1}">
                                            <div id ="s11" class = "insideDC" onclick = "vote('1', '${i.getCommentID()}', ' ${i.getNoVote()}', '${i.getsVote()}', '${i.getCd().getDiscussionID()}')">
                                                <div class = "DCimg" id = "img1${i.getCommentID()}">
                                                    <img src ="img/emptyStar.png"/>
                                                </div>
                                                <div class = "DCtext" id = "text1${i.getCommentID()}">
                                                    1 star
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test = "${i.getsVote() != 1}">
                                            <div id ="s1" class = "insideDC" onclick = "vote('1', '${i.getCommentID()}', ' ${i.getNoVote()}', '${i.getsVote()}', '${i.getCd().getDiscussionID()}')">
                                                <div class = "DCimg" id = "img1${i.getCommentID()}">
                                                    <img src ="img/emptyStar.png"/>
                                                </div>
                                                <div class = "DCtext" id = "text1${i.getCommentID()}">
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
                <div id="myPopup" class="popup">
                    <div class="popup-content">

                        <p>Để tránh spam, bạn có thể thay đổi vote của mình sau mỗi 2 giây</p>
                        <div class = "popupBtn">

                            <button id="closePopup">
                                OK
                            </button>
                        </div>
                    </div>
                </div>
                <div class="clearfix">
                    <div class="hint-text">Showing <b>${requestScope.size}</b> out of <b>${requestScope.totalEntity}</b> entries</div>
                    <ul class="pagination">

                        <li class="page-item"><a href ="dd?page=${(page - 1) < 1?1:(page-1)}&id=${requestScope.id}&class=${requestScope.cls}">Previous</a></li>
                            <c:forEach begin = "${1}" end = "${totalPage}" var = "i">
                            <li class="page-item"><a class ="${i == page ? "active":"noActive"}" href ="dd?page=${i}&id=${requestScope.id}&class=${requestScope.cls}">${i}</a></li>
                            </c:forEach>
                        <li class="page-item"><a href ="dd?page=${(page + 1) > totalPage?1:(page+1)}&id=${requestScope.id}&class=${requestScope.cls}">Next</a></li>
                    </ul>
                </div>
            </div>
        </c:if>

        <div class="footer-main">
            Copyright &copy Director, 2014
        </div>        <!-- jQuery 2.0.2 -->
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

        <!-- Director for demo purposes -->
        <script>
                                                if (document.getElementById("ms") !== null) {
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
        <script>
//                    var textArea = document.getElementById("summernote");
//                    $('#summernote').addEventListener('input', () => {
//                        console.log(document.getElementById("summernote")).value;
//                        var textareaPosition = textArea.getBoundingClientRect();
//                        var cursorPosition = textArea.selectionStart;
//                        var lineHeight = textArea.style.lineHeight;
//                        console.log(textareaPosition.top);
//                        var linePosition = Number(textareaPosition.top) + Number(cursorPosition) * Number(lineHeight);
//                        if (String(document.getElementById("textarea").value).includes('@')) {
//                            console.log(2);
//
//                            document.getElementById("u").style.display = 'block';
//                            document.getElementById("u").style.position = 'absolute';
//                            document.getElementById("u").style.top = -(Number(cursorPosition) + Number(textareaPosition.top)) + 'px';
//                            console.log(document.getElementById("u").style.top);
//                        }
//
//                    })
        </script>
        <script>
            const selectElement = document.getElementById("slt");
            var arr1 = Array.from(selectElement.options);
            var arr = [];
            var arr2 = [];
            for (var i = 0; i < arr1.length; i++) {
                arr.push(arr1[i].innerHTML);
            }

            $(document).ready(function () {
                $('#summernote').summernote({
                    height: 450,
                    fontsize: 20,
                    //placeholder: 'Description',
                    hint: {
                        mentions: arr,
                        match: /\B@(\w*)$/,
                        search: function (keyword, callback) {
                            callback($.grep(this.mentions, function (item) {
                                var cnt = 0;
                                for (var i = 0; i < arr.length; i++) {
                                    var a = (String)(document.getElementById("summernote").value);
                                    if (a.includes("@" + arr[i])) {
                                        console.log("32");
                                        arr.splice(i, 1);
                                    }
                                }
                                for (var i = 0; i < arr2.length; i++) {
                                    var a = (String)(document.getElementById("summernote").value);
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

                            const el = `<a id = "usertag" href = "` + "dd?id=" + document.getElementById("id").value + "&class=" + document.getElementById("cls").value + `" target="_blank">` + "@" + item + `</a>`;
                            return document.execCommand("insertHTML", false, el);
                        }
                    },

                    callbacks: {
                        onImageUpload: function (files) {
                            for (var i = 0; i < files.length; i++) {
                                sendFile(files[i]);
                            }
                        }
                    }
                });
                document.getElementById("txt").value = document.getElementById("summernote").value
                $('#frm').submit(function () {
                    document.getElementById("txt").value = document.getElementById("summernote").value;
                    // $('textarea[name=summerNoteText]').val($('#summernote')).summernote('code');
                });
            });



            function sendFile(file) {
                console.log("2");
                var data = new FormData();
                data.append("file", file);
                $.ajax({
                    data: data,
                    encType: 'multipart/form-data',
                    type: "POST",
                    url: "http://localhost:9999/prj/dd",
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
                document.getElementById("txt").value = document.getElementById("summernote");
                console.log(document.getElementById("summernote"));
            }



        </script>
        <script>
            function show() {
                document.getElementById("a").style.display = 'none';
                document.getElementById("new").style.display = 'block';
            }
        </script>
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
            start();
            end();
            function sbmit() {
                document.getElementById("form").submit();
            }

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

            function normal1(id) {
                document.getElementById("img1" + id).classList.remove("DCimg1");
                document.getElementById("img1" + id).classList.add("DCimg");
                document.getElementById("text1" + id).classList.remove("DCtext1");
                document.getElementById("text1" + id).classList.add("DCtext");
            }

            function vote(num, id, vote, sVote, cd) {
                var second = end();
                start();
                if (second >= 2) {

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
                                document.getElementById("s51").setAttribute("id", "s5");
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
                                document.getElementById("s41").setAttribute("id", "s4");
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
                                document.getElementById("s31").setAttribute("id", "s3");
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
                                document.getElementById("s21").setAttribute("id", "s2");
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
                                document.getElementById("s11").setAttribute("id", "s1");
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
                } else {
                    document.getElementById("myPopup").classList.add("show");
                    document.getElementById("closePopup").addEventListener("click", function () {
                        document.getElementById("myPopup").classList.remove("show");
                    });
                    window.addEventListener("click", function (event) {
                        if (event.target == document.getElementById("myPopup")) {
                            document.getElementById("myPopup").classList.remove("show");
                        }
                    });
                }
            }

        </script>
    </body>
</html>
