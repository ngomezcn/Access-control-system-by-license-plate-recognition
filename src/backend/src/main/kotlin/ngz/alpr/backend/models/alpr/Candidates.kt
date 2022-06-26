package ngz.alpr.backend.models.alpr

data class Candidates (

    var plate           : String? = null,
    var confidence      : Double? = null,
    var matches_template : Int?    = null)