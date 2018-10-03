<%--
  
  User: Aleksandr Semianikhin
  Document: set_mark
  Date: 02.10.2018
  Time: 20:04
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<!DOCTYPE html>
<html>

<fmt:setBundle basename="resources"/>
<fmt:message key="set.mark.title" var="pageTitle"/>
<jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title form-inline">
                <fmt:message key="set.mark.table.one.title"/>
            </h4>
        </div>
        <div class="panel-body">
            <form class="form-group" action="coach" method="post">
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="set.mark.table.studentName"/></th>
                    <th><fmt:message key="set.mark.table.mark"/></th>
                </tr>
                </thead>
                <tbody>
                <c:set var="count" value="0" scope="page" />
                <c:forEach items="${entries}" var="entry">
                    <c:set var="count" value="${count+1}" scope="page"/>
                    <tr>
                        <td><input hidden="true" name="ids" value="${entry.idRegister}"/></td>
                        <td><c:out value="${count}"/></td>
                        <td><c:out value="${entry.user.firstName} ${entry.user.lastName}"/></td>
                        <td><input type="number" name="marks" min="2" max="10"value="${entry.averageMark}"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
                <input hidden="true" name="command" value="setMark">
                <button class="btn btn-info" type="submit">
                    <fmt:message key="coach.set.mark.button"/></button>
            </form>
            <a class="btn btn-info" type="submit" href = ${pageContext.request.contextPath}/coach?command=mainCoachPanel>
                <fmt:message key="coach.users.back.button"/></a>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/view/jsp/footer.jsp"/>