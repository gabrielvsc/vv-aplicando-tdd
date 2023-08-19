package com.gerenciadordetarefas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerenciadorDeTarefas {

    private List<Tarefa> listaDeTarefas;

    public GerenciadorDeTarefas() {
        listaDeTarefas = new ArrayList<>();
    }

    public Tarefa criarTarefa(String titulo, String descricao, Date dataDeVencimento, Prioridade prioridade) {
        if (titulo.isEmpty() || descricao.isEmpty() || dataDeVencimento.toString().isEmpty() || !isValidDate(dataDeVencimento)) {
            return null;
        }

        Tarefa novaTarefa = new Tarefa(titulo, descricao, dataDeVencimento, prioridade);
        listaDeTarefas.add(novaTarefa);
        return novaTarefa;
    }

    public List<Tarefa> listaDeTarefas() {
        return listaDeTarefas;
    }

    private boolean isValidDate(Date date) {
        Date dataAtual = new Date();
        return date.after(dataAtual);
    }
}