package com.bsuir.petition.service.vote.util.impl;

import com.bsuir.petition.bean.dto.vote.ShortVoteDTO;
import com.bsuir.petition.bean.entity.Vote;
import com.bsuir.petition.dao.VoteDao;
import com.bsuir.petition.service.vote.util.VoteExchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteExchangerImpl implements VoteExchanger {

    private VoteDao voteDao;

    @Autowired
    public void setVoteDao(VoteDao voteDao) { this.voteDao = voteDao; }

    @Override
    public Vote getVote(ShortVoteDTO shortVoteDTO, long id) {
        Vote vote = voteDao.getVote(id);
        vote.setPetition(shortVoteDTO.getPetition());
        vote.setReason(shortVoteDTO.getReason());
        vote.setUser(shortVoteDTO.getUser());
        return vote;
    }

    @Override
    public Vote getVote(ShortVoteDTO shortVoteDTO) {
        Vote vote = new Vote();
        vote.setPetition(shortVoteDTO.getPetition());
        vote.setReason(shortVoteDTO.getReason());
        vote.setUser(shortVoteDTO.getUser());
        return vote;
    }
}
