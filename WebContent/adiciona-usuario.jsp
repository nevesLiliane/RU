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

    });
    // ]]>
  </script>
</head>
<body>
  <div id="container">
    <div id="header">
      <h1><a href="index.jsp"><%=Constantes.NOME_SISTEMA%></a></h1>
      <div id="user-navigation">
        <ul class="wat-cf">
          <li><a href="#">Configuração</a></li>
          <li><a class="logout" href="#">Sair</a></li>
        </ul>
      </div>
      <div id="main-navigation">
        <ul class="wat-cf">
          <li class="active"><a href="index.jsp">Index</a></li>
          <li><a href="adiciona-usuario.jsp">Cadastrar Usuário</a></li>
        </ul>
      </div>
    </div>
    <div id="box">
      <div class="block" id="block-signup">
        <h2>Cadastro de Login</h2>
        <div class="content">
        <form id="frmAdicionaUsuario" name="" action="adicionaUsuario" method="POST" class="form">
            <div class="group wat-cf">
            <%=mensagem%>
              <div class="left">
                <label class="label">Nome</label>
              </div>
              <div class="right">
              	<input type="text" id="nome" name="nome" class="text_field" />
              </div>
            </div>
            <div class="group wat-cf">
              <div class="left">
                <label class="label">Login</label>
              </div>
              <div class="right">
                <input type="text" id="login" name="login" class="text_field" />
                <span class="description">O login deve conter no mínimo 5 dígitos</span>
              </div>
            </div>
            <div class="group wat-cf">
              <div class="left">
                <label class="label">Senha</label>
              </div>
              <div class="right">
                <input type="password" id="senha" name="senha" class="text_field" />
                <span class="description">A senha deve conter no mínimo 8 dígitos</span>
              </div>
            </div>
            <div class="group wat-cf">
              <div class="left">
                <label class="label">Perfil</label>
              </div>
              <div class="right">
                <select id="perfil" name="perfil">
                	<option value="USU">Usuário</option>
                	<option value="ADM">Administrador</option>
                </select>
              </div>
            </div>
            <div class="group navform wat-cf">
              <button class="button" type="submit">
                <img src="Images/icons/tick.png" alt="Salvar" /> Salvar
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div> 
</body>
</html>