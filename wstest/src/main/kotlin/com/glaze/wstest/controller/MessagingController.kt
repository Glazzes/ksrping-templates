package com.glaze.wstest.controller

import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class MessagingController(private val template: SimpMessagingTemplate) {

    @MessageMapping("/chat/**")
    fun handleSomething(message: String){
        println("MESSAGE =>  $message")
    }

    @MessageMapping("/profile/{id}")
    fun doSomething(@Payload message: String, @DestinationVariable id: Long){
        // @Payload and @DestinationVariable are equivalent to @RequestBody
        // and @PathVariable

        println("Message [$message] with profile id $id")
    }

}