package ngz.alpr.backend.model.Api

import ngz.alpr.backend.interfaces.IApiResponse
import org.springframework.http.HttpStatus

data class ApiException(override val status: HttpStatus?, override val message: String?, val ex: String?)  :
    IApiResponse;
