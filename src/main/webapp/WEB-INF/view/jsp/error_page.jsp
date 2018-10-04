<%--
  
  User: Aleksandr Semianikhin
  Document: error_page
  Date: 24.09.2018
  Time: 16:29
  
--%>
<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>

<html>

<c:set var="title" value="Error" scope="page" />
<%@ include file="/WEB-INF/view/jsp/header.jsp" %>

<body>
<div class="container-fluid">
    <div class="panel panel-default">
<table id="main-container">
    <tr >
        <td class="content">
            <h2 class="error">
                The following error occurred
            </h2>
            <%-- this way we get the error information (error 404)--%>
            <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
            <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>

            <%-- this way we get the exception --%>
            <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

            <c:if test="${not empty code}">
                <h3>Error code: ${code}</h3>
            </c:if>

            <c:if test="${not empty message}">
                <h3>Message: ${message}</h3>
            </c:if>

            <%-- if get this page using forward --%>
            <c:if test="${not empty errorMessage and empty exception and empty code}">
                <h3>Error message: ${errorMessage}</h3>
            </c:if>
        </td>
    </tr>
</table></div></div>
<%@ include file="/WEB-INF/view/jsp/footer.jsp"%>
</body>
</html>
