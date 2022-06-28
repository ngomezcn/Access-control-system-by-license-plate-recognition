package ngz.alpr.backend

import ngz.alpr.backend.models.access.AccessManager
import ngz.alpr.backend.models.access.ConfigProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import java.util.*

@EnableConfigurationProperties(ConfigProperties::class)
@SpringBootApplication
class BackendApplication

var accessManager = AccessManager();

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Madrid"));

    runApplication<BackendApplication>(*args)
}
