<%--
  
  User: Aleksandr Semianikhin
  Document: authentication_error
  Date: 27.09.2018
  Time: 14:14
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<!DOCTYPE html>
<html>
<fmt:setBundle basename="strings"/>
<fmt:message key="authentification.error" var="pageTitle"/>
<jsp:include flush="true" page="header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>
<a href="login"><fmt:message key="authentification.go_to_login"/></a>
</body>
<jsp:include page="/WEB-INF/view/jsp/footer.jsp"/>
</html>