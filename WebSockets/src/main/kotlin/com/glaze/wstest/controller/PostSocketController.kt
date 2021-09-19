package com.glaze.wstest.controller

import org.springframework.http.HttpStatus
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class PostSocketController(private val template: SimpMessagingTemplate) {

    @PostMapping("/chat/**")
    @ResponseStatus(value = HttpStatus.OK)
    fun resendMessage(@RequestBody message: String){
        template.convertAndSend("/chatroom/3", message)
    }

}