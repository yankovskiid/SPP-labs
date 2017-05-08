package com.bsuir.petition.controller.document.impl;

import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.controller.document.DocumentController;
import com.bsuir.petition.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DocumentControllerImpl implements DocumentController {

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public ModelAndView generateCommentsDocument() {
        List<Comment> comments = this.commentService.getAllComments();
        return new ModelAndView("pdfBuilder", "comments", comments);
    }
}
