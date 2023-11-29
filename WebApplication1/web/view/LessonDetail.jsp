<%-- 
    Document   : SummerNote
    Created on : Oct 30, 2023, 7:45:09 AM
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
                width: 130px;
            }

            .searchAl span{
                width: 130px;
            }

            .searchA span{
                width: 130px;
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
            }

        </style>

        <!--Toast up-->

    </head>
    <body class = "skin-black">
        <%@include file = "menufl.jsp" %>
        <%@include file = "smfl.jsp" %>
        <aside class="right-side">
            <div class="container-xl">
                <c:if test = "${requestScope.upd != null}">
                    <div class = "ttle">Update Lesson</div>

                    <div class = "in4">
                        <form action = "llist?act=upd" id ="frm" method = "post" enctype="multipart/form-data">
                            <input type ="hidden" name ="type2" id = "type2"/>
                            <input type ="hidden" id = "id" name = "id" value = "${requestScope.id}"/>
                            <input type ="hidden" id = "page" name = "page" value = "${requestScope.page}"/>
                            <input type ="hidden" name ="upd" value ="1"/>
                            <div class = "insideF">
                                <div class = "searchAl">
                                    <span class = "spn">Subject:</span>
                                    <select name = "subject" onchange = "type1()">
                                        <c:forEach items = "${requestScope.listS}" var = "i">
                                            <option ${requestScope.l.getChapter().getSubject().getSubjectID() == i.getSubjectID()?"selected":""} value = "${i.getSubjectID()}">${i.getSubjectCode()}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class = "searchA">
                                    <span class = "spn">Chapter: </span>
                                    <select name ="chapter" onchange = "type1()">
                                        <c:forEach items = "${requestScope.listC}" var = "i">
                                            <option ${requestScope.l.getChapter().getChapterID() == i.getChapterID()?"selected":""} value = ${i.getChapterID()}>${i.getChapterName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class = "searchAl">
                                    <span class = "spn">Title:</span>
                                    <input class ="inputText" type ="text" name ="name" value = "${requestScope.l.getLessonName()}"/>
                                </div>
                                <div  class = "searchA">
                                    <span class = "r1">Lesson Type:</span>
                                    <input class ="rdio" type ="radio" onclick ="check()" ${requestScope.l.getLessonType().getSetting_name().equals("Video")?"checked":""} name ="type" value ="video"/><span class = "r">Video</span>
                                    <input class ="rdio" type ="radio" onclick ="check()" ${requestScope.l.getLessonType().getSetting_name().equals("Quiz")?"checked":""} name ="type" value ="quiz"/><span class = "r">Quiz</span>
                                    <input class ="rdio" type ="radio" onclick ="check()" ${requestScope.l.getLessonType().getSetting_name().equals("Assignment")?"checked":""} name ="type" value ="asm"/><span class = "r">Assignment</span>
                                </div>
                                <div class = "searchAl">
                                    <span class = "spn">Display Order</span>
                                    <input class ="inputText" type ="number" min ="0" max ="100" name ="order" value = "${requestScope.l.getDisplayOrder()}"/>
                                </div>
                                <div  class = "searchA">
                                    <span class = "r1">Status</span>
                                    <input class ="rdio" type ="radio" checked name ="status" ${(requestScope.l.getStatus() == 0)?"checked":""} value ="0"/><span class = "r">Inactive</span>
                                    <input class ="rdio" type ="radio" name ="status" ${(requestScope.l.getStatus() == 1)?"checked":""} value ="1"/><span class = "r">Active</span>
                                </div>
                                <div class = "searchAll">
                                    <span class = "spn">Class </span>
                                    <select name = "class">
                                        <option value = "0" >All</option>
                                        <c:forEach items = "${requestScope.listCls}" var = "i">
                                            <option ${(requestScope.l.getCls() != null && requestScope.l.getCls().classID == i.getClassID())?"selected":""} value = "${i.getClassID()}">${i.getClassName()} - ${i.getS().getSubjectCode()}</option>
                                        </c:forEach>
                                    </select>
                                </div>  
                                <div class = "searchAl" id ="link">
                                    <span class = "spn">Video Link</span>
                                    <input class ="inputText"  type ="text" name ="link" value = "${requestScope.l.getVideoLink()}"/>
                                </div>
                                <div class = "searchAl" id = "file">
                                    <span class = "spn">  File:</span>
                                    <input class ="inputText"  type ="text" value = "${requestScope.l.getFileName()}"/>
                                    <input class ="inputText" id ="fileI" onclick="takeF()" type ="file" name ="file1" value = "${requestScope.l.getAttatchedFile()}"/>
                                </div>
                                <div class = "searchAl" id ="quiz">
                                    <span class = "spn">Quiz:</span>
                                    <select name = "quiz" onchange = "type1()">
                                        <c:forEach items = "${requestScope.listQ}" var = "i">
                                            <option ${requestScope.l.getQuiz() != null && requestScope.l.getQuiz().getQuizID() == i.getQuiz().getQuizID()?"selected":""} value = "${i.getQuiz().getQuizID()}">${i.getQuiz().getQuizName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class = "searchAl" id = "asm">
                                    <span class = "spn">Assignment:</span>
                                    <select name = "asm" onchange = "type1()">
                                        <c:forEach items = "${requestScope.listA}" var = "i">
                                            <option ${requestScope.l.getAsm() != null && requestScope.l.getAsm().getAsmID() == i.getAsm().getAsmID()?"selected":""} value = "${i.getAsm().getAsmID()}">${i.getAsm().getAsmName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                    <textarea hidden="" id ="dcr">${requestScope.l.getDescription()}</textarea>
                                <div class = "searchAB" id = "description">
                                    <span class = "spn"> Description:</span>
                                    <textarea id ="summernote" onchange ="go1()" name="editordata" placeholder = "Description">
                                    </textarea>
                                    <input type ="hidden" name ="cls" value ="${requestScope.cls}"/>
                                    <input type ="hidden" name ="topic" id = "ko"/>
                                    <input type ="hidden" name ="ko1" id = "ko1" value =""/>
                                    <textarea style ="display:none" id ="txt" name ="summerNoteText"></textarea>
                                </div>

                            </div>
                            <button class ="btnS" type ="button" value ="Back"><a href = "llist?act=list">Back</a></button>
                            <input class ="btnS" type ="submit" id ="sbmit" value ="Update"/>

                        </form>

                    </div>
                </c:if>
            </div>
        </aside>
        <%@include file = "footerfl.jsp" %>

        <script>
    document.getElementById("summernote").innerHTML = document.getElementById("dcr").innerHTML;        
    
    $(document).ready(function () {
                $('#summernote').summernote({
                    height: 450,
                    width: 1230,
                    fontsize: 20,
                    placeholder: 'Description',
                    value: document.getElementById("dcr").value,
                    callbacks: {
                        onImageUpload: function (files) {
                            for (var i = 0; i < files.length; i++) {
                                sendFile(files[i]);
                            }
                        }
                    }
                });
                $('#summernote').html(escape($('#summernote').summernote('code', document.getElementById("dcr").value)));


                document.getElementById("summernote").value = document.getElementById("dcr").value;
                console.log(document.getElementById("summernote").value);
                document.getElementsByName("summerNoteText").value = document.getElementById("summernote").value;
                document.getElementById("txt").value = document.getElementById("summernote").value;
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
                    url: "http://localhost:9999/prj/llist?act=add",
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

            function type1() {
                document.getElementById("type2").value = "1";
                document.getElementById("frm").submit();
            }
            document.getElementById("quiz").style.display = "none";
            document.getElementById("asm").style.display = "none";

            function check() {
                var a = document.querySelector('input[name="type"]:checked')?.value;
                if (String(a) === "video") {
                    document.getElementById("file").style.display = "block";
                    document.getElementById("quiz").style.display = "none";
                    document.getElementById("asm").style.display = "none";
                    document.getElementById("link").style.display = "block";
                } else if (String(a) === "quiz") {
                    document.getElementById("file").style.display = "none";
                    document.getElementById("quiz").style.display = "block";
                    document.getElementById("asm").style.display = "none";
                    document.getElementById("link").style.display = "none";


                } else {
                    document.getElementById("file").style.display = "none";
                    document.getElementById("quiz").style.display = "none";
                    document.getElementById("asm").style.display = "block";
                    document.getElementById("link").style.display = "none";

                }
            }

            $('input[type=file]').change(function () {
                console.log(this.files[0].path);
            });

            function takeF() {
                const fileInput = document.getElementById("fileI");
                console.log(fileInput.value);
                const file = fileInput.files[0];

// Lấy đường dẫn của file
                const path = file.path;

// In đường dẫn của file
                console.log(path);
            }


        </script>
    </body>
</html>
