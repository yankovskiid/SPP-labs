package com.bsuir.petition.service.vote.util;

import com.bsuir.petition.bean.dto.vote.VoteListDTO;
import com.bsuir.petition.bean.entity.Vote;

import java.util.List;

public interface VoteDtoExchanger {
    VoteListDTO getVoteListDTO(List<Vote> votes);
}
