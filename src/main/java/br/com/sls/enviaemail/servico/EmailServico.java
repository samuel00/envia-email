package br.com.sls.enviaemail.servico;

import java.io.File;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.sls.enviaemail.comun.HTTPResponse;
import br.com.sls.enviaemail.dto.EmailDTO;
import br.com.sls.enviaemail.util.EmailUtil;

@Service
public class EmailServico {

	public HTTPResponse enviaSemail(EmailDTO emailDTO) {
		try {
			Session session = EmailUtil.getAutenticacao();
			Message message = EmailUtil.getMessage(session, emailDTO);
			Transport.send(message);
			return new HTTPResponse(HttpStatus.OK);
		} catch (MessagingException e) {
			e.printStackTrace();
			return new HTTPResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	public HTTPResponse enviaEmailComAnexo(List<File> listaDeArquivos, EmailDTO emailDTO) {
		try {			
			Session session = EmailUtil.getAutenticacao();
			Message message = EmailUtil.getMessage(session, emailDTO);
			message = EmailUtil.adicionaAnexo(listaDeArquivos, message, emailDTO);
			Transport.send(message);
			return new HTTPResponse(HttpStatus.OK);
		} catch (MessagingException e) {
			e.printStackTrace();
			return new HTTPResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
}
