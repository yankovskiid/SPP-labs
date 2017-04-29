import {Category} from "./Category";
export class PetitionAdd {
     name: string;
     description: string;
     expiryDate: number;
     numberNecessaryVotes: number;
     categories: Array<Category> = [];


    constructor() {
        
    }
}