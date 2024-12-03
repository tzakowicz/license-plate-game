import { Component, OnInit, output } from '@angular/core';
import { GameService } from '../service/game.service';
import { PlayerScore } from '../model/player-score.type';
import { Country } from '../model/country.type';
import { Territory } from '../model/territory.type';
import { SightingClaim } from '../model/sighting.claim.type';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrl: '../app.component.scss'
})
export class GameComponent implements OnInit{

  scoreboard: PlayerScore[] = [];
  borders: string[] = [];
  countries: Country[] = [];
  foundTerritories: string[] = [];

  selectedPlayer: number = -1;
  sightingPenalty: boolean = false;
  currentTerritory: string = "";

  endGameEmitter = output({alias: "endGame"});
  messageEmitter = output<string>({alias: "message"});

  constructor(private gameService: GameService) { }

  ngOnInit(): void {
    this.updateCurrentTerritory();
    this.updateScores();
    this.updateBorders();
    this.updateCountries();
    this.updateFoundTerritories();
  }

  territoryStyle(territory: Territory) {
    if (this.currentTerritory == territory.code) {
      return "green";
    }
    if (this.foundTerritories.indexOf(territory.code) >= 0) {
      return "red";
    }
    return "black";
  }

  updateCurrentTerritory(): void {
    this.gameService.current().then(code => {
      this.currentTerritory = code;
    });
  }

  updateCountries() {
    this.gameService.countries().then(map => {
      this.countries = map.countries;
    });
  }

  updateFoundTerritories() {
    this.gameService.found().then(found => this.foundTerritories = found);
  }

  updateBorders() {
    this.gameService.borders().then(borders => {
      this.borders = borders.borders;
    });
  }

  updateScores() {
    this.gameService.scores().then(scoreboard => {
      this.scoreboard = scoreboard.scores;
    });
  }

  crossBorder(code: string) {
    this.gameService.crossBorder(code).then(success => {
      if (success) {
        this.updateCurrentTerritory();
        this.updateBorders();
        this.updateFoundTerritories();
      } else {
        this.messageEmitter.emit("Unable to cross border")
      }
    });
  }

  choosePlayer(id: number) {
    this.selectedPlayer = id;
  }

  cancelPlayer() {
    this.selectedPlayer = -1;
  }

  sighting(code: string) {
    this.gameService.sighting(new SightingClaim(this.selectedPlayer, code, this.sightingPenalty)).then(res => {
      this.updateScores();
      this.updateFoundTerritories();
      this.cancelPlayer();
      this.sightingPenalty = false;
    });
  }

  endGame(): void {
    this.gameService.endGame().then(res => {
      this.endGameEmitter.emit()
    });
  }
}
