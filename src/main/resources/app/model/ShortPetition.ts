import { Category } from "./Category";

export class ShortPetition {
    id: number;

    name: string;
    description: string;
    status: string;
    numberNecessaryVotes: number;
    numberVotes: number;
    categories: Array<Category> = [];

    constructor() {
    }

    static deserialize(input: any): ShortPetition {
        var res = new ShortPetition();
        res.name = input.name;
        res.id = input.id;
        res.description = input.id;
        res.status = input.status;
        res.numberVotes = input.numberVotes;
        res.numberNecessaryVotes = input.numberNecessaryVotes;

        var temp = input.categories;
        for (var i = 0; i < temp.length; i++) {
            res.categories.push(Category.deserialize(temp[i]));
        }
        
        return res;
    }
}