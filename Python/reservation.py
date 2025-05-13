from datetime import date
from objet_louable import ObjetLouable

class Reservation:
    def __init__(self, objet: ObjetLouable, date: date):
        self._objet = objet
        self._date = date

    @property
    def objet(self):
        return self._objet

    @property
    def date(self):
        return self._date