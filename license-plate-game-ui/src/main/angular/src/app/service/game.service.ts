
import { Injectable } from '@angular/core';

import { PlateService } from './plate.service';

import { SightingClaim } from '../model/sighting.claim.type';
import { Map } from '../model/map.type';
import { SightingResult } from '../model/sighting.result.type';
import { Scoreboard } from '../model/scoreboard.type';
import { Borders } from '../model/borders.type';
import { GameSettings } from '../model/game-settings.type';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  
  //GET
  private baseUrl: string = "api/game"
  private playingUrl: string = this.baseUrl + "/playing"
  private scoresUrl: string = this.baseUrl + "/scores"
  private currentUrl: string = this.baseUrl + "/territory"
  private borderUrl: string = this.baseUrl + "/borders"
  private countriesUrl: string = this.baseUrl + "/countries"
  private foundUrl: string = this.currentUrl + "/found"
  private lastSightingUrl: string = this.baseUrl + "/sighting/last"

  //POST
  private startUrl: string = this.baseUrl + "/start"
  private endUrl: string = this.baseUrl + "/end"
  private sightingUrl: string = this.baseUrl + "/sighting"
  private crossBorderUrl: string = this.baseUrl + "/crossBorder"

  constructor(private client: PlateService) { }

  // GET
  working(): Promise<boolean> {
    return this.client.getMethod<boolean>(this.baseUrl);
  }

  playing(): Promise<boolean> {
    return this.client.getMethod<boolean>(this.playingUrl);
  }

  current(): Promise<string> {
    return this.client.getMethod<string>(this.currentUrl, {responseType: "text"});
  }

  countries(): Promise<Map> {
    return this.client.getMethod<Map>(this.countriesUrl);
  }

  found(): Promise<string[]> {
    return this.client.getMethod<string[]>(this.foundUrl);
  }

  borders(): Promise<Borders> {
    return this.client.getMethod<Borders>(this.borderUrl);
  }

  scores(): Promise<Scoreboard> {
    return this.client.getMethod<Scoreboard>(this.scoresUrl);
  }

  lastSighting(): Promise<SightingResult> {
    return this.client.getMethod<SightingResult>(this.lastSightingUrl);
  }

  //POST
  startGame(settings: GameSettings): Promise<boolean> {
    return this.client.postMethod<boolean>(this.startUrl, settings);
  }

  endGame(): Promise<boolean> {
    return this.client.postMethod<boolean>(this.endUrl, {});
  }

  sighting(body: SightingClaim): Promise<boolean> {
    return this.client.postMethod<boolean>(this.sightingUrl, body);
  }

  crossBorder(body: String): Promise<boolean> {
    return this.client.postMethod<boolean>(this.crossBorderUrl, body, {headers: {"Content-Type": "text/plain"}});
  }
}


