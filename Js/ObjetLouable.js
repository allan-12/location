class ObjetLouable {
    #nom;

    constructor(nom) {
        this.#nom = nom;
    }

    get nom() {
        return this.#nom;
    }

    toString() {
        return this.#nom;
    }
}

module.exports = ObjetLouable;