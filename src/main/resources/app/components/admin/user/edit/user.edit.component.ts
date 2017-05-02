import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';

@Component({
    selector: '[user-edit]',
    templateUrl: 'app/components/admin/user/edit/user.edit.component.html',
    styleUrls: ['app/components/admin/user/edit/user.edit.component.css']
})
export class UserEditComponent implements OnInit {

    @Input() user : any;
    @Output() endEdit = new EventEmitter<boolean>();

    ngOnInit(): void {

    }

    constructor() {

    }

    save(temp : boolean) {
        this.endEdit.emit(temp);
    }

}