package org.ccc.ufcg.repository;

import org.ccc.ufcg.model.Passageiro;
import org.ccc.ufcg.model.Passagem;
import org.ccc.ufcg.model.SolicitacaoCompra;
import org.ccc.ufcg.model.Voo;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDeDados {

    private static List<Voo> voos = new ArrayList<>();
    private static List<Passageiro> passageiros = new ArrayList<>();
    private static List<Passagem> passagems = new ArrayList<>();
    private static List<SolicitacaoCompra> compras = new ArrayList<>();

    public static List<Voo> getVoos() {
        return voos;
    }

    public static List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public static List<Passagem> getPassagems() {
        return passagems;
    }

    public static List<SolicitacaoCompra> getCompras() {
        return compras;
    }
}
