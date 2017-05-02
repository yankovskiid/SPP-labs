package com.bsuir.petition.controller.vote;

import com.bsuir.petition.bean.dto.vote.ShortVoteDTO;
import com.bsuir.petition.bean.dto.vote.VoteListDTO;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import com.bsuir.petition.service.vote.exception.SuchVoteExistsException;
import com.bsuir.petition.service.vote.exception.VoteNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface VoteController {

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/petition/{id}/vote", method = RequestMethod.POST)
    void addVote(ShortVoteDTO shortVoteDTO, long id) throws ServerException, ErrorInputException, SuchVoteExistsException;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/petition/{id}/votesCount", method = RequestMethod.GET)
    long petitionVotesCount(long id) throws ServerException, PetitionNotFoundException;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/votes", method = RequestMethod.GET)
    VoteListDTO getVotes() throws ServerException;
}
