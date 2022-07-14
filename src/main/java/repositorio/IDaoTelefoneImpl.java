package repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entidades.Lancamento;
import entidades.TelefoneUser;
import jpautil.JPAUtil;

public class IDaoTelefoneImpl implements IDaoTelefone  {
	
	@Override
	public List<TelefoneUser> consultarTelefone(Long codUser) {
		List<TelefoneUser> lista = null;
		
		EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		lista = entityManager.createQuery(" from TelefoneUser where usuarioPessoa.id = " + codUser).getResultList();
		
		transaction.commit();
		entityManager.close();
		
		return lista;
		
	}
}
