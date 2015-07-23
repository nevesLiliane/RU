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
		}else if(acao.equals(Constantes.NOVO)){	
			request.setAttribute("cursos", new Curso().list());
			request.getRequestDispatcher("CadAluno.jsp").forward(request, response);			
		}else if(acao.equals(Constantes.SALVAR)){
			String nome = (String)request.getParameter("nome");
			String anoIngresso = (String)request.getParameter("anoIngresso");
			String sexo = (String)request.getParameter("sexo");
			String titulo = (String)request.getParameter("titulo");
			String cpf = (String)request.getParameter("cpf");
			String Curso = (String)request.getParameter("curso");
			System.out.println("Nome" + nome + "Sexo" + sexo);
			if(matricula==null || nome==null || anoIngresso==null || sexo==null  || titulo==null || cpf==null || Curso==null){
				//System.out.println("Nome" + nome + "Sexo" + sexo);
				request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
				request.setAttribute("cursos", new Curso().list());
				request.getRequestDispatcher("CadAluno.jsp").forward(request, response);	
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
					request.setAttribute("mensagem", Constantes.SUCESSO);
					request.setAttribute("alunos", new Aluno().list());
					request.getRequestDispatcher("listarAluno.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					request.setAttribute("mensagem", Constantes.ERRO);
					request.getRequestDispatcher("CadAluno.jsp").forward(request, response);
					e.printStackTrace();
				}
			}
		}else if(acao.equals(Constantes.EDITAR)){
			
			request.setAttribute("Aluno", new Aluno().get(matricula));
			request.setAttribute("cursos", new Curso().list());
			request.getRequestDispatcher("CadAluno.jsp").forward(request, response);		
		}else if((matricula!=null) && acao.equals(Constantes.ACAO_EDITAR)){
			String nome = (String)request.getParameter("nome");
			String anoIngresso = (String)request.getParameter("anoIngresso");
			String sexo = (String)request.getParameter("sexo");
			String titulo = (String)request.getParameter("titulo");
			String cpf = (String)request.getParameter("cpf");
			String Curso = (String)request.getParameter("Curso");
			System.out.println("Nome" + nome + "Sexo" + sexo);
			if(matricula==null || nome==null || anoIngresso==null || sexo==null  || titulo==null || cpf==null || Curso==null){
				//System.out.println("Nome" + nome + "Sexo" + sexo);
				request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
				request.setAttribute("Cursos", new Curso().list());
				request.getRequestDispatcher("CadAluno.jsp").forward(request, response);	
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
					func.update(matricula);
					request.setAttribute("mensagem", Constantes.SUCESSO);
					request.setAttribute("Alunos", new Aluno().list());
					request.getRequestDispatcher("listarAluno.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					request.setAttribute("mensagem", Constantes.ERRO);
					request.getRequestDispatcher("CadAluno.jsp").forward(request, response);
					e.printStackTrace();
				}
			}
			request.setAttribute("Aluno", new Aluno().get(matricula));
			request.setAttribute("Cursos", new Curso().list());
			//request.getRequestDispatcher("listarCurso.jsp").forward(request, response);		
		}
			
	
	}


protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req, resp);
	}

}
