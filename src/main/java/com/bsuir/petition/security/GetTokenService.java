package com.bsuir.petition.security;

public interface GetTokenService {
    String getToken(String email, String password) throws Exception;
}
