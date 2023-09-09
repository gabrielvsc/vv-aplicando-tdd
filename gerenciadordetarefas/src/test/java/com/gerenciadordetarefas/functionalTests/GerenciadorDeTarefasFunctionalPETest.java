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
}
