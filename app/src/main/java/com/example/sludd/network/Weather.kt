//import kotlinx.serialization.Serializable

//@Serializable
//data class Weather(
//    val type: String,
//    val geometry: Geometry,
//    val properties: Properties
//)
//
//@Serializable
//data class Geometry(
//    val type: String,
//    val coordinates: List<Double>
//)
//
//@Serializable
//data class Properties(
//    val meta: Meta,
//    val timeseries: List<TimeSeries>
//)
//
//@Serializable
//data class Meta(
//    val updated_at: String,
//    val units: Units
//)
//
//@Serializable
//data class Units(
//    val air_temperature: String,
//    val precipitation_amount: String,
//    val precipitation_rate: String,
//    val relative_humidity: String,
//    val wind_from_direction: String,
//    val wind_speed: String,
//    val wind_speed_of_gust: String
//)
//
//@Serializable
//data class TimeSeries(
//    val time: String,
//    val data: Data
//)
//
//@Serializable
//data class Data(
//    val instant: Instant,
//    val next_1_hours: NextHours? = null
//)
//
//@Serializable
//data class Instant(
//    val details: Details
//)
//
//
//@Serializable
//data class NextHours(
//    val summary: Summary,
//    val details: Details? = null
//)
//
//@Serializable
//data class Summary(
//    val symbol_code: String
//)
//
//@Serializable
//data class Details(
//    val air_temperature: Double? = null,
//    val precipitation_rate: Double? = null,
//    val relative_humidity: Double? = null,
//    val wind_from_direction: Double? = null,
//    val wind_speed: Double? = null,
//    val wind_speed_of_gust: Double? = null,
//    val precipitation_amount: Double? = null
//)