package com.bsuir.petition.bean.entity;

import javax.persistence.*;

@Entity
@Table(name = "comments", schema = "petition_spring")
public class Comment extends BaseTable {

    @Column(name = "text", nullable = false, columnDefinition = "VARCHAR(1024)")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petition_id", nullable = false)
    private Petition petition;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Petition getPetition() {
        return petition;
    }

    public void setPetition(Petition petition) {
        this.petition = petition;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
