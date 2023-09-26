package org.ccc.ufcg.service.execicio3;

import org.ccc.ufcg.model.Passageiro;
import org.ccc.ufcg.model.Voo;
import org.ccc.ufcg.repository.BaseDeDados;
import org.ccc.ufcg.service.AdminService;
import org.ccc.ufcg.service.AdminServiceImlp;
import org.ccc.ufcg.service.UserService;
import org.ccc.ufcg.service.UserServiceImpl;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercicio3FuncionaisTest {
    private static UserService userService;
    private static AdminService adminService;
    private static Voo voo;
    private static Voo vo2;
    private static LocalDateTime hora;
    Passageiro passageiro1;
    Passageiro passageiro2;

    @BeforeAll
    static void setUpAll() {
        adminService = new AdminServiceImlp();
        userService = new UserServiceImpl();
        hora = LocalDateTime.now().plusDays(10);
    }

    @BeforeEach
    void setup() {
        voo = new Voo("SPPB001", LocalDate.now().plusDays(10), hora, BigDecimal.valueOf(100.10), "Compina Grande PB","São Paulo SP", 10);
        adminService.cadastrarVoo(voo);

        vo2 = new Voo("RNGO001", LocalDate.now().plusDays(10), hora, BigDecimal.valueOf(100.10), "Natal RN", "Goiania GO", 1);

        passageiro1 = new Passageiro("Ana", "83999888888", "ana@email.com");
        passageiro2 = new Passageiro("Jose", "8399988888", "jose@email.com");
    }

    @AfterEach
    void after() {
        BaseDeDados.getVoos().clear();
        BaseDeDados.getPassageiros().clear();
    }

    @Test
    void cs1() throws IllegalAccessException {
        Assertions.assertEquals(10, voo.getNumeroPassageiros());
        Assertions.assertEquals(0, voo.getPassageiros().size());
        userService.reservarVoo(List.of(passageiro1), voo);
        Assertions.assertEquals(1, voo.getPassageiros().size());
    }

    @Test
    void cs2() throws IllegalAccessException {
        Assertions.assertEquals(10, voo.getNumeroPassageiros());
        Assertions.assertEquals(0, voo.getPassageiros().size());
        userService.reservarVoo(List.of(passageiro1, passageiro2, passageiro1, passageiro2, passageiro1, passageiro2,
                passageiro1, passageiro2, passageiro1, passageiro2), voo);

        Assertions.assertEquals(10, voo.getPassageiros().size());
    }

    @Test
    void cs3() throws IllegalAccessException {
        Assertions.assertEquals(10, voo.getNumeroPassageiros());
        Assertions.assertEquals(0, voo.getPassageiros().size());
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> userService.reservarVoo(List.of(passageiro1, passageiro2, passageiro1, passageiro2, passageiro1,
                        passageiro2, passageiro1, passageiro2, passageiro1, passageiro2, passageiro1), voo)
        );
    }
    @Test
    void cs4() throws IllegalAccessException {
        Assertions.assertEquals(10, voo.getNumeroPassageiros());
        Assertions.assertEquals(0, voo.getPassageiros().size());
        userService.reservarVoo(new ArrayList<>(), voo);
        Assertions.assertEquals(0, voo.getPassageiros().size());
    }

    @Test
    void ct5() {
        String listaEsperada = String.format("Origem: Compina Grande PB, Destino: São Paulo SP, Horário: %s, Preço: 100.1, Lugares vazios: 10.\n", hora);
        Assertions.assertEquals(listaEsperada, userService.listarVoos());
    }

//    @Test
//    void ct6() throws IllegalAccessException {
//        Assertions.assertThrows( IllegalArgumentException.class,
//                () -> userService.listarVoo(vo2.getCodigo())
//        );
//    }

    @Test
    void ct7() throws IllegalAccessException {
        List<Passageiro> passageiros = Arrays.asList(passageiro1, passageiro2);
        Assertions.assertTrue(userService.reservarVoo(passageiros, voo));
    }

    @Test
    void c8() {
        vo2.getPassageiros().add(passageiro1);
        BaseDeDados.getVoos().add(vo2);
        Assertions.assertTrue(vo2.getPassageiros().contains(passageiro1));
        Assertions.assertTrue(userService.cancelarVoo(passageiro1, vo2));
        Assertions.assertFalse(vo2.getPassageiros().contains(passageiro1));
    }

}
