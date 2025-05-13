class ObjetLouable:
    def __init__(self, nom):
        self._nom = nom

    @property
    def nom(self):
        return self._nom

    def __str__(self):
        return self._nom