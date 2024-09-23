package com.example;

public class Main {
    public static void main(String[] args) {
        AlunoService alunoService = new AlunoService();
        System.out.println("Lista de Alunos:");
        alunoService.getAllAlunos().forEach(aluno ->
                System.out.println(aluno.getId() + ": " + aluno.getNome() + " - " + aluno.getNota())
        );
        Aluno aluno = alunoService.getAlunoById(1);
        System.out.println("Aluno encontrado: " + aluno.getNome());
        alunoService.updateAluno(1, "Andre Sousa", 9.0);
        System.out.println("Aluno atualizado: " + alunoService.getAlunoById(1).getNome());
        alunoService.deleteAluno(2);
        System.out.println("Alunos após exclusão:");
        alunoService.getAllAlunos().forEach(a ->
                System.out.println(a.getId() + ": " + a.getNome())
        );
    }
}