package org.ccc.ufcg.service;

import org.ccc.ufcg.model.Passageiro;
import org.ccc.ufcg.model.Voo;

import java.util.List;

public interface UserService {
    String listarVoos();

    Boolean reservarVoo(List<Passageiro> passageiros, Voo voo);
}
