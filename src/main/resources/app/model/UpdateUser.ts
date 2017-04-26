export class UpdateUser {
    private isBlocked: boolean;
    private roles: Array<string> = [];

    constructor(isBlocked: boolean, roles: Array<string>) {
        this.isBlocked = isBlocked;
        this.roles = roles;
    }
}