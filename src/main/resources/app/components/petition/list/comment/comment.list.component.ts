import {Component, OnInit} from "@angular/core";
import {ShortComment} from "../../../../model/ShortComment";
import {HttpService} from "../../../../services/httpServices/http.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Comment} from "../../../../model/Comment";
import {AuthenticationService} from "../../../../services/httpServices/authenticationServices/authentication.service";
@Component({
    selector: '[comments]',
    templateUrl: 'app/components/petition/list/comment/comment.list.component.html'
})
export class CommentListComponent implements OnInit {

    private editingComment: ShortComment = null;
    private comments: ShortComment[] = [];

    constructor(private http: HttpService, private activatedRoute: ActivatedRoute, private authService: AuthenticationService){}

    ngOnInit(): void {
        this.getComments();
    }

    getComments() {
        this.activatedRoute.params.subscribe((params: Params) => {
            let petitionId = params['id'];
            this.http
                .getData("/petition/" + petitionId + "/comments")
                .subscribe(data => {
                    var temp = data.comments;
                    var commentArray: ShortComment[] = [];
                    for (var i = 0; i < temp.length; i++) {
                        commentArray.push(ShortComment.deserialize(temp[i]));
                    }
                    this.comments = commentArray;
                });
        });
    }

    addComment() {
        if (this.editingComment === null) {
            this.editingComment = new ShortComment();
        } else {
            alert('End ending!');
        }
    }

    saveComment(temp: boolean) {
        if (temp) {
            this.activatedRoute.params.subscribe((params: Params) => {
                let petitionId = params['id'];
                this.http
                    .sendDataNoResponse("/petition/" + petitionId + "/comment", Comment.deserialize(this.editingComment))
                    .subscribe(() => {
                        this.getComments();
                        this.editingComment = null;
                    })
            });
        } else {
            this.editingComment = null;
        }
    }

    deleteComment(comment: ShortComment): void {
        var isDelete = confirm("Are you sure to delete category?");
        if (isDelete) {
            if (this.editingComment === null) {
                this.http
                    .deleteData("/category/" + comment)
                    .subscribe(() => {
                        this.getComments();
                    })
            } else {
                alert('End editing category!');
            }
        }
    }
}