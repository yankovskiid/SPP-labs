import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
@Component({
    selector: '[comment-edit]',
    templateUrl: 'app/components/petition/list/comment/edit/comment.edit.component.html',
    styleUrls: ['app/components/petition/list/comment/edit/comment.edit.component.css']
})
export class CommentEditComponent implements OnInit {
    @Input() comment: any;
    @Output() endEdit = new EventEmitter<boolean>();

    ngOnInit(): void {}

    constructor() {}

    save(temp: boolean) {
        this.endEdit.emit(temp);
    }
}