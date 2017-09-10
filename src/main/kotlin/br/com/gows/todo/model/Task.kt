package br.com.gows.todo.model

import org.springframework.data.annotation.Id
import java.time.LocalDate

data class Task(
        @Id
        var id: String? = null,
        var title: String = "",
        var description: String = "",
        var status: Status = Status.TODO,
        var dueDate: LocalDate? = null,
        var dateOfCompletion: LocalDate?
)

enum class Status {
    TODO, DOING, DONE, DELETED, ARCHIVED
}
