<%--
  
  User: Aleksandr Semianikhin
  Document: student_panel
  Date: 29.09.2018
  Time: 13:45
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<!DOCTYPE html>
<html>
<fmt:setBundle basename="resources"/>
<fmt:message key="student.panel.title" var="pageTitle"/>
<jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>
<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                    <fmt:message key="student.table.one.title"/></a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse in">
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="student.table.one.name"/></th>
                            <th><fmt:message key="student.table.one.description"/></th>
                            <th><fmt:message key="student.table.one.coachName"/></th>
                            <th><fmt:message key="student.table.one.startDate"/></th>
                            <th><fmt:message key="student.table.one.action"/> </th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:set var="count" value="0" scope="page" />
                    <c:forEach items="${coursesAvailable}" var="entry">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr>
                            <td><c:out value="${count}"/></td>
                            <td><c:out value="${entry.courseName}"/></td>
                            <td><c:out value="${entry.description}"/></td>
                            <c:set var="coach" value="${entry.courseCoach}"/>
                            <td>
                                <c:out value="${coach.firstName}"/>
                                <c:out value=" "/>
                                <c:out value="${coach.lastName}"/>
                            </td>
                            <td><c:out value="${entry.startDate}"/></td>
                            <td><form action="student" method="post">
                                <input hidden="true" value="registerToCourse" name ="command">
                                <input hidden="true" value="${entry.idCourse}" name ="course">
                                <button type="submit"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
                               </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                    <fmt:message key="student.table.two.title"/></a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse in">
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="student.table.two.name"/></th>
                        <th><fmt:message key="student.table.two.description"/></th>
                        <th><fmt:message key="student.table.two.coachName"/></th>
                        <th><fmt:message key="student.table.two.startDate"/></th>
                        <th><fmt:message key="student.table.two.action"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="count" value="0" scope="page" />
                    <c:forEach items="${coursesOpened}" var="entry">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr>
                            <td><c:out value="${count}"/></td>
                            <td><c:out value="${entry.courseName}"/></td>
                            <td><c:out value="${entry.description}"/></td>
                            <c:set var="coach" value="${entry.courseCoach}"/>
                            <td><c:out value="${coach.firstName} ${coach.lastName}"/></td>
                            <td><c:out value="${entry.startDate}"/></td>
                            <td><form action="student" method="post">
                                <input hidden="true" value="unregisterFromCourse" name ="command">
                                <input hidden="true" value="${entry.idCourse}" name ="course">
                                <button type="submit"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                            </form></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
                    <fmt:message key="student.table.three.title"/></a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse in">
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="student.table.two.name"/></th>
                        <th><fmt:message key="student.table.two.description"/></th>
                        <th><fmt:message key="student.table.two.coachName"/></th>
                        <th><fmt:message key="student.table.two.endDate"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="count" value="0" scope="page" />
                    <c:forEach items="${coursesStarted}" var="entry">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr>
                            <td><c:out value="${count}"/></td>
                            <td><c:out value="${entry.courseName}"/></td>
                            <td><c:out value="${entry.description}"/></td>
                            <c:set var="coach" value="${entry.courseCoach}"/>
                            <td><c:out value="${coach.firstName} ${coach.lastName}"/></td>
                            <td><c:out value="${entry.endDate}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">
                    <fmt:message key="student.table.four.title"/></a>
            </h4>
        </div>
        <div id="collapse4" class="panel-collapse collapse in">
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="student.table.four.name"/></th>
                        <th><fmt:message key="student.table.four.coachName"/></th>
                        <th><fmt:message key="student.table.four.endDate"/></th>
                        <th><fmt:message key="student.table.four.mark"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="count" value="0" scope="page" />
                    <c:forEach items="${coursesEnded}" var="entry">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr>
                            <td><c:out value="${count}"/></td>
                            <c:set var="course" value="${entry.course}"/>
                            <td><c:out value="${course.courseName}"/></td>
                            <c:set var="coach" value="${course.courseCoach}"/>
                            <td><c:out value="${coach.firstName} ${coach.lastName}"/></td>
                            <td><c:out value="${course.endDate}"/></td>
                            <td><c:out value="${entry.averageMark}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/jsp/footer.jsp"/>