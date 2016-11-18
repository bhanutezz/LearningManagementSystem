<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<title>LMS ${user.userType} Portal</title>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css" integrity="sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX" crossorigin="anonymous">

	<link rel="stylesheet" href="http://getbootstrap.com/examples/sticky-footer/sticky-footer.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
<style>
.container {
	width: 90%;
	max-width: 90%;
}

.courseCode {
  font-size: x-small;
}

.courseSectionNum {
  font-size: x-small;
}

.courseTitle {
  font-weight: bold;
}

.courseInstructor {
  font-style: italic;
}

.userImage {
	height: 125px; 
	margin-top: 20px; 
	margin-left: 60px; 
	margin-right: 20px; 
	margin-bottom: 10px;
}

</style>

<script type="text/javascript">
	var courseId = ${selectedCourseId};
	
	function switchCourse(argCourseId) {
		$("a.active").toggleClass("active");
		$("a#course_" + argCourseId).toggleClass("active");
	    courseId = argCourseId;

		// select the current area to trigger tab load
		$("li.active a").trigger('shown.bs.tab');
	}
	
</script>
</head>

<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Learning Management System</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right" method='POST' action="logout">
            <p class="navbar-text" style="margin-bottom: 0; margin-top: 8;">${user.displayName}</p>
            <button type="submit" class="btn btn-success">Sign Out</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron" style="padding-bottom: 8px;">
      <div class="container">
		<div class="row">
			<div class="col-md-2">
				<c:if test="${user.userType == 'STUDENT' }">
			      	<img class="userImage" src="static/assets/images/boy-student-hi.png">
				</c:if>
				<c:if test="${user.userType == 'INSTRUCTOR' }">
			      	<img class="userImage" src="static/assets/images/science-teacher-in-class.png">
				</c:if>
			</div>
			<div style="margin-left:25px;" class="col-md-9">
		        <h1>Hello ${user.displayName}</h1>
		        <p>${portalHeading}
	                <span style="display: block;" id="showPortalDesc" class="pull-right">
	                  <a onclick="$('#showPortalDesc').hide();$('#portalDesc').show();$('#hidePortalDesc').show();">Learn more &gt;&gt;&gt;</a>
                    </span>
                    <span style="display: none;" id="hidePortalDesc" class="pull-right">
                      <a onclick="$('#showPortalDesc').show();$('#portalDesc').hide();$('#hidePortalDesc').hide();">&lt;&lt;&lt;Show Less</a>
                    </span>
		        </p>
		        <div id="portalDesc" style="display:none">
		          <p>${portalMsg}</p>
		        </div>
			</div>
		</div>
      </div>
    </div>

    <div class="container">
		<div class="row">
			<div class="col-md-3">
				<p class="lead">My Classes</p>
				<div class="list-group">
					<c:forEach var="course" items="${courseList}" varStatus="status">
						<a id="course_${course.id}" href="#" onclick="switchCourse(${course.id})"
							 ${status.first ? 'class="list-group-item active"' : 'class="list-group-item"'}>
							<span class="courseCode">${course.code}</span>-<span class="courseSectionNum">${course.section}</span><span class="pull-right">(${course.id})</span><br/>
							<span class="courseTitle">${course.title}</span><br/>
							<span class="courseInstructor">${course.instructor}</span>
						</a>
					</c:forEach>
				</div>
			</div>
			<div class="col-md-9">
				<div class="tabbable">
				    <ul class="nav nav-tabs" id="myAreaTabs">    
				        <li class="active"><a href="#courseInfo" data-toggle="tab">Course Info</a></li>
				        <li><a href="#assignments" data-toggle="tab">Assignments</a></li>
				        <li><a href="#quizzes" data-toggle="tab">Quizzes</a></li>
				        <li><a href="#grades" data-toggle="tab">Grades</a></li>
				        <li><a href="#discussions" data-toggle="tab">Discussions</a></li>
				    </ul>
				 
				    <div class="tab-content">
				        <div class="tab-pane active" id="courseInfo"></div>
				        <div class="tab-pane" id="assignments"></div>
				        <div class="tab-pane" id="quizzes"></div>
				        <div class="tab-pane" id="grades"></div>
				        <div class="tab-pane" id="discussions">Some static text. (displayed if AJAX request fails)</div>
				    </div>
				</div>
			</div>
		</div>
		
		<div class="row" style="display:none">
	      <hr>
			<h3><span style="color:red">*** REMOVE THIS FOR THE OFFICIAL DEMO ***</span>  Session Scope (key==values)</h3>
			<%
			  java.util.Enumeration<String> sessEnum = request.getSession().getAttributeNames();
			  while (sessEnum.hasMoreElements()) {
			    String s = sessEnum.nextElement();
			    out.print(s);
			    out.println("==" + request.getSession().getAttribute(s) + "<br />");
			  }
			%>
		</div>
    </div> <!-- /container -->

    <footer class="footer">
      <div class="container">
        <p class="text-muted">&copy; Pete Lund, Bhanu Kuppili 2015</p>
      </div>
    </footer>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript">
	    $(function() {
	        var baseURL = 'userMain/${user.userType}/';
	        //load content for first tab and initialize
	        $('#courseInfo').load(baseURL+'courseInfo'+'/' + courseId, function() {
	            $('#myAreaTabs').tab(); //initialize tabs
	        });    
	        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {    
	            var pattern=/#.+/gi; //use regex to get anchor(==selector)
	            var contentID = e.target.toString().match(pattern)[0]; //get anchor         
	            //load content for selected tab
	            var tabUrl = baseURL+contentID+'/' + courseId;
	            $(contentID).load(tabUrl.replace('#',''), function(){
	                $('#myAreaTabs').tab(); //reinitialize tabs
	            });
	        });
	    });
	</script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
</body>
</html>