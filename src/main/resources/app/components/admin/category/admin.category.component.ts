import {Component, OnInit} from "@angular/core";
import {CategoryShort} from "../../../model/CategoryShort";
import {HttpService} from "../../../services/httpServices/http.service";
import {Category} from "../../../model/Category";

@Component({
    templateUrl: 'app/components/admin/category/admin.category.component.html',
    styleUrls: ['app/components/admin/category/admin.category.component.css']
})
export class AdminCategoryComponent implements OnInit {

    private editingCategory: CategoryShort = null;
    private categories: CategoryShort[] = [];

    ngOnInit(): void {
        this.getCategories();
    }

    constructor(private http: HttpService) {}

    editCategory(category: CategoryShort): void {
        if (this.editingCategory === null) {
            this.editingCategory = CategoryShort.deserialize(category);
        } else {
            alert('End editing the category');
        }
    }

    deleteCategory(category: CategoryShort): void {
        var isDelete = confirm("Are you sure to delete category?");
        if (isDelete) {
            if (this.editingCategory === null) {
                this.http
                    .deleteData("/category/" + category.id)
                    .subscribe(() => {
                        this.getCategories();
                    })
            } else {
                alert('End editing category!');
            }
        }
    }

    addCategory() {
        if (this.editingCategory === null) {
            this.editingCategory = new CategoryShort();
        } else {
            alert('End editing!');
        }
    }

    saveCategory(temp: boolean) {
        if (temp) {
            if (this.editingCategory.id) {
                this.http
                    .sendDataNoResponse("/category/" + this.editingCategory.id, Category.deserialize(this.editingCategory))
                    .subscribe(() => {
                        this.getCategories();
                        this.editingCategory = null;
                    })
            } else {
                this.http
                    .sendDataNoResponse("/category", Category.deserialize(this.editingCategory))
                    .subscribe(() => {
                        this.getCategories();
                        this.editingCategory = null;
                    })
            }
        } else {
            this.editingCategory = null;
        }
    }

    cancelEditing() {
        this.editingCategory = null;
    }

    private getCategories() {
        this.http
            .getData("/categories")
            .subscribe(data => {
                var temp = data.categories;
                var categoryArray: CategoryShort[] = [];
                for (var i = 0; i < temp.length; i++) {
                    categoryArray.push(CategoryShort.deserialize(temp[i]));
                }
                this.categories = categoryArray;
            })
    }
}