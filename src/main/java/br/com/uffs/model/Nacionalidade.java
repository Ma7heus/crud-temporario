package br.com.uffs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Nacionalidade {
	
	@Id
	@SequenceGenerator(name = "SEQUENCE_NACIONALIDADE",sequenceName = "NACIONALIDADE_IDNACIONALIDADE_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "SEQUENCE_NACIONALIDADE", strategy = GenerationType.SEQUENCE)
	private Long idNacionalidade;
	
	private String descricao;

	public Long getIdNacionalidade() {
		return idNacionalidade;
	}

	public void setIdNacionalidade(Long idNacionalidade) {
		this.idNacionalidade = idNacionalidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
