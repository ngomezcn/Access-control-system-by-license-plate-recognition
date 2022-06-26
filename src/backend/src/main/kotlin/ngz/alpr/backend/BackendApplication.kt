package ngz.alpr.backend

import ngz.alpr.backend.models.relay.PersonProperties
import ngz.alpr.backend.models.relay.Relay
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import java.util.*

val r0 = Relay("COM12")
val r1 = Relay("COM7")
val relays = listOf(r0,r1);
@EnableConfigurationProperties(PersonProperties::class)
@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Madrid"));

    runApplication<BackendApplication>(*args)
}
