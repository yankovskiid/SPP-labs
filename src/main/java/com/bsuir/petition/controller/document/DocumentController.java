package com.bsuir.petition.controller.document;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public interface DocumentController {
    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/document/comments/{type}")
    ModelAndView generateCommentsDocument(String type);

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/document/petitions/{type}")
    ModelAndView generatePetitionsDocument(String type);

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/document/petitions/{id}/{type}")
    ModelAndView generatePetitionDetailsDocument(long id, String type);
}
