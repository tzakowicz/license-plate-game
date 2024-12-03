import { Borders } from "./borders.type";
import { Map } from "./map.type";
import { ScoreSettings } from "./score-settings.type";
import { Scoreboard } from "./scoreboard.type";
import { SightingResult } from "./sighting.result.type";

class Response<T> {
  success: boolean = false;
  message: string = "";
  content: T;
  
  constructor(content: T) {
    this.content = content;
  }
}
  
// export class BooleanResponse extends Response<boolean> {}

// export class StringResponse extends Response<string> {}
  
// export class SightingResponse extends Response<SightingResult> {}
  
// export class MapResponse extends Response<Map> {}
  
// export class ScoreboardResponse extends Response<Scoreboard> {}

// export class ScoreSettingsResponse extends Response<ScoreSettings> {}

// export class BordersResponse extends Response<Borders> {}

// export class ListResponse<T> extends Response<T[]> {}
  
// export class StringListResponse extends ListResponse<string> {}
