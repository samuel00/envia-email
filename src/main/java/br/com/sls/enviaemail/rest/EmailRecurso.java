package br.com.sls.enviaemail.rest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sls.enviaemail.comun.HTTPResponse;
import br.com.sls.enviaemail.dto.EmailComAnexoDTO;
import br.com.sls.enviaemail.dto.EmailComAnexoDeFormularioDTO;
import br.com.sls.enviaemail.dto.EmailDTO;
import br.com.sls.enviaemail.servico.ArquivoServico;
import br.com.sls.enviaemail.servico.EmailServico;

@RestController
public class EmailRecurso {

	@Autowired
	private EmailServico emailServico;

	@Autowired
	private ArquivoServico arquivoServico;

	private HTTPResponse httpResponse;

	@RequestMapping(method = RequestMethod.POST, value = "/simples", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> enviaEmailSimples(@RequestBody EmailDTO emailDTO, HttpServletRequest request) {
		httpResponse = emailServico.enviaSemail(emailDTO);
		return new ResponseEntity<HTTPResponse>(httpResponse, httpResponse.getStatus());

	}

	@SuppressWarnings("static-access")
	@RequestMapping(method = RequestMethod.POST, value = "/multipart/comanexo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> enviaEmailComAnexo(@Valid @ModelAttribute() EmailComAnexoDeFormularioDTO emailDto, BindingResult bindingResult,
			HttpServletRequest request) throws IOException {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<HTTPResponse>(new HTTPResponse(bindingResult.getFieldError().getDefaultMessage(),
					HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		List<File> listaDeArquivos = arquivoServico.convertMultiPartFileParaLista(emailDto.getArquivo());
		httpResponse = emailServico.enviaEmailComAnexo(listaDeArquivos, emailDto);
		return new ResponseEntity<HTTPResponse>(httpResponse, httpResponse.getStatus());

	}

	@RequestMapping(method = RequestMethod.POST, value = "/comanexo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> enviaEmailComAnexo(@Valid @RequestBody EmailComAnexoDTO emailDTO,
			BindingResult bindingResult, HttpServletRequest request) throws IOException {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<HTTPResponse>(new HTTPResponse(bindingResult.getFieldError().getDefaultMessage(),
					HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		List<File> listaDeArquivos = arquivoServico.convertBase64ParaArquivo(emailDTO);
		httpResponse = emailServico.enviaEmailComAnexo(listaDeArquivos, emailDTO);
		return new ResponseEntity<HTTPResponse>(httpResponse, httpResponse.getStatus());

	}

}
