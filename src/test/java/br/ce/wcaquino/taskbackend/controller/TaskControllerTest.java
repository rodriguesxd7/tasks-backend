package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController task;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao(){
        Task todo = new Task();
        todo.setTask("Descrição");
        todo.setDueDate(LocalDate.now());
        try {
            task.save(todo);
            Assert.fail("Nao deveria chegar nese ponto");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData () {
        Task todo = new Task();
        todo.setTask("Descrição da task");
        todo.setDueDate(null);
        try {
            task.save(todo);
            todo.setTask("Descrição");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada () {
        Task todo = new Task();
        todo.setTask("Descrição da task");
        todo.setDueDate(LocalDate.of(2010, 01, 01));
        try {
            task.save(todo);
            todo.setTask("Descrição");
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void deveSalvarTarefaComSucesso () throws ValidationException {
        Task todo = new Task();
        todo.setTask("Descrição da task");
        todo.setDueDate(LocalDate.now());
        task.save(todo);
        Mockito.verify(taskRepo).save(todo);
    }

}
