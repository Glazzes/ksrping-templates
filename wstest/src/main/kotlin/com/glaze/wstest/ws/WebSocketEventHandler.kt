package com.glaze.wstest.ws

import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import org.springframework.web.socket.messaging.SessionSubscribeEvent
import java.util.concurrent.ConcurrentHashMap

@Component
class WebSocketEventHandler (private val template: SimpMessagingTemplate){
    private val sessionTracker: MutableMap<String?, MutableList<String?>> = ConcurrentHashMap()

    @EventListener
    fun onSubscriptionEvent(event: SessionSubscribeEvent){
        val accessor = StompHeaderAccessor.wrap(event.message)
        sessionTracker.merge(
            accessor.sessionId,
            mutableListOf(accessor.destination),
        )
        {prev, current ->
            prev.addAll(current)
            prev
        }

        println(sessionTracker)
    }

    @EventListener
    fun onDisconnectionEvent(event: SessionDisconnectEvent){
        val accessor = StompHeaderAccessor.wrap(event.message)
        println("Disconnected from session ${accessor.sessionId}")
        sessionTracker.remove(accessor.sessionId)
    }

}