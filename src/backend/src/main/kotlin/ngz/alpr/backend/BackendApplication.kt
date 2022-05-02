package ngz.alpr.backend

import ngz.alpr.backend.model.relay.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

val r0 = Relay("COM12")
val r1 = Relay("COM7")
val relays = listOf(r0,r1);

@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Madrid"));

    runApplication<BackendApplication>(*args)
}
