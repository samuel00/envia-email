package br.com.sls.enviaemail.configuracao;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.sls.enviaemail")
@MultipartConfig
public class ConfiguracaoDaAplicacao {

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 10MB
		multipartResolver.setMaxUploadSizePerFile(1048576); // 1MB
		return multipartResolver;
	}
	
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
	  ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
	  messageBundle.setBasename("classpath:messages/messages");
	  messageBundle.setDefaultEncoding("UTF-8");
	  return messageBundle;
	}
}
