package br.com.uffs.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author matheuspalacios
 *
 */

public abstract class GenericDAO<T,ID> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	EntityManager entityManager;
	
	public Class<T> clazz;

	public GenericDAO(Class<T> clazz) {
		this.clazz = clazz;
		this.entityManager = EntityManagerBuil.getEntityManager();
	}
	
	public void cadastrar(T entidade) {
		System.out.println("CADASTRANDO COM JPA");
		entityManager.persist(entidade);
	}
	
	public void atualizar(T entidade) {
		System.out.println("ATUALIZANDO COM JPA");
		entityManager.merge(entidade);
	}
	
	public void deletar(ID idEntidade) {
		System.out.println("DELETANDO COM JPA");
		T entidade = entityManager.find(clazz, idEntidade);
		entityManager.remove(entidade);
	}
	
	public T buscarById(ID idEntidade) {
		System.out.println("BUSCANDO POR ID COM JPA");
		return entityManager.find(clazz, idEntidade);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(){
		System.out.println("buscando todos JPA");
		return entityManager.createQuery(" select entidade from " + clazz.getSimpleName() 
		+ " entidade ").getResultList();
	}

}
