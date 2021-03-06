package aa.Controles;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import jdk.nashorn.internal.ir.RuntimeNode.Request;
import aa.Componentes.Constantes;
import aa.modelo.Refeicao;
import aa.modelo.Turno;

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
		}else{
			if(acao.equals(Constantes.NOVO)){
				request.getRequestDispatcher("CadRefeicao.jsp").forward(request, response);
			}else if(acao.equals(Constantes.SALVAR)){
				salvar(request, response);
				//request.getRequestDispatcher("CadRefeicao.jsp").forward(request, response);
			}else if(acao.equals(Constantes.ACAO_EDITAR)){
				editar(request, response);
			}
		}
	
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String acao = (String)req.getParameter("acao");
		
		if(acao==null){
			req.setAttribute("refeicoes", new Refeicao().list());
			req.getRequestDispatcher("listarRefeicao.jsp").forward(req, resp);
		}else if (acao.equals(Constantes.ACAO_EDITAR)){
			String id =(String)req.getParameter("id");
			if(id ==null){
				req.setAttribute("mensagem", "Erro, id vazio");
			}
			Refeicao refeicao = new Refeicao();
			refeicao = refeicao.get(id);
			req.setAttribute("refeicao", refeicao);
			req.getRequestDispatcher("CadRefeicao.jsp").forward(req, resp);
		}else if (acao.equals(Constantes.ACAO_DELETAR)){
			try {
				excluir(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	protected void salvar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String descricao = (String)req.getParameter("descricao");
		String opVeg = (String)req.getParameter("opVeg");
		String turno = (String)req.getParameter("turno");
		
		if(descricao == null || opVeg==null || turno ==null){
			req.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			req.getRequestDispatcher("CadRefeicao.jsp").forward(req, resp);
		}
		
		Refeicao refeicao = new Refeicao();
		refeicao.setDescricao(descricao);
		refeicao.setOpVeg(opVeg);
		refeicao.setTurno(Turno.valueOf(turno));

		try {
			refeicao.criar();
			req.setAttribute("refeicoes", new Refeicao().list());
			req.getRequestDispatcher("listarRefeicao.jsp").forward(req, resp);
		} catch (SQLException e) {
			req.setAttribute("mensagem", Constantes.ERRO);
			e.printStackTrace();
		}	
	}
	
	protected void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String descricao = (String)req.getParameter("descricao");
		String opVeg = (String)req.getParameter("opVeg");
		String turno = (String)req.getParameter("turno");
		String id = (String)req.getParameter("id");
		if(descricao == null || opVeg==null || turno ==null){
			req.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			req.getRequestDispatcher("CadRefeicao.jsp").forward(req, resp);
		}
		
		
		Refeicao refeicao = new Refeicao();
		refeicao.setId(Integer.parseInt(id));
		refeicao.setDescricao(descricao);
		refeicao.setOpVeg(opVeg);
		refeicao.setTurno(Turno.valueOf(turno));

		refeicao.atualizar(id);
		req.setAttribute("refeicoes", new Refeicao().list());
		req.getRequestDispatcher("listarRefeicao.jsp").forward(req, resp);	
	}
	
	protected void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
		String id =(String)req.getParameter("id");
		if(id.equals("")){
			req.setAttribute("mensagem", "Erro, id vazio");
			req.setAttribute("refeicoes", new Refeicao().list());
			req.getRequestDispatcher("listarRefeicao.jsp").forward(req, resp);

		}else{
		
			Refeicao refeicao = new Refeicao();
			refeicao = refeicao.get(id);
			if(refeicao==null){
				req.setAttribute("mensagem", "Refeicao Ja excluida");
				req.setAttribute("refeicoes", new Refeicao().list());
				req.getRequestDispatcher("listarRefeicao.jsp").forward(req, resp);
			}else{
				refeicao.excluir(id);
				req.setAttribute("mensagem", "Sucesso ao excluir");
				req.setAttribute("refeicoes", new Refeicao().list());
				req.getRequestDispatcher("listarRefeicao.jsp").forward(req, resp);
			}
		}
	}
	
}
