package ngz.alpr.backend.models.relay

import com.fazecast.jSerialComm.SerialPort
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated

/*
class Gate() {

    val relay = Relay()
    fun open() {
        relay.toggleAsync();
    }
}

class AccessManager() {

    val entranceGate = Gate("COM7");
    val exitGate = Gate();
}


fun main() {
    val accessManager = AccessManager();

    accessManager.entranceGate.open()
}

@ConstructorBinding
@ConfigurationProperties(prefix = "ngz")
class ConfigProperties(
    val relay_open_command : String,
    val relay_close_command : String,
    val entrance_gate_port : String,
    val exit_gate_port : String) {


}*/

@ConfigurationProperties(prefix = "person")
@ConstructorBinding
data class PersonProperties(
    val name: String,
    val age: Int,
)

class Relay(private val comPortName: String): Runnable {

    enum class ERelaySerialCommand(val command: ByteArray)
    {
        OPEN(
            byteArrayOf(0xA0.toByte(), 0x01, 0x01, 0xA2.toByte())
        ),
        CLOSE(
            byteArrayOf(0xA0.toByte(), 0x01, 0x00, 0xA1.toByte())
        ),
    }

    private var serialPort: SerialPort = SerialPort.getCommPort(comPortName);
    init {

        serialPort.openPort();
        if (serialPort.isOpen) {
            println("Has been initialize $comPortName")
            closeRelay()
        } else
        {
            println("Could not initialize $comPortName")
        }
    }

    private fun openRelay(): Boolean
    {


        serialPort.openPort();
        if (serialPort.isOpen) {
            serialPort.writeBytes(ERelaySerialCommand.OPEN.command, 4)
            serialPort.closePort();
            return true
        } else
        {
            println("Could not open $comPortName")
            return false;
        }
    }

    private fun closeRelay(): Boolean
    {
        serialPort.openPort();
        if (serialPort.isOpen) {
            serialPort.writeBytes(ERelaySerialCommand.CLOSE.command, 4)
            serialPort.closePort();
            return true
        } else
        {
            println("Could not close $comPortName")
            return false;
        }
    }

    fun toggleAsync() {
        Thread {
            openRelay()
            Thread.sleep(1500)
            closeRelay()
        }.start()
    }

    override fun run() {}
}

