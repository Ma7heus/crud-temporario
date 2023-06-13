package br.com.uffs.dao;

import javax.ejb.Stateless;

import br.com.uffs.model.PessoaFisica;

@Stateless
public class PessoaFisicaDAO extends GenericDAO<PessoaFisica, Long> {

	private static final long serialVersionUID = 1L;

	public PessoaFisicaDAO() {
		super(PessoaFisica.class);
	}	
	
}
