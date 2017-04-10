package com.bsuir.petition.service.category.util;

import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.bean.entity.Category;

public interface Exchanger {
    Category getCategory(ShortCategoryDTO shortCategoryDTO, long id);
    Category getCategory(ShortCategoryDTO categoryDTO);
}
