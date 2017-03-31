package com.bsuir.petition.security.service;

public interface GetTokenService {
    String getToken(String email, String password) throws Exception;
}
