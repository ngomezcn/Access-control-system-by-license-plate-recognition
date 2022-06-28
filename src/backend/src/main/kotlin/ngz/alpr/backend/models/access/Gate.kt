package ngz.alpr.backend.models.access

import ngz.alpr.backend.models.relay.Relay

class Gate(comPortName: String, open_command: IntArray, close_command: IntArray) {

    private val relay = Relay(comPortName, open_command, close_command)
    fun open() {
        relay.toggleAsync();
    }
}