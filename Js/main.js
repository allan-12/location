const readline = require('readline');
const ObjetLouable = require('./objetLouable');
const ReservationService = require('./reservationService');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

const reservationService = new ReservationService();
const voiture = new ObjetLouable('voiture');
const assiette = new ObjetLouable('assiette');
const velo = new ObjetLouable('vélo');

function afficherBienvenue() {
    console.log('🎉 Bienvenue dans notre système de location !');
    console.log('Objets disponibles : voiture, assiette, vélo');
}

function demanderObjetALouer() {
    return new Promise((resolve, reject) => {
        rl.question('\nQuel objet voulez-vous louer ? ', (nom) => {
            nom = nom.trim().toLowerCase();
            switch (nom) {
                case 'voiture':
                    resolve(voiture);
                    break;
                case 'assiette':
                    resolve(assiette);
                    break;
                case 'vélo':
                case 'velo':
                    resolve(velo);
                    break;
                default:
                    reject(new Error(`Objet inconnu : ${nom}`));
            }
        });
    });
}

function demanderDateDeReservation() {
    return new Promise((resolve, reject) => {
        rl.question('Entrez une date (AAAA-MM-JJ) : ', (dateStr) => {
            try {
                const date = new Date(dateStr.trim());
                if (isNaN(date.getTime())) {
                    throw new Error('Date invalide');
                }
                resolve(date);
            } catch (e) {
                reject(new Error('Format de date invalide. Utilisez AAAA-MM-JJ'));
            }
        });
    });
}

function demanderContinuer() {
    return new Promise((resolve) => {
        rl.question('Souhaitez-vous faire une autre réservation ? (o/n) : ', (reponse) => {
            resolve(reponse.trim().toLowerCase() === 'o');
        });
    });
}

async function main() {
    afficherBienvenue();

    while (true) {
        try {
            const objet = await demanderObjetALouer();
            const date = await demanderDateDeReservation();
            reservationService.reserverObjet(objet, date);
        } catch (e) {
            console.log(`❌ Erreur : ${e.message}`);
        }

        const continuer = await demanderContinuer();
        if (!continuer) {
            console.log('👋 Merci d’avoir utilisé notre application !');
            rl.close();
            break;
        }
    }
}

main();