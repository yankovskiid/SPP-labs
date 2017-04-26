package com.bsuir.petition.service.vote;

import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.bean.dto.vote.ShortVoteDTO;
import com.bsuir.petition.bean.dto.vote.VoteListDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.Vote;
import com.bsuir.petition.service.category.exception.SuchCategoryExistsException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.vote.exception.SuchVoteExistsException;

public interface VoteService {
    VoteListDTO getVotes() throws ServerException;
    void addVote(ShortVoteDTO shortVoteDTO, long id) throws ErrorInputException, ServerException, SuchVoteExistsException;
}
