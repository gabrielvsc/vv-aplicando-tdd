package com.gerenciadordetarefas.junit5Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
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

}
