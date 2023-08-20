package org.ccc.ufcg.service;

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
}
