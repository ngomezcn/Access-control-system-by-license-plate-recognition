package ngz.alpr.backend.controllers

import ngz.alpr.backend.model.AuthPlateService
import ngz.alpr.backend.model.alpr.AlprData
import ngz.alpr.backend.relays
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(path = ["api/v1/receiver"])
class AlprDataResource(val service: AuthPlateService) {

    @CrossOrigin(origins = ["*"])
    @PostMapping
    fun postCam(@RequestBody alprData: AlprData)  {
        val alprResults = alprData.results;
        if(alprResults.size > 0) {
            val recvedData = alprResults[0];

            if(recvedData.plate?.isEmpty() == false) {
                if(recvedData.plate?.length!! == 7) {
                    val recvedPlate = recvedData.plate;

                    var hasAccess = false;

                    for (i in service.getAllowedPlates()) {
                        if(recvedPlate == i.plate) {
                            hasAccess = true
                        }
                    }

                    if(hasAccess) {
                        println("La matricula $recvedPlate tiene acceso!!")
                        relays[0].toggleAsync()

                    } else
                    {
                        println("La matricula $recvedPlate NO tiene acceso!!")
                    }

                }
            }
        }

    }

    /* @CrossOrigin(origins = ["*"])
     @PostMapping
     fun postCam( httpEntity: HttpEntity<String>) {
         println(httpEntity)
     }*/
}





