
<%@page import="aa.modelo.Turno"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%

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
                <li class="active"><a href="#block-text"><%=Constantes.REFEICAO%></a></li>
            </ul>
          </div>
          <div class="content">
            <h2 class="title"><%=Constantes.CADREFEICAO%> </h2>
            <div class="inner">
            <%@include file="messagePage.jsp" %>
            
              <form id="FrmRefeicao" name="FrmRefeicao" action="Refeicao" method="POST" class="form">
        	  <input type = "hidden" id="id" name = "id" <% //if (funcionario != null && funcionario.getMatricula() != null ) { out.print(" value = '" + funcionario.getMatricula() + "'"); } %>>
                <div class="group">
                  <label class="label"><%=Constantes.DESCRICAO%></label>
                  <input type="text" id="descricao" name="descricao" <% // if (funcionario != null && funcionario.getNome() != null ) { out.print(" value = '" + funcionario.getNome() + "'"); } %> class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.OPVEG%></label>
                   <input type="text" id="opVeg" name="opVeg"  size="10"<%// if (funcionario != null && funcionario.getMatricula() != null ) { out.print(" value = '" + funcionario.getMatricula() + "'"); } %> class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.TURNO%></label>
                    <select id ="turno" name="turno">
                        <option value="">Selecione</option>
                        <option value="<%=Turno.MANHA%>"><%=Constantes.MANHA%></option>
                        <option value="<%=Turno.TARDE%>"><%=Constantes.TARDE%></option>
                        <option value="<%=Turno.NOITE%>"><%=Constantes.NOITE%></option>
                    </select>           
                </div>
                <div class="group navform wat-cf">
               <%//if(refeicao == null) {%>
                  <button class="button" type="submit" id='acao' name="acao" value="<%=Constantes.SALVAR%>"><%=Constantes.SALVAR%></button>
                <%//}else{%>
                <!--   <button class="button" type="submit" id='acao' name="acao" value="<%=Constantes.ACAO_EDITAR%>"><%=Constantes.SALVAR%></button>-->
                <%//} %>
                  <span class="text_button_padding">Ou</span>
                  <a class="text_button_padding link_button" href="Funcionario"><%=Constantes.CANCELAR%></a>
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