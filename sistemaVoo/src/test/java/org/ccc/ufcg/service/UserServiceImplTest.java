package org.ccc.ufcg.service;

import org.ccc.ufcg.model.Passageiro;
import org.ccc.ufcg.model.Voo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class UserServiceImplTest {

    private static UserService userService;
    private static AdminService adminService;
    private static Voo voo;
    private static LocalDateTime hora;
    Passageiro passageiro1;
    Passageiro passageiro2;

    @BeforeAll
    static void setUpAll() {
        adminService = new AdminServiceImlp();
        userService = new UserServiceImpl();
        hora = LocalDateTime.now();
        voo = new Voo("SPPB001", LocalDate.now(), hora, BigDecimal.valueOf(100.10), "Compina Grande PB","São Paulo SP", 80);
        adminService.cadastrarVoo(voo);
    }

    @BeforeEach
    void setup() {

        passageiro1 = new Passageiro("Ana", "83999888888", "ana@email.com");
        passageiro2 = new Passageiro("Jose", "8399988888", "jose@email.com");
    }

    @Test
    void deveListarVoosComSucesso() {
        String listaEsperada = String.format("Origem: Compina Grande PB, Destino: São Paulo SP, Horário: %s, Preço: 100.1, Lugares vazios: 80.\n", hora);
        Assertions.assertEquals(listaEsperada, userService.listarVoos());
    }

    @Test
    void deveReservarUmaVooComSucesso() {
        List<Passageiro> passageiros = Arrays.asList(passageiro1, passageiro2);
        Assertions.assertTrue(userService.reservarVoo(passageiros, voo));
    }
}
