package com.gerenciadordetarefas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
}
