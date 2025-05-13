package org.example.service;

import org.example.entiter.ObjetLouable;
import org.example.entiter.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private final List<Reservation> reservations = new ArrayList<>();

    public boolean estDisponible(ObjetLouable objet, LocalDate date) {
        return reservations.stream()
                .noneMatch(r -> r.getObjet().getNom().equalsIgnoreCase(objet.getNom())
                        && r.getDate().equals(date));
    }

    public void reserverObjet(ObjetLouable objet, LocalDate date) {
        if (!estDisponible(objet, date)) {
            throw new IllegalStateException("L'objet '" + objet.getNom() + "' est déjà réservé le " + date);
        }

        reservations.add(new Reservation(objet, date));
        System.out.println("✅ Réservation confirmée pour '" + objet.getNom() + "' le " + date);
    }
}
