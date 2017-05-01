package com.bsuir.petition.service.vote;

import com.bsuir.petition.bean.dto.vote.VoteListDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.Vote;
import com.bsuir.petition.dao.PetitionDao;
import com.bsuir.petition.dao.VoteDao;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import com.bsuir.petition.service.vote.impl.VoteServiceImpl;
import com.bsuir.petition.service.vote.util.VoteDataValidator;
import com.bsuir.petition.service.vote.util.VoteDtoExchanger;
import com.bsuir.petition.service.vote.util.VoteExchanger;
import com.sun.security.ntlm.Server;
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
public class VoteServiceTest {

    @InjectMocks
    private VoteServiceImpl voteServiceImpl;

    @Mock
    private VoteDataValidator voteDataValidator;

    @Mock
    private VoteDao voteDao;

    @Mock
    private VoteDtoExchanger voteDtoExchanger;

    @Mock
    private VoteExchanger voteExchanger;

    @Mock
    private PetitionDao petitionDao;

    /**
     * Get list of votes
     * VoteService.getVotes()
     * @throws Exception
     */
    @Test
    public void getVotes_shouldReturnListOfVotes() throws Exception {
        givenVoteDaoReturnsListOfVotes();
        givenDtoExchangerVoteReturnsListOfVoteDto();

        VoteListDTO actualDto = voteServiceImpl.getVotes();

        Assert.assertNotNull(actualDto);
        verifyThatVoteDaoWasCalled();
        verifyThatDtoExchangerVote();
    }

    @Test(expected = ServerException.class)
    public void getVotes_hibernateErrorInVoteDao_shouldThrowException() throws Exception {
        givenVoteDaoThrowsHibernateException();
        givenDtoExchangerVoteReturnsListOfVoteDto();

        voteServiceImpl.getVotes();
    }

    @Test(expected = RuntimeException.class)
    public void getVotes_exceptionInExchanger_shouldThrowException() throws Exception {
        givenVoteDaoReturnsListOfVotes();
        givenDtoExchangerVoteThrowsException();

        voteServiceImpl.getVotes();
    }

    /**
     * Get votes count by petition id
     * VoteService.petitionVotesCount(petitionId)
     */
    @Test
    public void getVotesCount_petitionId_shouldReturnVotesCount() throws Exception {
        givenPetitionDaoReturnsPetitionById();
        givenVoteDaoReturnsVotesCountByPetitionId();

        long votesCount = voteServiceImpl.petitionVotesCount(0);

        Assert.assertNotNull(votesCount);
    }

    @Test(expected = PetitionNotFoundException.class)
    public void getVotesCount_petitionId_shouldThrowException() throws Exception {
        givenPetitionDaoReturnsNull();
        voteServiceImpl.petitionVotesCount(0);
    }

    @Test(expected = ServerException.class)
    public void getVotesCount_petitionId_hibernateErrorInVoteDao_shouldThrowException() throws Exception {
        givenPetitionDaoReturnsPetitionById();
        givenVoteDaoByPetitionIdThrowsHibernateException();

        voteServiceImpl.petitionVotesCount(0);
    }

    @Test
    public void addVote_voteDao_petitionId() throws Exception {
        givenExchangerVoteByVoteDtoAndPetitionIdReturnsVote();

        voteServiceImpl.addVote(any(), 0);

        verifyThatVoteDataValidatorWasCalledByVoteDto();
        verifyThatExchangerVoteWasCalledByVoteDto();
        verifyThatVoteDaoAddVoteWasCalled();
    }

    public void givenExchangerVoteByVoteDtoAndPetitionIdReturnsVote() {
        when(voteExchanger.getVote(any(), anyLong()))
                .thenReturn(new Vote());
    }

    public void verifyThatExchangerVoteWasCalledByVoteDto() {
        verify(voteExchanger, times(1))
                .getVote(any(), anyLong());
    }

    public void verifyThatVoteDaoAddVoteWasCalled() {
        verify(voteDao, times(1))
                .addVote(any());
    }

    public void verifyThatVoteDataValidatorWasCalledByVoteDto() throws ErrorInputException {
        verify(voteDataValidator, times(1))
                .validate(any());
    }

    private void givenPetitionDaoReturnsNull() {
        when(petitionDao.getPetition(anyLong()))
                .thenReturn(null);
    }

    private void givenVoteDaoByPetitionIdThrowsHibernateException() {
        when(voteDao.petitionVotesCount(anyLong()))
                .thenThrow(HibernateException.class);
    }

    private void givenVoteDaoReturnsListOfVotes() {
        when(voteDao.getVotes())
                .thenReturn(Collections.emptyList());
    }

    private void givenPetitionDaoReturnsPetitionById() {
        when(petitionDao.getPetition(anyLong()))
                .thenReturn(new Petition());
    }

    private void givenVoteDaoThrowsHibernateException() {
        when(voteDao.getVotes())
                .thenThrow(HibernateException.class);
    }

    private void givenVoteDaoReturnsVotesCountByPetitionId() {
        when(voteDao.petitionVotesCount(anyLong()))
                .thenReturn(anyLong());
    }

    private void givenDtoExchangerVoteThrowsException() {
        when(voteDtoExchanger.getVoteListDTO(any()))
                .thenThrow(new RuntimeException());
    }

    private void givenDtoExchangerVoteReturnsListOfVoteDto() {
        when(voteDtoExchanger.getVoteListDTO(any()))
                .thenReturn(new VoteListDTO());
    }

    private void verifyThatVoteDaoWasCalled() {
        verify(voteDao, times(1))
                .getVotes();
    }

    private void verifyThatDtoExchangerVote() {
        verify(voteDtoExchanger, times(1))
                .getVoteListDTO(any());
    }

    private void verifyThatVoteDaoWasCalledByPetition() {
        verify(voteDao, times(1))
                .petitionVotesCount(anyLong());
    }
}
