<%@page import="utils.GetConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umlClasses.Event"%>
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
		Connection con = GetConnection.getConnection();
		Event event = new Event();
		ArrayList<Event> eventList = event.getExistingEvents(con);
		if (eventList == null) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('No events to display!');");
			out.println("location='index.jsp';");
			out.println("</script>");
		}
		int userId = 0;
		if (request.getParameter("userId") != null) {
			userId = Integer.parseInt(request.getParameter("userId"));
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
            <li class="active" style="color: #385185;"><a href="./Home.jsp">Home</a></li>
            <li><a href="./profile.jsp" style="color: #385185;">Profile</a></li>
            <li><a href="./addEvent.jsp" style="color: #385185;">Add Event</a></li>
            <li><a href="./index.jsp" style="color: #385185;">Logout</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

      <div class="container-fluid" style=" padding-top: 5%; padding-bottom: 10%;">

        <div class="row">
          <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
          <% for (Event e : eventList) { %>
            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
              <div class="panel panel-primary" >
                <div class="panel-heading" style="background-color: #DA4339;">
                  <div class="row">
                    <h3 class="panel-title col-lg-10 col-md-10 col-sm-12 col-xs-12">
                    <a href='eventPage.jsp?eventId=<%=e.getId()%>&userId=<%=userId%>'><%=e.getName()%></a>
                    </h3>
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                  </div>
                </div>
                <div class="panel-body">
                  <%=e.getDescription()%>
                </div>
              </div>
            </div>
           <% } %>
           </div>
          <!-- Movie List column closing -->

          <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
            <div class="panel panel-primary" >
                <div class="panel-heading" style="background-color: #E86834;">
                  <h3 class="panel-title">Recommended Events</h3>
                </div>
                <div class="panel-body">
                  <ul class="list-group">
                    <li class="list-group-item">
                      <span class="badge">3.5</span>
                      A Christmas Carol
                    </li>
                    <li class="list-group-item">
                      <span class="badge">4</span>
                      A Christmas Carol II
                    </li>
                    <li class="list-group-item">
                      <span class="badge">2</span>
                      A Christmas Carol III
                    </li>
                  </ul>
                </div>
              </div>
            </div>
        </div> <!-- Row -->
    </div><!-- /.container -->


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
