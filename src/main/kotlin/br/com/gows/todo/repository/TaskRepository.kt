package br.com.gows.todo.repository

import br.com.gows.todo.model.Task
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDate

interface TaskRepository : MongoRepository<Task, String> {
    fun findByDateOfCompletionBetween(start: LocalDate, end: LocalDate): List<Task>
}

