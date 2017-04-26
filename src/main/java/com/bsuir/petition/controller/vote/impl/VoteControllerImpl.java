package com.bsuir.petition.controller.vote.impl;

import com.bsuir.petition.bean.dto.vote.ShortVoteDTO;
import com.bsuir.petition.bean.dto.vote.VoteListDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.controller.vote.VoteController;
import com.bsuir.petition.dao.PetitionDao;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.vote.VoteService;
import com.bsuir.petition.service.vote.exception.SuchVoteExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteControllerImpl implements VoteController {

    private VoteService voteService;

    @Autowired
    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

    @Override
    public void addVote(@RequestBody  ShortVoteDTO shortVoteDTO, @PathVariable long id) throws ServerException, ErrorInputException, SuchVoteExistsException {
        voteService.addVote(shortVoteDTO, id);
    }

    @Override
    public VoteListDTO getVotes() throws ServerException {
        return voteService.getVotes();
    }
}
