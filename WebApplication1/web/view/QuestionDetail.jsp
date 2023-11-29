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
                text-align: center;
                color: #0077b6;
                font-size: 20px;
                font-style: italic;
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

                <div class = "ttle">Question Details</div>

                <div class = "in4">
                    <form action="ClassAssignmentUpdate" method="post" role="form" enctype="multipart/form-data">
                        <input type="hidden" name="quesID" value="${ques.quesID}">
                        <div class = "insideF">
                            <div class = "searchAl">
                                <label style="color: #0077b6; font-size: 20px; font-style: italic;"  for="chapter">Chapter:</label>
                                <span >${ques.subjectsetting.settingName}</span>
                                <input type="text" id="chapter" name="chapter" style="display: none;">
                            </div>
                            <div class = "searchA">
                                <label style="color: #0077b6; font-size: 20px; font-style: italic;" class = "spn">Subject:</label>                    
                                <span >${ques.subject.subjectCode}</span>
                                <input type="text" name="subjectName" value="${ques.subject.subjectCode}" style="display: none;">
                            </div>
                            <div class = "searchAl">
                                <label style="color: #0077b6; font-size: 20px; font-style: italic;" class = "spn">Created by:</label>
                                <span >${ques.createdBy.user}</span>
                                <input type="text" name="createdBy" value="${ques.createdBy.user}" style="display: none;">
                            </div>
                            <div class = "searchA">
                                <label style="color: #0077b6; font-size: 20px; font-style: italic;" class = "spn">Created at:</label>
                                <span >${ques.createdAt}</span>
                                <input type="text" name="createdAt" value="${ques.createdAt}" style="display: none;">
                            </div> 
                            <div class = "searchAB">
                                <label  style="color: #0077b6; font-size: 20px; font-style: italic;" class = "spn" for="topic">Topic:</label>
                                <span>${ques.topic}</span>
                                <input type="text" id="asmDes" name="topic" value="${ques.topic}" style="display: none;">
                            </div>



                        </div>
                        <button class ="btnS" type ="button" value ="Back"><a href = "QuestionList?page=1&size=5">Close</a></button>

                    </form>

                </div>
            </div>
        </aside>
        <%@include file = "footerfl.jsp" %>

        <script>
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
