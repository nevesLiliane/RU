package aa.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import aa.Componentes.Constantes;
import aa.bd.Conexao;

public class Aluno extends Consumidor{

	private Curso curso;

	public Aluno(String nome, String matricula, String anoDeIngresso, Sexo sexo, Titulo titulo, CPF cpf, Curso curso)
	{
		super(nome, matricula, anoDeIngresso, sexo, titulo, cpf);
		this.curso = curso;
	}

	public Aluno(){
		// TODO Auto-generated constructor stub
	}

	public Curso getCurso(){
		return curso;
	}

	public void setCurso(Curso curso){
		this.curso = curso;
	}

	public void criar() throws SQLException{

		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		try{
			String sql = "INSERT INTO consumidor VALUES (?,?,?, ?, ?,?, 1)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, getMatricula());
			stmt.setString(2,getNome());
			stmt.setString(3, getAnoIngresso());
			stmt.setString(4,getSexo().toString());
			stmt.setString(5, getTitulo().toString());
			stmt.setString(6,getCpf().getCPF());
			stmt.execute();
			sql = "INSERT INTO aluno VALUES (?,?)";
			PreparedStatement stmt2 = conn.prepareStatement(sql);
			stmt2.setString(1, getMatricula());
			stmt2.setString(2,getCurso().getId());
			stmt2.execute();
			conn.close();
		} catch (Exception e){
			conn.close();
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<Aluno> list(){

		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		ResultSet rs = null;
		Statement stat;
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		try{
			String sql = "SELECT a.consumidor_matricula, c.nome, c.ano_ingresso, c.sexo, c.titulo, c.cpf, a.curso_idcurso, situacao FROM  "
					+ "consumidor as c Join aluno a on a.consumidor_matricula = c.matricula  where situacao =1 and curso_idcurso IS NOT NULL";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			while (rs.next()){

				Aluno aluno = new Aluno();
				aluno.setMatricula(rs.getString(1));
				aluno.setNome(rs.getString(2));
				aluno.setAnoIngresso(rs.getString(3));
				String sexo = rs.getString(4);
				aluno.setSexo(Sexo.valueOf(sexo));
				String titulo = rs.getString(5);
				aluno.setTitulo(Titulo.valueOf(titulo));
				CPF cpf = new CPF();
				cpf.setCPF(rs.getString(6));
				aluno.setCpf(cpf);
				Curso depto = new Curso();
				aluno.setCurso(depto.get(rs.getString(7)));
				alunos.add(aluno);
			}

		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return alunos;
	}

	public void update(String matricula) throws SQLException{
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		try{
			String sql = "UPDATE consumidor set nome=?, ano_ingresso=?, sexo=?, titulo= ?, cpf=? where matricula=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,getNome());
			stmt.setString(2, getAnoIngresso());
			stmt.setString(3,getSexo().toString());
			stmt.setString(4,getTitulo().toString());
			stmt.setString(5,getCpf().getCPF());
			stmt.setString(6, getMatricula());
			stmt.execute();

			sql = "UPDATE aluno set curso_idcurso=? where consumidor_matricula=?";
			PreparedStatement stmt2 = conn.prepareStatement(sql);
			stmt2.setString(1, getCurso().getId());
			stmt2.setString(2, getMatricula());
			stmt2.execute();
			conn.close();
		} catch (Exception e){
			conn.close();
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void desativa(String matricula) throws SQLException{//remover
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		PreparedStatement stmt;
		try{
			System.out.println(matricula);
			String sql = "UPDATE consumidor set situacao=0 where matricula=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(matricula));
			stmt.executeUpdate();
			System.out.println("Aqui");
		} catch (SQLException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public Aluno get(String matricula){

		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		ResultSet rs = null;
		Statement stat;
		Aluno aluno = new Aluno();

		try{
			String sql = "SELECT a.consumidor_matricula, c.nome, c.ano_ingresso, c.sexo, c.titulo, c.cpf, a.curso_idcurso, situacao FROM  "
					+ "consumidor as c Join aluno a on a.consumidor_matricula = c.matricula  where situacao =1 and curso_idcurso IS NOT "
					+ "NULL and matricula="+ matricula;
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()){
				aluno.setMatricula(rs.getString(1));
				aluno.setNome(rs.getString(2));
				aluno.setAnoIngresso(rs.getString(3));
				
				String sexo = rs.getString(4);
				aluno.setSexo(Sexo.valueOf(sexo));
				
				String titulo = rs.getString(5);
				aluno.setTitulo(Titulo.valueOf(titulo));
				
				CPF cpf = new CPF();
				cpf.setCPF(rs.getString(6));
				aluno.setCpf(cpf);
			
				Curso curso = new Curso();
				curso.get(rs.getString(7));
				aluno.setCurso(curso);
			}
		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return aluno;

	}	



}
