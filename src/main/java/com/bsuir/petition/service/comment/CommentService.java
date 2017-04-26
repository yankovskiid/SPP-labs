package com.bsuir.petition.service.comment;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.dto.comment.CommentListDTO;
import com.bsuir.petition.bean.dto.comment.ShortCommentDTO;
import com.bsuir.petition.service.comment.exception.CommentNotFoundException;
import com.bsuir.petition.service.comment.exception.SuchCommentExistsException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;

public interface CommentService {
//    CommentListDTO getComments() throws ServerException;
    CommentListDTO getComments(long petitionId) throws ServerException, PetitionNotFoundException;
    void deleteComment(long id) throws CommentNotFoundException, ServerException;
    void addComment(ShortCommentDTO shortCommentDTO, long id) throws ErrorInputException, ServerException, SuchCommentExistsException, PetitionNotFoundException;
}
