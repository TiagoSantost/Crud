package repositorio;

import java.util.List;

import entidades.TelefoneUser;


public interface IDaoTelefone {

	List <TelefoneUser> consultarTelefone(Long codUser);
	
}
