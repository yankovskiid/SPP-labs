import { Vote } from "./Vote"

export class VoteList {
	private votes: Array<Vote>;
	
	constructor(votes: Array<Vote>) {
		this.votes = votes;
	}
}