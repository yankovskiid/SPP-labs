import {Component, OnInit} from "@angular/core";
import {ShortComment} from "../../../../model/ShortComment";
import {HttpService} from "../../../../services/httpServices/http.service";
import {ActivatedRoute, Params} from "@angular/router";
@Component({
    selector: '[comments]',
    templateUrl: 'app/components/petition/list/comment/comment.list.component.html'
})
export class CommentListComponent implements OnInit {

    private editingComment: ShortComment = null;
    private comments: ShortComment[] = [];
    private http: HttpService;

    ngOnInit(): void {
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

    constructor(http: HttpService, private activatedRoute: ActivatedRoute){}
}