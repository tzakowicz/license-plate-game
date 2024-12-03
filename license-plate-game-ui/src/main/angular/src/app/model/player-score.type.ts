export class PlayerScore {
    id: number;
    name: string = "";
    score: number = 0;

    constructor(id: number) {
        this.id = id;
    }
}