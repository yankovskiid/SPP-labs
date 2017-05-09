package com.bsuir.petition.controller.document.impl;

import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.controller.document.DocumentController;
import com.bsuir.petition.service.comment.CommentService;
import com.bsuir.petition.util.documents.DocumentTypes;
import com.bsuir.petition.util.documents.generate.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DocumentControllerImpl implements DocumentController {

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public ModelAndView generateCommentsDocument(@PathVariable String type) {
        List<Comment> comments = this.commentService.getAllComments();
        HashMap<String, Object> model = new HashMap<>();
        model.put("object", new Comments());
        model.put("model", comments);
        return new ModelAndView(DocumentTypes.getType(type), "model", model);
    }
}
