package aa.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import aa.Componentes.Constantes;
import aa.bd.Conexao;

public class Departamento {
	
	private String id;
	private String nome;
	private String sigla;
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
	
	public void criar() throws SQLException{
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		try{
			String sql = "INSERT INTO departamento (nome, sigla) VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, getId());
			stmt.setString(2, getSigla());
			stmt.execute();
			
			conn.close();
		} catch (Exception e){
			conn.close();
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Departamento> list(){
		
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		ResultSet rs = null;
		Statement stat;
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();

		try{
			String sql = "SELECT iddepartamento, nome, sigla FROM  departamento";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while (rs.next())
			{
				Departamento departamento = new Departamento();
				departamento.setId(rs.getString(1));
				departamento.setNome(rs.getString(2));
				departamento.setSigla(rs.getString(3));
				departamentos.add(departamento);
			}

		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return departamentos;
	}
	
	public void update(String id) throws SQLException{
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		try{
			String sql = "UPDATE departamento set nome=?, sigla=? where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,getNome());
			stmt.setString(2, getSigla());
			stmt.setString(3, getId());
			stmt.execute();	
			conn.close();
		} catch (Exception e){
			conn.close();
			System.out.println(e.getMessage());
		}	
	}
	
	public Departamento get(String id){
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ResultSet rs = null;
		Statement stat;
		Departamento departamento = new Departamento();
		try{
			String sql = "SELECT iddepartamento, nome, sigla FROM  departamento where "
					+ " matricula=?";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()){
				departamento.setId(rs.getString(1));
				departamento.setNome(rs.getString(2));
				departamento.setSigla(rs.getString(3));
			}
		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return departamento;		
	}

}
