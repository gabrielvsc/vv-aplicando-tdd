package org.ccc.ufcg.service;

import org.ccc.ufcg.model.Voo;
import org.ccc.ufcg.repository.BaseDeDados;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

class AdminServiceImplTest {

    private AdminService adminService;
    private Voo voo;

    @BeforeEach
    void setup() {
        adminService = new AdminServiceImlp();
        voo = new Voo("SPPB001",LocalDate.now(), LocalDateTime.now(), BigDecimal.valueOf(100.10), "Compina Grande PB","São Paulo SP", 80);
    }

    @AfterEach
    void after() {
        BaseDeDados.getVoos().clear();
    }

    @Test
    void deveCadastrarUmVooComSucesso() {
        Boolean cadastrado = adminService.cadastrarVoo(voo);
        Assertions.assertTrue(cadastrado);
    }

    @Test
    void deveFalharAoSalvarVooComMesmoCodigoEHora() {
        Boolean cadastrado = adminService.cadastrarVoo(voo);
        Assertions.assertTrue(cadastrado);

        cadastrado = adminService.cadastrarVoo(voo);
        Assertions.assertFalse(cadastrado);
    }

}
