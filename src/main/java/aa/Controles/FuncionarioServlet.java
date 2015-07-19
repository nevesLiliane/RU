package aa.Controles;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.Componentes.Constantes;
import aa.modelo.CPF;
import aa.modelo.Departamento;
import aa.modelo.Funcionario;
import aa.modelo.Sexo;
import aa.modelo.Titulo;

@WebServlet("/Funcionario")
public class FuncionarioServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String acao = (String)request.getParameter("acao");
	
		if(acao==null){
			request.setAttribute("funcionarios", new Funcionario().list());
			request.getRequestDispatcher("listarFuncionario.jsp").forward(request, response);
		}else if(acao.equals(Constantes.NOVO)){	
			request.setAttribute("departamentos", new Departamento().list());
			request.getRequestDispatcher("CadFuncionario.jsp").forward(request, response);			
		}else if(acao.equals(Constantes.SALVAR)){
			String matricula = (String)request.getParameter("matricula");
			String nome = (String)request.getParameter("nome");
			String anoIngresso = (String)request.getParameter("anoIngresso");
			String sexo = (String)request.getParameter("sexo");
			String titulo = (String)request.getParameter("titulo");
			String cpf = (String)request.getParameter("cpf");
			String departamento = (String)request.getParameter("departamento");
			System.out.println("Nome" + nome + "Sexo" + sexo);
			if(matricula==null || nome==null || anoIngresso==null || sexo==null  || titulo==null || cpf==null || departamento==null){
				System.out.println("Nome" + nome + "Sexo" + sexo);
				request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
				request.setAttribute("departamentos", new Departamento().list());
				request.getRequestDispatcher("CadFuncionario.jsp").forward(request, response);	
			}else{
				Funcionario func = new Funcionario();
				func.setAnoIngresso(anoIngresso);
				CPF cpftemp = new CPF();
				cpftemp.setCPF(cpf);
				func.setCpf(cpftemp);
				func.setDepartamento(new Departamento().get(departamento));
				func.setMatricula(matricula);
				func.setNome(nome);
				func.setSexo(Sexo.valueOf(sexo));
				func.setTitulo(Titulo.valueOf(titulo));
				try {
					func.criar();
					request.setAttribute("mensagem", Constantes.SUCESSO);
					request.setAttribute("funcionarios", new Funcionario().list());
					request.getRequestDispatcher("listarFuncionario.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					request.setAttribute("mensagem", Constantes.ERRO);
					request.getRequestDispatcher("CadFuncionario.jsp").forward(request, response);
					e.printStackTrace();
				}
			}
		}
	
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req, resp);
	}

}
