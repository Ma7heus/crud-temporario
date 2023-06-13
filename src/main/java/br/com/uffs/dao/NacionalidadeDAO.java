package br.com.uffs.dao;

import javax.ejb.Stateless;

import br.com.uffs.model.Nacionalidade;

@Stateless
public class NacionalidadeDAO extends GenericDAO<Nacionalidade, Long> {

	private static final long serialVersionUID = 1L;
	
	public NacionalidadeDAO() {
		super(Nacionalidade.class);
	}

}
