package com.bsuir.petition.bean.dto.request;

public class UserRegistrationDTO extends UserLoginDTO {

    private String repeatPassword;

    private String nick;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
