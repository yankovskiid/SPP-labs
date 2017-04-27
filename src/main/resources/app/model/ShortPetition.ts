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

        for (let temp  in input.categories) {
            res.categories.push(Category.deserialize(temp));
        }
        
        return res;
    }
}