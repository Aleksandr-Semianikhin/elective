<%--
  
  User: Aleksandr Semianikhin
  Document: create_tag
  Date: 01.10.2018
  Time: 14:08
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<!DOCTYPE html>
<html>
<fmt:setBundle basename="resources"/>
<fmt:message key="admin.create.tag.title" var="pageTitle"/>
<jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>

<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <fmt:message key="admin.create.tag.title"/>
            </h4>
        </div>
        <div class="panel-body">
            <form class="form-group" action="admin" method="post">
                <input hidden="true" name="command" value="adminActionTag"/>
                <input hidden="true" name="crud" value="create"/>
                <label for="name"><c:out value="${error}"/></label>
                <label for="name"><fmt:message key="admin.create.tag.name"/></label>
                <input required minlength="2" maxlength="20" type="text" name="name" id="name" class="form-control">
                <button class="btn btn-info" type="submit">
                    <fmt:message key="admin.create.tag.button"/></button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/view/jsp/footer.jsp"/>