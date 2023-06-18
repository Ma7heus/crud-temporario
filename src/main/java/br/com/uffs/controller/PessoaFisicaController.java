package br.com.uffs.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uffs.dao.NacionalidadeDAO;
import br.com.uffs.dao.PessoaFisicaDAO;
import br.com.uffs.dao.PessoaFisicaSemJpaDAO;
import br.com.uffs.model.Nacionalidade;
import br.com.uffs.model.PessoaFisica;

@Named
@ViewScoped
public class PessoaFisicaController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaFisicaSemJpaDAO pessoaFisicaDAO;
	
	@Inject
	private PessoaFisicaDAO pessoaFisicaDAOComJpa;
	
	@Inject
	private NacionalidadeDAO nacionalidadeDAO;
	
	@Inject
	private FacesContext facesContext;
	
	private List<PessoaFisica> pessoaFisicaList;
	
	private PessoaFisica pessoaFisica;
	
	private Boolean isEditando;
	
	private String textoConsulta;
	
	private String generoSelecionado;
	
	private Nacionalidade nacionalidadeSelecionada;
	
	private Long idNacionalidade;
	
	private List<Nacionalidade> nacionalidadesList = new ArrayList<>();
	
	
	private String txt1;

	@PostConstruct
	public void init() {
		this.pessoaFisicaList = new ArrayList<>();		
		this.pessoaFisicaList = listarTodos();
		nacionalidadesList = getNacionalidadeDAO().buscarTodos();
		this.isEditando = null;
		this.textoConsulta = null;
	}

	private List<PessoaFisica> listarTodos() {
		return pessoaFisicaDAOComJpa.buscarTodos();
	}
	
	public void salvar() {
		if (isEditando) {
			editarExistente();
		}else {
			cadastratNovo();			
		}
		this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,	"Salvo com sucesso!",null));
		this.pessoaFisicaList = listarTodos();
	}

	private void editarExistente() {
		popularNacionalidade();
		pessoaFisicaDAOComJpa.atualizar(this.pessoaFisica);
	}

	private void cadastratNovo() {
		popularNacionalidade();
		pessoaFisicaDAOComJpa.cadastrar(this.pessoaFisica);
	}

	private void popularNacionalidade() {
		nacionalidadeSelecionada = getNacionalidadeDAO().buscarById(idNacionalidade);
		pessoaFisica.setNacionalidade(nacionalidadeSelecionada);
	}

	public void remover() {
		pessoaFisicaDAOComJpa.deletar(pessoaFisica.getIdPessoaFisica());
		this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,	"Removido com sucesso!",null));
		this.pessoaFisicaList = listarTodos();
	}
	
	// CHAMADO AO ABRIR DIALOG ATUALIZAR
	public void atualizar(PessoaFisica pessoaFisica) {
		isEditando = true;
		this.pessoaFisica = pessoaFisica;
		this.idNacionalidade = pessoaFisica.getNacionalidade().getIdNacionalidade();
	}
	
	//chamado ao abrir o dialog
	public void novoCadastro(PessoaFisica pessoaFisica) {
		isEditando = false;
		this.pessoaFisica = new PessoaFisica();
		this.idNacionalidade = null;
	}
	
	public void filtrar() {
		if(Objects.nonNull(textoConsulta) || textoConsulta != "") {
			List<PessoaFisica> listaFiltrada = pessoaFisicaDAO.filtrar(textoConsulta.toLowerCase());
			this.pessoaFisicaList = listaFiltrada;			
		}
	}
	
	public void redirecionarParaOutraPagina() {
    	System.out.println("Redirecionando");
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.redirect("/crud_pessoa/nacionalidade.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
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
	
	public void opcaoSelecionada() {
		System.out.println(this.generoSelecionado);
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

	public PessoaFisicaDAO getPessoaFisicaDAOComJpa() {
		return pessoaFisicaDAOComJpa;
	}

	public void setPessoaFisicaDAOComJpa(PessoaFisicaDAO pessoaFisicaDAOComJpa) {
		this.pessoaFisicaDAOComJpa = pessoaFisicaDAOComJpa;
	}

	public Nacionalidade getNacionalidadeSelecionada() {
		return nacionalidadeSelecionada;
	}

	public void setNacionalidadeSelecionada(Nacionalidade nacionalidadeSelecionada) {
		this.nacionalidadeSelecionada = nacionalidadeSelecionada;
	}

	public List<Nacionalidade> getNacionalidadesList() {
		return nacionalidadesList;
	}

	public void setNacionalidadesList(List<Nacionalidade> nacionalidadesList) {
		this.nacionalidadesList = nacionalidadesList;
	}

	public NacionalidadeDAO getNacionalidadeDAO() {
		return nacionalidadeDAO;
	}

	public void setNacionalidadeDAO(NacionalidadeDAO nacionalidadeDAO) {
		this.nacionalidadeDAO = nacionalidadeDAO;
	}

	public Long getIdNacionalidade() {
		return idNacionalidade;
	}

	public void setIdNacionalidade(Long idNacionalidade) {
		this.idNacionalidade = idNacionalidade;
	}
	
}
