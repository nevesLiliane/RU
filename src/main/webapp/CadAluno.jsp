<%@page import="aa.modelo.Aluno"%>
<%@page import="aa.modelo.Curso"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	ArrayList<Curso> cursos = (ArrayList<Curso>)request.getAttribute("cursos");
	Aluno aluno = (Aluno)request.getAttribute("aluno");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title><%=Constantes.RU%></title>
  <%=Constantes.BASE_CSS%>
  <%=Constantes.ESTILO_CSS%>
  <%=Constantes.JQUERY_LINK%>
  <script type='text/javascript' charset='utf-8' src='resources/Javascripts/jsAlunos.js'></script>
  <script type="text/javascript" charset="utf-8">
  </script>
  </script>
</head>
<div id="container">
	<%@include file="menu.jspf" %>
    <div id="wrapper" class="wat-cf">
      <div id="main">
        <div class="block" id="block-forms">
          <div class="secondary-navigation">
            <ul class="wat-cf">
                <li class="active"><a href="#block-text"><%=Constantes.ALUNOS%></a></li>
            </ul>
          </div>
          <div class="content">
            <h2 class="title"><%=Constantes.CADALUNOS%> </h2>
            <div class="inner">
            <%@include file="messagePage.jsp" %>
            
              <form id="Frmpergunta" name="FrmAluno" action="Aluno" method="POST" class="form">
        	  <input type = "hidden" id="matriculaHidden" name = "matriculaHidden" <% if (aluno != null && aluno.getMatricula() != null ) { out.print(" value = '" + aluno.getMatricula() + "'"); } %>>
                <div class="group">
                  <label class="label"><%=Constantes.NOME%></label>
                  <input type="text" id="nome" name="nome" <%  if (aluno != null && aluno.getNome() != null ) { out.print(" value = '" + aluno.getNome() + "'"); } %> class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.MATRICULA%></label>
                   <input type="text" id="matricula" name="matricula"  size="10"<% if (aluno != null && aluno.getMatricula() != null ) { out.print(" value = '" + aluno.getMatricula() + "'"); } %> class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.ANOINGRESSO%></label>
                  	<input type="text"  id="anoIngresso" name="anoIngresso" size="10" <%if (aluno != null && aluno.getAnoIngresso() != null ) { out.print(" value = '" + aluno.getAnoIngresso() + "'"); } %> class="text_field" />
                </div> 
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.SEXO%></label>
                    <input type="radio" id="sexo" name="sexo" value="M"> &nbsp;<%=Constantes.MASCULINO%> &nbsp;&nbsp;
                    <input type="radio" id="sexo" name="sexo" value="F"> &nbsp;<%=Constantes.FEMININO%>
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.TITULO%></label>
                    <select id ="titulo" name="titulo">
                        <option value="">Selecione</option>
                        <option value="ESPECIALIZACAO"><%=Constantes.ESPECIALIZACAO%></option>
                        <option value="MESTRADO"><%=Constantes.MESTRADO%></option>
                        <option value="DOUTORADO"><%=Constantes.DOUTORADO%></option>
                    </select>           
                </div>
                
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.CPF%></label>
                    <input type="text"  id="cpf" name="cpf" <% if (aluno != null && aluno.getCpf().getCPF() != null ) { out.print(" value = '" + aluno.getCpf().getCPF() + "'");} %> class="text_field" onblur="valida(this)"/>     
             	</div>
     	         <div class="group">
                    <label class="label" for="post_title"><%=Constantes.CURSO%></label>
                    <select id ="Curso" name="Curso">
                    <% for(Curso curso : cursos){ %>
                        <option value="<%=curso.getId() %>" <% if(aluno!=null && (curso.getId().equals(aluno.getCurso().getId()))){ out.print(" selected");} %>><%=curso.getNome()%></option>
                    <% } %>                        
                    </select>
                </div>
                <div class="group navform wat-cf">
               <%if(aluno == null) {%>
                  <button class="button" type="submit" id='acao' name="acao" value="<%=Constantes.SALVAR%>"><%=Constantes.SALVAR%></button>
                <%}else{%>
                 <button class="button" type="submit" id='acao' name="acao" value="<%=Constantes.ACAO_EDITAR%>"><%=Constantes.EDITAR%></button>
                <%} %>
                  <span class="text_button_padding">Ou</span>
                  <a class="text_button_padding link_button" href="Aluno"><%=Constantes.CANCELAR%></a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
</div>
</body>
</html>