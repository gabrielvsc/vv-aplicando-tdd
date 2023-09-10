package com.gerenciadordetarefas.functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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

public class GerenciadorDeTarefasFunctionalTDTest {

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

  // [CT27] Criar uma tarefa
  @Test
  public void testCT27CriarTarefa() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
  }

  // [CT28] Atualizar Tarefa
  @Test
  public void testCT28AtualizarTarefa() {
    // Simulando uma tarefa existente
    String tituloOriginal = "Tarefa Antiga";
    Tarefa tarefa = gerenciador.criarTarefa(tituloOriginal, "Descrição antiga", new Date(System.currentTimeMillis() + 999), Prioridade.BAIXA);

    // Atualizar a tarefa com novas informações
    String novoTitulo = "Tarefa Atualizada";
    String novaDescricao = "Descrição atualizada";
    Date novaDataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Prioridade novaPrioridade = Prioridade.ALTA;

    gerenciador.atualizarTituloTarefa(tarefa.getId(), novoTitulo);
    gerenciador.atualizarDescricaoTarefa(tarefa.getId(), novaDescricao);
    gerenciador.atualizarDataDeVencimentoTarefa(tarefa.getId(), novaDataDeVencimento);
    gerenciador.atualizarPrioridadeTarefa(tarefa.getId(), novaPrioridade);

    assertEquals(novoTitulo, tarefa.getTitulo());
    assertEquals(novaDescricao, tarefa.getDescricao());
    assertEquals(novaDataDeVencimento, tarefa.getDataDeVencimento());
    assertEquals(novaPrioridade, tarefa.getPrioridade());
  }
  
  // [CT29] Excluir Tarefa
  @Test
  public void testCT29ExcluirTarefa() {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);

    gerenciador.excluirTarefa(tarefa.getId());

    assertFalse("A tarefa ainda está presente na lista após exclusão", gerenciador.listaDeTarefas().contains(tarefa));
  }
  
  // [CT30] Listar Tarefas
  public void testCT30ListarTarefas() {
    List<Tarefa> listaVazia = gerenciador.listaDeTarefas();

    assertNotNull("A lista de tarefas não deveria ser 'null'", listaVazia);
    assertTrue("A lista de tarefas deveria estar vazia", listaVazia.isEmpty());
  }
}