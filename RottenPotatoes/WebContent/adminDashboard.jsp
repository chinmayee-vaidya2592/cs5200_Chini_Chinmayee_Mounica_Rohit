<%@page import="utils.GetConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umlClasses.Event"%>
<%@page import="umlClasses.RegisteredUser" %>
<%@page import="umlClasses.Admin" %>
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
		RegisteredUser rs = new RegisteredUser();
		Admin a = new Admin(rs.getId());
		ArrayList<RegisteredUser> userList = a.getAllUsers(con);
		ArrayList<Event> eventList = a.getAllEvents(con);
		if (userList == null) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('No events to display!');");
			out.println("location='adminDashboard.jsp';");
			out.println("</script>");
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
            <li class="active" style="color: #385185;"><a href="./index.jsp">Home</a></li>
            <li><a href="./login.jsp" style="color: #385185;">Sign In</a></li>
            <li><a href="./register.jsp" style="color: #385185;">Register</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

      <div class="container" style=" padding-top: 5%; padding-bottom: 10%;">

        <div class="row">
        	<div class="panel panel-info">
	        	<div class="panel-heading" style="background-color: ##385185;">
	        		<h3 class="panel-title">All Data</h3>
	        	</div>
	        	<div class="panel-body">
		        	<ul class="nav nav-tabs">
					  <li class="active"><a href="#userList" data-toggle="tab">Users</a></li>
					  <li><a href="#eventList" data-toggle="tab">Events</a></li>
					</ul>
			
					<div id="myTabContent" class="tab-content">
					<% for (RegisteredUser u : userList) { %>
					  <div class="tab-pane fade active in" id="userList">
					    <table class="table table-striped table-hover ">
						  <thead>
						    <tr>
						      <th>User Name</th>
						      <th>Email</th>
						      <th>Actions</th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr>
						      <td><%= u.getusername() %></td>
						      <td><%= u.getemail() %></td>
						      <td>
						      	<div class="btn-group">
						      		<a href="editUser.jsp?userId=<%= u.getId() %>" >
						      			<span class="btn btn-warning glyphicon glyphicon-pencil"></span>
						      		</a>
						      	</div>
                    			<div class="btn-group">
                    				<a href="#" class="btn btn-danger dropdown-toggle glyphicon glyphicon-trash" 
                    							data-toggle="dropdown" 
                    							aria-expanded="false">
                    					<span class="glyphicon glyphicon-triangle-bottom"></span>			
                 					</a>
									  <ul class="dropdown-menu">
									    <li><a>Delete</a></li>
									    <li><a href="#">Cancel</a></li>
									  </ul>	
								</div>
						      </td>
						    </tr>
						  </tbody>
						</table>
					  </div>
					  <% } %>
					 
					  <div class="tab-pane fade" id="eventList">
					    <table class="table table-striped table-hover ">
						  <thead>
						    <tr>
						      <th>#</th>
						      <th>Event Name</th>
						      <th>Event Description</th>
						      <th>Actions</th>
						    </tr>
						  </thead>
						  <tbody>
						   <% for (Event e : eventList) { %>
						    <tr>
						      <td>
						      	<a href='eventPage.jsp?eventId=<%= e.getId() %>'><%= e.getId() %></a>
						      </td>
						      <td><%=e.getName()%></td>
						      <td><%=e.getDescription()%></td>
						      <td>
						      	<div class="btn-group">
						      		<a href="editEvent.jsp?eventId=<%= e.getId() %>" >
						      			<span class="btn btn-warning glyphicon glyphicon-pencil"></span>
						      		</a>
						      	</div>
                    			<div class="btn-group">
                    				<a href="#" class="btn btn-danger dropdown-toggle glyphicon glyphicon-trash" 
                    							data-toggle="dropdown" 
                    							aria-expanded="false">
                    					<span class="glyphicon glyphicon-triangle-bottom"></span>			
                 					</a>
									  <ul class="dropdown-menu">
									    <li><a href="#">Delete</a></li>
									    <li><a href="#">Cancel</a></li>
									  </ul>	
								</div>
						      </td>
						    </tr>
						     <% } %>
						  </tbody>
						</table>
					  </div>
					 
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
