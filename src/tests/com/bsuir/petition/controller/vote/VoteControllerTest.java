package com.bsuir.petition.controller.vote;

import com.bsuir.petition.bean.dto.vote.ShortVoteDTO;
import com.bsuir.petition.bean.dto.vote.VoteListDTO;
import com.bsuir.petition.controller.vote.impl.VoteControllerImpl;
import com.bsuir.petition.service.vote.VoteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VoteControllerTest {

    @InjectMocks
    private VoteControllerImpl voteControllerImpl;

    @Mock
    private VoteService voteService;

    @Test
    public void getVotes_shouldReturnVoteListDto() throws Exception {
        when(voteService.getVotes()).thenReturn(new VoteListDTO());

        VoteListDTO votes = voteControllerImpl.getVotes();

        Assert.assertNotNull(votes);
        verify(voteService, times(1)).getVotes();
    }

    @Test
    public void addVote_dto_petitionId() throws Exception {
        voteControllerImpl.addVote(new ShortVoteDTO(), 0);

        verify(voteService, times(1)).addVote(any(), anyLong());
    }
}
