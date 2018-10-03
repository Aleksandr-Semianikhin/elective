<%--
  
  User: Aleksandr Semianikhin
  Document: index.jsp
  Date: 27.09.2018
  Time: 11:31
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<!DOCTYPE html>
<html>

<fmt:setBundle basename="resources"/>
<fmt:message key="index.title" var="pageTitle"/>
<jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>
    <div class="container-fluid">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title form-inline">
                    <fmt:message key="index.table.one.title"/>
                </h4>
            </div>
            <div class="panel-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="index.table.one.courseName"/>
                        <a href="${pageContext.request.contextPath}/index?sort=sortByName">
                            <span class="glyphicon glyphicon-sort" aria-hidden="true"></span></a></th>
                        <th><fmt:message key="index.table.one.tag"/></th>
                        <th><fmt:message key="index.table.one.courseCoach"/></th>
                        <th><fmt:message key="index.table.one.startDate"/></th>
                        <th><fmt:message key="index.table.one.days" />
                            <a href="${pageContext.request.contextPath}/index?sort=sortByDays">
                                <span class="glyphicon glyphicon-sort" aria-hidden="true"></span></a></th>
                        <th><fmt:message key="index.table.one.numberStudents" />
                            <a href="${pageContext.request.contextPath}/index?sort=sortByNumbers">
                                <span class="glyphicon glyphicon-sort" aria-hidden="true"></span></a></th>
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
                            <td><a href="${pageContext.request.contextPath}/index?command=chooseByTag&tagId=${tag.idTag}">
                                <c:out value="${tag.name}" /></a></td>
                            <td><a href="${pageContext.request.contextPath}/index?command=chooseByCoach&coachId=${course.courseCoach.idUser}">
                                <c:out value="${course.courseCoach.firstName} ${course.courseCoach.lastName}"/></a></td>
                            <td><c:out value="${course.startDate}"/></td>
                            <td><c:out value="${course.days}"/></td>
                            <td><c:out value="${course.countStudents}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


<my:footer/>

