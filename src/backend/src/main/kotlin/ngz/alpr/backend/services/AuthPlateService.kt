package ngz.alpr.backend.services

import ngz.alpr.backend.interfaces.IAuthPlateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class  AuthPlateService(val db: IAuthPlateRepository) {

    @Autowired
    fun getAllowedPlates(): List<AuthPlate> = db.getAuthPlates()
    fun post(newAuthPlate: AuthPlate) {
        db.save(newAuthPlate)
    }

    fun delete(id: String) {
        if (!db.existsById(id)) {
            throw IllegalStateException(
                "The license plate $id is not in the database"
            )
        }
        db.deleteById(id)
    }

    fun update(authPlate: AuthPlate) {
        if (!authPlate.id?.let { db.existsById(it) }!!) {
            throw IllegalStateException(
                "The license plate ${authPlate.id} is not in the database"
            )
        }
        db.save(authPlate)
    }
}
