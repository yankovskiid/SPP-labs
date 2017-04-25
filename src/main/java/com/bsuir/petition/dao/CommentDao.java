package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.Comment;

import java.util.List;

public interface CommentDao {
    Comment getCommentById(long id);

    void addComment(Comment comment);
    void deleteComment(Comment comment);
//    List<Comment> getComments();
    List<Comment> getComments(long petitionId);
}
