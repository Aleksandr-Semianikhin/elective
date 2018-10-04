<%--
  
  User: Aleksandr Semianikhin
  Document: coach_panel
  Date: 29.09.2018
  Time: 17:36
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<!DOCTYPE html>
<html>
<fmt:setBundle basename="resources"/>
<fmt:message key="coach.title" var="pageTitle"/>
<jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title form-inline">
                <fmt:message key="coach.table.title"/>
            </h4>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="coach.table.courseName"/></th>
                    <th><fmt:message key="coach.table.startDate"/></th>
                    <th><fmt:message key="coach.table.endDate"/></th>
                    <th><fmt:message key="coach.table.numberStudents"/></th>
                    <th><fmt:message key="coach.table.action"/></th>
                </tr>
                </thead>
                <tbody>
                <c:set var="count" value="0" scope="page" />
                <c:forEach items="${courses}" var="entry">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <c:if test="${entry.statusId eq 0}"><tr class="success"></c:if>
                    <c:if test="${entry.statusId eq 1}"><tr class="warning"></c:if>
                    <c:if test="${entry.statusId eq 2}"><tr class="danger"></c:if>
                    <td><c:out value="${count}"/></td>
                    <td><c:out value="${entry.courseName}"/></td>
                    <td><c:out value="${entry.startDate}"/></td>
                    <td><c:out value="${entry.endDate}"/></td>
                    <td><c:out value="${entry.countStudents}"/></td>
                    <c:choose>
                        <c:when test="${entry.statusId eq 2}">
                            <td>
                                <a href="${pageContext.request.contextPath}/coach?command=setMarkPage&course=<c:out value="${entry.idCourse}"/>">
                                    <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
                                </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <a href="${pageContext.request.contextPath}/coach?command=getCourseUsers&course=<c:out value="${entry.idCourse}"/>">
                                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                </a>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/view/jsp/footer.jsp"/>
