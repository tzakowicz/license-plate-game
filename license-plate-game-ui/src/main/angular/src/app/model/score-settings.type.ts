export class ScoreSettings {
  territoryBorderScore: number
  countryBorderScore: number
  oceanBorderScore: number

  constructor(territoryBorderScore: number, countryBorderScore: number, oceanBorderScore: number) {
    this.territoryBorderScore = territoryBorderScore;
    this.countryBorderScore = countryBorderScore;
    this.oceanBorderScore = oceanBorderScore;
  }
}