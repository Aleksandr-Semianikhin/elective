<%--
  
  User: Aleksandr Semianikhin
  Document: index_tag
  Date: 02.10.2018
  Time: 18:23
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<!DOCTYPE html>
<html>

<fmt:setBundle basename="resources"/>
<fmt:message key="index.tag.title" var="pageTitle"/>
<jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title form-inline">
                <fmt:message key="index.tag.table.one.title"/>
            </h4>
        </div>
        <div class="panel-body">
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="index.table.one.courseName"/></th>
                    <th><fmt:message key="index.table.one.tag"/></th>
                    <th><fmt:message key="index.table.one.courseCoach"/></th>
                    <th><fmt:message key="index.table.one.startDate"/></th>
                    <th><fmt:message key="index.table.one.days" /></th>
                    <th><fmt:message key="index.table.one.numberStudents" /></th>
                </tr>
                </thead>
                <tbody>
                <c:set var="count" value="0" scope="page" />
                <c:forEach items="${courses}" var="course">
                    <c:set var="count" value="${count+1}" scope="page"/>
                    <tr>
                        <td><c:out value="${count}"/></td>
                        <td><c:out value="${course.courseName}"/></td>
                        <c:set var="tag" value="${course.tag}"/>
                        <td><c:out value="${tag.name}" /></td>
                        <td><c:out value="${course.courseCoach.firstName} ${course.courseCoach.lastName}"/></td>
                        <td><c:out value="${course.startDate}"/></td>
                        <td><c:out value="${course.days}"/></td>
                        <td><c:out value="${course.countStudents}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <a type="button" class="btn btn-default" aria-label="Left Align" href="${pageContext.request.contextPath}/index">
            <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
        </a>
    </div>
</div>