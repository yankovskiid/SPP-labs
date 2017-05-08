package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.Vote;
import com.bsuir.petition.dao.VoteDao;
import com.bsuir.petition.security.TokenAuthentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        query = session.createQuery("select count(*) from Vote where petition_id = :idPetition and user_id = :idUser");
        query.setParameter("idPetition", id);
        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication) SecurityContextHolder.getContext().getAuthentication();
        query.setParameter("idUser", (Long)tokenAuthentication.getDetails());
        long vote = (long)query.uniqueResult();
        if(vote > 0) {
            return votesCount * (-1);
        }
        return votesCount;
    }

    @Override
    public void addVote(Vote vote) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.query.Query query = session.createQuery("select count(*) from Vote WHERE petition_id = :idPetition AND user_id = :idUser");
        query.setParameter("idPetition", vote.getPetition().getId());
        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication) SecurityContextHolder.getContext().getAuthentication();
//        long id = vote.getUser().getId();
        query.setParameter("idUser", (Long)tokenAuthentication.getDetails());
        long votesCount = (long)query.uniqueResult();
        if (votesCount == 0)
            session.save(vote);
    }
}
