const ObjetLouable = require('./objetLouable');

class Reservation {
    #objet;
    #date;

    constructor(objet, date) {
        this.#objet = objet;
        this.#date = date;
    }

    get objet() {
        return this.#objet;
    }

    get date() {
        return this.#date;
    }
}

module.exports = Reservation;