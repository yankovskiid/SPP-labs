export class ShortVote {
	private reason: string;
	private petitionId: number;
	private userId: number;
	
	constructor(reason: string, petitionId: number, userId: number) {
		this.reason = reason;
		this.userId = userId;
		this.petitionId = petitionId;
	}
}