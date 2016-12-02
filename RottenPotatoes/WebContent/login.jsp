<%@page import="umlClasses.RegisteredUser"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
	<%
		DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms_project",
			"root", "chini");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		if(request.getParameter("username")!=null && request.getParameter("password")!=null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			RegisteredUser rs = new RegisteredUser();
			RegisteredUser rs1 = rs.getUser(connection, username, password);
			if (rs1.getid() > 0) {
				 response.sendRedirect("http://localhost:8080/RottenPotatoes/profile.jsp?userId="+rs1.getid());
			}
		}
		
	%>
    <title>RottenPotatoes</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap_flat.css" rel="stylesheet">
    <style>
          body {
              padding-top: 50px;
              padding-bottom: 20px;
          }
    </style>
    <link rel="stylesheet" type="text/css" href="css/main.css">


    <!-- Custom styles for this template -->
    
  </head>

  <body style="background-color: #39A0CD;">

    <nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: #EBEBEB;">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="./index.jsp" style="color: #385185;">RottenPotatoes</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse pull-right">
          <ul class="nav navbar-nav">
          <!-- LINK TO REMOVE -->

            <li><a href="./eventPage.jsp" style="color: #385185;">Event Page (Development Link)</a></li>
            
          <!-- LINK TO REMOVE -->
            <li><a href="./index.jsp" style="color: #385185;">Home</a></li>
            <li><a href="./profile.jsp" style="color: #385185;">Profile</a></li>
            <li><a href="./addEvent.jsp" style="color: #385185;">Add Event</a></li>
            <li class="active" style="color: #385185;"><a href="./login.jsp">Sign In</a></li>
            <li><a href="./register.jsp" style="color: #385185;">Register</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div id="wrap">
    <div class="container center_div" id="top">
      <div class="panel panel-default">
          <div class="panel-heading">Sign In</div>
            <div class="panel-body">
              <form class="form-horizontal">
                <fieldset>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">Username</label>
                    <div class="col-lg-10">
                      <input name="username" type="text" class="form-control" id="inputEmail" placeholder="Email">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                    <div class="col-lg-10">
                      <input type="password" class="form-control" name="password" id="inputPassword" placeholder="Password">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                      <button type="submit" class="btn btn-primary" on-click="submit()">Sign In</button>
                    </div>
                  </div>
                </fieldset>
              </form>
            </div>
          </div>
      </div>
    </div><!-- /.container -->
    </div>

    <footer class="footer" style="background-color:#fff;">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <ul class="list-unstyled">
              <li class="pull-right"><a href="#top">Back to top</a></li>
              <li><a href="https://github.com/chinmayee-vaidya2592/cs5200_Chini_Chinmayee_Mounica_Rohit">GitHub</a></li>
              <p>Made by Chini, Chinmayee, Monica and Rohit</p>
            </ul>
          </div>
        </div>
      </div>
    </footer>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
