package org.ccc.ufcg.model;

import java.util.ArrayList;
import java.util.List;

public class SolicitacaoCompra {

    private Voo voo;
    private List<Passageiro> passageiros = new ArrayList<>();

    public SolicitacaoCompra() {
    }

    public SolicitacaoCompra(Voo voo, List<Passageiro> passageiros) {
        this.voo = voo;
        this.passageiros = passageiros;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(List<Passageiro> passageiros) {
        this.passageiros = passageiros;
    }
}
