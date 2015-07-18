package aa.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import aa.Componentes.Constantes;
import aa.bd.Conexao;

public class Consumidor {
	private String nome;
	private String matricula;
	private String anoIngresso;
	private Sexo sexo;
	private Titulo titulo;
	private CPF cpf;
	private String situacao;
	public Consumidor(String nome, String matricula, String anoDeIngresso,
			Sexo sexo, Titulo titulo, CPF cpf) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.anoIngresso = anoDeIngresso;
		this.sexo = sexo;
		this.titulo = titulo;
		this.cpf = cpf;
	}
	public Consumidor() {
		// TODO Auto-generated constructor stub
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getAnoIngresso() {
		return anoIngresso;
	}
	public void setAnoIngresso(String anoIngresso) {
		this.anoIngresso = anoIngresso;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Titulo getTitulo() {
		return titulo;
	}
	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}
	public CPF getCpf() {
		return cpf;
	}
	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public void criar() throws SQLException{
		
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		
		try
		{
			String sql = "INSERT INTO consumidor (matricula, nome, ano_ingresso, sexo, titulo, cpf, situacao) VALUES (?,?,?,?,?,?,1)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, getMatricula());
			stmt.setString(2,getNome());
			stmt.setString(3, getAnoIngresso());
			stmt.setString(4,getSexo().toString());
			stmt.setString(4,getTitulo().toString());
			stmt.setString(5,getCpf().getCPF());
			stmt.setString(6,getSituacao());
			stmt.execute();
			
			conn.close();
		} catch (Exception e)
		{
			conn.close();
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Consumidor> list(){
		
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		ResultSet rs = null;
		Statement stat;
		ArrayList<Consumidor> consumidores = new ArrayList<Consumidor>();

		try{
			String sql = "SELECT matricula, nome, ano_ingresso,sexo, titulo, sexo, titulo, cpf, situacao FROM  consumidor where situacao =1";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while (rs.next())
			{
				Consumidor consumidor = new Consumidor();
				consumidor.setMatricula(rs.getString(1));
				consumidor.setNome(rs.getString(2));
				consumidor.setAnoIngresso(rs.getString(3));
				String sexo = rs.getString(4);
				consumidor.setSexo(Sexo.valueOf(sexo));
				String titulo = rs.getString(5);
				consumidor.setTitulo(Titulo.valueOf(titulo));
				CPF cpf = new CPF();
				cpf.setCPF(rs.getString(6));
				consumidor.setCpf(cpf);
				consumidores.add(consumidor);
			}

		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return consumidores;
	}
	
	public void update(String matricula) throws SQLException{
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		
		try
		{
			String sql = "UPDATE consumidor set nome=?, ano_ingresso=?, sexo=?, titulo= ?, cpf=? where matricula=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,getNome());
			stmt.setString(2, getAnoIngresso());
			stmt.setString(3,getSexo().toString());
			stmt.setString(4,getTitulo().toString());
			stmt.setString(5,getCpf().getCPF());
			stmt.setString(6, getMatricula());
			stmt.execute();
			
			conn.close();
		} catch (Exception e)
		{
			conn.close();
			System.out.println(e.getMessage());
		}
		
	}
	
	public void desativa(String matricula) throws SQLException{//remover
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		
		try
		{
			String sql = "UPDATE consumidor set situacao=0 where matricula=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, getMatricula());
			stmt.execute();
			
			conn.close();
		} catch (Exception e)
		{
			conn.close();
			System.out.println(e.getMessage());
		}
		
	}
	
	public Consumidor get(String matricula){
		
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		ResultSet rs = null;
		Statement stat;
		Consumidor consumidor = new Consumidor();

		try{
			String sql = "SELECT matricula, nome, ano_ingresso,sexo, titulo, sexo, titulo, cpf, situacao FROM  consumidor where "
					+ "situacao =1 and matricula=?";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()){
				consumidor.setMatricula(rs.getString(1));
				consumidor.setNome(rs.getString(2));
				consumidor.setAnoIngresso(rs.getString(3));
				String sexo = rs.getString(4);
				consumidor.setSexo(Sexo.valueOf(sexo));
				String titulo = rs.getString(5);
				consumidor.setTitulo(Titulo.valueOf(titulo));
				CPF cpf = new CPF();
				cpf.setCPF(rs.getString(6));
				consumidor.setCpf(cpf);
			}

		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return consumidor;
		
	}
	


}
