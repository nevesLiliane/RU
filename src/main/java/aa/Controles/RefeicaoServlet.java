package aa.Controles;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.modelo.Refeicao;

@WebServlet("/Refeicao")
public class RefeicaoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String acao = (String)request.getParameter("acao");
		
		if(acao==null){
			request.setAttribute("refeicoes", new Refeicao().list());
			request.getRequestDispatcher("listarRefeicao.jsp").forward(request, response);
		}
	
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req, resp);
	}

	
}
