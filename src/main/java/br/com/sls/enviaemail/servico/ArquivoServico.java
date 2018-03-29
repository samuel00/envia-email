package br.com.sls.enviaemail.servico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.sls.enviaemail.dto.EmailComAnexoDTO;
import br.com.sls.enviaemail.modelo.PropriedadeArquivo;

@Service
public class ArquivoServico {
	
	private static final String BARRA = File.separator;
	private static final String DIRETORIO_TEMPORARIO = System.getProperty("java.io.tmpdir") + BARRA;

	public static List<File> convertMultiPartFileParaLista(MultipartFile[] uploadfile) {
		List<File> listaDeArquivos = new ArrayList<File>();
		for (MultipartFile multiPartFile : uploadfile) {
			if (!multiPartFile.getOriginalFilename().isEmpty()) {
				try {
					File arquivo = new File(multiPartFile.getOriginalFilename());
					multiPartFile.transferTo(arquivo);
					listaDeArquivos.add(arquivo);
				} catch (FileNotFoundException e) {
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return listaDeArquivos;

	}

	public List<File> convertBase64ParaArquivo(EmailComAnexoDTO dto) {
		List<File> listaDeArquivo = new ArrayList<File>();
		for (PropriedadeArquivo propriedadeArquivo : dto.getPropriedadeArquivo()) {
			byte[] imageBytesDecode = Base64.getDecoder().decode(propriedadeArquivo.getBase64());
			try {
			FileOutputStream fos = new FileOutputStream( DIRETORIO_TEMPORARIO + propriedadeArquivo.getNomeArquivo() + propriedadeArquivo.getTipoArquivo());
				fos.write(imageBytesDecode);
				fos.close();
				listaDeArquivo.add(new File(DIRETORIO_TEMPORARIO + propriedadeArquivo.getNomeArquivo() + propriedadeArquivo.getTipoArquivo()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return listaDeArquivo;
	}

}
