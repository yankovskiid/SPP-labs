import {Component, OnInit} from "@angular/core";
import {ShortVote} from "../../../../model/ShortVote";
import {HttpService} from "../../../../services/httpServices/http.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Vote} from "../../../../model/Vote";
import {AuthenticationService} from "../../../../services/httpServices/authenticationServices/authentication.service";
import {Petition} from "../../../../model/Petition";
@Component({
    selector: '[votes]',
    templateUrl: 'app/components/petition/list/votes/votes.component.html',
    styleUrls: ['app/components/petition/list/votes/votes.component.css']
})
export class VotesComponent implements OnInit {

    private votes: ShortVote[] = [];
    private votesCount: number = 0;
    private editingVote: ShortVote = null;
    private numberNecessaryVotes: number;

    constructor(private http: HttpService, private activatedRoute: ActivatedRoute, private authService: AuthenticationService) {}

    ngOnInit(): void {
        this.getVotesCount();
    }

    addVote() {
        if (this.editingVote === null) {
            this.editingVote = new ShortVote();
        } else {
            alert('End editing!');
        }
    }

    saveVote(temp: boolean) {
        if (temp) {
            this.activatedRoute.params.subscribe((params: Params) => {
                let petitionId = params['id'];
                this.http
                    .sendDataNoResponse("/petition/" + petitionId + "/vote", Vote.deserialize(this.editingVote))
                    .subscribe(() => {
                        this.getVotesCount();
                        this.editingVote = null;
                        var el = document.getElementById("vote-progress");
                        var progress = (this.votesCount + 1) / this.numberNecessaryVotes;
                        el.style.width = isNaN(progress) || !isFinite(progress) ? "100%" : (progress * 100) + "%";
                    })
            });
        } else {
            this.editingVote = null;
        }
    }

    getVotesCount() {
        this.activatedRoute.params.subscribe((params: Params) => {
            let petitionId = params['id'];
            this.http
                .getData("/petition/" + petitionId + "/votesCount")
                .subscribe(data => {
                    this.votesCount = data;
                    this.http
                        .getData("/petition/" + petitionId)
                        .subscribe(data => {
                            var petition = Petition.deserialize(data);
                            this.numberNecessaryVotes = petition.numberNecessaryVotes;
                            var el = document.getElementById("vote-progress");
                            var progress = this.votesCount / this.numberNecessaryVotes;
                            el.style.width = isNaN(progress) ? "100%" : (progress * 100) + "%";
                            console.log(this.votesCount);
                            console.log(this.numberNecessaryVotes);
                        });
                });
        })
    }
}