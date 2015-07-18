package aa.modelo;

public class Funcionario extends Consumidor {

	private Departamento departamento;

	public Funcionario(String nome, String matricula, String anoDeIngresso, Sexo sexo, Titulo titulo, CPF cpf, Departamento departamento)
	{
		super(nome, matricula, anoDeIngresso, sexo, titulo, cpf);
		this.departamento = departamento;
	}

	public Funcionario()
	{
		// TODO Auto-generated constructor stub
	}

	public Departamento getDepartamento()
	{
		return departamento;
	}

	public void setDepartamento(Departamento departamento)
	{
		this.departamento = departamento;
	}
}
