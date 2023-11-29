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
        </style>
    </head>
    <body>
        <div>
            <div class = "inp">
                <input type ="text" id ="topic" placeholder = "Topic"/>
            </div>
            <div class = "txtA">
                <textarea id ="summernote" onchange ="go1()"  name="editordata" placeholder = "Description">
                </textarea>
                <form id = "frm" method="post" action = "cds">
                    <input type ="hidden" name ="cls" value ="${requestScope.cls}"/>
                    <input type ="submit" id ="sbmit" value ="Post"/>
                    <input type ="hidden" name ="topic" id = "ko"/>
                    <input type ="hidden" name ="ko1" id = "ko1" value =""/>
                    <textarea style ="display:none" id ="txt" name ="summerNoteText"></textarea>
                </form>
            </div>
        </div>
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



            function sendFile(file) {
                console.log("2");
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
                document.getElementById("txt").value = document.getElementById("summernote");
                console.log(document.getElementById("summernote"));
            }



        </script>
    </body>
</html>
