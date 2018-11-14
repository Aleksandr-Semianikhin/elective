<%--
  
  User: Aleksandr Semianikhin
  Document: admin_page
  Date: 26.09.2018
  Time: 17:48
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="resources"/>
    <fmt:message key="admin.title" var="pageTitle"/>
    <jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
    <div class="panel-group" id="accordion">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                        <fmt:message key="admin.table.one"/></a>
                </h4>
            </div>
            <div id="collapse1" class="panel-collapse collapse in">
                <div class="panel-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="admin.table.one.firstName"/> </th>
                            <th><fmt:message key="admin.table.one.lastName"/> </th>
                            <th><fmt:message key="admin.table.one.courses"/> </th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:set var="count" value="0" scope="page" />
                        <c:forEach items="${coaches}" var="entry">
                            <tr>
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <td><c:out value="${count}"/> </td>
                                <c:set var="coach" value="${entry.key}"/>
                                <td><c:out value="${coach.firstName}"/></td>
                                <td><c:out value="${coach.lastName}"/></td>
                                <td>${entry.value}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <a href="${pageContext.request.contextPath}/admin?command=adminActionCoach&crud=page" class="btn btn-info" role="button">
                        <fmt:message key="admin.table.one.button.create"/>
                    </a>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title form-inline">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                        <fmt:message key="admin.table.two"/> </a>
                </h4>
            </div>
            <div id="collapse2" class="panel-collapse collapse">
                <div class="panel-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="admin.table.two.courseName"/> </th>
                            <th><fmt:message key="admin.table.two.tag"/></th>
                            <th><fmt:message key="admin.table.two.description"/></th>
                            <th><fmt:message key="admin.table.two.startDate"/></th>
                            <th><fmt:message key="admin.table.two.endDate"/></th>
                            <th><fmt:message key="admin.table.two.numberStudents"/></th>
                            <th colspan="2"><fmt:message key="admin.table.two.action"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="0" scope="page" />
                            <c:forEach items="${courses}" var="entry">
                                <c:set var="x" value="${entry.statusId}"/>
                                <c:if test="${entry.statusId eq 0}" var="var1" scope="page"><tr class="success"></c:if>
                                <c:if test="${x eq 1}" var="var2" scope="page"><tr class="warning"></c:if>
                                <c:if test="${x eq 2}" var="var3" scope="page"><tr class="danger"></c:if>
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                    <td><c:out value="${count}"/> </td>
                                    <td><c:out value="${entry.courseName}"/></td>
                                    <c:set var="tag" value="${entry.tag}"/>
                                    <td><c:out value="${tag.name}"/> </td>
                                    <td><c:out value="${entry.description}"/> </td>
                                    <td><c:out value="${entry.startDate}"/> </td>
                                    <td><c:out value="${entry.endDate}"/> </td>
                                    <td><c:out value="${entry.countStudents}"/> </td>
                                    <td>
                                        <form action="admin" method="get">
                                            <input hidden name="command" value="adminActionCourse">
                                            <input hidden name="crud" value="page">
                                            <input hidden name="course" value="${entry.idCourse}">
                                            <button class="button bubble-legend-symbol" type="submit">
                                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                            </button></form>
                                    </td>
                                    <td>
                                        <form action="admin" method="post">
                                            <input hidden name="command" value="adminActionCourse">
                                            <input hidden name="crud" value="delete">
                                            <input hidden name="course" value="${entry.idCourse}">
                                            <button class="button bubble-legend-symbol" type="submit">
                                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                            </button></form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <a href="${pageContext.request.contextPath}/admin?command=adminActionCourse&crud=createPage" class="btn btn-info" role="button">
                        <fmt:message key="admin.table.two.button.create"/>
                    </a>
                    <div class="alert alert-success" role="alert"><fmt:message key="admin.table.two.opened" /></div>
                    <div class="alert alert-warning" role="alert"><fmt:message key="admin.table.two.started" /></div>
                    <div class="alert alert-danger" role="alert"><fmt:message key="admin.table.two.ended" /></div>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
                        <fmt:message key="admin.table.three"/> </a>
                </h4>
            </div>
            <div id="collapse3" class="panel-collapse collapse">
                <div class="panel-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="admin.table.three.firstName"/> </th>
                            <th><fmt:message key="admin.table.three.lastName"/></th>
                            <th><fmt:message key="admin.table.three.blocked"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="0" scope="page" />
                        <c:forEach items="${students}" var="entry">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <td><c:out value="${count}"/> </td>
                                <td><c:out value="${entry.firstName}"/> </td>
                                <td><c:out value="${entry.lastName}"/> </td>
                                <c:choose>
                                    <c:when test="${entry.blocked}">
                                        <td>
                                            <form action="admin" method="post">
                                                <input hidden name="command" value="blockedStudent">
                                                <input hidden name="state" value="false">
                                                <input hidden name="student" value="${entry.idUser}">
                                                <button class="button bubble-legend-symbol" type="submit">
                                                    <span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
                                                </button></form>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <form action="admin" method="post">
                                                <input hidden name="command" value="blockedStudent">
                                                <input hidden name="state" value="true">
                                                <input hidden name="student" value="${entry.idUser}">
                                                 <button class="button bubble-legend-symbol" type="submit">
                                                    <span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
                                                </button></form>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">
                        <fmt:message key="admin.table.four"/> </a>
                </h4>
            </div>
            <div id="collapse4" class="panel-collapse collapse">
                <div class="panel-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="admin.table.four.tagName"/> </th>
                            <th colspan="2"><fmt:message key="admin.table.four.action"/> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="0" scope="page" />
                        <c:forEach items="${tags}" var="entry">
                            <c:set var="count" value="${count+1}" scope="page"/>
                            <tr>
                                <td><c:out value="${count}"/></td>
                                <td><c:out value="${entry.name}"/></td>
                                <td>
                                    <form action="admin" method="get">
                                        <input hidden name="command" value="adminActionTag">
                                        <input hidden name="crud" value="editPage">
                                        <input hidden name="tag" value="${entry.idTag}">
                                        <button class="button bubble-legend-symbol" type="submit">
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        </button></form>
                                </td>
                                <td>
                                    <form action="admin" method="get">
                                        <input hidden name="command" value="adminActionTag">
                                        <input hidden name="crud" value="delete">
                                        <input hidden name="tag" value="${entry.idTag}">
                                        <button class="button bubble-legend-symbol" type="submit">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </button></form>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <a href="${pageContext.request.contextPath}/admin?command=adminActionTag&crud=createPage" class="btn btn-info" role="button">
                        <fmt:message key="admin.table.four.button.create"/>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/view/jsp/footer.jsp"/>

