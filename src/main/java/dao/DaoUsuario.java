package dao;

import javax.transaction.Transactional;

import entidades.Pessoa;

public class DaoUsuario<E>  extends DaoGeneric<Pessoa>  {
	
	@Transactional
	public void removerUsuario(Pessoa pessoa) throws Exception{
		getEntityManager().getTransaction().begin();
		String sqlDelteFone = "delete from telefoneuser where usuariopessoa_id = " + pessoa.getId();
		getEntityManager().createNativeQuery(sqlDelteFone).executeUpdate();
		getEntityManager().getTransaction().commit();
		super.deletePorId(pessoa);
	}
}
