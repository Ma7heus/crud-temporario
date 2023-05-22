package br.com.uffs.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uffs.dao.PessoaFisicaSemJpaDAO;
import br.com.uffs.model.PessoaFisica;

@Named
@ViewScoped
public class PessoaFisicaController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaFisicaSemJpaDAO pessoaFisicaDAO;
	
	private List<PessoaFisica> pessoaFisicaList;
	
	private PessoaFisica pessoaFisica;

	@PostConstruct
	public void init() {
		this.pessoaFisicaList = new ArrayList<>();		
		this.pessoaFisicaList = pessoaFisicaDAO.listAll();
	}
	
	// chamadoao clicar no botao salvar
	public void salvar() {
		Boolean cadastrar = pessoaFisicaDAO.cadastrar(this.pessoaFisica);
		if(cadastrar) {
			System.out.println("Cliente salvo nome: " + pessoaFisica.getNome());
			this.pessoaFisica = new PessoaFisica();
		}
		this.pessoaFisicaList = pessoaFisicaDAO.listAll();
	}
	
	public void remover() {
		System.out.println("Remover");
		pessoaFisicaDAO.deletar(this.pessoaFisica.getIdPessoaFisica());
	}
	
	// CHAMADO AO ABRIR DIALOG ATUALIZAR
	public void atualizar() {
		System.out.println("Atualizando cliente");
		this.pessoaFisica = pessoaFisicaDAO.findById(1L);
	}
	
	//chamado ao abrir o dialog
	public void novoCadastro() {
		this.pessoaFisica = new PessoaFisica();
	}
	
	public List<PessoaFisica> getPessoaFisicaList() {
		return pessoaFisicaList;
	}

	
	public void setPessoaFisicaList(List<PessoaFisica> pessoaFisicaList) {
		this.pessoaFisicaList = pessoaFisicaList;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

}
