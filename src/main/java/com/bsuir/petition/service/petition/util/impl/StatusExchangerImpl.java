package com.bsuir.petition.service.petition.util.impl;

import com.bsuir.petition.service.petition.util.StatusExchanger;
import org.springframework.stereotype.Component;

@Component
public class StatusExchangerImpl implements StatusExchanger {

    private final static String OPENED = "OPENED";
    private final static String CLOSED = "CLOSED";

    @Override
    public String getStatusName(int status) {
        String result = null;
        if (status == 1) {
            result = OPENED;
        } else if (status == 2) {
            result = CLOSED;
        }
        return result;
    }

    @Override
    public int getStatusId(String statusName) {
        int result = 0;
        String upperStatus = statusName.toUpperCase();
        if (OPENED.equals(upperStatus)) {
            result = 1;
        } else if (CLOSED.equals(upperStatus)) {
            result = 2;
        }
        return result;
    }
}
