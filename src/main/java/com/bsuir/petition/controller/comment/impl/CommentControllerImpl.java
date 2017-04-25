package com.bsuir.petition.controller.comment.impl;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.dto.comment.CommentListDTO;
import com.bsuir.petition.controller.comment.CommentController;
import com.bsuir.petition.service.comment.CommentService;
import com.bsuir.petition.service.comment.exception.CommentNotFoundException;
import com.bsuir.petition.service.comment.exception.SuchCommentExistsException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentControllerImpl implements CommentController {

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void addComment(CommentDTO commentDTO, long id) throws ServerException, ErrorInputException, SuchCommentExistsException {
        commentService.addComment(commentDTO, id);
    }

    @Override
    public CommentListDTO getComments(long id) throws ServerException, PetitionNotFoundException {
        CommentListDTO commentListDTO;
        commentListDTO = commentService.getComments(id);
        return commentListDTO;
    }

    @Override
    public void deleteComment(long id) throws ServerException, CommentNotFoundException {
        commentService.deleteComment(id);
    }
}
