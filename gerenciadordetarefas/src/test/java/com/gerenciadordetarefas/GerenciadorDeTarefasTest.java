package com.gerenciadordetarefas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.gerenciadordetarefas.enums.Prioridade;
import com.gerenciadordetarefas.model.Tarefa;

public class GerenciadorDeTarefasTest {

  // 1. Testes de Criar Tarefa
  @Test
  public void testCriarTarefaComInformacoesCompletas() throws ParseException {
      GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();

      // Definir as informações completas para a tarefa
      String titulo = "Tarefa Importante";
      String descricao = "Realizar a apresentação para a equipe.";
      Date dataVencimento = new SimpleDateFormat("yyyy-MM-dd").parse("2023-08-25");
      Prioridade prioridade = Prioridade.ALTA;

      // Criar a tarefa usando o método do gerenciador de tarefas
      Tarefa tarefa = gerenciador.gerenciadorDeTarefasService.criarTarefa(titulo, descricao, dataVencimento, prioridade);

      // Verificar se a tarefa foi criada com sucesso
      assertNotNull("A tarefa não deveria ser 'null'", tarefa);

      // Verificar se a tarefa está presente na lista de tarefas do gerenciador
      assertTrue("A tarefa não está presente na lista", gerenciador.gerenciadorDeTarefasService.listaDeTarefas().contains(tarefa));

      // Verificar se os detalhes da tarefa estão corretos
      assertEquals("Título incorreto", titulo, tarefa.getTitulo());
      assertEquals("Descrição incorreta", descricao, tarefa.getDescricao());
      assertEquals("Data de vencimento incorreta", dataVencimento, tarefa.getDataDeVencimento());
      assertEquals("Prioridade incorreta", prioridade, tarefa.getPrioridade());
    }

    @Test
    public void testCriarTarefaComCamposEmBranco() {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();

        // Tentar criar uma tarefa com campos em branco
        Tarefa tarefa = gerenciador.gerenciadorDeTarefasService.criarTarefa("", "", null, Prioridade.ALTA);

        // Verificar se a tarefa não foi criada
        assertNull("A tarefa deveria ser nula devido aos campos em branco", tarefa);
    }

    @Test
    public void testCriarTarefaComDataInvalida() throws ParseException {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();

        Date dataVencimentoPassado = new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-01");

        // Tentar criar uma tarefa com data de vencimento no passado
        Tarefa tarefaPassado = gerenciador.gerenciadorDeTarefasService.criarTarefa("Título", "Descrição", dataVencimentoPassado, Prioridade.ALTA);
        assertNull("A tarefa deveria ser nula devido à data de vencimento no passado", tarefaPassado);
    }

    @Test
    public void testCriarTarefaComPrioridadeInvalida() {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();

        Date dataVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
        
        // Tentar criar uma tarefa com prioridade inválida
        Tarefa tarefaInvalida = gerenciador.gerenciadorDeTarefasService.criarTarefa("Título", "Descrição", dataVencimento, null);
        assertNull("A tarefa deveria ser nula devido à prioridade inválida", tarefaInvalida);
    }
    
    // 2. Testes de Atualizar Tarefa

    @Test
    public void testAtualizarTituloTarefa() {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();

        Date dataVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã

        // Criar uma tarefa inicial com um título
        Tarefa tarefaInicial = gerenciador.gerenciadorDeTarefasService.criarTarefa("Título Inicial", "Descrição", dataVencimento, Prioridade.ALTA);
        String tarefaId = tarefaInicial.getId();
        assertNotNull("A tarefa inicial não deveria ser nula", tarefaInicial);
        assertEquals("Título inicial incorreto", "Título Inicial", tarefaInicial.getTitulo());

        // Atualizar o título da tarefa utilizando o serviço
        String novoTitulo = "Novo Título";
        gerenciador.gerenciadorDeTarefasService.atualizarTituloTarefa(tarefaId, novoTitulo);
        assertEquals("Título não foi atualizado corretamente", novoTitulo, tarefaInicial.getTitulo());
    }
}
