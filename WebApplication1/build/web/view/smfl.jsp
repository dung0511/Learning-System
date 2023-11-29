<%-- 
    Document   : userMgm
    Created on : Sep 17, 2023, 3:04:41 PM
    Author     : Acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Director | Dashboard</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
      

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
          <![endif]-->

        <style>
            .sidebar{
                color: black;
            }
            
            .pull-left {
                color: black;
            }
            .left-side sidebar-offcanvas{
                height: 100%;
            }
        </style>

    </head>
    <body class="skin-black">
     
        <aside class="left-side sidebar-offcanvas" style="height:140%;">
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
                            <i class="fa fa-list"></i> <span>Subject Setting</span>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <i class="fa fa-list"></i> <span>Question List</span>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <i class="fa fa-list"></i> <span>Quiz List</span>
                        </a>
                    </li>
                    
                    <li>
                        <a href="llist?act=list">
                            <i class="fa fa-list"></i> <span>Lesson List</span>
                        </a>
                    </li>
                    <li>
                        <a href="clist?act=list">
                            <i class="fa fa-list"></i> <span>Class List</span>
                        </a>
                    </li>

                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
    

    
    </body>
</html>