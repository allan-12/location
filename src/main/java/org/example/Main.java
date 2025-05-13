package org.example;

import org.example.entiter.ObjetLouable;
import org.example.service.ReservationService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ReservationService reservationService = new ReservationService();

    private static final ObjetLouable voiture = new ObjetLouable("voiture");
    private static final ObjetLouable assiette = new ObjetLouable("assiette");
    private static final ObjetLouable velo = new ObjetLouable("vélo");

    public static void main(String[] args) {
        afficherBienvenue();

        while (true) {
            try {
                ObjetLouable objet = demanderObjetALouer();
                LocalDate date = demanderDateDeReservation();
                reservationService.reserverObjet(objet, date);
            } catch (IllegalArgumentException | IllegalStateException | DateTimeParseException e) {
                System.out.println("❌ Erreur : " + e.getMessage());
            }

            if (!demanderContinuer()) {
                System.out.println("👋 Merci d’avoir utilisé notre application !");
                break;
            }
        }
    }

    private static void afficherBienvenue() {
        System.out.println("🎉 Bienvenue dans notre système de location !");
        System.out.println("Objets disponibles : voiture, assiette, vélo");
    }

    private static ObjetLouable demanderObjetALouer() {
        System.out.print("\nQuel objet voulez-vous louer ? ");
        String nom = scanner.nextLine().trim().toLowerCase();

        return switch (nom) {
            case "voiture" -> voiture;
            case "assiette" -> assiette;
            case "vélo", "velo" -> velo;
            default -> throw new IllegalArgumentException("Objet inconnu : " + nom);
        };
    }

    private static LocalDate demanderDateDeReservation() {
        System.out.print("Entrez une date (AAAA-MM-JJ) : ");
        String dateStr = scanner.nextLine().trim();
        return LocalDate.parse(dateStr);
    }

    private static boolean demanderContinuer() {
        System.out.print("Souhaitez-vous faire une autre réservation ? (o/n) : ");
        String reponse = scanner.nextLine().trim();
        return reponse.equalsIgnoreCase("o");
    }
}