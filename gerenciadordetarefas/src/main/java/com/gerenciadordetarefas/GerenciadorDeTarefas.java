package com.gerenciadordetarefas;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTarefas {

    private List<Tarefa> listaDeTarefas;

    public GerenciadorDeTarefas() {
        listaDeTarefas = new ArrayList<>();
    }

    public Tarefa criarTarefa(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
        Tarefa novaTarefa = new Tarefa(titulo, descricao, dataVencimento, prioridade);
        listaDeTarefas.add(novaTarefa);
        return novaTarefa;
    }

    public List<Tarefa> listaDeTarefas() {
        return listaDeTarefas;
    }
}