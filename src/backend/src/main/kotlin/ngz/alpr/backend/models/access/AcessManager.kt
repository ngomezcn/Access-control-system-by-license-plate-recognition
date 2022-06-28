package ngz.alpr.backend.models.access

import ngz.alpr.backend.accessManager
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

class AccessManager() {
    lateinit var entranceGate : Gate
    lateinit var exitGate: Gate
}

@ConstructorBinding
@ConfigurationProperties(prefix = "ngz")
class ConfigProperties(
    relay_open_command : IntArray,
    relay_close_command : IntArray,

    entrance_gate_port : String,
    exit_gate_port : String) {

    init {
        accessManager.entranceGate = Gate(entrance_gate_port, relay_open_command, relay_close_command)
        accessManager.exitGate = Gate(exit_gate_port, relay_open_command, relay_close_command)
    }
}
