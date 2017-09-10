package br.com.gows.todo

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TodoApplication {

    private val log = LoggerFactory.getLogger(TodoApplication::class.java)


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(TodoApplication::class.java, *args)
        }
    }
}

