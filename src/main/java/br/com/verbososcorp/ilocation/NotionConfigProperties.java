package br.com.verbososcorp.ilocation;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("notion")
public record NotionConfigProperties(String secretKey, String dbPassword) {

}
