package com.bsuir.petition.bean.dto.request;

public class UserRegistrationDTO extends UserLoginDTO {

    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
