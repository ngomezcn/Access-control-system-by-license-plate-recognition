package ngz.alpr.backend.controllers

import ngz.alpr.backend.interfaces.IApiResponse
import ngz.alpr.backend.model.*
import ngz.alpr.backend.model.Api.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import javax.persistence.PostUpdate


@RestController
@RequestMapping(path = ["api/v1/authcars"])
class AuthCarResource(val service: AuthPlateService) {

    @CrossOrigin(origins = ["*"])
    @RequestMapping
    fun getAuthorizedCars(): List<AuthPlate> = service.getAllowedPlates();

    @PostMapping
    fun post(@RequestBody newCar: AuthPlate): IApiResponse {
        return try {
            service.post(newCar)
            ApiStatus(HttpStatus.OK,"Matrícula agregada correctamente.");
        } catch (e: Exception) {
            ApiException(HttpStatus.NOT_ACCEPTABLE,"No se ha podido añadir la matricula", e.message);
        }
    }

    @CrossOrigin(origins = ["*"])
    @DeleteMapping
    fun delete(@RequestParam(required = true) id:  String): IApiResponse {
        return try {
            service.delete(id)
            ApiStatus(HttpStatus.OK,"Matrícula eliminada correctamente.");
        } catch (e: Exception) {
            ApiException(HttpStatus.NOT_ACCEPTABLE,"No se ha podido eliminar la matricula.", e.message);
        }
    }

    @CrossOrigin(origins = ["*"])
    @PostUpdate
    @RequestMapping(path = ["update"])
    fun update(@RequestBody car: AuthPlate): IApiResponse {
        return try {
            service.update(car)
            ApiStatus(HttpStatus.OK,"Matrícula modificada correctamente.");
        } catch (e: Exception) {
            ApiException(HttpStatus.NOT_ACCEPTABLE,"No se ha podido modificar la matricula", e.message);
        }
    }
}