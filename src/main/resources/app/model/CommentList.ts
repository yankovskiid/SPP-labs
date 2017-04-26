import {Comment} from './Comment';

export class CommentList {
    private comments: Array<Comment> = [];


    constructor(comments: Array<Comment>) {
        this.comments = comments;
    }
}