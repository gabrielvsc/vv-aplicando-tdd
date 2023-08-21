package com.gerenciadordetarefas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.gerenciadordetarefas.enums.Prioridade;
import com.gerenciadordetarefas.model.Tarefa;
import com.gerenciadordetarefas.repositories.TarefasRepository;
import com.gerenciadordetarefas.service.GerenciadorDeTarefasServiceImpl;

public class GerenciadorDeTarefasTest {

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

  private void assertTarefaDetalhesIguais(String mensagem, Tarefa tarefa, String titulo, String descricao, Date dataVencimento, Prioridade prioridade) {
    assertEquals(mensagem, titulo, tarefa.getTitulo());
    assertEquals(mensagem, descricao, tarefa.getDescricao());
    assertEquals(mensagem, dataVencimento, tarefa.getDataDeVencimento());
    assertEquals(mensagem, prioridade, tarefa.getPrioridade());
  }

  // 1. Testes de Criar Tarefa
  @Test
  public void testCriarTarefaComInformacoesCompletas() throws ParseException {
    // Definir as informações completas para a tarefa
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);
    
    // Verificar se a tarefa foi criada com sucesso
    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    // Verificar se a tarefa está presente na lista de tarefas do gerenciador
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    // Verificar se os detalhes da tarefa estão corretos
    assertTarefaDetalhesIguais("Título incorreto", tarefa, "Tarefa Importante", "Realizar a apresentação para a equipe.", dataDeVencimento , Prioridade.ALTA);
  }

  @Test
  public void testCriarTarefaComCamposEmBranco() {
    // Tentar criar uma tarefa com campos em branco
    Tarefa tarefa = gerenciador.criarTarefa("", "", null, Prioridade.ALTA);

    // Verificar se a tarefa não foi criada
    assertNull("A tarefa deveria ser nula devido aos campos em branco", tarefa);
  }

  @Test
  public void testCriarTarefaComDataInvalida() throws ParseException {
    Date dataVencimentoPassado = new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-01");

    // Tentar criar uma tarefa com data de vencimento no passado
    Tarefa tarefaPassado = gerenciador.criarTarefa("Título", "Descrição", dataVencimentoPassado, Prioridade.ALTA);
    assertNull("A tarefa deveria ser nula devido à data de vencimento no passado", tarefaPassado);
  }

  @Test
  public void testCriarTarefaComPrioridadeInvalida() {
    Date dataVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    
    // Tentar criar uma tarefa com prioridade inválida
    Tarefa tarefaInvalida = gerenciador.criarTarefa("Título", "Descrição", dataVencimento, null);
    assertNull("A tarefa deveria ser nula devido à prioridade inválida", tarefaInvalida);
  }
    
    // 2. Testes de Atualizar Tarefa

  @Test
  public void testAtualizarTituloTarefa() {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    
    // Título: "Tarefa Importante"
    Tarefa tarefaInicial = criarTarefaCompleta(dataDeVencimento);
    String tarefaId = tarefaInicial.getId();
    
    assertNotNull("A tarefa inicial não deveria ser nula", tarefaInicial);
    assertEquals("Título inicial incorreto", "Tarefa Importante", tarefaInicial.getTitulo());

    // Atualizar o título da tarefa utilizando o serviço
    String novoTitulo = "Novo Título";
    gerenciador.atualizarTituloTarefa(tarefaId, novoTitulo);
    assertEquals("Título não foi atualizado corretamente", novoTitulo, tarefaInicial.getTitulo());
  }

  @Test
  public void testAtualizarDataDeVencimentoTarefa() {
    Date dataDeVencimentoInicial = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã

    // Criar uma tarefa inicial com uma data de vencimento
    Tarefa tarefaInicial = criarTarefaCompleta(dataDeVencimentoInicial);
    String tarefaId = tarefaInicial.getId();

    assertNotNull("A tarefa inicial não deveria ser nula", tarefaInicial);
    assertEquals("Data de vencimento inicial incorreta", dataDeVencimentoInicial, tarefaInicial.getDataDeVencimento());

    // Definir nova data de vencimento
    Date novaDataDeVencimento = new Date(System.currentTimeMillis() + 172800000); // Data de depois de amanhã
    gerenciador.atualizarDataDeVencimentoTarefa(tarefaId, novaDataDeVencimento);

    // Verificar se a data de vencimento foi atualizada corretamente
    assertEquals("Data de vencimento não foi atualizada corretamente", novaDataDeVencimento, tarefaInicial.getDataDeVencimento());

    // Verificar se os outros detalhes da tarefa permaneceram inalterados
    assertTarefaDetalhesIguais(
      "Tarefa Diferente", tarefaInicial,
      "Tarefa Importante",
      "Realizar a apresentação para a equipe.",
      novaDataDeVencimento ,
      Prioridade.ALTA
    );
  }

  @Test
  public void testAtualizarPrioridadeTarefa() {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã

    // Criar uma tarefa inicial com uma prioridade
    Tarefa tarefaInicial = criarTarefaCompleta(dataDeVencimento);
    String tarefaId = tarefaInicial.getId();
    assertNotNull("A tarefa inicial não deveria ser nula", tarefaInicial);
    assertEquals("Prioridade inicial incorreta", Prioridade.ALTA, tarefaInicial.getPrioridade());

    // Atualizar a prioridade da tarefa utilizando o serviço
    Prioridade novaPrioridade = Prioridade.BAIXA;
    gerenciador.atualizarPrioridadeTarefa(tarefaId, novaPrioridade);
    assertEquals("Prioridade não foi atualizada corretamente", novaPrioridade, tarefaInicial.getPrioridade());

    // Verificar se os outros detalhes da tarefa permaneceram inalterados
    assertTarefaDetalhesIguais(
      "Tarefa Diferente", tarefaInicial,
      "Tarefa Importante",
      "Realizar a apresentação para a equipe.",
      dataDeVencimento,
      Prioridade.BAIXA
    );
  }

  @Test
  public void testAtualizarDescricaoTarefa() {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã

    // Criar uma tarefa inicial
    Tarefa tarefaInicial = criarTarefaCompleta(dataDeVencimento);
    assertNotNull("A tarefa inicial não deveria ser nula", tarefaInicial);
    
    // Atualizar a descrição da tarefa utilizando o serviço
    String tarefaId = tarefaInicial.getId();
    String novaDescricao = "Nova Descrição";
    gerenciador.atualizarDescricaoTarefa(tarefaId, novaDescricao);

    // Verificar se a descrição da tarefa foi atualizada corretamente
    assertEquals("Descrição não foi atualizada corretamente", novaDescricao, tarefaInicial.getDescricao());

    // Verificar se os outros detalhes da tarefa permaneceram inalterados
    assertTarefaDetalhesIguais(
      "Tarefa Diferente", tarefaInicial,
      "Tarefa Importante",
      "Nova Descrição",
      dataDeVencimento,
      Prioridade.ALTA
    );
  }


  @Test
  public void testAtualizarTarefaInexistente() {
    // Tente atualizar os detalhes de uma tarefa inexistente
    String tarefaIdInexistente = "tarefa_inexistente_id";
    String novoTitulo = "Novo Título";

    try {
      gerenciador.atualizarTituloTarefa(tarefaIdInexistente, novoTitulo);
      fail("Deveria ter lançado uma exceção ao tentar atualizar tarefa inexistente");
    } catch (NoSuchElementException e) {
      // Verificar se a exceção foi lançada corretamente
      assertEquals("Tarefa não encontrada", e.getMessage());
    }
  }

  // 3. Testes de excluir Tarefa

  @Test
  public void testExcluirTarefaExistente() {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã

    // Criar uma tarefa
    Tarefa tarefa = criarTarefaCompleta(dataDeVencimento);
    assertNotNull("A tarefa não deveria ser nula", tarefa);

    // Excluir a tarefa
    String tarefaId = tarefa.getId();
    gerenciador.excluirTarefa(tarefaId);

    // Verificar se a tarefa foi excluída
    try {
        gerenciador.getTarefa(tarefaId);
        fail("A tarefa não deveria mais existir");
    } catch (NoSuchElementException e) {
    }

    // Verificar se a lista de tarefas está vazia
    List<Tarefa> listaDeTarefas = gerenciador.listaDeTarefas();
    assertTrue("A lista de tarefas deveria estar vazia", listaDeTarefas.isEmpty());
  }

  @Test
  public void testExcluirTarefaInexistente() {
    String tarefaIdInexistente = "tarefa_inexistente_id";

    try {
      gerenciador.excluirTarefa(tarefaIdInexistente);
      fail("Deveria ter lançado uma NoSuchElementException ao tentar excluir tarefa inexistente");
    } catch (NoSuchElementException e) {
      assertEquals("Tarefa não encontrada", e.getMessage());
    }

    // Verificar se a lista de tarefas permanece inalterada
    List<Tarefa> listaDeTarefas = gerenciador.listaDeTarefas();
    assertTrue("A lista de tarefas deveria estar vazia", listaDeTarefas.isEmpty());
  }

  // 4. Testes de listar Tarefas
  @Test
  public void testListarTarefasVazias() {
    // Listar as tarefas quando não há nenhuma tarefa criada
    List<Tarefa> listaDeTarefas = gerenciador.listaDeTarefas();

    // Verificar se a lista está vazia
    assertTrue("A lista de tarefas deveria estar vazia", listaDeTarefas.isEmpty());
  }

  @Test
  public void testListarTarefasOrdenadas() {
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
}
