package com.bsuir.petition.service.comment.impl;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.dto.comment.CommentListDTO;
import com.bsuir.petition.bean.dto.comment.ShortCommentDTO;
import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.dao.CommentDao;
import com.bsuir.petition.dao.PetitionDao;
import com.bsuir.petition.service.comment.CommentService;
import com.bsuir.petition.service.comment.exception.CommentNotFoundException;
import com.bsuir.petition.service.comment.exception.SuchCommentExistsException;
import com.bsuir.petition.service.comment.util.CommentDataValidator;
import com.bsuir.petition.service.comment.util.DtoExchangerComment;
import com.bsuir.petition.service.comment.util.ExchangerComment;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentDataValidator commentDataValidator;
    private CommentDao commentDao;
    private PetitionDao petitionDao;
    private ExchangerComment exchangerComment;
    private DtoExchangerComment dtoExchangerComment;


    @Autowired
    public void setCommentDataValidator(CommentDataValidator commentDataValidator) {
        this.commentDataValidator = commentDataValidator;
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Autowired
    public void setExchangerComment(ExchangerComment exchangerComment) {
        this.exchangerComment = exchangerComment;
    }

    @Autowired
    public void setDtoExchangerComment(DtoExchangerComment dtoExchangerComment) {
        this.dtoExchangerComment = dtoExchangerComment;
    }

    @Autowired
    public void setPetitionDao(PetitionDao petitionDao) {
        this.petitionDao = petitionDao;
    }

    @Override
    public CommentListDTO getComments(long petitionId) throws ServerException, PetitionNotFoundException {
        Petition petition = petitionDao.getPetition(petitionId);
        if (petition == null) {
            throw new PetitionNotFoundException("Petition not found!");
        }

        CommentListDTO commentListDTO;
        List<Comment> comments;

        try {
            comments = commentDao.getComments(petitionId);
            commentListDTO = dtoExchangerComment.getCommentListDTO(comments);
        } catch (HibernateException exception) {
            throw new ServerException("Server exception!", exception);
        }
        return commentListDTO;
    }

    @Override
    public void deleteComment(long id) throws CommentNotFoundException, ServerException {
        try {
            Comment comment;
            comment = commentDao.getCommentById(id);

            if (comment == null) {
                throw new CommentNotFoundException("Comment not found!");
            }

            commentDao.deleteComment(comment);
        } catch (HibernateException exception) {
            throw new ServerException("Such comment does", exception);
        }
    }

    @Override
    public void addComment(ShortCommentDTO shortCommentDTO, long id) throws ErrorInputException, ServerException, SuchCommentExistsException {
        commentDataValidator.validate(shortCommentDTO);

        Comment comment;
        comment = exchangerComment.getComment(shortCommentDTO, id);
        try {
            commentDao.addComment(comment);
        } catch (HibernateException exception) {
            throw new SuchCommentExistsException("Server exception!", exception);
        }
    }
}
