<%@page import="aa.modelo.Funcionario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aa.Componentes.Constantes"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%@include file="messagePage.jsp" %>
<%
	ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>)request.getAttribute("funcionarios");
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
    	<div id="wrapper" class="wat-cf">
      		<div id="main">
        		<div class="block" id="block-tables">
          			<div class="content">
            			<h2 class="title"><%=Constantes.FUNCIONARIO %></h2><br>
            				<div class="inner">
             					<form  id="FrmListaAlunos" name="FrmListaAlunos" action="Funcionario"  method="POST" class="form">
					               <table id="table-resultado" class="table">
										<tr>
						                   <th class="first"><input type="checkbox" id="check_todos" class="checkbox toggle" /></th>
						                   <th><%=Constantes.NOME%></th>
						                   <th><%=Constantes.MATRICULA%></th>
						                   <th><%=Constantes.ANOINGRESSO%></th>
						                   <th><%=Constantes.SEXO%></th>
						                   <th><%=Constantes.DEPTO%></th>
						                   <th><%=Constantes.CPF%></th>
						                   <th class="last">&nbsp;</th>
					                 	</tr>
					                 <% 	                
					            
					                for(int i=0; i < funcionarios.size(); i++) {
					                  	if(i%2 == 0){ %>
					                  	<tr class="odd">
					                  	    <td>
					                   			<input type="checkbox" class="checkbox" name="id" value=<%=funcionarios.get(i).getMatricula()%> />
					                   		</td>                   
					                        <td><%=funcionarios.get(i).getNome()%></td>
					                        <td><%=funcionarios.get(i).getMatricula()%></td>
					                        <td><%=funcionarios.get(i).getAnoIngresso()%></td>
					                        <td><%=funcionarios.get(i).getSexo()%></td>
					                        <td><%=funcionarios.get(i).getDepartamento().getSigla()%></td>
					                        <td><%=funcionarios.get(i).getCpf().getCPF()%></td>
					                        <td class="last"><a href="#"><%=Constantes.EDITAR%></a> </td>	                        
					                    </tr>
					                <% } else { %>
					                    <tr class="even">
					                  	    <td>
					                   			<input type="checkbox" class="checkbox" name="id" value=<%=funcionarios.get(i).getMatricula()%> />
					                   		</td>                   
					                         <td><%=funcionarios.get(i).getNome()%></td>
					                        <td><%=funcionarios.get(i).getMatricula()%></td>
					                        <td><%=funcionarios.get(i).getAnoIngresso()%></td>
					                        <td><%=funcionarios.get(i).getSexo()%></td>
					                        <td><%=funcionarios.get(i).getDepartamento()%></td>
					                        <td><%=funcionarios.get(i).getCpf()%></td>
					                        <td class="last"><a href="#"><%=Constantes.EDITAR%></a> </td>	                        
					                    </tr>                    
					            	<%}  
					           		}%>
					               </table>
					               <div class="actions-bar wat-cf">
                  <div class="actions">
                  	<button id="acao" name="acao" class="button" type="submit" value="<%=Constantes.NOVO%>">
                		<img src="Images/icons/tick.png" alt="<%=Constantes.NOVO%>"  /> <%=Constantes.NOVO%>
              		</button>
                    <button  id="excluir-aluno" name="excluir-aluno" class="button" type="button">
                      <img src="Images/icons/cross.png" alt="delete" /> <%=Constantes.DELETE%>
                    </button>
                  </div>
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