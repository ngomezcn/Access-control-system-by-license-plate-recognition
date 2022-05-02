package ngz.alpr.backend.model.relay

import com.fazecast.jSerialComm.SerialPort

class Relay(private val comPortName: String ): Runnable {

    /**
     *
     */
    enum class ERelaySerialCommand(val command: ByteArray)
    {
        OPEN(byteArrayOf(0xA0.toByte(), 0x01, 0x01, 0xA2.toByte())),
        CLOSE(byteArrayOf(0xA0.toByte(), 0x01, 0x00, 0xA1.toByte())),
    }

    /**
     *
     */
    private var serialPort: SerialPort = SerialPort.getCommPort(comPortName);

    /**
     \
     */
    init {
        serialPort.openPort();
        if (serialPort.isOpen) {
            println("Has been opened $comPortName")
            closeRelay()
        } else
        {
            println("Could not open $comPortName")
        }
    }

    /**
     *
     */
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
            return false
        }
    }

    /**
     *
     */
    private fun closeRelay(): Boolean
    {
        serialPort.openPort();
        if (serialPort.isOpen) {
            serialPort.writeBytes(ERelaySerialCommand.CLOSE.command, 4)
            serialPort.closePort();
            return true
        } else
        {
            println("Could not open $comPortName")
            return false
        }
    }

    /**
     *
     */
    fun toggleAsync() {

        Thread {
            openRelay()
            Thread.sleep(1500)
            closeRelay()
        }.start()
    }

    override fun run() {}
}

