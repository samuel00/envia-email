package br.com.sls.enviaemail.modelo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class PropriedadeArquivo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7727014218025684L;
	
	@NotNull
	private String nomeArquivo;
	
	@NotNull
	private String tipoArquivo;
	
	@NotNull
	private String base64;

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}


}
