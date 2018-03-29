package br.com.sls.enviaemail.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
public class EmailDTO implements Serializable{

	private static final long serialVersionUID = 6187413527504516676L;
	
	@NotNull(message = "{error.destinatario.notnull}")
	private String destinatario;
	
	@NotNull(message = "{error.assunto.notnull}")
	private String assunto;
	
	@NotNull(message = "{error.texto.notnull}")
	private String texto;
	
	public EmailDTO() {}

	public EmailDTO(String destinatario, String assunto, String texto) {
		this.destinatario = destinatario;
		this.assunto= assunto;
		this.texto = texto;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
