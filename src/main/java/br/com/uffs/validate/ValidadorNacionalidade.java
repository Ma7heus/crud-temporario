package br.com.uffs.validate;

import java.util.List;

import javax.inject.Inject;

import br.com.uffs.dao.NacionalidadeDAO;
import br.com.uffs.model.PessoaFisica;

public class ValidadorNacionalidade {
	
	@Inject
	private static NacionalidadeDAO dao;
	
	public static Boolean podeDeletar(Long idNacionalidade) {
//			List<PessoaFisica> pessoaFisicaList = dao.findPessoaFisicaByNacionalidade(idNacionalidade);
//			
//			if(pessoaFisicaList.size() == 0) {
//				System.out.println("Retornou nada");
//			}else {
//				System.out.println("retornou total de " + idNacionalidade + " registros:");
//			}
			
		return false;
	}

}
