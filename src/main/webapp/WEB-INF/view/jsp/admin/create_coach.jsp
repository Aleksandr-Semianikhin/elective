<%--
  
  User: Aleksandr Semianikhin
  Document: create_coach
  Date: 30.09.2018
  Time: 15:25
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<!DOCTYPE html>
<html>
<fmt:setBundle basename="resources"/>
<fmt:message key="admin.create.coach.title" var="pageTitle"/>
<jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>
<div class="container-fluid">
         <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <fmt:message key="admin.create.coach.title"/>
                </h4>
            </div>
            <div class="panel-body">
                <form class="form-group" action="admin" method="post">
                    <input hidden="true" name="command" value="adminActionCoach"/>
                    <input hidden="true" name="crud" value="create"/>
                    <label for="login"><c:out value="${error}"/></label>
                    <label for="login"><fmt:message key="admin.create.coach.login"/></label>
                    <input type="text" class="form-control" id="login" name="login" required
                           minlength="6" maxlength="15" pattern="[a-zA-Z0-9]{6,15}">
                    <label for="password"><fmt:message key="admin.create.coach.password"/></label>
                    <input type="password" class="form-control" id="password" name="password" required
                           minlength="8" maxlength="20">
                    <label for="firstName"><fmt:message key="admin.create.coach.firstName"/></label>
                    <input type="text" class="form-control" id="firstName" name="firstName" required minlength="2">
                    <label for="lastName"><fmt:message key="admin.create.coach.lastName"/></label>
                    <input type="text" class="form-control" id="lastName" name="lastName" required minlength="2"><br/>
                    <button class="btn btn-info" type="submit">
                        <fmt:message key="admin.create.coach.button"/></button>
                </form>
            </div>
        </div>
</div>


<jsp:include page="/WEB-INF/view/jsp/footer.jsp"/>