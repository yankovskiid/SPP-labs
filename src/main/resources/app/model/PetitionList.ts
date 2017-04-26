import {ShortPetition} from "./ShortPetition";
export class PetitionList {
    private petitions: Array<ShortPetition> = [];

    constructor(petitions: Array<ShortPetition>) {
        this.petitions = petitions;
    }
}