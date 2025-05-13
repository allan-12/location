from datetime import date
from objet_louable import ObjetLouable
from reservation_service import ReservationService

class Main:
    _reservation_service = ReservationService()
    _voiture = ObjetLouable("voiture")
    _assiette = ObjetLouable("assiette")
    _velo = ObjetLouable("vélo")

    @staticmethod
    def afficher_bienvenue():
        print("🎉 Bienvenue dans notre système de location !")
        print("Objets disponibles : voiture, assiette, vélo")

    @staticmethod
    def demander_objet_a_louer() -> ObjetLouable:
        nom = input("\nQuel objet voulez-vous louer ? ").strip().lower()
        match nom:
            case "voiture":
                return Main._voiture
            case "assiette":
                return Main._assiette
            case "vélo" | "velo":
                return Main._velo
            case _:
                raise ValueError(f"Objet inconnu : {nom}")

    @staticmethod
    def demander_date_de_reservation() -> date:
        date_str = input("Entrez une date (AAAA-MM-JJ) : ").strip()
        return date.fromisoformat(date_str)

    @staticmethod
    def demander_continuer() -> bool:
        reponse = input("Souhaitez-vous faire une autre réservation ? (o/n) : ").strip()
        return reponse.lower() == "o"

    @staticmethod
    def main():
        Main.afficher_bienvenue()

        while True:
            try:
                objet = Main.demander_objet_a_louer()
                date_reservation = Main.demander_date_de_reservation()
                Main._reservation_service.reserver_objet(objet, date_reservation)
            except (ValueError, TypeError) as e:
                print(f"❌ Erreur : {str(e)}")

            if not Main.demander_continuer():
                print("👋 Merci d’avoir utilisé notre application !")
                break

if __name__ == "__main__":
    Main.executer()
