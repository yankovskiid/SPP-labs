import {Category} from "./Category";
export class PetitionAdd {
    private name: string;
    private description: string;
    private expiryDate: number;
    private numberNecessaryVotes: number;
    private categories: Array<Category> = [];


    constructor(name: string, description: string, expiryDate: number, numberNecessaryVotes: number, categories: Array<Category>) {
        this.name = name;
        this.description = description;
        this.expiryDate = expiryDate;
        this.numberNecessaryVotes = numberNecessaryVotes;
        this.categories = categories;
    }
}