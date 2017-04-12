package com.bsuir.petition.bean.dto.petition;

import java.util.ArrayList;

public class PetitionListDTO {
    private ArrayList<ShortPetitionDTO> petitions = new ArrayList<>(0);

    public ArrayList<ShortPetitionDTO> getPetitions() {
        return petitions;
    }

    public void setPetitions(ArrayList<ShortPetitionDTO> petitions) {
        this.petitions = petitions;
    }
}
