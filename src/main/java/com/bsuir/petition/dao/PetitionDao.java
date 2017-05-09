package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.Petition;

import java.util.List;

public interface PetitionDao {
    List<Petition> getPetitions();
    List<Petition> getCompletedPetitions();
    Petition getPetition(long id);
    void addPetition(Petition petition);
    void deletePetition(Petition petition);
}
