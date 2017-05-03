package com.bsuir.petition.service.comment;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.dto.comment.CommentListDTO;
import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.dao.CommentDao;
import com.bsuir.petition.dao.PetitionDao;
import com.bsuir.petition.service.comment.exception.CommentNotFoundException;
import com.bsuir.petition.service.comment.impl.CommentServiceImpl;
import com.bsuir.petition.service.comment.util.CommentDataValidator;
import com.bsuir.petition.service.comment.util.DtoExchangerComment;
import com.bsuir.petition.service.comment.util.ExchangerComment;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    @Mock
    private CommentDataValidator commentDataValidator;

    @Mock
    private CommentDao commentDao;

    @Mock
    private DtoExchangerComment dtoExchangerComment;

    @Mock
    private ExchangerComment exchangerComment;

    @Mock
    private PetitionDao petitionDao;

    /**
     * Get comments by petition id
     * CommentService.getComments(petitionId);
     *
     * @throws Exception Should return list of comments
     */
    @Test
    public void getComments_petitionId_shouldReturnListOfComments() throws Exception {
        givenPetitionDaoReturnsPetitionById();
        givenCommentDaoReturnsListOfCommentsByPetitionId();
        givenDtoExchangerCommentReturnsListOfCommentDto();

        CommentListDTO actualDto = commentServiceImpl.getComments(0);

        Assert.assertNotNull(actualDto);
        verifyThatPetitionDaoWasCalled();
        verifyThatCommentDaoWasCalledByPetitionId();
        verifyThatDtoExchangerComment();
    }

    @Test(expected = PetitionNotFoundException.class)
    public void getComments_petitionId_shouldReturnThrowException() throws Exception {
        givenPetitionDaoReturnsNull();
        commentServiceImpl.getComments(0);
    }

    @Test(expected = ServerException.class)
    public void getComments_petitionId_hibernateErrorInCommentDao_shouldThrowException() throws Exception {
        givenPetitionDaoReturnsPetitionById();
        givenCommentDaoByPetitionIdThrowsHibernateException();
        givenDtoExchangerCommentReturnsListOfCommentDto();

        commentServiceImpl.getComments(0);
    }

    /**
     * Delete comment by comment id
     * CommentService.deleteComment(commentId)
     *
     * @throws Exception
     */
    @Test
    public void deleteComment_commentId() throws Exception {
        givenCommentDaoByCommentId();
        commentServiceImpl.deleteComment(0);
        verifyThatCommentDaoWasCalledByCommentId();
        verifyThatDeleteInCommentDaoWasCalled();
    }

    @Test(expected = CommentNotFoundException.class)
    public void deleteComment_commentId_shouldThrowException() throws Exception {
        givenCommentDaoByCommentIdThrowsException();

        commentServiceImpl.deleteComment(0);
    }

    @Test(expected = ServerException.class)
    public void deleteComment_commentId_hibernateException_shouldThrowException() throws Exception {
        givenCommentDaoByCommentIdThrowsHibernateException();

        commentServiceImpl.deleteComment(0);
    }

    /**
     * Add comment by commentDto and petition id
     * CommentService.addComment(commentDto, petitionId)
     * @throws Exception
     */
    @Test
    public void addComment_commentDto_petitionId() throws Exception {
        givenPetitionDaoReturnsPetitionById();
        givenDtoExchangerCommentByCommentDtoAndPetitionIdReturnsComment();

        commentServiceImpl.addComment(any(), 0);

        verifyThatPetitionDaoWasCalled();
        verifyThatCommentDataValidatorWasCalledByCommentDto();
        verifyThatExchangerCommentWasCalledByCommentDto();
        verifyThatCommentDaoAddCommentWasCalled();
    }

    @Test(expected = PetitionNotFoundException.class)
    public void addComment_commentDto_petitionId_shouldThrowException() throws Exception {
        givenPetitionDaoByPetitionIdThrowsException();

        commentServiceImpl.addComment(any(), 0);
    }

    @Test(expected = HibernateException.class)
    public void addComment_commentDao_petitionId_hibernateException_shouldThrowException() throws Exception {
        givenPetitionDaoReturnsPetitionById();
        givenPetitionDaoByPetitionIdThrowsHibernateException();

        commentServiceImpl.addComment(any(), 0);
    }

    private void givenCommentDaoReturnsListOfCommentsByPetitionId() {
        when(commentDao.getComments(anyLong()))
                .thenReturn(Collections.emptyList());
    }

    private void givenPetitionDaoReturnsPetitionById() {
        when(petitionDao.getPetition(anyLong()))
                .thenReturn(new Petition());
    }

    private void givenDtoExchangerCommentReturnsListOfCommentDto() {
        when(dtoExchangerComment.getCommentListDTO(any()))
                .thenReturn(new CommentListDTO());
    }

    private void givenDtoExchangerCommentByCommentDtoAndPetitionIdReturnsComment() {
        when(exchangerComment.getComment(any(), anyLong()))
                .thenReturn(new Comment());
    }

    private void verifyThatPetitionDaoWasCalled() {
        verify(petitionDao, times(1)).getPetition(anyLong());
    }

    private void verifyThatCommentDaoWasCalledByPetitionId() {
        verify(commentDao, times(1)).getComments(anyLong());
    }

    private void verifyThatDtoExchangerComment() {
        verify(dtoExchangerComment, times(1)).getCommentListDTO(any());
    }

    private void verifyThatCommentDaoWasCalledByCommentId() {
        verify(commentDao, times(1)).getCommentById(anyLong());
    }

    private void verifyThatDeleteInCommentDaoWasCalled() {
        verify(commentDao, times(1)).deleteComment(any());
    }

    private void verifyThatCommentDataValidatorWasCalledByCommentDto() throws ErrorInputException {
        verify(commentDataValidator, times(1))
                .validate(any());
    }

    private void verifyThatExchangerCommentWasCalledByCommentDto() {
        verify(exchangerComment, times(1))
                .getComment(any(), anyLong());
    }

    private void verifyThatCommentDaoAddCommentWasCalled() {
        verify(commentDao, times(1))
                .addComment(any());
    }

    private void givenPetitionDaoByPetitionIdThrowsException() {
        when(petitionDao.getPetition(anyLong()))
                .thenThrow(PetitionNotFoundException.class);
    }

    private void givenPetitionDaoByPetitionIdThrowsHibernateException() {
        when(petitionDao.getPetition(anyLong()))
                .thenThrow(HibernateException.class);
    }

    private void givenPetitionDaoReturnsNull() {
        when(petitionDao.getPetition(anyLong()))
                .thenReturn(null);
    }

    private void givenCommentDaoByCommentId() {
        when(commentDao.getCommentById(anyLong()))
                .thenReturn(new Comment());
    }

    private void givenCommentDaoByCommentIdThrowsException() {
        when(commentDao.getCommentById(anyLong()))
                .thenThrow(CommentNotFoundException.class);
    }

    private void givenCommentDaoByCommentIdThrowsHibernateException() {
        when(commentDao.getCommentById(anyLong()))
                .thenThrow(HibernateException.class);
    }

    private void givenCommentDaoByPetitionIdReturnsListOfComments() {
        when(commentDao.getComments(anyLong()))
                .thenReturn(Collections.emptyList());
    }

    private void givenCommentDaoByPetitionIdThrowsHibernateException() {
        when(commentDao.getComments(anyLong()))
                .thenThrow(HibernateException.class);
    }

    private void givenDtoExchangerCommentByCommentDtoThrowsException() {
        when(dtoExchangerComment.getCommentDTO(any()))
            .thenThrow(CommentNotFoundException.class);
    }
}