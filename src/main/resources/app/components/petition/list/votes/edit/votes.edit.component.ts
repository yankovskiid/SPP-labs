import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
@Component({
    selector: '[vote-edit]',
    templateUrl: 'app/components/petition/list/votes/edit/votes.edit.component.html'
})
export class VotesEditComponent implements OnInit {
    @Input() vote: any;
    @Output() endEdit = new EventEmitter<boolean>();

    ngOnInit(): void {}

    constructor() {}

    save (temp: boolean) {
        this.endEdit.emit(temp);
    }
}