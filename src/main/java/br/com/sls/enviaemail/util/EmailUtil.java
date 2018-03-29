package br.com.sls.enviaemail.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.sls.enviaemail.comun.AutenticacaoEmail;
import br.com.sls.enviaemail.comun.PropriedadesEmail;
import br.com.sls.enviaemail.dto.EmailDTO;

public class EmailUtil {

	public static Session getAutenticacao() {
		Session session = Session.getInstance(PropriedadesEmail.configuracoes, new AutenticacaoEmail());
		return session;
	}

	public static Message getMessage(Session session, EmailDTO emailDTO) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(PropriedadesEmail.configuracoes.getProperty("mail.smtp.usuario"),
					PropriedadesEmail.configuracoes.getProperty("mail.smtp.alias")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDTO.getDestinatario()));
			message.setSubject(emailDTO.getAssunto());
			message.setText(emailDTO.getTexto());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}

	public static Message adicionaAnexo(List<File> listaDeArquivos, Message message, EmailDTO emailDTO) {
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			messageBodyPart.setText(emailDTO.getTexto());
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			for (File file : listaDeArquivos) {
				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(file);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(file.getName());
				multipart.addBodyPart(messageBodyPart);
				message.setContent(multipart);
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}

}
