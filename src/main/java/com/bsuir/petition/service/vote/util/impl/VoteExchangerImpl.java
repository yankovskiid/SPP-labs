package com.bsuir.petition.service.vote.util.impl;

import com.bsuir.petition.bean.dto.vote.ShortVoteDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.Vote;
import com.bsuir.petition.dao.PetitionDao;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.dao.VoteDao;
import com.bsuir.petition.security.TokenAuthentication;
import com.bsuir.petition.service.vote.util.VoteExchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class VoteExchangerImpl implements VoteExchanger {

    private VoteDao voteDao;
    private PetitionDao petitionDao;
    private UserDao userDao;

    @Autowired
    public void setVoteDao(VoteDao voteDao) { this.voteDao = voteDao; }

    @Override
    public Vote getVote(ShortVoteDTO shortVoteDTO, long id) {
        Vote vote = new Vote();
        vote.setPetition(petitionDao.getPetition(id));
        vote.setReason(shortVoteDTO.getReason());

        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication) SecurityContextHolder.getContext().getAuthentication();

        vote.setUser(userDao.getUserById((Long)tokenAuthentication.getDetails()));
        return vote;
    }
}
