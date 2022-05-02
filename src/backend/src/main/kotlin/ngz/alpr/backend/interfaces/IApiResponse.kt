package ngz.alpr.backend.interfaces

import org.springframework.http.HttpStatus

interface IApiResponse {
    val status: HttpStatus?;
    val message: String?;
}
