package br.com.gows.todo.controller

import br.com.gows.todo.model.Task
import br.com.gows.todo.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController(private val repository: TaskRepository) {

    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")

    @GetMapping
    fun getAll(): List<Task>? = repository.findAll()

    @GetMapping("/{start}/{end}")
    fun getAllCompletedBetween(@PathVariable start: String, @PathVariable end: String) =
            repository.findByDateOfCompletionBetween(toLocalDate(start), toLocalDate(end))

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Optional<Task>> {
        return if (repository.existsById(id)) {
            ResponseEntity(repository.findById(id), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping(consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun add(@RequestBody task: Task): ResponseEntity<Task> = ResponseEntity(repository.insert(task), HttpStatus.CREATED)

    @PutMapping("/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun update(@PathVariable id: String, @RequestBody task: Task): ResponseEntity<Task> {
        return if (repository.existsById(id)) {
            task.id = id
            ResponseEntity(repository.save(task), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    private fun toLocalDate(start: String) = LocalDate.parse(start, dateFormatter)

}