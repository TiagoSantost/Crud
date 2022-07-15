package dao;

import javax.transaction.Transactional;

import entidades.Pessoa;

public class DaoUsuario<E>  extends DaoGeneric<Pessoa>  {
	
	
	public void removerUsuario(Pessoa pessoa) throws Exception{
		getEntityManager().getTransaction().begin();
		String sqlDelteFone = "delete from telefoneuser where usuariopessoa_id = " + pessoa.getId();
		String sqlDelteLancamento = "delete from lancamento where usuario_id = " + pessoa.getId();
		getEntityManager().createNativeQuery(sqlDelteFone).executeUpdate();
		getEntityManager().createNativeQuery(sqlDelteLancamento).executeUpdate();
		getEntityManager().getTransaction().commit();
		super.deletePorId(pessoa);
	}
}
