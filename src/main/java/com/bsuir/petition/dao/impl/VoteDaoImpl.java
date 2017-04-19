package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.Vote;
import com.bsuir.petition.dao.VoteDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class VoteDaoImpl implements VoteDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    @Override
    public Vote getVote(long id) {
        Session session = sessionFactory.getCurrentSession();
        Vote vote = session.get(Vote.class, id);
        return vote;
    }

    @Override
    public List<Vote> getVotesByPetitionId(long petitionId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Vote where Vote.petition = :petitionId");
        query.setParameter("petitionId", petitionId);
        List<Vote> votes = (List<Vote>) query.getResultList();
        return votes;
    }

    @Override
    public List<Vote> getVotes() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Vote");
        List<Vote> votes = (List<Vote>) query.getResultList();
        return votes;
    }

    @Override
    public void addVote(Vote vote) {
        Session session = sessionFactory.getCurrentSession();
        session.save(vote);
    }
}
