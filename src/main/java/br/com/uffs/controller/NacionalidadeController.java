package br.com.uffs.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uffs.dao.NacionalidadeDAO;
import br.com.uffs.model.Nacionalidade;

@Named
@ViewScoped
public class NacionalidadeController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Nacionalidade nacionalidade;
	
	private Nacionalidade nacionalidadeSelecionada;
	
	@Inject
	private NacionalidadeDAO nacionalidadeDAO;
	
    private List<Nacionalidade> nacionalidadeList;

    @PostConstruct
	public void init() {
		this.nacionalidadeList = new ArrayList<>();		
		atualizarListagem();
	}
    
    public void salvar() {
    	if(Objects.isNull(nacionalidade.getIdNacionalidade())) {
    		getNacionalidadeDAO().cadastrar(nacionalidade);
    	} else {
    		getNacionalidadeDAO().atualizar(nacionalidade);
    	}
    	atualizarListagem();
        nacionalidade = new Nacionalidade();
    }

	public void remover() {
		getNacionalidadeDAO().deletar(nacionalidade.getIdNacionalidade());
		atualizarListagem();
    }
	
	public void atualizar(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

    public void novoCadastro() {
        nacionalidade = new Nacionalidade();
    }
    
    public void redirecionarParaOutraPagina() {
    	System.out.println("Redirecionando");
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.redirect("/crud_pessoa/pessoa-fisica.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void atualizarListagem() {
    	nacionalidadeList = getNacionalidadeDAO().buscarTodos();
    }

    public NacionalidadeController() {
    	nacionalidade = new Nacionalidade();
    	nacionalidadeList = new ArrayList<>();
    }
    
    public Nacionalidade getNacionalidade() {
    	return nacionalidade;
    }
    
    public void setNacionalidade(Nacionalidade nacionalidade) {
    	this.nacionalidade = nacionalidade;
    }
    
    public List<Nacionalidade> getNacionalidadeList() {
    	return nacionalidadeList;
    }

    public void setNacionalidadeList(List<Nacionalidade> nacionalidadeList) {
		this.nacionalidadeList = nacionalidadeList;
	}

	public NacionalidadeDAO getNacionalidadeDAO() {
		return nacionalidadeDAO;
	}

	public void setNacionalidadeDAO(NacionalidadeDAO nacionalidadeDAO) {
		this.nacionalidadeDAO = nacionalidadeDAO;
	}

	public Nacionalidade getNacionalidadeSelecionada() {
		return nacionalidadeSelecionada;
	}

	public void setNacionalidadeSelecionada(Nacionalidade nacionalidadeSelecionada) {
		this.nacionalidadeSelecionada = nacionalidadeSelecionada;
	}
    
}

