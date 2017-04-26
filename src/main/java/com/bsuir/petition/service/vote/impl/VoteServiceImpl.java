package com.bsuir.petition.service.vote.impl;

import com.bsuir.petition.bean.dto.vote.ShortVoteDTO;
import com.bsuir.petition.bean.dto.vote.VoteListDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.Vote;
import com.bsuir.petition.dao.VoteDao;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.vote.VoteService;
import com.bsuir.petition.service.vote.exception.SuchVoteExistsException;
import com.bsuir.petition.service.vote.util.VoteDataValidator;
import com.bsuir.petition.service.vote.util.VoteDtoExchanger;
import com.bsuir.petition.service.vote.util.VoteExchanger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {

    private VoteDataValidator voteDataValidator;
    private VoteDao voteDao;
    private VoteDtoExchanger voteDtoExchanger;
    private VoteExchanger voteExchanger;

    @Autowired
    public void setVoteDataValidator(VoteDataValidator voteDataValidator) {
        this.voteDataValidator = voteDataValidator;
    }

    @Autowired
    public void setVoteDao(VoteDao voteDao) {
        this.voteDao = voteDao;
    }

    @Autowired
    public void setVoteDtoExchanger(VoteDtoExchanger voteDtoExchanger) {
        this.voteDtoExchanger = voteDtoExchanger;
    }

    @Autowired
    public void setVoteExchanger(VoteExchanger voteExchanger) {
        this.voteExchanger = voteExchanger;
    }

    @Override
    public VoteListDTO getVotes() throws ServerException {
        VoteListDTO voteListDTO;
        List<Vote> votes;
        try {
            votes = voteDao.getVotes();
            voteListDTO = voteDtoExchanger.getVoteListDTO(votes);
        } catch (HibernateException e) {
            throw new ServerException("Server exception!", e);
        }

        return voteListDTO;
    }

    @Override
    public void addVote(ShortVoteDTO shortVoteDTO, long id) throws ErrorInputException, ServerException, SuchVoteExistsException {
        voteDataValidator.validate(shortVoteDTO);
        Vote vote;
        vote = voteExchanger.getVote(shortVoteDTO, id);
        try {
            voteDao.addVote(vote);
        } catch (HibernateException e) {
            throw new SuchVoteExistsException("Server exception!", e);
        }
    }
}
