package org.ccc.ufcg.service;

import org.ccc.ufcg.model.Voo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

class UserServiceImplTest {

    private UserService userService;
    private AdminService adminService;
    private Voo voo;
    private LocalDate data;
    private LocalDateTime hora;

    @BeforeEach
    void setup() {
        userService = new UserServiceImpl();
        adminService = new AdminServiceImlp();

        data = LocalDate.now();
        hora = LocalDateTime.now();
        voo = new Voo("SPPB001", data, hora, BigDecimal.valueOf(100.10), "Compina Grande PB","São Paulo SP", 80);

        adminService.cadastrarVoo(voo);
    }

    @Test
    void deveListarVoosComSucesso() {
        String listaEsperada = String.format("Origem: Compina Grande PB, Destino: São Paulo SP, Horário: %s, Preço: 100.10, Lugares vazios: 80.", hora);
        Assertions.assertEquals(listaEsperada, userService.listarVoos());
    }

}
