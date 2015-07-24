package aa.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import aa.Componentes.Constantes;
import aa.bd.Conexao;

public class Funcionario extends Consumidor{

	private Departamento departamento;

	public Funcionario(String nome, String matricula, String anoDeIngresso, Sexo sexo, Titulo titulo, CPF cpf, Departamento departamento)
	{
		super(nome, matricula, anoDeIngresso, sexo, titulo, cpf);
		this.departamento = departamento;
	}

	public Funcionario(){
		// TODO Auto-generated constructor stub
	}

	public Departamento getDepartamento(){
		return departamento;
	}

	public void setDepartamento(Departamento departamento){
		this.departamento = departamento;
	}
	
	public void criar() throws SQLException{
		
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		
		try{
			String sql = "INSERT INTO consumidor VALUES (?,?,?, ?, ?,?, 1)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, getMatricula());
			stmt.setString(2,getNome());
			System.out.println(getNome());
			stmt.setString(3, getAnoIngresso());
			stmt.setString(4,getSexo().toString());
			stmt.setString(5, getTitulo().toString());
			stmt.setString(6,getCpf().getCPF());
			stmt.execute();
			sql = "INSERT INTO funcionario VALUES (?,?)";
			PreparedStatement stmt2 = conn.prepareStatement(sql);
			stmt2.setString(1,getDepartamento().getId());
			stmt2.setString(2, getMatricula());
			
			stmt2.execute();
			conn.close();
		} catch (Exception e){
			conn.close();
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Funcionario> list(){
		
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		ResultSet rs = null;
		Statement stat;
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try{
			String sql = "SELECT f.consumidor_matricula, c.nome, c.ano_ingresso, c.sexo, c.titulo, c.cpf, f.departamento_iddepartamento, situacao FROM  "
					+ "consumidor as c Join funcionario f on f.consumidor_matricula = c.matricula  where situacao =1 and departamento_iddepartamento IS NOT NULL";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while (rs.next())
			{
				
				Funcionario funcionario = new Funcionario();
				funcionario.setMatricula(rs.getString(1));
				funcionario.setNome(rs.getString(2));
				funcionario.setAnoIngresso(rs.getString(3));
				String sexo = rs.getString(4);
				funcionario.setSexo(Sexo.valueOf(sexo));
				String titulo = rs.getString(5);
				funcionario.setTitulo(Titulo.valueOf(titulo));
				CPF cpf = new CPF();
				cpf.setCPF(rs.getString(6));
				funcionario.setCpf(cpf);
				Departamento depto = new Departamento();
				funcionario.setDepartamento(depto.get(rs.getString(7)));
				funcionarios.add(funcionario);
			}

		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return funcionarios;
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
			
			sql = "UPDATE funcionario set iddepartamento=? where matricula=?";
			PreparedStatement stmt2 = conn.prepareStatement(sql);
			stmt2.setString(1, getDepartamento().getId());
			stmt2.setString(2, getMatricula());
			stmt2.execute();
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
	public Funcionario get(String matricula){
		Connection conn = Conexao.Conecta(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ResultSet rs = null;
		Statement stmt;
		Funcionario funcionario = new Funcionario();
		try{
			String sql = "SELECT matricula, c.nome, c.ano_ingresso, c.sexo, c.titulo, c.cpf, departamento_iddepartamento, "
					+ "situacao FROM  consumidor as c Join funcionario f on consumidor_matricula = matricula  where situacao =1 and "
					+ "departamento_iddepartamento IS NOT NULL and matricula="+ Integer.parseInt(matricula);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				funcionario.setMatricula(rs.getString(1));
				funcionario.setNome(rs.getString(2));
				funcionario.setAnoIngresso(rs.getString(3));
				String sexo = rs.getString(4);
				funcionario.setSexo(Sexo.valueOf(sexo));
				String titulo = rs.getString(5);
				funcionario.setTitulo(Titulo.valueOf(titulo));
				CPF cpf = new CPF();
				cpf.setCPF(rs.getString(6));
				System.out.println(cpf.getCPF());
				funcionario.setCpf(cpf);
				Departamento depto = new Departamento();
				funcionario.setDepartamento(depto.get(rs.getString(7)));
			}

		} 
		catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return funcionario;
	}
}
