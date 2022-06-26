package ngz.alpr.backend.services

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
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


