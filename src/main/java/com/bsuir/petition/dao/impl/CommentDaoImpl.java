package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.dao.CommentDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentDaoImpl implements CommentDao{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Comment getCommentById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Comment comment;
        Query query = session.createQuery("from Comment where id = :commentId");
        query.setParameter("commentId", id);
        comment = (Comment)query.getSingleResult();
        return comment;
    }

    @Override
    public void addComment(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(comment);
    }

//    @Override
//    public List<Comment> getComments() {
//        Session session = sessionFactory.getCurrentSession();
//        List<Comment> comments = (List<Comment>) session.createQuery("from Comment ").list();
//        return comments;
//    }

    @Override
    public List<Comment> getComments(long petitionId) {
        Session session = sessionFactory.getCurrentSession();
        List<Comment> comments;
        Query query = session.createQuery("from Comment where petition_id = :idPetition");
        query.setParameter("idPetition", petitionId);
        comments = (List<Comment>)query.getResultList();
        return comments;
    }
}
