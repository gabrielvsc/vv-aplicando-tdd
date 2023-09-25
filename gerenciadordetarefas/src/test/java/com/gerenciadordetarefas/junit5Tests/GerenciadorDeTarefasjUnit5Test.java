package com.gerenciadordetarefas.junit5Tests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Tag;
import com.gerenciadordetarefas.enums.Prioridade;
import com.gerenciadordetarefas.model.Tarefa;
import com.gerenciadordetarefas.repositories.TarefasRepository;
import com.gerenciadordetarefas.service.GerenciadorDeTarefasServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GerenciadorDeTarefasjUnit5Test {

  private static GerenciadorDeTarefasServiceImpl gerenciador;

  @BeforeAll
  public static void setup() {
    List<Tarefa> tarefas = new ArrayList<>();
    TarefasRepository tarefasRepository = new TarefasRepository(tarefas);
    gerenciador = new GerenciadorDeTarefasServiceImpl(tarefasRepository);
  }

  @BeforeEach
  public void beforeEach() {
    // Inicialização comum para cada teste
  }

  @AfterEach
  public void afterEach() {
    // Limpeza comum após cada teste
  }

  private void assertTarefaDetalhesIguais(Tarefa tarefa, String titulo, String descricao, Date dataVencimento, Prioridade prioridade) {
    assertEquals(titulo, tarefa.getTitulo(), "Título Incorreto");
    assertEquals(descricao, tarefa.getDescricao(), "Descrição Incorreta");
    assertEquals(dataVencimento.toString(), tarefa.getDataDeVencimento().toString(), "Data de Vencimento Incorreta");
    assertEquals(prioridade.toString(), tarefa.getPrioridade().toString(), "Prioridade Incorreta");
  }

  private Tarefa criarTarefaCompleta(Date dataDeVencimento) {
    return gerenciador.criarTarefa("Tarefa Importante", "Realizar a apresentação para a equipe.", dataDeVencimento, Prioridade.ALTA);
  }

  @Test
  @Order(1)
  @DisplayName("CT01: Todas variáveis - Mínimo")
  @Tag("Análise de Valores Limite")
  public void testCT01TodasVariaveisMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 999); // Data de hoje
    Tarefa tarefa = gerenciador.criarTarefa("T", "d", dataDeVencimento, Prioridade.BAIXA);

    System.out.println(tarefa);
    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "T", "d", dataDeVencimento, Prioridade.BAIXA);
  }

  @Test
  @Order(2)
  @DisplayName("CT02: Variável Título - Mínimo")
  @Tag("Análise de Valores Limite")
  public void testCT02VariavelTituloMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("T", "descrição qualquer", dataDeVencimento, Prioridade.MEDIA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "T", "descrição qualquer", dataDeVencimento, Prioridade.MEDIA);
  }

  @Test
  @Order(3)
  @DisplayName("CT03: Variável Descrição - Mínimo")
  @Tag("Análise de Valores Limite")
  public void testCT03VariavelDescricaoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "d", dataDeVencimento, Prioridade.MEDIA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "Título qualquer", "d", dataDeVencimento, Prioridade.MEDIA);
  }

  @Test
  @Order(4)
  @DisplayName("CT04: Variável Data de Vencimento - Mínimo")
  @Tag("Análise de Valores Limite")
  public void testCT04VariavelDataVencimentoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 999); // Data de hoje
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.MEDIA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.MEDIA);
  }

  @Test
  @Order(5)
  @DisplayName("CT05: Variável Prioridade - Mínimo")
  @Tag("Análise de Valores Limite")
  public void testCT05VariavelPrioridadeMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 999); // Data de hoje
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);
  }

  @Test
  @Order(6)
  @DisplayName("CT06: Todas variáveis - Logo acima do mínimo")
  @Tag("Análise de Valores Limite")
  public void testCT06TodasVariaveisLogoAcimaDoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Tí", "de", dataDeVencimento, Prioridade.MEDIA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "Tí", "de", dataDeVencimento, Prioridade.MEDIA);
  }

  @Test
  @Order(7)
  @DisplayName("CT07: Variável Título - Logo acima do mínimo")
  @Tag("Análise de Valores Limite")
  public void testCT07VariavelTituloLogoAcimaDoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Tí", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "Tí", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);
  }

  @Test
  @Order(8)
  @DisplayName("CT08: Variável Descrição - Logo acima do mínimo")
  @Tag("Análise de Valores Limite")
  public void testCT08VariavelDescricaoLogoAcimaDoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "de", dataDeVencimento, Prioridade.BAIXA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "Título qualquer", "de", dataDeVencimento, Prioridade.BAIXA);
  }

  @Test
  @Order(9)
  @DisplayName("CT09: Variável Data de Vencimento - Logo acima do mínimo")
  @Tag("Análise de Valores Limite")
  public void testCT09VariavelDataVencimentoLogoAcimaDoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);
  }

  @Test
  @Order(10)
  @DisplayName("CT10: Variável Prioridade - Logo acima do mínimo")
  @Tag("Análise de Valores Limite")
  public void testCT10VariavelPrioridadeLogoAcimaDoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.ALTA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.ALTA);
  }

  @Test
  @Order(11)
  @DisplayName("CT11: Todas variáveis - Máximo")
  @Tag("Análise de Valores Limite")
  public void testCT11TodasVariaveisMaximo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000 * 365); // Data daqui a um ano
    Tarefa tarefa = gerenciador.criarTarefa("Título muito longo", "Descrição muito longa", dataDeVencimento, Prioridade.ALTA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "Título muito longo", "Descrição muito longa", dataDeVencimento, Prioridade.ALTA);
  }

  @Test
  @Order(12)
  @DisplayName("CT12: Variável Título - Máximo")
  @Tag("Análise de Valores Limite")
  public void testCT12VariavelTituloMaximo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000 * 365); // Data daqui a um ano
    Tarefa tarefa = gerenciador.criarTarefa("Título muito longo", "Descrição qualquer", dataDeVencimento, Prioridade.ALTA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "Título muito longo", "Descrição qualquer", dataDeVencimento, Prioridade.ALTA);
  }

  @Test
  @Order(13)
  @DisplayName("CT13: Variável Descrição - Máximo")
  @Tag("Análise de Valores Limite")
  public void testCT13VariavelDescricaoMaximo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000 * 365); // Data daqui a um ano
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "Descrição muito longa", dataDeVencimento, Prioridade.ALTA);

    assertNotNull(tarefa, "A tarefa não deveria ser 'null'");
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa), "A tarefa não está presente na lista");
    assertTarefaDetalhesIguais(tarefa, "Título qualquer", "Descrição muito longa", dataDeVencimento, Prioridade.ALTA);
  }

  @Test
  @Order(14)
  @DisplayName("CT14: 1.1 Criar Tarefa Válida")
  @Tag("Partições de Equivalência")
  public void testCT14CriarTarefaValida() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);

    assertNotNull(tarefa);
    assertTrue(gerenciador.listaDeTarefas().contains(tarefa));
  }

  @Test
  @Order(15)
  @DisplayName("CT15: 1.2 Criar Tarefa Sem Título")
  @Tag("Partições de Equivalência")
  public void testCT15CriarTarefaSemTitulo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("", "Descrição qualquer", dataDeVencimento, Prioridade.MEDIA);

    assertNull(tarefa);
    assertFalse(gerenciador.listaDeTarefas().contains(tarefa));
  }

  @Test
  @Order(16)
  @DisplayName("CT16: 1.3 Criar Tarefa com Data de Vencimento Inválida")
  @Tag("Partições de Equivalência")
  public void testCT16CriarTarefaComDataDeVencimentoInvalida() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() - 86400000); // Data passada
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);

    assertNull(tarefa);
    assertFalse(gerenciador.listaDeTarefas().contains(tarefa));
  }

  @Test
  @Order(17)
  @DisplayName("CT17: 1.4 Criar Tarefa com Prioridade Inválida")
  @Tag("Partições de Equivalência")
  public void testCT17CriarTarefaComPrioridadeInvalida() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "Descrição qualquer", dataDeVencimento, null);

    assertNull(tarefa);
    assertFalse(gerenciador.listaDeTarefas().contains(tarefa));
  }
}
