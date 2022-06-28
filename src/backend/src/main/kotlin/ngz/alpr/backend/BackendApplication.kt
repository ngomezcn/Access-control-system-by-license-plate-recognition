package ngz.alpr.backend

import ngz.alpr.backend.models.relay.ConfigProperties
import ngz.alpr.backend.models.relay.Relay
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import java.util.*


@EnableConfigurationProperties(ConfigProperties::class)
@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Madrid"));

    runApplication<BackendApplication>(*args)
}
