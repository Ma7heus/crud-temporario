package br.com.uffs.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.uffs.model.PessoaFisica;

@Stateless
public class PessoaFisicaDAO extends GenericDAO<PessoaFisica, Long> {

	private static final long serialVersionUID = 1L;

	public PessoaFisicaDAO() {
		super(PessoaFisica.class);
	}	
	
	public List<PessoaFisica> findPessoaFisicaByNacionalidade(Long idNacionalidade) {
		 String queryStr = "SELECT pf FROM PessoaFisica pf JOIN pf.nacionalidade n WHERE n.idNacionalidade = :idNacionalidade";
		  
		 TypedQuery<PessoaFisica> query = entityManager.createQuery(queryStr, PessoaFisica.class);
		    query.setParameter("idNacionalidade", idNacionalidade);
		  
		    return query.getResultList();
	}
	
}
