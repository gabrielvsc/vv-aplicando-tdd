package org.ccc.ufcg.service;

import org.ccc.ufcg.model.Voo;
import org.ccc.ufcg.repository.BaseDeDados;

public class AdminServiceImlp implements AdminService{

   public Boolean cadastrarVoo(Voo voo) {
       if(BaseDeDados.getVoos().contains(voo)){
           return false;
       }
       BaseDeDados.getVoos().add(voo);
       return true;
   }
}
