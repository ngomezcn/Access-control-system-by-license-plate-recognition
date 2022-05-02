package ngz.alpr.backend.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.sql.Timestamp

@Table("auth_plates")
data class AuthPlate(
                @Id
                val id: String?,

                val plate: String,
                val register_date: Timestamp?,
                val drop_out_date: Timestamp?,
                val open_time: String?,
                val close_time: String?)

interface AuthPlateRepository : CrudRepository<AuthPlate, String> {

    @Query("select * from auth_plates")
    fun getAuthPlates(): List<AuthPlate>
}

@Repository
class  AuthPlateService(val db: AuthPlateRepository) {

    @Autowired
    fun getAllowedPlates(): List<AuthPlate> = db.getAuthPlates()
    fun post(newAuthPlate: AuthPlate) {
        db.save(newAuthPlate)
    }

    fun delete(id: String) {
        if (!db.existsById(id)) {
            throw IllegalStateException(
                "La matrícula $id no existe."
            )
        }
        db.deleteById(id)
    }

    fun update(authPlate: AuthPlate) {
        if (!authPlate.id?.let { db.existsById(it) }!!) {
            throw IllegalStateException(
                "La matrícula ${authPlate.id} no existe."
            )
        }
        db.save(authPlate)
    }
}
