package com.gerenciadordetarefas;

import java.util.ArrayList;
import java.util.List;

import com.gerenciadordetarefas.model.Tarefa;
import com.gerenciadordetarefas.repositories.TarefasRepository;
import com.gerenciadordetarefas.service.GerenciadorDeTarefasService;

public class GerenciadorDeTarefas {
    
    GerenciadorDeTarefasService gerenciadorDeTarefasService;

    public GerenciadorDeTarefas() {
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        TarefasRepository tarefaRepository = new TarefasRepository(tarefas);
        this.gerenciadorDeTarefasService = new GerenciadorDeTarefasService(tarefaRepository);
    }
}