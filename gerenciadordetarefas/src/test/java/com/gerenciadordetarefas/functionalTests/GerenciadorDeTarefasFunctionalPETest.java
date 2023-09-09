  package com.gerenciadordetarefas.functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gerenciadordetarefas.enums.Prioridade;
import com.gerenciadordetarefas.model.Tarefa;
import com.gerenciadordetarefas.repositories.TarefasRepository;
import com.gerenciadordetarefas.service.GerenciadorDeTarefasServiceImpl;

public class GerenciadorDeTarefasFunctionalPETest {

  private GerenciadorDeTarefasServiceImpl gerenciador;

  @Before
  public void setup() {
    List<Tarefa> tarefas = new ArrayList<>();
    TarefasRepository tarefasRepository = new TarefasRepository(tarefas);
    this.gerenciador = new GerenciadorDeTarefasServiceImpl(tarefasRepository);
  }

  private Tarefa criarTarefaCompleta(Date dataDeVencimento) {
    return gerenciador.criarTarefa("Tarefa Importante", "Realizar a apresentação para a equipe.", dataDeVencimento, Prioridade.ALTA);
  }

  // Testes de Criar Tarefa

  // [CT14] 1.1 Criar uma tarefa válida
  @Test
  public void testCT14CriarTarefaValida() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
  }

  // [CT15] 1.2 Criar uma tarefa sem título
  @Test
  public void testCT15CriarTarefaSemTitulo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("", "Descrição qualquer", dataDeVencimento, Prioridade.MEDIA);

    assertNull("A tarefa deveria ser 'null'", tarefa);
    assertFalse("A tarefa não deveria estar presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
  }

  // [CT16] 1.3 Criar uma tarefa com data de vencimento inválida
  @Test
  public void testCT16CriarTarefaComDataDeVencimentoInvalida() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() - 86400000); // Data passada
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);

    assertNull("A tarefa deveria ser 'null'", tarefa);
    assertFalse("A tarefa não deveria estar presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
  }

  // [CT17] 1.4 Criar uma tarefa com prioridade inválida
  @Test
  public void testCT17CriarTarefaComPrioridadeInvalida() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "Descrição qualquer", dataDeVencimento, null);

    assertNull("A tarefa deveria ser 'null'", tarefa);
    assertFalse("A tarefa não deveria estar presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
  }

  // [CT18] 2.1 Atualizar título de uma tarefa
  @Test
  public void testCT18AtualizarTituloTarefa() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);
    gerenciador.atualizarTituloTarefa(tarefa.getId(), "Novo Título");

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertEquals("O título da tarefa não foi atualizado corretamente", "Novo Título", tarefa.getTitulo());
  }

  // [CT19] 2.2 Atualizar descrição de uma tarefa
  @Test
  public void testCT19AtualizarDescricaoTarefa() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);
    gerenciador.atualizarDescricaoTarefa(tarefa.getId(), "Nova Descrição");

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertEquals("A descrição da tarefa não foi atualizada corretamente", "Nova Descrição", tarefa.getDescricao());
  }

  // [CT20] 2.3 Atualizar data de vencimento de uma tarefa
  @Test
  public void testCT20AtualizarDataDeVencimentoTarefa() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);
    Date novaDataDeVencimento = new Date(System.currentTimeMillis() + 2 * 86400000); // Data depois de amanhã
    gerenciador.atualizarDataDeVencimentoTarefa(tarefa.getId(), novaDataDeVencimento);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertEquals("A data de vencimento da tarefa não foi atualizada corretamente", novaDataDeVencimento, tarefa.getDataDeVencimento());
  }

  // [CT21] 2.4 Atualizar prioridade de uma tarefa
  @Test
  public void testCT21AtualizarPrioridadeTarefa() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);
    gerenciador.atualizarPrioridadeTarefa(tarefa.getId(), Prioridade.MEDIA);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertEquals("A prioridade da tarefa não foi atualizada corretamente", Prioridade.MEDIA, tarefa.getPrioridade());
  }

  // [CT22] 2.5 Tentar atualizar uma tarefa inexistente
  @Test
  public void testCT22TentarAtualizarTarefaInexistente() throws ParseException {
    boolean atualizada = gerenciador.atualizarTituloTarefa("Id-Nao-Existente", "Novo Título");

    assertFalse("A tarefa não deveria ter sido atualizada", atualizada);
  }

  // [CT23] 3.1 Excluir uma tarefa existente
  @Test
  public void testCT23ExcluirTarefaExistente() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);
    boolean excluida = gerenciador.excluirTarefa(tarefa.getId());

    assertTrue("A tarefa deveria ter sido excluída", excluida);
    assertFalse("A tarefa não deveria estar presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
  }

  // [CT24] 3.2 Tentar excluir uma tarefa inexistente
  @Test
  public void testCT24TentarExcluirTarefaInexistente() {
    boolean excluida = gerenciador.excluirTarefa("Id-Nao-Existente");

    assertFalse("A tarefa não deveria ter sido excluída", excluida);
  }

  // [CT25] 4.1 Exibir lista de tarefas ordenadas por data de vencimento e prioridade
  @Test
  public void testCT25ExibirListaTarefasOrdenadas() throws ParseException {
    Date dataVencimentoAmanha = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Date dataVencimentoDepoisAmanha = new Date(System.currentTimeMillis() + 172800000); // Data de depois de amanhã

    // Criar tarefas com diferentes datas de vencimento e prioridades
    gerenciador.criarTarefa("Tarefa 1", "Descrição", dataVencimentoDepoisAmanha, Prioridade.BAIXA);
    gerenciador.criarTarefa("Tarefa 2", "Descrição", dataVencimentoAmanha, Prioridade.ALTA);
    gerenciador.criarTarefa("Tarefa 3", "Descrição", dataVencimentoDepoisAmanha, Prioridade.MEDIA);

    // Listar as tarefas
    List<Tarefa> listaDeTarefas = gerenciador.listaDeTarefas();

    // Verificar se a lista está ordenada corretamente
    assertEquals("Tarefa 2", listaDeTarefas.get(0).getTitulo()); // Tarefa com prioridade alta e data de amanhã
    assertEquals("Tarefa 3", listaDeTarefas.get(1).getTitulo()); // Tarefa com prioridade média e data de depois de amanhã
    assertEquals("Tarefa 1", listaDeTarefas.get(2).getTitulo()); // Tarefa com prioridade baixa e data de depois de amanhã
  }

  // [CT26] 4.2 Exibir lista de tarefas vazia
  @Test
  public void testCT26ExibirListaTarefasVazia() {
    List<Tarefa> listaVazia = gerenciador.listaDeTarefas();

    assertNotNull("A lista de tarefas não deveria ser 'null'", listaVazia);
    assertTrue("A lista de tarefas deveria estar vazia", listaVazia.isEmpty());
  }
}
