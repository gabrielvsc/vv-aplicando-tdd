package org.ccc.ufcg.service;

import org.ccc.ufcg.model.Passageiro;
import org.ccc.ufcg.model.Voo;
import org.ccc.ufcg.repository.BaseDeDados;
import org.junit.jupiter.api.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class UserServiceImplTest {

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
        hora = LocalDateTime.now();
    }

    @BeforeEach
    void setup() {
        voo = new Voo("SPPB001", LocalDate.now(), hora, BigDecimal.valueOf(100.10), "Compina Grande PB","São Paulo SP", 80);
        adminService.cadastrarVoo(voo);

        vo2 = new Voo("RNGO001", LocalDate.now(), hora, BigDecimal.valueOf(100.10), "Natal RN", "Goiania GO", 1);

        passageiro1 = new Passageiro("Ana", "83999888888", "ana@email.com");
        passageiro2 = new Passageiro("Jose", "8399988888", "jose@email.com");
    }

    @AfterEach
    void after() {
        BaseDeDados.getVoos().clear();
        BaseDeDados.getPassageiros().clear();
    }

    @Test
    void deveListarVoosComSucesso() {
        String listaEsperada = String.format("Origem: Compina Grande PB, Destino: São Paulo SP, Horário: %s, Preço: 100.1, Lugares vazios: 80.\n", hora);
        Assertions.assertEquals(listaEsperada, userService.listarVoos());
    }

    @Test
    void deveReservarUmaVooComSucesso() throws IllegalAccessException {
        List<Passageiro> passageiros = Arrays.asList(passageiro1, passageiro2);
        Assertions.assertTrue(userService.reservarVoo(passageiros, voo));
    }

    @Test
    void devefalharAoTentarReservarVooComLimiteDeVagasExcedidas() throws IllegalAccessException {
        List<Passageiro> passageiros = Arrays.asList(passageiro1, passageiro2);
        adminService.cadastrarVoo(vo2);

        IllegalArgumentException excecaoLancada = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> userService.reservarVoo(passageiros, vo2)
        );

        String mensagemEsperada = "Limite de vagas excedido";
        Assertions.assertEquals(mensagemEsperada, excecaoLancada.getMessage());
    }

    @Test
    void deveCancelarReservaComSucesso() {
        vo2.getPassageiros().add(passageiro1);
        BaseDeDados.getVoos().add(vo2);

        Assertions.assertTrue(vo2.getPassageiros().contains(passageiro1));
        Assertions.assertTrue(userService.cancelarVoo(passageiro1, vo2));

        Assertions.assertFalse(vo2.getPassageiros().contains(passageiro1));
    }

    @Test
    void deveGerarConfirmacaoDeReserva() {
        vo2.getPassageiros().add(passageiro1);
        BaseDeDados.getVoos().add(vo2);
        String msgEsperada =  vo2.toString() + passageiro1.toString();
        Assertions.assertEquals(msgEsperada, userService.gerarConfirmacao(passageiro1, vo2));
    }

    @Test
    void deveLancarExceptionQuandoVooNaoExiste() {
        IllegalArgumentException excecaoLancada = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> userService.gerarConfirmacao(passageiro1, vo2)
        );

        String mensagemEsperada = "Esse voo não existe";
        Assertions.assertEquals(mensagemEsperada, excecaoLancada.getMessage());
    }

    @Test
    void deveLancarExceptionQuandoPassageiroNaoEstaNoVoo() {
        BaseDeDados.getVoos().add(vo2);
        IllegalArgumentException excecaoLancada = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> userService.gerarConfirmacao(passageiro1, vo2)
        );

        String mensagemEsperada = "Passageiro não cadastrado no voo";
        Assertions.assertEquals(mensagemEsperada, excecaoLancada.getMessage());
    }
}
