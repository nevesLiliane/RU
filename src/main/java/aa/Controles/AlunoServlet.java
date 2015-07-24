package aa.Controles;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.Componentes.Constantes;
import aa.modelo.Aluno;
import aa.modelo.CPF;
import aa.modelo.Curso;
import aa.modelo.Sexo;
import aa.modelo.Titulo;

@WebServlet("/Aluno")
public class AlunoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String acao = (String)request.getParameter("acao");
		String matricula = (String)request.getParameter("matricula");	
		if(acao==null){
			request.setAttribute("alunos", new Aluno().list());
			request.getRequestDispatcher("listarAluno.jsp").forward(request, response);
		}else if(acao.equals(Constantes.SALVAR)){
			salvar(request, response);	
		}else if((matricula!=null) && acao.equals(Constantes.ACAO_EDITAR)){
			editar(request, response);	
		}else if(acao.equals(Constantes.NOVO)){	
			request.setAttribute("cursos", new Curso().list());
			request.getRequestDispatcher("CadAluno.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String acao = (String)req.getParameter("acao");
		if(acao==null){
			req.setAttribute("alunos", new Aluno().list());
			req.getRequestDispatcher("listarAluno.jsp").forward(req, resp);
		}else if(acao.equals(Constantes.ACAO_EDITAR)){
			String matricula = (String)req.getParameter("matricula");
			req.setAttribute("aluno", new Aluno().get(matricula));
			req.setAttribute("cursos", new Curso().list());
			req.getRequestDispatcher("CadAluno.jsp").forward(req, resp);
		}else if(acao.equals(Constantes.ACAO_DELETAR)){
			excluir(req, resp);
			//req.getRequestDispatcher("CadAluno.jsp").forward(req, resp);
		}
	}

	protected void salvar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String nome = (String)req.getParameter("nome");
		String matricula = (String)req.getParameter("matricula");
		String anoIngresso = (String)req.getParameter("anoIngresso");
		String sexo = (String)req.getParameter("sexo");
		String titulo = (String)req.getParameter("titulo");
		String cpf = (String)req.getParameter("cpf");
		String Curso = (String)req.getParameter("Curso");


		if(matricula.equals("") || nome.equals("") || anoIngresso.equals("") || sexo.equals("")  || titulo.equals("") || cpf.equals("") || 
				Curso.equals("")){
			//System.out.println("Matricula"+ matricula+"Nome" + nome+"AnoIngresso"+anoIngresso + "Sexo" + sexo +"titulo" + titulo + "cpf" + cpf + "curso" + Curso);
			req.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			req.setAttribute("cursos", new Curso().list());
			req.getRequestDispatcher("CadAluno.jsp").forward(req, resp);	
		}else{
			Aluno func = new Aluno();
			func.setAnoIngresso(anoIngresso);
			CPF cpftemp = new CPF();
			cpftemp.setCPF(cpf);
			func.setCpf(cpftemp);
			func.setCurso(new Curso().get(Curso));
			func.setMatricula(matricula);
			func.setNome(nome);
			func.setSexo(Sexo.valueOf(sexo));
			func.setTitulo(Titulo.valueOf(titulo));
			try {
				func.criar();
				req.setAttribute("mensagem", Constantes.SUCESSO);
				req.setAttribute("alunos", new Aluno().list());
				req.getRequestDispatcher("listarAluno.jsp").forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				req.setAttribute("mensagem", Constantes.ERRO);
				req.getRequestDispatcher("CadAluno.jsp").forward(req, resp);
				e.printStackTrace();
			}

		}
	}

	protected void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		String matricula = (String)req.getParameter("matricula");
		String nome = (String)req.getParameter("nome");
		String anoIngresso = (String)req.getParameter("anoIngresso");
		String sexo = (String)req.getParameter("sexo");
		String titulo = (String)req.getParameter("titulo");
		String cpf = (String)req.getParameter("cpf");
		String curso = (String)req.getParameter("Curso");

		if(matricula.equals("") || nome.equals("") || anoIngresso.equals("") || sexo.equals("")  || cpf.equals("") || 
				curso.equals("") || titulo.equals("")){
			req.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			req.setAttribute("cursos", new Curso().list());
			req.getRequestDispatcher("CadAluno.jsp").forward(req, resp);	
		}else{
			Aluno func = new Aluno();
			func.setAnoIngresso(anoIngresso);
			CPF cpftemp = new CPF();
			cpftemp.setCPF(cpf);
			func.setCpf(cpftemp);
			func.setCurso(new Curso().get(curso));
			func.setMatricula(matricula);
			func.setNome(nome);
			func.setSexo(Sexo.valueOf(sexo));
			if(!titulo.equals(""))
				func.setTitulo(Titulo.valueOf(titulo));

			try {
				func.update(matricula);
				req.setAttribute("mensagem", Constantes.SUCESSO);
				req.setAttribute("alunos", new Aluno().list());
				req.getRequestDispatcher("listarAluno.jsp").forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				req.setAttribute("mensagem", Constantes.ERRO);
				req.getRequestDispatcher("CadAluno.jsp").forward(req, resp);
				e.printStackTrace();
			}
		}
		req.setAttribute("Aluno", new Aluno().get(matricula));
		req.setAttribute("Cursos", new Curso().list());
		//request.getRequestDispatcher("listarCurso.jsp").forward(request, response);
	}

	protected void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		String matricula = (String)req.getParameter("matricula");
		if(matricula.equals("")){
			req.setAttribute("mensagem","Erro, matricula vazia");
			req.setAttribute("alunos", new Aluno().list());
		}else{
			Aluno aluno = new Aluno();
			aluno.get(matricula);
			
			try {
				aluno.desativa(matricula);
				req.setAttribute("mensagem", Constantes.SUCESSO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				req.setAttribute("mensagem", Constantes.ERRO);
			}
		}
		req.setAttribute("alunos", new Aluno().list());
		req.getRequestDispatcher("listarAluno.jsp").forward(req, resp);
	}


}
