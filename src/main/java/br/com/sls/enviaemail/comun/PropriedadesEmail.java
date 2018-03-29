package br.com.sls.enviaemail.comun;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class PropriedadesEmail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8241028617405162968L;

	static {
		loadProperties();
	}

	public static Properties configuracoes;

	private static void loadProperties() {
		configuracoes = new Properties();
		InputStream in = PropriedadesEmail.class.getClassLoader().getResourceAsStream("email.properties");
		try {
			configuracoes.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}