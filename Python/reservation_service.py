from datetime import date
from typing import List
from objet_louable import ObjetLouable
from reservation import Reservation

class ReservationService:
    def __init__(self):
        self._reservations: List[Reservation] = []

    def est_disponible(self, objet: ObjetLouable, date: date) -> bool:
        return not any(
            r.objet.nom.lower() == objet.nom.lower() and r.date == date
            for r in self._reservations
        )

    def reserver_objet(self, objet: ObjetLouable, date: date):
        if not self.est_disponible(objet, date):
            raise ValueError(f"L'objet '{objet.nom}' est déjà réservé le {date}")
        
        self._reservations.append(Reservation(objet, date))
        print(f"✅ Réservation confirmée pour '{objet.nom}' le {date}")