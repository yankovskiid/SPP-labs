package com.bsuir.petition.service.comment.util.impl;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.dto.comment.CommentListDTO;
import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.service.comment.util.DtoExchangerComment;
import com.bsuir.petition.service.user.util.UserDtoExchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoExchangerCommentImpl implements DtoExchangerComment {

    private UserDtoExchanger userDtoExchanger;

    @Autowired
    public void setUserDtoExchanger(UserDtoExchanger userDtoExchanger) {
        this.userDtoExchanger = userDtoExchanger;
    }

    @Override
    public CommentDTO getCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setText(comment.getText());
        commentDTO.setUserNick(comment.getUser().getNick());

        return commentDTO;
    }

    @Override
    public CommentListDTO getCommentListDTO(List<Comment> comments) {
        CommentListDTO commentListDTO = new CommentListDTO();
        ArrayList<CommentDTO> commentDTOs = commentListDTO.getComments();

        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setText(comment.getText());
            commentDTO.setUserNick(comment.getUser().getNick());
            commentDTOs.add(commentDTO);
        }

        return commentListDTO;
    }
}
