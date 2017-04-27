import {UserInformation} from "./UserInformation";
import {ShortPetition} from "./ShortPetition";
import {Category} from "./Category";
export class Petition extends ShortPetition {
     expiryDate: number;
     userInformationDTO: UserInformation;

    constructor() {
        super();
    }
}