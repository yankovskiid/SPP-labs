package com.bsuir.petition.service.petition.util;

public interface StatusExchanger {
    String getStatusName(int status);
    int getStatusId(String statusName);
}
