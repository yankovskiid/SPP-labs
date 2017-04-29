import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
@Component({
    selector: '[category-edit]',
    templateUrl: 'app/components/admin/category/edit/category.edit.component.html',
    styleUrls: ['app/components/admin/category/edit/category.edit.component.css']
})
export class CategoryEditComponent implements OnInit {

    @Input() category : any;
    @Output() endEdit = new EventEmitter<boolean>();

    ngOnInit(): void {}

    constructor() {}

    save(temp: boolean) {
        this.endEdit.emit(temp);
    }
}