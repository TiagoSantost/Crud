package entidades;

public enum PerfilUser {
	Administrador("Administrador"), Secretaria("Secretaria");
	
	private String nome;
	
	private PerfilUser(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
