<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<%@page import="modelo.Constantes"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	String mensagem = request.getAttribute("mensagem") == null ? "" : (String)request.getAttribute("mensagem");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title><%=Constantes.TITLE%></title>
  <link rel="stylesheet" href="Stylesheets/base.css" type="text/css" media="screen" />
  <link rel="stylesheet" id="current-theme" href="Stylesheets/themes/default/style.css" type="text/css" media="screen" />
  <%=Constantes.SCRIPT_JQUERY%>
  <script type="text/javascript" charset="utf-8">
    // <![CDATA[
    $(document).ready(function() {
		alert("Funcionou");
    });
    // ]]>
  </script>
</head>
<body>
  <div id="container">
    <div id="header">
      <h1><a href="index.jsp"><%=Constantes.NOME_SISTEMA%></a></h1>
      <div id="user-navigation">

      </div>
      <div id="main-navigation">

      </div>
    </div>
    <div id="box">
      <div class="block" id="block-login">
        <h2>Acesso ao Sistema</h2>
        <div class="content login">
         	<%=mensagem%>
          <form id="frmLogin" name="" action="login" method="POST" class="form login">
            <div class="group wat-cf">
              <div class="left">
                <label class="label right">Login</label>
              </div>
              <div class="right">
                <input id="login" name="login" type="text" class="text_field" />
              </div>
            </div>
            <div class="group wat-cf">
              <div class="left">
                <label class="label right">Senha</label>
              </div>
              <div class="right">
                <input id="senha" name="senha" type="password" class="text_field" />
              </div>
            </div>
            <div class="group navform wat-cf">
              <div class="right">
                <button class="button" type="submit">
                  <img src="Images/icons/key.png" alt="logar" /> Login
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div> 
</body>
</html>