package aa.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
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
			String sql = "SELECT idRefeicao, descricao, opcaovegetariana, turno FROM  refeicao";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while (rs.next())
			{
				Refeicao refeicao = new Refeicao();
				refeicao.setId(rs.getInt(1));
				refeicao.setDescricao(rs.getString(2));
				refeicao.setOpVeg(rs.getString(3));
				String turno= rs.getString(4);
				System.out.println(Turno.valueOf(turno));
				refeicao.setTurno(Turno.valueOf(String.valueOf(turno)));
				refeicoes.add(refeicao);
			}

		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return refeicoes;
		
	}
}
