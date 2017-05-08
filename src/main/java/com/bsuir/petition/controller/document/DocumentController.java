package com.bsuir.petition.controller.document;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public interface DocumentController {
    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/document/comments")
    ModelAndView generateCommentsDocument();
}
