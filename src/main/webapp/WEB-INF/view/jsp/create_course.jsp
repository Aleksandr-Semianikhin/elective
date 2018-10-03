<%--
  
  User: Aleksandr Semianikhin
  Document: create_course
  Date: 01.10.2018
  Time: 12:02
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<!DOCTYPE html>
<html>
<fmt:setBundle basename="resources"/>
<fmt:message key="admin.create.course.title" var="pageTitle"/>
<jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title form-inline">
                <fmt:message key="admin.create.course.title"/>
                <c:out value=" ${course.courseName}"/>
            </h4>
        </div>
        <div class="panel-body">
            <form class="form-group" action="admin" method="post">
                <input hidden="true" name="command" value="adminActionCourse"/>
                <input hidden="true" name="crud" value="create"/>
                <label for="courseName"><c:out value="${error}"/></label>
                <label for="courseName"><fmt:message key="admin.create.course.name"/></label>
                <input required minlength="10" maxlength="45" type="text" class="form-control" id="courseName" name="courseName" value="${course.courseName}"><br/>
                <label for="tags"><fmt:message key="admin.create.course.tag"/></label>
                <select class="form-control" id="tags" name="tag">
                    <c:forEach items="${tags}" var="entry">
                        <option value="${entry.idTag}">${entry.name}</option>
                    </c:forEach>
                </select><br/>
                <label for="startDate"><fmt:message key="admin.create.course.startDate"/></label>
                <input required type="date" class="form-control" id="startDate" name="startDate"><br/>
                <label for="endDate"><fmt:message key="admin.course.course.endDate"/></label>
                <input required type="date" class="form-control" id="endDate" name="endDate"><br/>
                <label for="description"><fmt:message key="admin.create.course.description"/></label>
                <textarea required maxlength="255" class="form-control" name="description" id="description" placeholder="${course.description}"></textarea><br/>
                <label for="couches"><fmt:message key="admin.create.course.coaches"/></label>
                <select class="form-control" id="couches" name="coach">
                    <c:forEach items="${coaches}" var="entry">
                        <option value="${entry.idUser}">${entry.firstName} ${entry.lastName}</option>
                    </c:forEach>
                </select><br/>
                <button class="btn btn-info" type="submit">
                    <fmt:message key="admin.create.course.button"/></button>
            </form>
        </div>
    </div>
</div>