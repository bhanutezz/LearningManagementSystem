<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>

.courseInfo .heading {
    font-size:x-large; 
    font-weight: bold;
}

.courseInfo p.instructor {
    font-size:x-large; 
}

.courseInfo span.instructor {
    font-style:italic; 
}

.courseInfo .schedule {
    font-size:large; 
}

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
</style>
<div class="courseInfo">
    <p></p>
	<p class="heading">
	   <span class="code">${courseInfo.course.code}</span>
       <span class="title">${courseInfo.course.title}</span>
       <span class="section">${courseInfo.course.section}</span>
	</p>
    <p class="instructor">
       Instructor: 
       <span class="instructor">${courseInfo.course.instructor}</span>
    </p>
    <p class="schedule">
       <span class="day">${courseInfo.schedule.dayOfWeek}</span>
       <span class="time">${courseInfo.schedule.startTime}</span> -
       <span class="time">${courseInfo.schedule.endTime}</span>
    </p>
    <p class="description">
       <span class="description">${courseInfo.description}</span>
    </p>
    <p class="refMaterials">
      <span class="sectionTitle">Reference Materials</span>
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
    </p>
  </div>
