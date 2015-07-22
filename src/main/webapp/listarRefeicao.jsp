
<%@page import="aa.modelo.Refeicao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aa.Componentes.Constantes"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%@include file="messagePage.jsp" %>
<%
	ArrayList<Refeicao> refeicoes = (ArrayList<Refeicao>)request.getAttribute("refeicoes");

%>

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
            <h2 class="title"><%=Constantes.DEPTO %></h2><br>
            
             <div class="inner">
                                <form  id="FrmListaAlunos" name="FrmListaAlunos" action="Refeicao"  method="POST" class="form">
                <table id="table-resultado" class="table">
                  <tr>
                    <th class="first"><input type="checkbox" id="check_todos" class="checkbox toggle" /></th>
                    <th><%=Constantes.TURNO%></th>
                    <th><%=Constantes.DESCRICAO%></th>
                    <th><%=Constantes.OPVEG%></th>
                    <th class="last">&nbsp;</th>
                  </tr>
                  <% 	                
	                for(int i=0; i < refeicoes.size(); i++) {
	                  	if(i%2 == 0){ %>
	                  	<tr class="odd">
	                  	    <td>
                    			<input type="checkbox" class="checkbox" name="id" value=<%=refeicoes.get(i).getId()%> />
                    		</td> 
                    		<td><%=refeicoes.get(i).getTurno().toString()%></td>                  
	                        <td><%=refeicoes.get(i).getDescricao()%></td>
	                        <td><%=refeicoes.get(i).getOpVeg()%></td>
	                        <td class="last"><a href="#"><%=Constantes.EDITAR%></a> </td>	                        
	                    </tr>
	                <% } else { %>
	                    <tr class="even">
	                  	    <td>
                    			<input type="checkbox" class="checkbox" name="id" value=<%=refeicoes.get(i).getId()%> />
                    		</td>                   
	                        <td><%=refeicoes.get(i).getTurno().toString()%></td>                  
	                        <td><%=refeicoes.get(i).getDescricao()%></td>
	                        <td><%=refeicoes.get(i).getOpVeg()%></td>
	                        <td class="last"><a href="#"><%=Constantes.EDITAR%></a> </td>	                        
	                    </tr>                    
	            	<%}  
	           		}%>
                </table>
                <div class="actions-bar wat-cf">
                  <div class="actions">
                  	<button id="novo-aluno" name="novo-aluno" class="button" type="button">
                		<img src="Images/icons/tick.png" alt="<%=Constantes.NOVO%>" /> <%=Constantes.NOVO%>
              		</button>
                    <button  id="excluir-aluno" name="excluir-aluno" class="button" type="button">
                      <img src="Images/icons/cross.png" alt="delete" /> <%=Constantes.DELETE%>
                    </button>
                  </div>
                  <!--<div class="pagination">
                    <span class="disabled prev_page">« Previous</span><span class="current">1</span><a rel="next" href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">7</a><a href="#">8</a><a href="#">9</a><a href="#">10</a><a href="#">11</a><a rel="next" class="next_page" href="#">Next »</a>
                  </div>-->
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