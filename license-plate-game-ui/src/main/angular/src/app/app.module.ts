import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { SetupComponent } from "./game/setup.component";
import { GameComponent } from './game/game.component';
import { LastSightingComponent } from './game/last-sighting.component';

import { GameService } from './service/game.service';

@NgModule({
  declarations: [
    AppComponent,
    SetupComponent,
    GameComponent,
    LastSightingComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    provideHttpClient(),
    GameService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
