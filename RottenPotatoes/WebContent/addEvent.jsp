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
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-timepicker/0.5.2/css/bootstrap-timepicker.min.css"/>
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
          <a class="navbar-brand" href="./index.html" style="color: #385185;">RottenPotatoes</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse pull-right">
          <ul class="nav navbar-nav">
          <!-- LINK TO REMOVE -->

            <li><a href="./eventPage.html" style="color: #385185;">Event Page (Development Link)</a></li>
            
          <!-- LINK TO REMOVE -->
            <li><a href="./index.html" style="color: #385185;">Home</a></li>
            <li class="active" style="color: #385185;"><a href="./profile.html">Profile</a></li>
            <li><a href="./login.html" style="color: #385185;">Sign In</a></li>
            <li><a href="./register.html" style="color: #385185;">Register</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

        <div id="wrap">
    <div class="container center_div" id="top">
      <div class="panel panel-default" style="margin-bottom: 25%;">
          <div class="panel-heading">Profile</div>
            <div class="panel-body">
              <form class="form-horizontal">
                <fieldset>
                  <div class="form-group">
                    <label for="inputEventName" class="col-lg-2 control-label">Event Name</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEvent Name" placeholder="First Name">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEventDescription" class="col-lg-2 control-label">Event Description</label>
                    <div class="col-lg-10">
                      <textarea class="form-control" rows="3" id="inputEventDescription" placeholder="Event Description"></textarea>
                      <span class="help-block">Enter a small synopsis of your event or give away the whole plot!</span>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-2 control-label">Genres</label>
                    <div class="col-lg-10">
                      <div class="radio">
                        <label>
                          <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked="">
                          Horror
                        </label>
                      </div>
                      <div class="radio">
                        <label>
                          <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                          Thriller
                        </label>
                      </div>
                      <div class="radio">
                        <label>
                          <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                          History
                        </label>
                      </div>
                      <div class="radio">
                        <label>
                          <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                          Drama
                        </label>
                      </div>
                      <div class="radio">
                        <label>
                          <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                          Comedy
                        </label>
                      </div>
                    </div>
                  </div>
                  <!-- Form code begins -->
                    <div class="form-group"> <!-- Date input -->
                      <label class="col-lg-2 col-md-2 col-sm-12 col-xs-12 control-label" for="date">Starting Date</label>
                      <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12">
                      <input class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text"/>
                      </div>
                    </div>
                   <!-- Form code ends -->
                   <!-- Form code begins -->
                    <div class="form-group"> <!-- Date input -->
                      <label class="col-lg-2 col-md-2 col-sm-12 col-xs-12 control-label" for="date">Ending Date</label>
                      <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12">
                      <input class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text"/>
                      </div>
                    </div>
                   <!-- Form code ends -->
                    <div class="input-group bootstrap-timepicker timepicker">
                        <label class="col-lg-3 col-md-2 col-sm-12 col-xs-12 control-label" for="date">Show Time</label>
                        <div class="col-lg-9 col-md-10 col-sm-12 col-xs-12">
                          <input id="timepicker1" type="text" class="form-control input-small">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                        </div>
                    </div>
                    <div class="form-group" style="margin: 5%;">
                      <div class="col-lg-10 col-lg-offset-2">
                        <button type="submit" class="btn btn-primary">Add Event</button>
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
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-timepicker/0.5.2/js/bootstrap-timepicker.min.js"></script>
<script>
    $(document).ready(function(){
      var date_input=$('input[name="date"]'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
</script>
<script type="text/javascript">
            $('#timepicker1').timepicker();
        </script>
  </body>
</html>
