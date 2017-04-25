package com.bsuir.petition.controller.comment;


import com.bsuir.petition.bean.dto.comment.CommentListDTO;
import com.bsuir.petition.bean.dto.comment.ShortCommentDTO;
import com.bsuir.petition.service.comment.exception.CommentNotFoundException;
import com.bsuir.petition.service.comment.exception.SuchCommentExistsException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public interface CommentController {

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/petition/{id}/comment", method = RequestMethod.POST)
    void addComment(ShortCommentDTO shortCommentDTO, long id) throws ServerException, ErrorInputException, SuchCommentExistsException;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/petition/{id}/comments", method = RequestMethod.GET)
    CommentListDTO getComments(long id) throws ServerException, PetitionNotFoundException, PetitionNotFoundException;

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    void deleteComment(long id) throws ServerException, CommentNotFoundException;
}