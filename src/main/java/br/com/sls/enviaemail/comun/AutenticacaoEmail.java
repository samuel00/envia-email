package br.com.sls.enviaemail.comun;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AutenticacaoEmail extends Authenticator{

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(PropriedadesEmail.configuracoes.getProperty("mail.smtp.usuario"),
				PropriedadesEmail.configuracoes.getProperty("mail.smtp.senha"));
	}
}
