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

  public List<Tarefa> listaDeTarefas() {
    return tarefas;
  }
}
