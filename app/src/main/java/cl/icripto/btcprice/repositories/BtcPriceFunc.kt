package cl.icripto.btcprice.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import cl.icripto.btcprice.api.ApiBtcPrice
import cl.icripto.btcprice.invoicemodels.InvoiceData
import cl.icripto.btcprice.pricemodels.PriceConv
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.*

@SuppressLint("SetTextI18n")
fun getBtcPrice(currency : String, amount : Double,
                btcPriceView : TextView, satsPagarView : TextView,
                paymentData : TextView,
                lnWalletId : String, onChainWalletId : String,
                completeLink : String, webHook : String,
                callbackMessage : String, merchantName : String,
                lnbitsServer : String,
                context: Context) {
    var satsAmount: Int
    val amount100x = amount * 100
    val btcPriceUrl = "https://api.yadio.io/convert/${amount100x}/$currency/"


    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(btcPriceUrl)
        .build()
        .create(ApiBtcPrice::class.java)
    val retrofitData = retrofitBuilder.getData()
    retrofitData.enqueue(object : Callback<PriceConv?> {
        override fun onFailure(call: Call<PriceConv?>, t: Throwable) {
            Log.d("MainActivity", "OnFailure: " + t.message)
        }

        @SuppressLint("SetTextI18n")
        override fun onResponse(call: Call<PriceConv?>, response: Response<PriceConv?>) {
            val responseBody = response.body()
            val priceBTC = 1 / responseBody!!.rate
            val btcAmount = responseBody.result / 100
            btcPriceView.text = "Price of Bitcoin is = ${priceBTC.toInt()} $currency"
            satsAmount = (btcAmount * 100000000).toInt()
            satsPagarView.text = "Client pays $amount $currency,\n in sats is = $satsAmount"


            val apiService = RestApiService()


            val invoiceData = InvoiceData(
                amount = satsAmount,
                balance = null,
                completelink = completeLink,
                completelinktext = callbackMessage,
                description = merchantName,
                id = null,
                lnbitswallet = lnWalletId,
                onchainwallet = onChainWalletId,
                onchainaddress = null,
                paid = null,
                payment_hash = null,
                payment_request = null,
                time = 5,
                time_elapsed = null,
                time_left = null,
                timestamp = null,
                user = null,
                webhook = webHook,
                detail = null
            )

            apiService.getInvoice(invoiceData) {
                if (it?.id != null) {
                    //datosPago.text = "Ok, el id es ${it.id}"
                    startActivity(
                        context,
                        Intent.parseUri("$lnbitsServer/${it.id}", 0)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                        null
                    )

                } else {
                    paymentData.text =  "Error registering new user"
                }
            }

        }

    })

}