package com.gerenciadordetarefas.repositories;

import java.util.ArrayList;
import java.util.List;

import com.gerenciadordetarefas.model.Tarefa;

public class TarefasRepository {

  private List<Tarefa> tarefas;

  public TarefasRepository(List<Tarefa> tarefas) {
    this.tarefas = new ArrayList<Tarefa>(tarefas);
  }

  public boolean criarTarefa(Tarefa tarefa) {
    return tarefas.add(tarefa);
  }

  public void excluirTarefa(String id) {
    Tarefa tarefa = findById(id);
    tarefas.remove(tarefa);
  }

  public List<Tarefa> listaDeTarefas() {
    return tarefas;
  }

  public Tarefa findById(String id) {
    for (Tarefa tarefa : tarefas) {
      if (tarefa.getId().equals(id)) {
        return tarefa;
      }
    }
    return null;
  }
}
