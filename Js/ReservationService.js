const Reservation = require('./reservation');

class ReservationService {
    #reservations = [];

    estDisponible(objet, date) {
        return !this.#reservations.some(
            (r) =>
                r.objet.nom.toLowerCase() === objet.nom.toLowerCase() &&
                r.date.toISOString().split('T')[0] === date.toISOString().split('T')[0]
        );
    }

    reserverObjet(objet, date) {
        if (!this.estDisponible(objet, date)) {
            throw new Error(`L'objet '${objet.nom}' est déjà réservé le ${date.toISOString().split('T')[0]}`);
        }

        this.#reservations.push(new Reservation(objet, date));
        console.log(`✅ Réservation confirmée pour '${objet.nom}' le ${date.toISOString().split('T')[0]}`);
    }
}

module.exports = ReservationService;