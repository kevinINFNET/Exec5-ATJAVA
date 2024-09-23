package com.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import static spark.Spark.*;

public class AlunoController {
 //  AlunoService que será utilizada na API.
 private static final AlunoService alunoService = new AlunoService();
  // ObjectMapper que será utilizada na API.
 private static final ObjectMapper objectMapper = new ObjectMapper();
  public static void main(String[] args) {
   port(4567); // definição da porta
   get("/alunos", (req, res) -> {
    res.type("application/json");
     return objectMapper.writeValueAsString(alunoService.getAllAlunos());
  });
    get("/alunos/:id", (req, res) -> {
     res.type("application/json");
      int id = Integer.parseInt(req.params(":id"));
      Aluno aluno = alunoService.getAlunoById(id);
      if (aluno == null) {
       res.status(404);
      return objectMapper.writeValueAsString("Aluno não encontrado");
  }
      return objectMapper.writeValueAsString(aluno);
   });

  put("/alunos/:id", (req, res) -> {
      res.type("application/json");
      int id = Integer.parseInt(req.params(":id"));
      Aluno aluno = alunoService.getAlunoById(id);
      if (aluno == null) {
        res.status(404);
      return objectMapper.writeValueAsString("Aluno não encontrado");
     }
      Aluno alunoAtualizado = objectMapper.readValue(req.body(), Aluno.class);
       alunoService.updateAluno(id, alunoAtualizado.getNome(), alunoAtualizado.getNota());
       return objectMapper.writeValueAsString(alunoService.getAlunoById(id));
     });
     delete("/alunos/:id", (req, res) -> {
      res.type("application/json");
        int id = Integer.parseInt(req.params(":id"));
        Aluno aluno = alunoService.getAlunoById(id);
         if (aluno == null) {
          res.status(404);
        return objectMapper.writeValueAsString("Aluno não encontrado");
      }
        alunoService.deleteAluno(id);
          res.status(204);
          return "";
      });
  }
}
