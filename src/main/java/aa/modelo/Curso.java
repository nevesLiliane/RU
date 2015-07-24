package aa.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import aa.Componentes.Constantes;
import aa.bd.Conexao;

public class Curso {
	
	private String id;
	private String nome;
	private String sigla;
	private Departamento departamento;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}


public void criar() throws SQLException{
	
	Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
	
	try{
		String sql = "INSERT INTO curso VALUES (?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1,getNome());
		stmt.setString(2, getSigla());
		stmt.setString(3,getDepartamento().getId());
		stmt.executeQuery();
		conn.close();
	} catch (Exception e){
		conn.close();
		System.out.println(e.getMessage());
	}
}

public ArrayList<Curso> list(){
	
	Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

	ResultSet rs = null;
	Statement stat;
	ArrayList<Curso> cursos = new ArrayList<Curso>();
	
	try{//idcurso, nome, sigla, departamento_iddepartamento from curso
		String sql = "SELECT  idcurso, nome, sigla, departamento_iddepartamento FROM  "
				+ "curso";
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		
		while (rs.next())
		{
			
			Curso curso = new Curso();
			curso.setId(rs.getString(1));
			curso.setNome(rs.getString(2));
			curso.setSigla(rs.getString(3));
			Departamento depto = new Departamento();
			curso.setDepartamento(depto.get(rs.getString(4)));
			cursos.add(curso);
		}

	} 
	catch (Exception e){
		System.out.println(e.getMessage());
	}
	return cursos;
}

public void update(String id) throws SQLException{
	Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
	
	try{
		String sql = "UPDATE curso set nome=?,sigla=? where iddepartamento=;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,getNome());
		stmt.setString(2, getSigla());
		stmt.setString(3, getDepartamento().getId());
		stmt.execute();
		conn.close();
	} catch (Exception e){
		conn.close();
		System.out.println(e.getMessage());
	}
	
}

public Curso get(String id){
	
	Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

	ResultSet rs = null;
	Statement stat;
	Curso curso = new Curso();

	try{
		String sql = "SELECT  idcurso, nome, sigla, departamento_iddepartamento FROM  "
				+ "curso";
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		
		if (rs.next()){
			curso.setId(rs.getString(1));
			curso.setNome(rs.getString(2));
			curso.setSigla(rs.getString(3));
			Departamento depto = new Departamento();
			curso.setDepartamento(depto.get(rs.getString(4)));
		}

	} 
	catch (Exception e){
		System.out.println(e.getMessage());
	}
	return curso;
	
}

}