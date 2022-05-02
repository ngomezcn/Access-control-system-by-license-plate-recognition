package ngz.alpr.backend.model.alpr

data class AlprData(
    var version           : Int?                         = null,
    var data_type          : String?                      = null,
    var epoch_time         : String?                         = null,
    var img_width          : Int?                         = null,
    var img_height         : Int?                         = null,
    var processing_time_ms  : Double?                      = null,
    var regions_of_interest : ArrayList<RegionsOfInterest> = arrayListOf(),
    var results           : ArrayList<Results>           = arrayListOf(),
    var uuid              : String?                      = null,
    var camera_id          : Int?                         = null,
    var site_id            : String?                      = null)

