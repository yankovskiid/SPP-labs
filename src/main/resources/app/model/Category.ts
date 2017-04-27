import {CategoryShort} from "./CategoryShort";

export class Category extends CategoryShort {
    id: number;


    constructor() {
        super();
    }

    static deserialize(input: any): Category {
        var res = new Category();
        res.name = input.name;
        res.id = input.id;
        return res;
    }
}