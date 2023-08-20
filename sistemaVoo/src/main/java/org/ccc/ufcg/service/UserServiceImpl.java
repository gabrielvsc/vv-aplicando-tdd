package org.ccc.ufcg.service;

import org.ccc.ufcg.model.Passageiro;
import org.ccc.ufcg.model.Passagem;
import org.ccc.ufcg.model.Voo;
import org.ccc.ufcg.repository.BaseDeDados;

import java.util.List;

public class UserServiceImpl implements UserService{
    @Override
    public String listarVoos() {
       List<Voo> voos = BaseDeDados.getVoos();
       StringBuilder sb = new StringBuilder();

       voos.forEach(voo -> sb.append(voo.toString()));
       return sb.toString();
    }

    @Override
    public Boolean reservarVoo(List<Passageiro> passageiros, Voo voo) throws IllegalAccessException {
        if(voo.getAcentosVazios() < passageiros.size()) {
            throw new IllegalArgumentException("Limite de vagas excedido");
        }

        voo.getPassageiros().addAll(passageiros);

        for(int i = 0; i < BaseDeDados.getVoos().size(); i++ ) {
            if(BaseDeDados.getVoos().get(i).equals(voo)) {
                BaseDeDados.getVoos().add(i, voo);

                passageiros.forEach(passageiro -> {
                    Passagem passagem = new Passagem(passageiro, voo);
                    BaseDeDados.getPassagems().add(passagem);
                });
                return true;
            }
        }

        throw new IllegalArgumentException("Esse voo não existe");
    }

    @Override
    public Boolean cancelarVoo(Passageiro passageiro, Voo voo) {
        for(int i = 0; i < BaseDeDados.getVoos().size(); i++ ) {
            if(BaseDeDados.getVoos().get(i).equals(voo)) {
                voo.getPassageiros().remove(passageiro);
                Passagem passagem = new Passagem(passageiro, voo);
                BaseDeDados.getPassagems().remove(passagem);
                return true;
            }
        }

        return false;
    }

    @Override
    public String gerarConfirmacao(Passageiro passageiro1, Voo vo2) {
        return null;
    }
}
