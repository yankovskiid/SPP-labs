import {Category} from "./Category";
export class ShortPetition {
     name: string;
     description: string;
     status: string;
     numberNecessaryVotes: number;
     numberVotes: number;
     categories: Array<Category> = [];

    constructor() {
    }
}