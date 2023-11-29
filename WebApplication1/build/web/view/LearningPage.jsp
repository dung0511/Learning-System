<%-- 
    Document   : test32
    Created on : Sep 25, 2023, 12:19:17 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <style>
            .container-xl{
                width: 100%;
            }
            html, body {
                position: relative;
            }
            .side-bar{
                position: absolute;
                width: 100%;
                background: #f00;
                top: 150px;
                bottom: 150px;
                height: 100%;
            }

            hr {
                height: 1px;
                background-color: #e5e5e5;
                border: none;
            }

            .chapter{
                display: flex;
                flex-direction: column;
                padding-left: 20px;
                padding-right: 20px;
                border-top: 1px solid black;
                border-bottom: 1px solid black;
                height: 80px;
                cursor: pointer;
            }

            .nav-left {
                flex:1;
                overflow: auto;
                scroll-behavior: smooth;
            }
            .nav-left {
                background: white;
                position: absolute;
                right: 0;
                top: 50px;
                bottom: 0;
                width: 25%;
                height: 100%;
                margin-bottom: 10px;



            }

            .lesson{
                margin-bottom: 10px;
            }





            .side-bar {
                position: relative;
                height: 100%;
                top: 20px;
                right: 0;
            }

            .nav-left {
                position: fixed;
                top: 12px;
                right: 0;
                bottom: 0;
                overflow-y: scroll;
                padding-top: 50px;
                padding-bottom: 40px;
            }

            .cName{

                color: black;
                padding-top: 5%;
                font-weight: bold;
                font-size: 15px;
                padding-bottom: 2%;
                margin-right: 5%;
                width: 95%;
            }

            .cNameT{

                color: black;
                padding-top: 5%;
                font-weight: bold;
                font-size: 18px;
                padding-bottom: 2%;
                margin-right: 5%;
                width: 95%;
            }

            .numLesson {
                padding-bottom: 5%;
            }

            .ttle{
                font-size: 20px;
                color: black;
                font-weight: bold;
                padding-bottom: 12px;
                border-bottom: 1px solid black;
                padding-left: 20px;
                padding-right: 20px;
            }

            .arw{
                width: 15px;
                height: 15px;
            }

            .chapter:hover{
                background-color: #d6d6d4;
            }

            .lIcon{
                width: 30px;
                margin-right: 20px;
            }


            .arw2{
                width: 100%;
                height: 30px;

            }

            .arw3{
                width: 25px;
                height: 25px;
                margin-right: 20px;
            }

            .in4{
                display: flex;
                flex-direction: row;
                align-items: center;
            }

            .lesson{
                display: flex;
                flex-direction: row;
                align-items: center;
                padding-left: 20px;
                font-size: 15px;
                padding-top: 5px;
                padding-bottom: 5px;
                cursor: pointer;
            }

            .lessonIn4{
                display: flex;
                flex-direction: column;
                padding-left: 20px;
                font-size: 18px;
                padding-top: 5px;
                padding-bottom: 5px;
                cursor: pointer;
            }

            .quiz{
                display: flex;
                flex-direction: column;


                cursor: pointer;
            }


            .iQuiz {
                display: flex;
                flex-direction: row;
                align-items: center;
                padding-left: 80px;
                font-size: 15px;
                padding-top: 8px;
                margin-bottom: 10px;
            }

            .lessonP{
                padding-top: 10px;
                padding-bottom: 10px;
                display: none;
            }
            .lessonPActive{
                padding-top: 10px;
                padding-bottom: 10px;
            }
            .lName a{
                color: black;
            }

            .msg{
                color: black;
                font-size: 15px;
                padding-left: 25px;
            }

            .material{
                margin-top: 20px;
                margin-right: 20px;
                margin-left: -62px;
                width: 79%;
            }

            .video{
                margin: 0px;

            }

            .video #iframe{
                width: 100%;
                height: 500px;

            }
            .lessonActive{
                display: flex;
                flex-direction: row;
                align-items: center;
                padding-left: 20px;
                font-size: 15px;
                padding-top: 5px;
                padding-bottom: 5px;
                cursor: pointer;
                color: #00c0ef;
            }

            .lessonActive a{
                color: #00c0ef;
            }

            .lttle{
                font-size: 15px;
                color: black;
                font-weight: bold;
                padding-bottom: 12px;
                padding-left: 25px;
                padding-right: 25px;
            }

            .qttle{
                font-size: 15px;
                color: black;
                font-weight: bold;
                padding-bottom: 12px;
                padding-left: 80px;
                padding-right: 25px;
            }

            .lName{
                width: 240px;
                margin-right: 15px;
            }

            .quiz{
                display: none;
            }

            .quizActive{
                display: active;
            }

            .vidTtle{
                margin-top: 15px;
                font-size: 23px;
                font-weight: bold;
                margin-bottom: 10px;
            }

            .visDes{
                margin-top: 10px;
                margin-bottom: 20px;
                font-size: 15px;
                height: 80px;
                width: 90%;
                height: 70px;
                overflow: hidden;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-line-clamp: 4;
                -webkit-box-orient: vertical;
                line-height: 1.6;
            }

            #des{
                margin-top: 10px;
                margin-bottom: 20px;
                font-size: 15px;


                line-height: 1.6;
            }

            .vidDes{
                margin-top: 10px;
                margin-bottom: 20px;
                font-size: 15px;
                height: 140px;
                width: 90%;
                line-height: 1.6;
            }

            .visDes:hover{
                background-color: #d6d6d4;
                cursor: pointer;
            }

            .tab{
                display: flex;
                flex-direction: row;
                margin-top: 15px;
                font-size: 18px;
                border-bottom: 1px solid grey;

            }

            .tab1{
                width: 50%;
                margin-right: 20px;
                border-bottom: 2px solid black;
                margin-right: 20px;
                font-weight: bold;
            }

            .tab1:hover{
                cursor: pointer;
            }

            .tab2:hover{
                cursor: pointer;
            }

            .tab2{
                width: 50%;
                text-align: right;
            }

            #omtr{
                display: none;
            }

            #irm{
                border: 1px solid black;
            }

            .trainerName{
                font-weight: bold;
                font-size: 18px;
            }
            .control{
                width: 30px;
                margin-right: 0px;

            }
            #iconL{
                position: absolute;
                right: 0;
                top: 50%;
                bottom: 0;
                width: 10%;
                height: 100%;
                margin-bottom: 10px;
                cursor: pointer;
                display: none;
            }
            #iconL img{
                width: 100%;
            }
            .iconR{
                width: 20px;
                margin-right: 0px;
            }
            .ttle:hover{
                cursor: pointer;
            }

            .qname{
                font-size: 30px;
                font-weight: bold;
            }

            .vidDes{
                margin-bottom: 20px;
            }

            .duration{
                margin-top: 40px;
                display: flex;
                flex-direction: row;
                font-size: 20px;

                margin-bottom: 10px;
                align-items: center;
            }

            .i{
                width: 30px;
                margin-right: 15px;
            }

            .text{
                color: red;
            }

            .btn{
                margin-top: 10px;
                border: 1px solid black;
                border-radius: 12px;
                padding-top: 10px;
                padding-bottom: 10px;
                padding-left: 15px;
                padding-right: 15px;
                width: 120px;
                background-color: red;
                color: white;
            }

            .attempt{
                font-size: 20px;
                margin-bottom: 10px;
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
            p{
                text-align: center;
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

            .rw{
                border-radius: 12px;
                background-color: #0075b0;
                color:white;
                margin-left: 40px;
            }

            #myButton{
                border-radius: 12px;
                background-color: red;
                color: white;
            }

            .ac{
                width: 20px;
            }

            .container-xl{
                width: 100%;
                margin-left: 0px;
            }

            .right-side{
                margin-left: 0px;
            }
            .blk{
                display: inline-block;
                width: 135px;
                font-weight: bold;
                color: #566787;
            }


            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 15px;
                background: #299be4;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn {
                color: #566787;
                float: right;
                font-size: 13px;
                background: #fff;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn:hover, .table-title .btn:focus {
                color: #566787;
                background: #f2f2f2;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.settings {
                color: #2196F3;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .med{
                width: 30%;
            }

          

            .lessonIn4{
                width: 90%;
            }
            
            table{
                margin-left: 10px;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <%@include file = "menu.jsp" %>
        <%@include file = "navbarTrainee2.jsp" %>
        <!--
            Write code
        
        -->
        <aside class="right-side">
            <div class="container   -xl">
                <div class = "material" id = "material">
                    <div class = "chapter" onclick = "showP('class')">
                        <div class = "in4">
                            <div class = "cNameT">Class</div>
                            <img id ="clsImg" src ="img/downArrow.png" class = "arw"/>

                        </div>
                    </div>
                    <div id ="lcl" class = "lessonP">
                        <div class = "lessonIn4">
                            <div>
                                <span class = "blk">Name: </span></span> ${requestScope.cin4.getClassName()} 
                            </div>
                            <div>
                                <span class = "blk">Start Date: </span></span> ${requestScope.cin4.getStartS()} 
                            </div>
                            <div>
                                <span class = "blk">End Date: </span></span> ${requestScope.cin4.getEndS()} 
                            </div>
                            <div>
                                <span class = "blk">Description: </span>${requestScope.cin4.getDescription()}
                            </div>
                        </div>
                    </div>
                    <div class = "chapter" onclick = "showP('subject')">
                        <div class = "in4">                
                            <div class = "cNameT">Subject</div>
                            <img id ="sjImg" src ="img/downArrow.png" class = "arw"/>
                        </div>
                    </div>
                    <div id ="scl" class = "lessonP">
                        <div class = "lessonIn4">
                            <div>
                                <span class = "blk">Code: </span></span> ${requestScope.cin4.getS().getSubjectCode()} 
                            </div>
                            <div>
                                <span class = "blk">Name: </span></span> ${requestScope.cin4.getS().getSubjectName()} 
                            </div>

                            <div>
                                <span class = "blk">Description: </span>${requestScope.cin4.getS().getSubjectDescription()}
                            </div>
                        </div>
                    </div>
                    <div class = "chapter" onclick = "showP('trainer')">
                        <div class = "in4">
                            <div class = "cNameT">Trainer</div>
                            <img id ="trrImg" src ="img/downArrow.png" class = "arw"/>
                        </div>
                    </div>
                    <div id ="trr" class = "lessonP">
                        <div class = "lessonIn4">
                            <div>
                                <span class = "blk">Username: </span></span> ${requestScope.cin4.getTrainer().getUser()} 
                            </div>
                            <div>
                                <span class = "blk">Name: </span></span> ${requestScope.cin4.getTrainer().getFullName()} 
                            </div>
                            <div>
                                <span class = "blk">Email: </span></span> ${requestScope.cin4.getTrainer().getEmail()} 
                            </div>
                        </div>
                    </div>
                    <div class = "chapter" onclick = "showP('trainee')">
                        <div class = "in4">
                            <div class = "cNameT">Trainee</div>
                            <img id ="trainee" src ="img/downArrow.png" class = "arw"/>
                        </div>
                    </div>
                    <div id ="trn" class = "lessonP">
                        <div class = "lessonIn4">
                            <div>
                                <span class = "blk">Number trainee: </span>${requestScope.listAcc.size()}
                            </div>
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th class = "first" >
                                            Username
                                        </th>
                                        <th class = "med">
                                            Name
                                        </th>
                                        <th class = "last" >
                                            Email
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach var="i" items="${requestScope.listAcc}" varStatus="loop">
                                        <tr>
                                            <td>${i.getUser()}</td>
                                            <td class = "med">${i.getFullName()}</td>
                                            <td>${i.getEmail()}</td>

                                        </tr>
                                    </c:forEach>    
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class=”side-bar” id = "side-bar">
                    <div class="nav-left" id = "nav-left">
                        <input type ="hidden" value ="${requestScope.chapter}" id ="scroll"/>
                        <div class = "ttle">Subject content</div>
                        <c:forEach var="i" items="${requestScope.list}" varStatus="loop">

                            <div class = "chapter" id = "chapter${i.getChapter().getChapterID()}" onclick ="show('${i.getChapter().getChapterID()}')">
                                <div class = "in4">
                                    <div class = "cName">Part: ${i.getChapter().getChapterID()} - ${i.getChapter().getChapterName()}</div>
                                    <img id ="img${i.getChapter().getChapterID()}" src ="img/downArrow.png" class = "arw"/>
                                </div>
                                <c:if test = "${i.getChapter().getNumAchieve() != i.getList().size()}">
                                    <div  class ="numLesson">${i.getChapter().getNumAchieve()} / ${i.getList().size()}</div>
                                </c:if>
                                <c:if test = "${i.getChapter().getNumAchieve() == i.getList().size()}">

                                    <div  class ="numLesson">
                                        <img class ="ac" src ="img/activateIcon.png"/>
                                        &nbsp;
                                        ${i.getChapter().getNumAchieve()} / ${i.getList().size()}
                                    </div>
                                </c:if>
                            </div>
                            <div class = "lessonP" id = "${i.getChapter().getChapterID()}">
                                <c:forEach var="j" items="${i.getList()}" varStatus="loop">
                                    <c:if test = "${j.getLessonType().getSetting_name() == 'Video'}">

                                        <div class = "lesson">
                                            <c:if test = "${j.isAchieve() == 'true'}">
                                                <div class = "lIcon">
                                                    <img src = "img/activateIcon.png" class = "arw2"/>
                                                </div>
                                            </c:if>
                                            <c:if test = "${j.isAchieve() == 'false'}">
                                                <div class = "lIcon">
                                                    <img src = "img/videoIcon.png" class = "arw2"/>
                                                </div>
                                            </c:if>
                                            <div class = "lName">
                                                <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()}</a>
                                            </div>
                                        </div>          
                                    </c:if>
                                    <c:if test = "${j.getLessonType().getSetting_name() == 'Quiz'}">
                                        <div class = "lesson">
                                            <c:if test = "${j.isAchieve() == 'true'}">
                                                <div class = "lIcon">
                                                    <img src = "img/activateIcon.png" class = "arw2"/>
                                                </div>
                                            </c:if>
                                            <c:if test = "${j.isAchieve() == 'false'}">
                                                <div class = "lIcon">
                                                    <img src = "img/quizIcon.png" class = "arw2"/>
                                                </div>
                                            </c:if>
                                            <div class = "lName">
                                                <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()} </a>
                                            </div>
                                        </div>          
                                    </c:if>
                                    <c:if test = "${j.getLessonType().getSetting_name() == 'Assignment'}">
                                        <div class = "lesson">
                                            <c:if test = "${j.isAchieve() == 'true'}">
                                                <div class = "lIcon">
                                                    <img src = "img/activateIcon.png" class = "arw2"/>
                                                </div>
                                            </c:if>
                                            <c:if test = "${j.isAchieve() == 'false'}">
                                                <div class = "lIcon">
                                                    <img src = "img/asmIcon.png" class = "arw2"/>
                                                </div>
                                            </c:if>
                                            <div class = "lName">
                                                <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()} </a>
                                            </div>
                                        </div>          
                                    </c:if>
                                </c:forEach>

                            </div>






                        </c:forEach>
                    </div>
                </div>

            </div>
            <hr>
            </div>
        </aside>
        <%@include file = "footer.jsp" %>


        <script type="text/javascript">
            console.log(document.getElementById("state").value);
            var txt = document.getElementById("desc").value;
            var txtT = document.getElementById("descT").value;
            function show(id) {
                var img = document.getElementById("img" + id);
                if (img.src === "http://localhost:9999/prj/img/upArrow.png") {
                    document.getElementById(id).style.display = 'none';
                    img.src = "http://localhost:9999/prj/img/downArrow.png";
                } else if (img.src === "http://localhost:9999/prj/img/downArrow.png") {
                    document.getElementById(id).style.display = 'block';
                    img.src = "http://localhost:9999/prj/img/upArrow.png";
                }

            }

            function showP(id) {
                if (String(id) === 'class') {
                    var img = document.getElementById("clsImg");
                    if (img.src === "http://localhost:9999/prj/img/upArrow.png") {
                        document.getElementById("lcl").style.display = 'none';
                        img.src = "http://localhost:9999/prj/img/downArrow.png";
                    } else if (img.src === "http://localhost:9999/prj/img/downArrow.png") {
                        document.getElementById("lcl").style.display = 'block';
                        img.src = "http://localhost:9999/prj/img/upArrow.png";
                    }
                } else if (String(id) === 'subject') {
                    var img = document.getElementById("sjImg");
                    if (img.src === "http://localhost:9999/prj/img/upArrow.png") {
                        document.getElementById("scl").style.display = 'none';
                        img.src = "http://localhost:9999/prj/img/downArrow.png";
                    } else if (img.src === "http://localhost:9999/prj/img/downArrow.png") {
                        document.getElementById("scl").style.display = 'block';
                        img.src = "http://localhost:9999/prj/img/upArrow.png";
                    }
                } else if (String(id) === 'trainer') {
                    var img = document.getElementById("trrImg");
                    if (img.src === "http://localhost:9999/prj/img/upArrow.png") {
                        document.getElementById("trr").style.display = 'none';
                        img.src = "http://localhost:9999/prj/img/downArrow.png";
                    } else if (img.src === "http://localhost:9999/prj/img/downArrow.png") {
                        document.getElementById("trr").style.display = 'block';
                        img.src = "http://localhost:9999/prj/img/upArrow.png";
                    }
                } else if (String(id) === 'trainee') {
                    var img = document.getElementById("trainee");
                    if (img.src === "http://localhost:9999/prj/img/upArrow.png") {
                        document.getElementById("trn").style.display = 'none';
                        img.src = "http://localhost:9999/prj/img/downArrow.png";
                    } else if (img.src === "http://localhost:9999/prj/img/downArrow.png") {
                        document.getElementById("trn").style.display = 'block';
                        img.src = "http://localhost:9999/prj/img/upArrow.png";
                    }
                }
            }

            function showL(id) {
                var img = document.getElementById("imgL" + id);
                if (img.src === "http://localhost:9999/prj/img/upArrow.png") {
                    document.getElementById("lesson" + id).style.display = 'none';
                    img.src = "http://localhost:9999/prj/img/downArrow.png";
                } else if (img.src === "http://localhost:9999/prj/img/downArrow.png") {
                    document.getElementById("lesson" + id).style.display = 'block';
                    img.src = "http://localhost:9999/prj/img/upArrow.png";
                }
            }

            var a = document.getElementById("scroll").value;
            document.getElementById("chapter" + a).scrollIntoView({behavior: "smooth", inline: "nearest", alignToTop: true});
            //            function truncate(elt, content, height) {
            //
            //                function getHeight(elt) {
            //                    return elt.getBoundingClientRect().height;
            //                }
            //                function shorten(str) {
            //                    return str.slice(0, -1);
            //                }
            //
            //                elt.style.height = "auto";
            //                elt.textContent = content;
            //
            //                // Shorten the string until it fits vertically.
            //                while (getHeight(elt) > height && content) {
            //                    elt.textContent = (content = shorten(content)) + '...thêm';
            //                }
            //
            //            }
            function showAll() {
                document.getElementById("des").classList.remove("visDes");
                document.getElementById("des").classList.add("vidDes");
                document.getElementById("des").textContent = txt;
                document.getElementById("material").style.width = "79%";
                document.getElementById("material").style.marginRight = "20px";
                document.getElementById("material").style.marginLeft = "-54px";
                document.getElementById("side-bar").style.width = "100%";
                document.getElementById("nav-left").style.width = "24%";
                document.getElementById("irm").style.border = "1px solid black";
                //                console.log(document.getElementById("des").textContent);

                // Shorten the string until it fits vertically.
            }

            function showAllT() {
                document.getElementById("desT").classList.remove("visDes");
                document.getElementById("desT").classList.add("vidDes");
                document.getElementById("des").textContent = txt;
                document.getElementById("material").style.width = "79%";
                document.getElementById("material").style.marginRight = "20px";
                document.getElementById("material").style.marginLeft = "-54px";
                document.getElementById("side-bar").style.width = "100%";
                document.getElementById("nav-left").style.width = "24%";
                document.getElementById("irm").style.border = "1px solid black";
                //                console.log(document.getElementById("des").textContent);

                // Shorten the string until it fits vertically.
            }

            function tab(id) {
                console.log(id);
                if (String(id) === 'tab1') {
                    document.getElementById("videoIN4").style.display = 'block';
                    document.getElementById("omtr").style.display = 'none';
                    document.getElementById("tab1").style.fontWeight = "bold";
                    document.getElementById("tab2").style.fontWeight = "normal";
                    document.getElementById("tab1").style.borderBottom = "2px solid black";
                    document.getElementById("tab2").style.border = "none";
                    document.getElementById("material").style.width = "79%";
                    document.getElementById("material").style.marginRight = "20px";
                    document.getElementById("material").style.marginLeft = "-54px";
                    document.getElementById("side-bar").style.width = "100%";
                    document.getElementById("nav-left").style.width = "24%";
                    document.getElementById("irm").style.border = "1px solid black";
                } else if (String(id) === 'tab2') {
                    document.getElementById("videoIN4").style.display = 'none';
                    document.getElementById("omtr").style.display = 'block';
                    document.getElementById("tab2").style.fontWeight = "bold";
                    document.getElementById("tab1").style.fontWeight = "normal";
                    document.getElementById("tab2").style.borderBottom = "2px solid black";
                    document.getElementById("tab1").style.border = "none";
                    document.getElementById("material").style.width = "79%";
                    document.getElementById("material").style.marginRight = "20px";
                    document.getElementById("material").style.marginLeft = "-54px";
                    document.getElementById("side-bar").style.width = "100%";
                    document.getElementById("nav-left").style.width = "24%";
                    document.getElementById("irm").style.border = "1px solid black";
                }
            }




            //            var a = 0;

            //            const y = document.getElementById("chapter" + a).getBoundingClientRect().top + window.scrollY;
            //            console.log(y);
            //            
            //            window.scroll({
            //                top: y,
            //                behavior: 'smooth'
            //            });
            //            window.scrollTo(0, y);
        </script>
    </body>
</html>
