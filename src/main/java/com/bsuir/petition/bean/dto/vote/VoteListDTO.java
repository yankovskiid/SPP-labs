package com.bsuir.petition.bean.dto.vote;

import java.util.ArrayList;

public class VoteListDTO {
    private ArrayList<VoteDTO> votes = new ArrayList<>();

    public ArrayList<VoteDTO> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<VoteDTO> votes) {
        this.votes = votes;
    }
}
