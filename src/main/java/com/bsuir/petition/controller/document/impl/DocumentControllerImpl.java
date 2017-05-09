package com.bsuir.petition.controller.document.impl;

import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.controller.document.DocumentController;
import com.bsuir.petition.service.comment.CommentService;
import com.bsuir.petition.service.petition.PetitionService;
import com.bsuir.petition.util.documents.DocumentTypes;
import com.bsuir.petition.util.documents.generate.Comments;
import com.bsuir.petition.util.documents.generate.Petitions;
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
    private PetitionService petitionService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setPetitionService(PetitionService petitionService) { this.petitionService = petitionService; }

    @Override
    public ModelAndView generateCommentsDocument(@PathVariable String type) {
        List<Comment> comments = this.commentService.getAllComments();
        return generateModel(type, new Comments(), comments);
    }

    @Override
    public ModelAndView generatePetitionsDocument(@PathVariable String type) {
        List<Petition> petitions = petitionService.getPetitionsList();
        return generateModel(type, new Petitions(), petitions);
    }

    @Override
    public ModelAndView generatePetitionDetailsDocument(@PathVariable long id, @PathVariable String type) {

        return null;
    }

    private ModelAndView generateModel(String type, Object object, Object model) {
        HashMap<String, Object> newModel = new HashMap<>();
        newModel.put("object", object);
        newModel.put("model", model);
        return new ModelAndView(DocumentTypes.getType(type), "model", newModel);
    }
}
