package com.fasterxml.jackson.core.sym;

/**
 * Implémentation concrète de la classe abstraite {@link Name}
 * pour les besoins des tests unitaires.
 */
class NameImpl extends Name {

    public NameImpl(String name, int hashCode) {
        super(name, hashCode);
    }

    @Override
    public boolean equals(int q1) {
        return this._hashCode == q1;
    }

    @Override
    public boolean equals(int q1, int q2) {
        return this._hashCode == (q1 + q2);
    }

    @Override
    public boolean equals(int q1, int q2, int q3) {
        return this._hashCode == (q1 + q2 + q3);
    }

    @Override
    public boolean equals(int[] quads, int qlen) {
        int sum = 0;
        for (int i = 0; i < qlen; i++) {
            sum += quads[i];
        }
        return this._hashCode == sum;
    }

    // Fixing equals method to compare content instead of reference
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name other = (Name) o;
        return this._name.equals(other._name) && this._hashCode == other._hashCode;
    }
}
