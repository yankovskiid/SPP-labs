package com.bsuir.petition.security.service;

import com.bsuir.petition.security.service.exception.AuthenticationException;

public interface GetTokenService {
    String getToken(String email, String password) throws AuthenticationException;
}
