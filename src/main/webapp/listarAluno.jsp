<%@page import="aa.modelo.Aluno"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aa.Componentes.Constantes"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>

<%
	ArrayList<Aluno> alunos = (ArrayList<Aluno>)request.getAttribute("alunos");
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
            			<h2 class="title"><%=Constantes.ALUNOS %></h2><br>
            				<div class="inner">
             					<form  id="FrmListaAlunos" name="FrmListaAlunos" action="Aluno"  method="POST" class="form">
             					<%@include file="messagePage.jsp" %>
					               <table id="table-resultado" class="table">
					               
										<tr>
						                   <th><%=Constantes.NOME%></th>
						                   <th><%=Constantes.MATRICULA%></th>
						                   <th><%=Constantes.ANOINGRESSO%></th>
						                   <th><%=Constantes.SEXO%></th>
						                   <th><%=Constantes.CURSO%></th>
						                   <th><%=Constantes.CPF%></th>
						                   <th class="last">&nbsp;</th>
					                 	</tr>
					                 <% 	                
					            
					                for(int i=0; i < alunos.size(); i++) {
					                	String urlEditar = "Aluno?acao=" +Constantes.ACAO_EDITAR+"&matricula="+alunos.get(i).getMatricula();
					                	String urlDeletar = "Aluno?acao=" +Constantes.ACAO_DELETAR+"&matricula="+alunos.get(i).getMatricula();
					                  	if(i%2 == 0){ %>
					                  	<tr class="odd">
					                  	                      
					                        <td><%=alunos.get(i).getNome()%></td>
					                        <td><%=alunos.get(i).getMatricula()%></td>
					                        <td><%=alunos.get(i).getAnoIngresso()%></td>
					                        <td><%=alunos.get(i).getSexo()%></td>
					                        <td><%=alunos.get(i).getCurso().getSigla()%></td>
					                        <td><%=alunos.get(i).getCpf().getCPF()%></td>
					                        <td class="last"><a href="<%=urlEditar%>"><%=Constantes.EDITAR%></a>&nbsp;&nbsp;
					                        <a href="<%=urlDeletar%>"><%=Constantes.DELETE%></a> </td>	                        
					                    </tr>
					                <% } else { %>
					                    <tr class="even">
					                  	                     
					                        <td><%=alunos.get(i).getNome()%></td>
					                        <td><%=alunos.get(i).getMatricula()%></td>
					                        <td><%=alunos.get(i).getAnoIngresso()%></td>
					                        <td><%=alunos.get(i).getSexo()%></td>
					                        <td><%=alunos.get(i).getCurso().getSigla()%></td>
					                        <td><%=alunos.get(i).getCpf().getCPF()%></td>
					                        <td class="last"><a href="<%=urlEditar%>"><%=Constantes.EDITAR%></a>&nbsp;&nbsp;
					                        <a href="<%=urlDeletar%>"><%=Constantes.DELETE%></a> </td>                        
					                    </tr>                    
					            	<%}  
					           		}%>
					               </table>
					               <div class="actions-bar wat-cf">
                  <div class="actions">
                  	<button id="acao" name="acao" class="button" type="submit" value="<%=Constantes.NOVO%>">
                		<img src="Images/icons/tick.png" alt="<%=Constantes.NOVO%>"  /> <%=Constantes.NOVO%>
              		</button>
                    <!--<button  id="excluir-aluno" name="excluir-aluno" class="button" type="button">
                      <img src="Images/icons/cross.png" alt="delete" /> <%=Constantes.DELETE%>
                    </button>  -->
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