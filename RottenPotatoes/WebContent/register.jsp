<%@page import="utils.Utils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umlClasses.Admin"%>
<%@page import="java.sql.Connection"%>
<%@page import="utils.GetConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

	<%
		//register.jsp?firstName=Chini&lastName=Sinha&userName=csinha&email=chinisinha%40gmail.com&password=chini&Horror=on&Thriller=on&History=on
		if (request.getParameter("firstName") != null &&
			request.getParameter("lastName") != null && 
			request.getParameter("userName") != null &&
			request.getParameter("email") != null &&
			request.getParameter("password") != null) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String userName = request.getParameter("userName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
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
            <li><a href="./index.jsp" style="color: #385185;">Home</a></li>
            <li><a href="./login.jsp" style="color: #385185;">Sign In</a></li>
            <li class="active" href="./register.jsp" style="color: #385185;"><a>Register</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div id="wrap">
    <div class="container center_div" id="top">
      <div class="panel panel-default">
          <div class="panel-heading">Register</div>
            <div class="panel-body">
              <form class="form-horizontal">
                <fieldset>
                  <div class="form-group">
                    <label for="inputFirstName" class="col-lg-2 control-label">First Name</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputFirstName" placeholder="First Name" name="firstName">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputLastName" class="col-lg-2 control-label">Last Name</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputLastName" placeholder="Last Name" name="lastName">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputUsername" class="col-lg-2 control-label">Username</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputUsername" placeholder="Username" name="userName">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" placeholder="Email" name="email">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                    <div class="col-lg-10">
                      <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password">
                    </div>
                  </div>
                  <div class="form-group">
                  <div class="row">
                  	<span class="col-lg-2 col-md-2"></span>
                  	<h6 class="col-lg-10 col-md-10">
                  		Choose you favorite genres for specially curated recommendations
                  	</h6>
                  	</div>
                  	<div class="checkbox">
                  	<span class="col-lg-2 col-md-2"></span>
			          <label for="genreSelection" class="col-lg-2 control-label">
			            <input type="checkbox" name="Horror">Horror
			          </label>
			          <label for="genreSelection" class="col-lg-2 control-label">
			            <input type="checkbox" name="Thriller">Thriller
			          </label>
			          <label for="genreSelection" class="col-lg-2 control-label">
			            <input type="checkbox" name="History">History
			          </label>
			          <label for="genreSelection" class="col-lg-2 control-label">
			            <input type="checkbox" name="Drama">Drama
			          </label>
			          <label for="genreSelection" class="col-lg-2 control-label">
			            <input type="checkbox" name="Comedy">Comedy
			          </label>
			        </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                      <button type="submit" class="btn btn-primary">Sign In</button>
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