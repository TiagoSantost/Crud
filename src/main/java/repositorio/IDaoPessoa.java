package repositorio;

import entidades.Pessoa;

public interface IDaoPessoa {
		
	public Pessoa consultarUsuario(String login, String senha);

}
