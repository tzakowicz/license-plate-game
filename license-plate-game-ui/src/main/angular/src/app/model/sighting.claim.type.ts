export class SightingClaim {
    id: number;
    code: string;
    penalty: boolean;

    constructor(id: number, code: string, penalty: boolean = false) {
        this.id = id;
        this.code = code;
        this.penalty = penalty;
    }
}