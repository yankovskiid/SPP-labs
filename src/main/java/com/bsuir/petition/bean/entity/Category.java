package com.bsuir.petition.bean.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories", schema = "petition_spring")
public class Category extends BaseTable{


    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Petition> petitions = new HashSet<Petition>();

    @Column(name = "category_name", nullable = false, unique = true, length = 128)
    private String name;

    public Set<Petition> getPetitions() {
        return petitions;
    }

    public void setPetitions(Set<Petition> petitions) {
        this.petitions = petitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
