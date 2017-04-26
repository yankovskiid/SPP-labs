export class ShortUserInformation {
    private email: string;
    private nick: string;
    private id: number;

    constructor(email: string, nick: string, id: number) {
        this.email = email;
        this.nick = nick;
        this.id = id;
    }
}