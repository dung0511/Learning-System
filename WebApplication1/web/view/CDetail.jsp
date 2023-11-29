<%-- 
    Document   : NewClass
    Created on : Nov 6, 2023, 6:46:15 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title><link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <!-- include summernote css/js -->
        <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
        <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
        <style>
            body{
                overflow-y: auto;
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

            .btnS{
                background-color: #006dcc;
                color: white;
                padding-top: 5px;
                padding-bottom: 5px;
                padding-left: 25px;
                padding-right: 25px;
                font-weight: bold;
            }

            .btnS a{
                text-decoration: none;
                color: white;
            }
        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
        <style>
            .ttle{
                font-size: 25px;
                font-weight: bold;
                margin-left: 10px;
                margin-top: 10px;
            }

            .in4{
                margin-top: 20px;
                font-size: 20px;
                display: flex;
                flex-direction: row;
                flex-wrap: wrap;
                width: 100%;
            }

            .timeline-body img{
                max-width: 600px;
            }

            .note-editable img{
                max-height: 100px;
                max-width: 600px;
            }

            select{
                width: 350px;
                padding: 5px;
            }

            .inputText {
                width: 350px;
                padding: 5px;
            }

            .inputFile {
                width: 350px;
            }

            .searchAl{
                width: 40%;
                margin-left: 30px;
                margin-right: 0px;
                margin-bottom: 10px;
                font-weight: bold;
                margin-top: 20px;
            }

            .searchAll{
                width: 40%;
                margin-left: 30px;
                margin-right: 0px;
                margin-bottom: 10px;
                font-weight: bold;
                margin-top: 20px;
            }

            .searchA{
                width: 40%;
                margin-left: 0px;
                margin-bottom: 10px;
                font-weight: bold;
                margin-top: 20px;
            }

            .insideF{
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
            }

            .r{
                margin-left: 18px;
                margin-right: 18px;
            }

            .r1{
                display: inline-block;
                width: 130px;
            }

            .spn{
                display: inline-block;
                width: 150px;
            }

            .searchAl span{
                width: 150px;
            }

            .searchA span{
                width: 150px;
            }

            .searchAB{
                width: 80%;
                margin-left: 30px;
                margin-right: 10px;
                margin-bottom: 10px;
                font-weight: bold;
                margin-top: 20px;
            }

            .btnS{
                margin-left: 30px;
                margin-bottom: 50px;
                margin-right: 20px;
                margin-top: 20px;
            }

            .ttle{
                font-size: 25px;
                font-weight: bold;
                margin-bottom: 20px;
                color: #006dcc;
            }

            .container-xl{
                font-size: 20px;
            }

            .container-xl{
                color: black;
            }

            .ms{
                font-size: 20px;
                color: red;
                margin-left: 28px;
            }
            
            #txt{
                width: 120%;
                margin-right: 18px;
                height: 300px;
            }
        </style>
    </head>
    <body class = "skin-black">
        <%@include file = "menufl.jsp" %>
        <%@include file = "smfl.jsp" %>
        <aside class="right-side">
            <div class="container-xl">
                <div class = "ttle">
                    New Class
                </div>
                <div class = "ms">
                    ${requestScope.ms}
                </div>
                <form action = "clist?act=upd" id ="frm" method = "post" >
                    <div class = "insideF">
                        <div class = "searchAl">
                            <span class = "spn">Name: <span style = "color:red">*</span></span>
                            <input class ="inputText" required="" type ="text" name ="name" value = "${requestScope.c.getClassName()}"/>
                        </div>

                        <div class = "searchA">
                            <span class = "spn">Subject: </span>
                            <select name ="subject" >
                                <c:forEach items = "${requestScope.listS}" var = "i">
                                    <option ${requestScope.c.getS().getSubjectID() == i.getSubjectID()?"selected":""} value = ${i.getSubjectID()}>${i.getSubjectCode()}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class = "searchAl">
                            <span class = "spn">Display Order:  <span style = "color:red">*</span></span>
                            <input class ="inputText" type ="number" required="" min ="0" max ="100" name ="order" value = "${requestScope.c.getDisplayOrder()}"/>
                        </div>
                        <div class = "searchA">
                            <span class = "spn">Semester </span>
                            <select name ="semester" >
                                <c:forEach items = "${requestScope.listSS}" var = "i">
                                    <option ${requestScope.c.getSemester().getSetting_id() == i.getSetting_id()?"selected":""}  value = ${i.getSetting_id()}>${i.getSetting_name()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class = "searchAl">
                            <span class = "spn">Start Date: <span style = "color:red">*</span></span>
                            <input class ="inputText" required="" type ="date" min ="0" max ="100" name ="sd" value = "${requestScope.c.getStartDate()}"/>
                        </div><!-- comment -->
                        <div class = "searchA">
                            <span class = "spn">End Date: <span style = "color:red">*</span></span>
                            <input class ="inputText" required="" type ="date"  min ="0" max ="100" name ="ed" value = "${requestScope.c.getEndDate()}"/>
                        </div>
                        <div class = "searchAl">
                            <span class = "spn">Trainer: </span>
                            <select name ="trainer" >
                                <c:forEach items = "${requestScope.listT}" var = "i">
                                    <option ${requestScope.c.getTrainer().getID() == i.getID()?"selected":""}  value = ${i.getID()}>${i.getFullName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div  class = "searchA">
                            
                            <span class = "r1">Status</span>
                            <input class ="rdio" ${(requestScope.c.getActivate() == 0) ? "checked": ""} type ="radio" checked name ="status"  value ="0"/><span class = "r">Inactive</span>
                            <input class ="rdio" ${(requestScope.c.getActivate() == 1) ? "checked": ""} type ="radio" name ="status" value ="1"/><span class = "r">Active</span>
                        </div>
                        <div class = "searchAB">
                            <span class = "spn">Description  <span style = "color:red"></span></span>
                            <textarea class ="inputText" id ="txt" name ="description">${requestScope.c.getDescription()}</textarea>
                        </div>


                    </div>
                    <input type ="hidden" name ="id" value ="${requestScope.c.getClassID()}"/>
                    <input type ="hidden" name ="page" value ="${requestScope.page}"/>
                    <input type ="hidden" name ="upd" id ="add" value ="1"/>
                    <button class ="btnS" type ="button" value ="Back"><a href = "clist?act=list">Back</a></button>
                    <input class ="btnS" type ="submit" id ="sbmit" value ="Update"/>

                </form>
            </div>
        </aside>
    </body>
</html>
