package br.com.sls.enviaemail.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sls.enviaemail.modelo.PropriedadeArquivo;

public class EmailComAnexoDTO extends EmailDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3404480331904498577L;
	
	@NotNull(message="{error.lista.size}")
	@Size(min=1,message="{error.lista.size}")
	private List<PropriedadeArquivo> propriedadeArquivo;

	public List<PropriedadeArquivo> getPropriedadeArquivo() {
		return propriedadeArquivo;
	}

	public void setPropriedadeArquivo(List<PropriedadeArquivo> propriedadeArquivo) {
		this.propriedadeArquivo = propriedadeArquivo;
	}
}
