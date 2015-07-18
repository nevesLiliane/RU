<%@page import="aa.Componentes.Constantes"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title><%=Constantes.RU%></title>
  <%=Constantes.JQUERY_LINK%>
  <%=Constantes.ESTILO_CSS%>
  <%=Constantes.BASE_CSS%>
</head>
<body>
  <div id="container">
  <%@include file="menu.jspf" %>
    <div id="header">
      <div id="main-navigation">
    </div>
    <div id="wrapper" class="wat-cf">
      <div id="main">
        <div class="block" id="block-tables">
          <div class="content">
            <h2 class="title"><%=Constantes.BEMVINDO %></h2><br>
          </div>
        </div>
      </div>
    </div>
</div>
</body>
</html>