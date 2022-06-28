package ngz.alpr.backend.models.relay

import com.fazecast.jSerialComm.SerialPort
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated



class Gate(comPortName: String, open_command: IntArray, close_command: IntArray) {

    private val relay = Relay(comPortName, open_command, close_command)
    fun open() {
        relay.toggleAsync();
    }

    init {
        open()
    }
}

class AccessManager() {
    lateinit var entranceGate : Gate
    lateinit var exitGate: Gate
}

var accessManager = AccessManager();

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

        //val a = byteArrayOf(0xA0.toByte(), 0x01, 0x01, 0xA2.toByte())
        //val b = intArrayOf(160, 1, 1, 162)
        //val bytes : ByteArray = b.foldIndexed(ByteArray(b.size)) { i, a, v -> a.apply { set(i, v.toByte()) } }
    }
}

class Relay(private val comPortName: String, private val open_command: IntArray, private val close_command: IntArray): Runnable {

    /*enum class ERelaySerialCommand(val command: ByteArray)
    {
        OPEN(
            byteArrayOf(0xA0.toByte(), 0x01, 0x01, 0xA2.toByte())
        ),
        CLOSE(
            byteArrayOf(0xA0.toByte(), 0x01, 0x00, 0xA1.toByte())
        ),
    }*/

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
            serialPort.writeBytes(open_command.foldIndexed(ByteArray(open_command.size)) { i, a, v -> a.apply { set(i, v.toByte()) } }, 4)
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
            serialPort.writeBytes(close_command.foldIndexed(ByteArray(close_command.size)) { i, a, v -> a.apply { set(i, v.toByte()) } }, 4)
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

