package cl.icripto.btcprice.api

import cl.icripto.btcprice.pricemodels.PriceConv
import retrofit2.Call
import retrofit2.http.*



interface ApiBtcPrice {
    @GET("BTC")
    fun getData(): Call<PriceConv>
}