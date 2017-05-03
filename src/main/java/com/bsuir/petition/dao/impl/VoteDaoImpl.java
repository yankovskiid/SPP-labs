package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.Comment;
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
    public List<Vote> getVotes() {
        Session session = sessionFactory.getCurrentSession();
        List<Vote> votes = session.createQuery("from Vote").list();
        return votes;
    }

    @Override
    public long petitionVotesCount(long id) {
        Session session = sessionFactory.getCurrentSession();
        long votesCount;
        org.hibernate.query.Query query = session.createQuery("select count(*) from Vote where petition_id = :idPetition");
        query.setParameter("idPetition", id);
        votesCount = (long)query.uniqueResult();
        return votesCount;
    }

    @Override
    public void addVote(Vote vote) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.query.Query query = session.createQuery("select count(*) from Vote WHERE petition = :idPetition AND user = :idUser");
        query.setParameter("idPetition", vote.getPetition().getId());
        query.setParameter("idUser", vote.getUser().getId());
        long votesCount = (long)query.uniqueResult();
        if (votesCount == 0)
            session.save(vote);
    }
}
