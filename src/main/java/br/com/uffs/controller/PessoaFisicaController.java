package br.com.uffs.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	
	private Boolean isEditando;
	
	private String textoConsulta;
	
	private String generoSelecionado;
	
	private String txt1;

	@PostConstruct
	public void init() {
		this.pessoaFisicaList = new ArrayList<>();		
		this.pessoaFisicaList = pessoaFisicaDAO.listAll();
		this.isEditando = null;
		this.textoConsulta = null;
	}
	
	// chamadoao clicar no botao salvar
	public void salvar() {
		if (isEditando) {
			editarExistente();
		}else {
			cadastratNovo();			
		}
		this.pessoaFisicaList = pessoaFisicaDAO.listAll();
	}

	private void editarExistente() {
		Boolean editar = pessoaFisicaDAO.atualizar(this.pessoaFisica);
		if(editar) {
			System.out.println("Cliente atualizado nome: " + pessoaFisica.getNome());
			this.pessoaFisica = pessoaFisicaDAO.findById(pessoaFisica.getIdPessoaFisica());
		}
	}

	private void cadastratNovo() {
		Boolean cadastrar = pessoaFisicaDAO.cadastrar(this.pessoaFisica);
		if(cadastrar) {
			System.out.println("Cliente salvo nome: " + pessoaFisica.getNome());
			this.pessoaFisica = new PessoaFisica();
		}
	}

	public void remover() {
		System.out.println("Remover");
		pessoaFisicaDAO.deletar(this.pessoaFisica.getIdPessoaFisica());
		this.pessoaFisicaList = pessoaFisicaDAO.listAll();
	}
	
	// CHAMADO AO ABRIR DIALOG ATUALIZAR
	public void atualizar() {
		isEditando = true;
		System.out.println("Atualizando cliente");
	}
	
	//chamado ao abrir o dialog
	public void novoCadastro() {
		isEditando = false;
		this.pessoaFisica = new PessoaFisica();
	}
	
	public void filtrar() {
		System.out.println(txt1);
		
		if(Objects.nonNull(textoConsulta) || textoConsulta != "") {
			System.out.println("Filtrando dados " + textoConsulta);
			List<PessoaFisica> listaFiltrada = pessoaFisicaDAO.filtrar(textoConsulta.toLowerCase());
			this.pessoaFisicaList = listaFiltrada;			
		}
	}
	
	public List<String> getListaGeneros(String query) {
		generoSelecionado = query;
		List<String> generos = new ArrayList<>();
		String m = "Masculino";
		String f = "Feminino";
		String x = "Outro";
		
		generos.add(m);
		generos.add(f);
		generos.add(x);
		return generos;
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

	public Boolean getIsEditando() {
		return isEditando;
	}

	public void setIsEditando(Boolean isEditando) {
		this.isEditando = isEditando;
	}


	public String getTextoConsulta() {
		return textoConsulta;
	}

	public void setTextoConsulta(String textoConsulta) {
		this.textoConsulta = textoConsulta;
	}

	public String getGeneroSelecionado() {
		return generoSelecionado;
	}

	public void setGeneroSelecionado(String generoSelecionado) {
		this.generoSelecionado = generoSelecionado;
	}

	public String getTxt1() {
		return txt1;
	}

	public void setTxt1(String txt1) {
		this.txt1 = txt1;
	}
	
}
