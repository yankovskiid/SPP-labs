package com.bsuir.petition.service.vote.util;

import com.bsuir.petition.bean.dto.vote.ShortVoteDTO;
import com.bsuir.petition.bean.entity.Vote;

public interface VoteExchanger {
    Vote getVote(ShortVoteDTO shortVoteDTO, long id);
    Vote getVote(ShortVoteDTO shortVoteDTO);
}
