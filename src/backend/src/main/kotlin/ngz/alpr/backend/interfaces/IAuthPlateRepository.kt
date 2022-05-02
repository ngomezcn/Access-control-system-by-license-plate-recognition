package ngz.alpr.backend.interfaces

import ngz.alpr.backend.services.AuthPlate
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface IAuthPlateRepository : CrudRepository<AuthPlate, String> {

    @Query("select * from auth_plates")
    fun getAuthPlates(): List<AuthPlate>
}
