<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

    <head>
        <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
        <style>
            body {
                display: flex;
                flex-direction: column;
            }

            #menu {
                width: 100%;
                z-index: 1;
            }
            #content-container {
                width: 100%;
                margin-top: 25px;
                height: 120%;

            }
            body {
                font-family: Arial, sans-serif;
            }
            #tabs {
                overflow: hidden;
                border: 1px solid #ccc;
                background-color: #f1f1f1;
            }
            .tab {
                background-color: #ddd;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 14px 16px;
                transition: 0.3s;
                font-size: 17px;
                width: 20%;
            }

            .tabActive{
                background-color: #aaa;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 14px 16px;
                transition: 0.3s;
                font-size: 17px;
                width: 20%;

            }
            .tab:hover {
                background-color: #bbb;
            }
            .tab.active {
                background-color: #aaa;
            }
            #editArea {
                padding: 6px 12px;
                border: 1px solid #ccc;
                border-top: none;
                margin-left: 0px;
                width: 100%;
                height: 120%;
                text-align: left;
            }
            #tabs{
                margin-left: 20px;
                margin-right: 20px;
            }

            .footer-main{
                margin-top: 20px;
                left: 0%;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $('.tab').click(function () {
                    var tabId = $(this).attr('id');
                    var id = '<%=request.getAttribute("id")%>';
                    var url;
                    if (tabId == 'lp') {
                        url = '/prj/cds?class=' + id;
                    } else if (tabId == 'al') {
                        url = '/prj/cds?class=' + id;

                    } else if (tabId == 'ql') {
                        url = '/prj/cds?class=' + id;
                    } else if (tabId == 'cg') {
                        url = '/prj/cg?class=' + id;
                    } else if (tabId == 'dimension') {
                        url = '/prj/cds?class=' + id;
                    }
                    $('.tab').removeClass('active');
                    $(this).addClass('active');
                    $.ajax({
                        url: url,
                        success: function (data) {
                            $('#editArea').html(data);
                        }
                    });
                });
                //$('#general').click(); // Tab "General" sẽ được chọn mặc định
                $(document).on('click', '.pagination a', function (e) {
                    e.preventDefault();
                    var url = $(this).attr('href');
                    console.log(url);
                    $.ajax({
                        url: url,
                        success: function (data) {
                            $('#editArea').html(data);
                        }
                    });
                });
            });

        </script>
    </head>
    <body>

        <%@include file = "menu.jsp" %>
        <div id="content-container">
            <div id="tabs">
                <button class="tab" id="lp">Learning Page</button>
                <button class="tab" id="al">Assignment List</button>
                <button class="tab" id="ql">Quiz List</button>
                <c:if test = "${requestScope.act != 'cg'}">
                    <button class="tab" id="cg">Class Grade</button>
                </c:if>
                <c:if test = "${requestScope.act == 'cg'}">
                    <button class="tabActive" id="cg">Class Grade</button>
                </c:if>
                <button class="tab" id="dimension">Class Discussion</button>
            </div>
            <div id="editArea">
                <c:if test = "${requestScope.act == 'cg'}">
                    <%@include file = "ClassGrade.jsp" %>
                </c:if>
                <c:if test = "${requestScope.act == 'cds'}">
                    <%@include file = "ClassDiscussion.jsp" %>
                </c:if>
            </div>        
        </div>
        <%@include file = "footer.jsp" %>
        <div class="footer-main">
            Copyright &copy Director, 2014
        </div>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
         <script>
                                                                    function show() {
                                                                        document.getElementById("a").style.display = 'none';
                                                                        document.getElementById("new").style.display = 'block';
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
                                                                        $('#frm').submit(function () {
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

    </body>
</html>
