package aa.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import aa.Componentes.Constantes;
import aa.bd.Conexao;

public class Refeicao {
	
	private Integer id;
	private String descricao;
	private String opVeg;
	private Turno turno; 
	private Boolean situacao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getOpVeg() {
		return opVeg;
	}
	public void setOpVeg(String opVeg) {
		this.opVeg = opVeg;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public Boolean getSituacao() {
		return situacao;
	}
	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}
	
	public ArrayList<Refeicao> list(){
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		ResultSet rs = null;
		Statement stat;
		ArrayList<Refeicao> refeicoes = new ArrayList<Refeicao>();

		try{
			String sql = "SELECT idRefeicao, descricao, opcaovegetariana, turno FROM  refeicao where situacao=1";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while (rs.next())
			{
				Refeicao refeicao = new Refeicao();
				refeicao.setId(rs.getInt(1));
				refeicao.setDescricao(rs.getString(2));
				refeicao.setOpVeg(rs.getString(3));
				String turno= rs.getString(4);
				refeicao.setTurno(Turno.valueOf(String.valueOf(turno)));
				refeicoes.add(refeicao);
			}

		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return refeicoes;
		
	}
	
	public void criar() throws SQLException{
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		try{
			String sql = "INSERT INTO refeicao (descricao, opcaoVegetariana, turno, situacao) VALUES (?,?, ?, 1)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, getDescricao());
			stmt.setString(2, getOpVeg());
			stmt.setString(3, getTurno().toString());
			stmt.execute();
			
			conn.close();
		} catch (Exception e){
			conn.close();
			System.out.println(e.getMessage());
		}
	}
	
	public Refeicao get(String id){
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		ResultSet rs = null;
		Statement stat;
		Refeicao refeicao= null;
		try{
			String sql = "SELECT idRefeicao, descricao, opcaovegetariana, turno FROM  refeicao where idRefeicao=" +id;
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()){
				refeicao = new Refeicao();
				refeicao.setId(rs.getInt(1));
				refeicao.setDescricao(rs.getString(2));
				refeicao.setOpVeg(rs.getString(3));
				String turno= rs.getString(4);
				refeicao.setTurno(Turno.valueOf(String.valueOf(turno)));
			}

		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return refeicao;
		
	}
	
	public Refeicao atualizar(String id){
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		ResultSet rs = null;
		PreparedStatement stat;
		Refeicao refeicao= null;
		try{
			String sql = "UPDATE refeicao set descricao=?,  opcaovegetariana=? where idRefeicao=?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, getDescricao());
			stat.setString(2, getOpVeg());
			stat.setString(3, id);
			stat.execute();
		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return refeicao;
	}
	
	public void excluir(String id)throws SQLException{
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		ResultSet rs = null;
		PreparedStatement stat;
		Refeicao refeicao= null;
		try{
			String sql = "UPDATE refeicao set situacao=0 where idRefeicao=?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, id);
			stat.execute();
		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
