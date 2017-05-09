import {Category} from "./Category";
import {CategoryForPetition} from "./CategoryForPetition";
export class PetitionAdd {
     name: string;
     description: string;
     expiryDate: number;
     numberNecessaryVotes: number;
     categories: Array<Category> = [];


    constructor() {
        
    }

    static deserialize(data: any, categories: any): PetitionAdd {
        var res = new PetitionAdd();
        res.name = data.name;
        res.description = data.description;
        res.numberNecessaryVotes = data.numberNecessaryVotes;
        res.expiryDate = Date.now() + 604800000;

        for(var i = 0; i < categories.length; i++) {
            if(categories[i].isActive)
                res.categories.push(CategoryForPetition.deserializeTwo(categories[i]));
        }

        return res;
    }
}