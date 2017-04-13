package com.bsuir.petition.bean.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cities", schema = "petition_spring")
public class City extends BaseTable{

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Set<UserInformation> userInformations = new HashSet<UserInformation>();

    public Set<UserInformation> getUserInformations() {
        return userInformations;
    }

    public void setUserInformations(Set<UserInformation> userInformations) {
        this.userInformations = userInformations;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country countryId) {
        this.country = countryId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
