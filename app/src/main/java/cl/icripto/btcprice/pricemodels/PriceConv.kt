package cl.icripto.btcprice.pricemodels

data class PriceConv(
    val rate: Double,
    val request: Request,
    val result: Double,
    val timestamp: Long
)

data class Request(
    val amount: Int,
    val from: String,
    val to: String
)