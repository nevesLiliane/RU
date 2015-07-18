package aa.Controles;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.modelo.Departamento;

@WebServlet("/Departamento")
public class DepartamentoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String acao = (String)request.getParameter("acao");
		
		if(acao==null){
			request.setAttribute("departamentos", new Departamento().list());
			request.getRequestDispatcher("listarDepartamento.jsp").forward(request, response);
		}
	
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req, resp);
	}
	
	
	

}
