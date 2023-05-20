package com.nordenms.to_do

import com.nordenms.to_do.oas.api.ToDoApi
import com.nordenms.to_do.oas.model.ToDoItem
import com.nordenms.to_do.oas.model.ToDoItemRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ToDoController : ToDoApi {
    override fun createToDoItem(toDoItemRequest: ToDoItemRequest?): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getAllToDoItems(): ResponseEntity<List<ToDoItem>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getToDoItem(toDoId: Int): ResponseEntity<ToDoItem> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun updateToDoItem(toDoId: Int, toDoItemRequest: ToDoItemRequest?): ResponseEntity<ToDoItem> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun deleteToDoItem(toDoId: Int): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}