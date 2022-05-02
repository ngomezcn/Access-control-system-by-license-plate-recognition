package ngz.alpr.backend.model

import ngz.alpr.backend.model.alpr.AlprData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
class  AlprDataService() {

    fun camReceived(alprData: AlprData) {
        println(alprData)
    }
}
