package com.bsuir.petition.service.vote;

import com.bsuir.petition.bean.dto.vote.VoteListDTO;
import com.bsuir.petition.dao.PetitionDao;
import com.bsuir.petition.dao.VoteDao;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.vote.impl.VoteServiceImpl;
import com.bsuir.petition.service.vote.util.VoteDataValidator;
import com.bsuir.petition.service.vote.util.VoteDtoExchanger;
import com.bsuir.petition.service.vote.util.VoteExchanger;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Matchers.any;
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

    private void givenVoteDaoReturnsListOfVotes() {
        when(voteDao.getVotes())
                .thenReturn(Collections.emptyList());
    }

    private void givenVoteDaoThrowsHibernateException() {
        when(voteDao.getVotes())
                .thenThrow(HibernateException.class);
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
}
