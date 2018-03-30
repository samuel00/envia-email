package br.com.sls.enviaemail.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class EmailComAnexoDeFormularioDTO extends EmailDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5540531425509484090L;
	
	@NotNull(message="{error.lista.size}")
	@Size(min=1,message="{error.lista.size}")
	private MultipartFile[] arquivo;

	public MultipartFile[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(MultipartFile[] arquivo) {
		this.arquivo = arquivo;
	}

}
