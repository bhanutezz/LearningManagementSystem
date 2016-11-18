<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
label.leftmost { width: 100px; }

input.time-input { width:50px; }

.courseInfo .refMaterials .sectionTitle {
    text-decoration: underline;
}

.icon-list li {
	padding: 0 0 5px 20px;
	display: block;
	position: relative;
}
.icon-list li:before {
	font-family: 'Glyphicons Halflings';
	position: absolute;
	left: 0px;
	top:3px;
}

.file li:before {
    content: '\e022';
}

ul#fileList {
    margin-left: 20px;
}

.btn-file {
  position: relative;
  overflow: hidden;
}
.btn-file input[type=file] {
  position: absolute;
  top: 0;
  right: 0;
  min-width: 100%;
  min-height: 100%;
  font-size: 100px;
  text-align: right;
  filter: alpha(opacity=0);
  opacity: 0;
  background: red;
  cursor: inherit;
  display: block;
}
input[readonly] {
  background-color: white !important;
  cursor: text !important;
}
</style>

<script type="text/javascript">
$(document).on('change', '.btn-file :file', function() {
	  var input = $(this),
	      numFiles = input.get(0).files ? input.get(0).files.length : 1,
	      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	  input.trigger('fileselect', [numFiles, label]);
	});

	$(document).ready( function() {
	    $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
	        
	        var input = $(this).parents('.input-group').find(':text'),
	            log = numFiles > 1 ? numFiles + ' files selected' : label;
	        
	        if( input.length ) {
	            input.val(log);
	        } else {
	            if( log ) alert(log);
	        }
	        
	    });

	    $("#subbutton").click(function(){
		          processFileUpload();
		  });

		  $("#loader1").on('change',prepareLoad);
		  var files = [];
		  function prepareLoad(event)
		  {
		      console.log(' event fired'+event.target.files[0].name);
		      files=event.target.files;
		      $("#loader1").files = [];
		  }
		  function processFileUpload()
		  {
		      console.log("fileupload clicked");
		      var oMyForm = new FormData();
		      oMyForm.append("file", files[0]);
		      $.ajax({
		            dataType : 'json',
		            url : "${pageContext.request.contextPath}/uploadMyFile/" + ${courseInfo.course.id},
		            data : oMyForm,
		            type : "POST",
		            enctype: 'multipart/form-data',
		            processData: false,
		            contentType:false,
		            success : function(result) {
		                if (result.status == 'OK') {
		                    alert('Your file was successfully added.');

		                    var e = $("#fileUploadText");
		                    e.wrap('<form>').closest('form').get(0).reset();
		                    e.unwrap();
		                    items = [];
		                    
		                    $.each(result.fileNames, function(i, filename) {
		                       var listItem = 
		                           '<li><a href="${pageContext.request.contextPath}/files/${courseInfo.course.id}/' + filename +'" target="_blank"><span class="filename">' + filename +'</span></a></li>';
		                          items.push(listItem);
		                    });  // close each()
		                    $('#fileList').html( items.join('') );
		                } else {
		                    alert('Failure\n\n' + result.errorMessage);
		                }
		            },
		            error : function(result){
		                alert('error'+JSON.stringify(result));
		            }
		        });
		  }
		});
</script>

<div class="courseInfo panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Make your changes to the Course Information below.</h3>
  </div>
  <div class="panel-body">
	<form id="courseInfoForm" method="POST" action="userMain/INSTRUCTOR/courseInfo/${courseInfo.course.id}">
		<input type="hidden" name="id" value="${courseInfo.course.id}" />
		<p class="heading">
			<label class="leftmost" for="courseInfo-course-code">Code:</label>
			<input type="text" class="form-control" name="courseInfo.course.code" id="courseInfo-course-code" style="width:6em; display:inline;" disabled value="${courseInfo.course.code}" />
			  <span style="margin-right:2em; display:inline-block;"></span>
	
			<label for="courseInfo-course-section">Section:</label>
			<input type="text" class="form-control" name="courseInfo.course.section" id="courseInfo-course-section" style="width:5em; display:inline;" disabled value="${courseInfo.course.section}" />
		</p>
		
		<p class="heading-title">
			<label class="leftmost" for="courseInfo-course-title">Title:</label>
			<input type="text" class="form-control" name="courseInfo.course.title" id="courseInfo-course-title" style="width:60%; display:inline;" disabled value="${courseInfo.course.title}" />
		</p>
				
	    <p class="instructor">
			<label class="leftmost" for="courseInfo-course-instructor">Instructor:</label>
			<input type="text" class="form-control" name="courseInfo.course.instructor" id="courseInfo-course-instructor" style="width:250px; display:inline;" disabled value="${courseInfo.course.instructor}" />
	    </p>
	    
	    <p class="schedule">
			<label class="leftmost" for="courseInfo-schedule-dayOfWeek">Day of Week:</label>
			<select name="courseInfo.schedule.dayOfWeek" id="courseInfo-schedule-dayOfWeek" class="form-control" style="width:9em; display:inline;">
				<option value="Sunday" <c:if test="${courseInfo.schedule.dayOfWeek == 'Sunday'}">selected='selected'</c:if> >Sunday</option>
				<option value="Monday" <c:if test="${courseInfo.schedule.dayOfWeek == 'Monday'}">selected='selected'</c:if> >Monday</option>
				<option value="Tuesday" <c:if test="${courseInfo.schedule.dayOfWeek == 'Tuesday'}">selected='selected'</c:if> >Tuesday</option>
				<option value="Wednesday" <c:if test="${courseInfo.schedule.dayOfWeek == 'Wednesday'}">selected='selected'</c:if> >Wednesday</option>
				<option value="Thursday" <c:if test="${courseInfo.schedule.dayOfWeek == 'Thursday'}">selected='selected'</c:if> >Thursday</option>
				<option value="Friday" <c:if test="${courseInfo.schedule.dayOfWeek == 'Friday'}">selected='selected'</c:if> >Friday</option>
				<option value="Saturday" <c:if test="${courseInfo.schedule.dayOfWeek == 'Saturday'}">selected='selected'</c:if> >Saturday</option>
			</select>
			
		  <span style="margin-right:2em; display:inline-block;"></span>
			<label for="courseInfo-schedule-startTime">Start:</label>
			<input type="text" class="time-input form-control" style="width:70px; display:inline;" name="courseInfo.schedule.startTime" id="courseInfo-schedule-startTime" value="${courseInfo.schedule.startTime}" />
		  <span style="margin-right:1em; display:inline-block;"></span>
			<label for="courseInfo-schedule-endTime">End:</label>
			<input type="text" class="time-input form-control" style="width:70px; display:inline;" name="courseInfo.schedule.endTime" id="courseInfo-schedule-endTime" value="${courseInfo.schedule.endTime}" />
	    </p>
	    
	    <p class="description">
			<label for="courseInfo-description">Description:</label><br />
			<textarea class="form-control" name="courseInfo.description" id="courseInfo-description"
			     rows='5' style="width:100%;">${courseInfo.description}</textarea>
	    </p>
        
        <button type="button" class="btn btn-default pull-right" onclick="mySubmit();">Submit</button>
        <script type="text/javascript">
            function mySubmit() {
                $.ajax({
                    type: "PUT",
                    url: "userMain/INSTRUCTOR/courseInfo/${courseInfo.course.id}",
                    contentType: 'application/json',
                    data: JSON.stringify({id: ${courseInfo.course.id}, 
                        description: $('textarea#courseInfo-description').val(), 
                        schedule: {dayOfWeek: $('select#courseInfo-schedule-dayOfWeek option:selected').val(), 
                                   startTime: $('input#courseInfo-schedule-startTime').val(), 
                                   endTime: $('input#courseInfo-schedule-endTime').val()}
                    }),
                    success: function(resp) {
                        if ("OK" == resp.status) {
                            alert("Your updates have been saved.");         
                        } else {
                            alert("An unexpected error has occurred.\n\n" 
                                  + resp.errorMessage );         
                        }
                    }
                });
                                
            }
        </script>
	    
	    <hr style="margin-top: 60px;"> 
	    
        <p class="refMaterials">
            <span class="sectionTitle">Reference Materials</span>
        </p>
		<div class="row">
			<div class="col-md-7">
		          <ul id="fileList" class="list-unstyled icon-list file">
		            <c:forEach var="attachment" items="${courseInfo.attachments}">
		            <li>
		                <a id="course_${course.id}-${attachment.filename}" 
		                   href="${pageContext.request.contextPath}/files/${courseInfo.course.id}/${attachment.filename}" target="_blank">
		                    <span class="filename">${attachment.filename}</span>
		                </a>
		                
		            </li>
		            </c:forEach>
		          </ul>
	        </div>
			<div class="col-md-5">
			    <div class="input-group">
                   <span class="input-group-btn">
                       <span class="btn btn-primary btn-file">
                        Browse&hellip; <input type="file" multiple="" name="loader1" id="loader1">
                       </span>
                   </span>
                   <input id="fileUploadText" type="text" readonly="" class="form-control">
                </div>
				<input class="btn btn-default btn-file" type="button" id="subbutton" value="Upload" style="margin-top: 10px;"/>
	        </div>
		</div>   
    </form>
  </div>
</div>
