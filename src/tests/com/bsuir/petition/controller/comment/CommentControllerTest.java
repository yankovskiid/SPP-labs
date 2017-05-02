package com.bsuir.petition.controller.comment;

import com.bsuir.petition.bean.dto.comment.CommentListDTO;
import com.bsuir.petition.bean.dto.comment.ShortCommentDTO;
import com.bsuir.petition.controller.comment.impl.CommentControllerImpl;
import com.bsuir.petition.service.comment.CommentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {

    @InjectMocks
    private CommentControllerImpl commentControllerImpl;

    @Mock
    private CommentService commentService;

    @Test
    public void getComment_petitionId_shouldReturnCommentListDto() throws Exception {
        when(commentService.getComments(anyLong())).thenReturn(new CommentListDTO());

        CommentListDTO comments = commentControllerImpl.getComments(0);

        Assert.assertNotNull(comments);
        verify(commentService, times(1)).getComments(anyLong());
    }

    @Test
    public void addComment_dto_id() throws Exception {
        commentControllerImpl.addComment(new ShortCommentDTO(), 0);

        verify(commentService, times(1)).addComment(any(), anyLong());
    }

    @Test
    public void deleteComment_id() throws Exception {
        commentControllerImpl.deleteComment(0);

        verify(commentService, times(1)).deleteComment(anyLong());
    }
}
