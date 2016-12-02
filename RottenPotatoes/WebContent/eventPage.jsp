<%@page import="umlClasses.Ticket"%>
<%@page import="utils.Utils"%>
<%@page import="umlClasses.Reviews"%>
<%@page import="umlClasses.Comments"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umlClasses.Event"%>
<%@page import="java.sql.Connection"%>
<%@page import="utils.GetConnection"%>
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
    	int eventId = 0;
    	if (request.getParameter("eventId") != null) {
    		eventId = Integer.parseInt(request.getParameter("eventId"));
    	}
    	int userId = 0;
		if (request.getParameter("userId") != null) {
			userId = Integer.parseInt(request.getParameter("userId"));
		}
    	Connection con = GetConnection.getConnection();
    	Event event = new Event(con, eventId);
    	System.out.println("Start Date: " + event.getStartDate().toString());
    	System.out.println("End Date: " + event.getEndDate().toString());
    	ArrayList<String> dateRange = Utils.getDateRange(event.getStartDate(), event.getEndDate());
    	int count = 0;
    	String showDate = "";
    	if(request.getParameter("ticDate") != null && request.getParameter("ticCount") != null) {
    		count = Integer.parseInt(request.getParameter("ticCount"));
    		showDate = request.getParameter("ticDate");
    	}
    	ArrayList<Ticket> ticketCreated = new ArrayList<Ticket>();
    	if (count > 0) {
    		for (int i=1; i <= count; i++) {
    			Ticket t = new Ticket();
    		    Ticket created = t.createTicket(con, eventId, userId, showDate);
    		    ticketCreated.add(created);
    		}
    		StringBuilder sb = new StringBuilder("Ticket for " + Utils.getUserNameById(con, userId));
        	for (Ticket tick : ticketCreated) {
        		sb.append(": Ticket ID:" + tick.getTicketId() + ",");
        	}
        	out.println("<script type=\"text/javascript\">");
			out.println("alert('"+ sb.toString() + "');");
			out.println("location='Home.jsp';");
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

                    <!-- Modal -->
                      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                              <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                            </div>
                            <div class="modal-body">
                              ...
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!-- Modal end -->

    <nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: #EBEBEB;">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="./index.html" style="color: #385185;">RottenPotatoes</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse pull-right">
          <ul class="nav navbar-nav">
            <li class="active" style="color: #385185;"><a href="./index.jsp">Home</a></li>
            <li><a href="./profile.jsp" style="color: #385185;">Profile</a></li>
            <% if (!(userId > 0)) { %>
            <li><a href="./login.jsp" style="color: #385185;">Sign In</a></li>
            <li><a href="./register.jsp" style="color: #385185;">Register</a></li>
            <% } else { %>
            <li><a href="./addEvent.jsp" style="color: #385185;">Add Event</a></li>
            <li><a href="./index.jsp" style="color: #385185;">Logout</a></li>
            <% } %>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div id="wrap">
    <div class="container" id="top">
      <div class="row" style="margin-top: 5%; margin-bottom: 5%;">
        <div class="starter-template col-lg-7 col-md-7 col-sm-12 col-xs-12" style="color: #fff;">
          <h1 style="color: #fff;"><%=event.getName()%></h1>
          <p class="lead"><%=event.getDescription()%></p>
          <h2 class="rating-badge"><%=event.getCalculatedRating()%>/5</h2>
        </div>

        <div class="panel panel-default col-lg-5 col-md-5">
          <div class="panel-heading">Book Tickets</div>
            <div class="panel-body">
            <h4><b>Event Time: </b><%=event.getShowTime() %></h4>
            <h4><b>Cost: </b>$<%=event.getTicketPrice() %></h4>
              <form class="form-horizontal" >
                <fieldset>
                <div class="form-group">
                <label for="select" class="col-lg-2 control-label">Select Date:</label>
                 <div class="col-lg-10">
                  <select class="form-control" id="select" name="ticDate">
                    <% for (String date : dateRange) { %>
                      <option><%= date %></option>
                    <% } %>
                  </select>
                  <br>
                  </div>
              </div>
                  <div class="form-group">
                    <label for="select" class="col-lg-2 control-label">Ticket Count</label>
                    <div class="col-lg-10">
                      <select class="form-control" id="select" name='ticCount'>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option>6</option>
                        <option>7</option>
                        <option>8</option>
                        <option>9</option>
                        <option>10</option>
                      </select>
                      <br>
                    </div>
                  </div>
                  <div class="form-group">
                  <input type="hidden" name='eventId' value="<%=eventId%>"/>
                  <input type="hidden" name='userId' value="<%=userId%>"/>
                  </div>
                  <% if (userId > 0) { %>
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                      <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Buy Tickets</button>
                    </div>
                  </div>
                  <% } %>
                </fieldset>
              </form>
            </div>
          </div>
        </div>
      </div>

      <div class="container-fluid" style=" padding-top: 5%; padding-bottom: 10%;">
      <h3 style="color: #fff;">Comments and Reviews</h3>
      <hr>
        <div class="row">
          <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
          <% if (userId > 0) { %>
              <div class="panel panel-primary">
                <div class="panel-heading">Add a comment</div>
                <div class="panel-body">
                  <form class="form-horizontal">
                    <fieldset>
                      <div class="form-group">
                        <label for="addComment" class="col-lg-2 control-label" style="color: #000;">Comment</label>
                        <div class="col-lg-10">
                          <textarea name="addComment" class="form-control" rows="3" id="addComment" placeholder="Write your comment"></textarea>
                        </div>
                      </div>
                      <div class="form-group" style="margin: 5%;">
                        <div class="col-lg-10 col-lg-offset-2">
                          <button type="submit" class="btn btn-primary">Comment</button>
                        </div>
                      </div>
                    </fieldset>
                  </form>
                </div>
                </div>
              <% 
          		}
              	for (Comments c : event.getCommentList()) {
              %>
              <div class="panel panel-primary" >
                <div class="panel-heading" style="background-color: #DA4339;">
                  <h3 class="panel-title"><%=c.getUserNameById(con, c.getUserId()) %></h3>
                </div>
                <div class="panel-body">
                  <%=c.getCommentText() %>
                </div>
              </div>
              <% } %>

          </div> <!-- Movie List column closing -->

          <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
			<% if (userId > 0) { %>
            <div class="panel panel-primary">
                <div class="panel-heading">Add a Review</div>
                  <div class="panel-body">
                    <form class="form-horizontal">
                      <fieldset>
                        <div class="form-group">
                          <label for="select" class="col-lg-2 control-label" style="color: #000;">Rating</label>
                          <div class="col-lg-10">
                            <select class="form-control" id="select">
                              <option>1</option>
                              <option>1.5</option>
                              <option>2</option>
                              <option>2.5</option>
                              <option>3</option>
                              <option>3.5</option>
                              <option>4</option>
                              <option>4.5</option>
                              <option>5</option>
                            </select>
                            <br>
                          </div>
                        </div>
                        <div class="form-group">
                          <label for="addReview" class="col-lg-2 control-label" style="color: #000;">Review</label>
                          <div class="col-lg-10">
                            <textarea name="addReview" class="form-control" rows="3" id="addReview" placeholder="Write your review here"></textarea>
                          </div>
                        </div>
                        <div class="form-group" style="margin: 5%;">
                          <div class="col-lg-10 col-lg-offset-2">
                            <button type="submit" class="btn btn-primary">Review</button>
                          </div>
                        </div>
                      </fieldset>
                    </form>
                  </div>
              </div>
			<% } %>
            <div class="panel panel-primary" >
                <div class="panel-heading" style="background-color: #E86834;">
                  <h3 class="panel-title">Reviews</h3>
                </div>
                <div class="panel-body">
                  <ul class="list-group">
                  <%
                  	for (Reviews r : event.getReviewList()) {
                  %>
                    <li class="list-group-item">
                      <span class="badge"><%=r.getRating() %></span>
                      <%=r.getDescription() %>
                    </li>
                   <% } %>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div> <!-- Row -->
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
    <script type="text/javascript">
      $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').focus()
      })
    </script>
  </body>
</html>
