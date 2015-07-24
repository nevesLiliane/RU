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
			stmt.executeQuery();
			sql = "INSERT INTO aluno VALUES (?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, getMatricula());
			stmt.setString(2,getCurso().getId());
			stmt.executeQuery();
			conn.close();
		} catch (Exception e){
			conn.close();
			System.out.println(e.getMessage());
		}
	}

public ArrayList<Aluno> list(){
	
	Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

	ResultSet rs = null;
	Statement stat;
	ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	
	try{
		String sql = "SELECT f.consumidor_matricula, c.nome, c.ano_ingresso, c.sexo, c.titulo, c.cpf, f.departamento_iddepartamento, situacao FROM  "
				+ "consumidor as c Join funcionario f on f.consumidor_matricula = c.matricula  where situacao =1 and departamento_iddepartamento IS NOT NULL";
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		
		while (rs.next())
		{
			
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
		
		sql = "UPDATE aluno set idcurso=? where matricula=?";
		stmt.setString(1, getCurso().getId());
		stmt.setString(2, getMatricula());
		stmt.execute();
		conn.close();
	} catch (Exception e){
		conn.close();
		System.out.println(e.getMessage());
	}
	
}

public void desativa(String matricula) throws SQLException{//remover
	Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
	
	try{
		String sql = "UPDATE consumidor set situacao=0 where matricula=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, getMatricula());
		stmt.execute();
		conn.close();
	} catch (Exception e){
		conn.close();
		System.out.println(e.getMessage());
	}
}


public Aluno get(String matricula){
		
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		ResultSet rs = null;
		Statement stat;
		Aluno aluno = new Aluno();

		try{
			String sql = "SELECT f.matricula, c.nome, c.ano_ingresso, c.sexo, c.titulo, c.cpf, f.iddepartamento, situacao FROM  "
					+ "consumidor as c Join funcionario f on f.matricula = c.matricula  where situacao =1 and iddepartamento IS NOT "
					+ "NULL and matricula=?";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()){
				aluno.setMatricula(rs.getString(1));
				Curso curso = new Curso();
				curso.get(rs.getString(2));
				aluno.setCurso(curso);
			}

		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return aluno;
		
	}	
	


}
