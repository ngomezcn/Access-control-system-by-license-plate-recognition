package ngz.alpr.backend.models.alpr

data class Results (

    var plate            : String?                = null,
    var confidence       : Double?                = null,
    var matches_template  : Int?                   = null,
    var plate_index       : Int?                   = null,
    var region           : String?                = null,
    var region_confidence : Int?                   = null,
    var processing_time_ms : Double?                = null,
    var requested_topn    : Int?                   = null,
    var coordinates      : ArrayList<Coordinates> = arrayListOf(),
    var candidates       : ArrayList<Candidates>  = arrayListOf())
