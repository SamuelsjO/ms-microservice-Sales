package br.com.samuel.productapi.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConfigUtility {

    private Environment env;

    public ConfigUtility() {

    }

    public String getProperty(String pPropertyKey) {
        return this.env.getProperty(pPropertyKey);
    }

    public String getProperty(String pPropertyKey, String pDefault) {
        return this.env.getProperty(pPropertyKey, pDefault);
    }


}
