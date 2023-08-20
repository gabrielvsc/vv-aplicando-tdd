package org.ccc.ufcg.model;

public class Passagem {

    private Passageiro passageiro;
    private Voo voo;

    public Passagem() {
    }

    public Passagem(Passageiro passageiro, Voo voo) {
        this.passageiro = passageiro;
        this.voo = voo;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }
}
