package br.com.samuel.productapi.jwt.service;

public interface JwtServiceInterface {

    void validateAuthorization(String token);
}
