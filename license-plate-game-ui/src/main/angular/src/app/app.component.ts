import { Component } from '@angular/core';

import { GameService } from './service/game.service';
import { environment } from 'src/environments/environment';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {

  playing: boolean = false;
  loading: boolean = false;

  env = environment

  constructor(private gameService: GameService) { 
	  this.updatePlaying();
  }

  updatePlaying(): void {
    this.gameService.playing().then(res => {
      this.playing = res;
    });
  }

  showMessage(message: string): void {
    console.debug("showMessage", message);
  }
}