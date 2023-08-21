package com.gerenciadordetarefas.repositories;

import java.util.ArrayList;
import java.util.List;

import com.gerenciadordetarefas.model.Tarefa;

/**
 * Repositório de tarefas que mantém uma lista de tarefas.
 */
public class TarefasRepository {

  private List<Tarefa> tarefas;

  /**
   * Construtor da classe.
   *
   * @param tarefas Uma lista de tarefas para inicializar o repositório.
   */
  public TarefasRepository(List<Tarefa> tarefas) {
    this.tarefas = new ArrayList<Tarefa>(tarefas);
  }

  /**
   * Cria uma nova tarefa e a adiciona ao repositório.
   *
   * @param tarefa A tarefa a ser criada e adicionada.
   * @return {@code true} se a tarefa foi adicionada com sucesso, {@code false} caso contrário.
   */
  public boolean criarTarefa(Tarefa tarefa) {
    return tarefas.add(tarefa);
  }

  /**
   * Remove uma tarefa do repositório com base no seu ID.
   *
   * @param id O ID da tarefa a ser excluída.
   */
  public void excluirTarefa(String id) {
    tarefas.removeIf(tarefa -> tarefa.getId().equals(id));
  }

  /**
   * Retorna a lista de todas as tarefas presentes no repositório.
   *
   * @return A lista de tarefas.
   */
  public List<Tarefa> listaDeTarefas() {
    return tarefas;
  }

  /**
   * Busca uma tarefa no repositório com base no seu ID.
   *
   * @param id O ID da tarefa a ser buscada.
   * @return A tarefa correspondente ao ID, ou {@code null} se não encontrada.
   */
  public Tarefa findById(String id) {
    for (Tarefa tarefa : tarefas) {
      if (tarefa.getId().equals(id)) {
        return tarefa;
      }
    }
    return null;
  }
}
