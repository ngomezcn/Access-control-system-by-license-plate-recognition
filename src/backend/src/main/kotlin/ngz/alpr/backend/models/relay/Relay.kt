package ngz.alpr.backend.models.relay

import com.fazecast.jSerialComm.SerialPort

class Relay(private val comPortName: String, private val open_command: IntArray, private val close_command: IntArray): Runnable {

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
        return if (serialPort.isOpen) {
            serialPort.writeBytes(open_command.foldIndexed(ByteArray(open_command.size)) { i, a, v -> a.apply { set(i, v.toByte()) } }, 4)
            serialPort.closePort();
            true
        } else {
            println("Could not open $comPortName")
            false;
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

