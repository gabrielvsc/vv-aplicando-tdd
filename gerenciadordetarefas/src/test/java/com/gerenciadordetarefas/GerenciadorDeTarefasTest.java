package com.gerenciadordetarefas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class GerenciadorDeTarefasTest {

  @Test
  public void testCriarTarefaComInformacoesCompletas() {
      GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();

      // Definir as informações completas para a tarefa
      String titulo = "Tarefa Importante";
      String descricao = "Realizar a apresentação para a equipe.";
      String dataVencimento = "2023-08-25";
      Prioridade prioridade = Prioridade.ALTA;

      // Criar a tarefa usando o método do gerenciador de tarefas
      Tarefa tarefa = gerenciador.criarTarefa(titulo, descricao, dataVencimento, prioridade);

      // Verificar se a tarefa foi criada com sucesso
      assertNotNull("A tarefa não deveria ser 'null'", tarefa);

      // Verificar se a tarefa está presente na lista de tarefas do gerenciador
      assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));

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
        Tarefa tarefa = gerenciador.criarTarefa("", "", "", Prioridade.ALTA);

        // Verificar se a tarefa não foi criada
        assertNull("A tarefa deveria ser nula devido aos campos em branco", tarefa);
    }

    @Test
    public void testCriarTarefaComDataInvalida() {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();

        String dataVencimentoPassado = "2023-07-01"; // Exemplo de data no passado
        String dataVencimentoInvalida = "2023-02-30"; // Exemplo de data inválida (não existe)

        // Tentar criar uma tarefa com data de vencimento no passado
        Tarefa tarefaPassado = gerenciador.criarTarefa("Título", "Descrição", dataVencimentoPassado, Prioridade.ALTA);
        assertNull("A tarefa deveria ser nula devido à data de vencimento no passado", tarefaPassado);

        // Tentar criar uma tarefa com data de vencimento inválida
        Tarefa tarefaInvalida = gerenciador.criarTarefa("Título", "Descrição", dataVencimentoInvalida, Prioridade.ALTA);
        assertNull("A tarefa deveria ser nula devido à data de vencimento inválida", tarefaInvalida);
    }
}
