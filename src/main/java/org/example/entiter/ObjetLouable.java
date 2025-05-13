package org.example.entiter;

public class ObjetLouable {
    private final String nom;

    public ObjetLouable(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}

