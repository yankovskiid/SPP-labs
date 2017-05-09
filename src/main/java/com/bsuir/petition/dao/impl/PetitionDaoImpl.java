package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.dao.PetitionDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PetitionDaoImpl implements PetitionDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Petition> getPetitions() {
        Session session = sessionFactory.getCurrentSession();
        List<Petition> petitions;
        petitions = (List<Petition>) session.createQuery("from Petition ").list();
        return petitions;
    }

    @Override
    public List<Petition> getCompletedPetitions() {
        Session session = sessionFactory.getCurrentSession();
        List<Petition> petitions;
        petitions = (List<Petition>) session.createQuery("from Petition").list();
        List<Petition> completedPetitions = new ArrayList<>();
        for (Petition petition : petitions) {
            if (petition.getVoteSet().size() >= petition.getNumberNecessaryVotes()) {
                completedPetitions.add(petition);
            }
        }
        return completedPetitions;
    }

    @Override
    public Petition getPetition(long id) {
        Petition petition;
        Session session = sessionFactory.getCurrentSession();
        petition = session.get(Petition.class, id);
        return petition;
    }

    @Override
    public void addPetition(Petition petition) {
        Session session = sessionFactory.getCurrentSession();
        session.save(petition);
    }

    @Override
    public void deletePetition(Petition petition) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(petition);
    }
}
