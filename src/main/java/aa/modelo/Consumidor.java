package aa.modelo;

public class Consumidor{
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
	
}
