package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.Vote;

import java.util.List;

public interface VoteDao {
    Vote getVote(long id);
    List<Vote> getVotesByPetitionId(long petitionId);
    List<Vote> getVotes();
    void addVote(Vote vote);
}
