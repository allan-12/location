package org.example.entiter;

import java.time.LocalDate;

public class Reservation {
    private final ObjetLouable objet;
    private final LocalDate date;

    public Reservation(ObjetLouable objet, LocalDate date) {
        this.objet = objet;
        this.date = date;
    }

    public ObjetLouable getObjet() {
        return objet;
    }

    public LocalDate getDate() {
        return date;
    }
}
