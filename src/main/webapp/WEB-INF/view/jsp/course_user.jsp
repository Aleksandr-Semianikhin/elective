<%--
  
  User: Aleksandr Semianikhin
  Document: course_user
  Date: 02.10.2018
  Time: 21:35
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<!DOCTYPE html>
<html>

<fmt:setBundle basename="resources"/>
<fmt:message key="coach.course.users.title" var="pageTitle"/>
<jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title form-inline">
                <fmt:message key="coach.course.users.title"/>
            </h4>
        </div>
        <div class="panel-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="set.mark.table.studentName"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="count" value="0" scope="page" />
                    <c:forEach items="${entries}" var="entry">
                        <c:set var="count" value="${count+1}" scope="page"/>
                        <tr>
                            <td><c:out value="${count}"/></td>
                            <td><c:out value="${entry.user.firstName} ${entry.user.lastName}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <input hidden="true" name="command" value="setMark">
                <a class="btn btn-info" type="submit" href = ${pageContext.request.contextPath}/coach?command=mainCoachPanel>
                    <fmt:message key="coach.users.back.button"/></a>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/view/jsp/footer.jsp"/>