import { Component, OnInit, output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

import { GameService } from '../service/game.service';
import { Country } from '../model/country.type';
import { Territory } from '../model/territory.type';
import { environment } from 'src/environments/environment';
import { GameSettings } from '../model/game-settings.type';

@Component({
  selector: 'app-setup',
  templateUrl: './setup.component.html',
  styleUrl: '../app.component.scss'
})
export class SetupComponent implements OnInit {

  form: FormGroup = this.fb.group({
    players: this.fb.array([this.newPlayerControl()]),
    territoryBorderScore: this.fb.control(1, {validators: [Validators.required, Validators.min(1), Validators.max(99)]}),
    countryBorderScore: this.fb.control(5, {validators: [Validators.required, Validators.min(1), Validators.max(99)]}),
    oceanBorderScore: this.fb.control(10, {validators: [Validators.required, Validators.min(1), Validators.max(99)]}),
    startingTerritory: this.fb.control("", [Validators.required])
  })

  get players(): FormArray {
    return this.form.controls["players"] as FormArray;
  }

  get territoryBorderScore(): FormControl<Number> {
    return this.form.controls["territoryBorderScore"] as FormControl<Number>
  }

  get countryBorderScore(): FormControl<Number> {
    return this.form.controls["countryBorderScore"] as FormControl<Number>
  }

  get oceanBorderScore(): FormControl<Number> {
    return this.form.controls["oceanBorderScore"] as FormControl<Number>
  }

  get startingTerritory(): FormControl<string> {
    return this.form.controls["startingTerritory"] as FormControl<string>;
  }

  setupState: SetupState = SetupState.PLAYERS;
  countries: Array<Country> = environment.countries!;
  
  startGameEmitter = output({alias: "startGame"});
  messageEmitter = output<string>({alias: "message"});
  
  constructor(private fb: FormBuilder, private gameService: GameService) { }
  
  ngOnInit(): void {
    this.updateTerritories();
  }

  private updateTerritories(): void {
    this.gameService.countries().then(res => this.countries = res.countries);
  }

  nextSetupState(): void {
    switch(this.setupState) {
      case SetupState.SCORES:
      case SetupState.PLAYERS:
      case SetupState.STATE:
        this.setupState++;
        break;
      case SetupState.POINTS:
    }
  }

  previousSetupState(): void {
    switch(this.setupState) {
      case SetupState.SCORES:
      case SetupState.PLAYERS:
        break;
      case SetupState.STATE:
      case SetupState.POINTS:
        this.setupState--;
        break;
    }
  }

  private newPlayerControl(): FormControl {
    return this.fb.control("", Validators.required);
  }

  addPlayer(): void {
    this.players.push(this.newPlayerControl());
  }

  removePlayer(idx: number): void {
    this.players.removeAt(idx);
  }

  setCurrentState(code: string): void {
    this.startingTerritory.setValue(code);
  }

  startGame(): void {
    this.gameService.startGame(new GameSettings(this.form.value)).then(success => {
      if (success) {
        this.startGameEmitter.emit();
      } else {
        this.messageEmitter.emit("Setup is invalid");
      }
    });
  }

  territoryStyle(state: Territory): string {
    if (this.startingTerritory.value == state.code) {
      return "green";
    }
    return "black";
  }

  get SetupState() {
    return SetupState
  }
}

enum SetupState {
  SCORES,
  PLAYERS,
  STATE,
  POINTS
}