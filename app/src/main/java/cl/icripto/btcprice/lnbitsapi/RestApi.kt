package cl.icripto.btcprice.lnbitsapi


import cl.icripto.btcprice.invoicemodels.InvoiceData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

//satspay/api/v1/charge?api-key=149e50e346754b9695178a011ddd05e4"


interface RestApi {
    @Headers("Content-Type: application/json")
    @POST("api/v1/charge")
    fun addUser(@Query("api-key") apikey: String, @Body invoiceData: InvoiceData): Call<InvoiceData>

}

//    @POST("satspay/api/v1/charge?api-key=149e50e346754b9695178a011ddd05e4")