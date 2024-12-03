import { ScoreSettings } from "./score-settings.type";

export class GameSettings {
    players: string[] = [];
    startingTerritory: string = "";
    scoreSettings: ScoreSettings = new ScoreSettings(0, 0, 0);

    constructor(settings: {players: string[], startingTerritory: string, territoryBorderScore: number, countryBorderScore: number, oceanBorderScore: number}) {
      this.players = settings.players;
      this.startingTerritory = settings.startingTerritory;
      this.scoreSettings = new ScoreSettings(settings.territoryBorderScore, settings.countryBorderScore, settings.oceanBorderScore);
    }
  }