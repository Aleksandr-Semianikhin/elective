<%--
  
  User: Aleksandr Semianikhin
  Document: login
  Date: 24.09.2018
  Time: 12:05
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<!DOCTYPE html>
<html>

        <fmt:setBundle basename="resources"/>
        <fmt:message key="login.title" var="pageTitle"/>
        <jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include>
        <div class="container-fluid">
            <div class="card card-container">
                <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"/>
                <p id="profile-name" class="profile-name-card"></p>
                <form action="index" method="POST" class="form-signin">
                    <span id="reauth-email" class="reauth-email"></span>
                    <input hidden="true" name="command" value="login"/>
                    <label for="login"><c:out value="${error}"/> </label>
                    <input type="text" id="login" name="login" class="form-control" placeholder="<fmt:message key="login.login"/>" required="true" autofocus>
                    <input type="password" id="password" name="password" class="form-control" placeholder="<fmt:message key="login.password"/>" required="true">
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">
                        <fmt:message key="login.button.signin"/></button>
                </form>
            </div>
        </div>
<jsp:include page="footer.jsp"/>
