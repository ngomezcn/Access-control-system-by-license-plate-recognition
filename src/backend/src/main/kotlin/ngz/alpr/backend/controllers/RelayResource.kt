package ngz.alpr.backend.controllers

import ngz.alpr.backend.interfaces.IApiResponse
import ngz.alpr.backend.model.api.*
import ngz.alpr.backend.relays
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api/v1/activateRelay"])
class RelayResource() {
    @GetMapping
    fun index(@RequestParam(required = true) id: Int) : IApiResponse {

        return if(id in relays.indices) {
            relays[id].toggleAsync()
            ApiStatus(HttpStatus.OK, "Accionado.");
        } else {
            ApiStatus(HttpStatus.NOT_ACCEPTABLE, "El rele indicado no existe.");
        }
    }
}