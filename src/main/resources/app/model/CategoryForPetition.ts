import {Category} from "./Category";
export class CategoryForPetition {
    private name: String;
    public isActive: boolean;

    static deserialize(input: any): CategoryForPetition {
        var res = new CategoryForPetition();
        res.name = input.name;
        res.isActive = false;
        return res;
    }

    static deserializeTwo(input: any): Category {
        var res = new Category();
        res.name = input.name;
        return res;
    }
}