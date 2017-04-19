package com.bsuir.petition.service.vote.util.impl;

import com.bsuir.petition.bean.dto.vote.VoteDTO;
import com.bsuir.petition.bean.dto.vote.VoteListDTO;
import com.bsuir.petition.bean.entity.Vote;
import com.bsuir.petition.service.vote.util.VoteDtoExchanger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VoteDtoExchangerImpl implements VoteDtoExchanger {
    @Override
    public VoteListDTO getVoteListDTO(List<Vote> votes) {
        VoteListDTO voteListDTO = new VoteListDTO();
        ArrayList<VoteDTO> voteDTOs = voteListDTO.getVotes();

        for (Vote vote : votes) {
            VoteDTO voteDTO = new VoteDTO();
            voteDTO.setId(vote.getId());
            voteDTO.setPetition(vote.getPetition());
            voteDTO.setReason(vote.getReason());
            voteDTO.setUserId(vote.getUser().getId());
            voteDTOs.add(voteDTO);
        }

        return voteListDTO;
    }
}
