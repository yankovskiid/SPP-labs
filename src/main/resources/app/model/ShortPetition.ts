import {Category} from "./Category";
export class ShortPetition {
    protected name: string;
    protected description: string;
    protected status: string;
    protected numberNecessaryVotes: number;
    protected numberVotes: number;
    private categories: Array<Category> = [];

    constructor(name: string, description: string, status: string, numberNecessaryVotes: number, numberVotes: number, categories: Array<Category>) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.numberNecessaryVotes = numberNecessaryVotes;
        this.numberVotes = numberVotes;
        this.categories = categories;
    }
}