package com.bsuir.petition.service.petition.util.impl;

import com.bsuir.petition.bean.dto.petition.AddPetitionDTO;
import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.service.category.util.ExchangerCategory;
import com.bsuir.petition.service.petition.util.PetitionExchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PetitionExchangerImpl implements PetitionExchanger {

    private ExchangerCategory exchangerCategory;

    @Autowired
    public void setExchangerCategory(ExchangerCategory exchangerCategory) {
        this.exchangerCategory = exchangerCategory;
    }

    @Override
    public Petition getPetition(AddPetitionDTO addPetitionDTO, User user) {
        Petition petition = new Petition();
        petition.setName(addPetitionDTO.getName());
        petition.setNumberNecessaryVotes(addPetitionDTO.getNumberNecessaryVotes());
        petition.setDescription(addPetitionDTO.getDescription());

        petition.setCreatedUser(user);
        petition.setExpiryDate(addPetitionDTO.getExpiryDate());

        Set<Category> categories;
        categories = exchangerCategory.getCategories(addPetitionDTO.getCategories());
        petition.setCategories(categories);

        return petition;
    }
}
