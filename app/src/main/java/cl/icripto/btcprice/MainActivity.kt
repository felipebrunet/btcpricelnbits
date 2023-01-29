package cl.icripto.btcprice

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.icripto.btcprice.repositories.getBtcPrice
import java.util.*

const val urlSite = "https://lnbits.icripto.cl/"

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currency = "CLP"
        val amount = 20.0
        val lnWalletId = "485dd2ed0cad49cb8e1ffcebe8fdcf8b"
        val onChainWalletId = "XLtdyJvwKYBHYxiVbPEhNX"
        val completeLink = "https://icripto.cl"
        val webHook = "https://icripto.cl"
        val callbackMessage = "Thank you"
        val merchantName = "Restaurant A"
        val lnbitsServer = "https://lnbits.icripto.cl/satspay"



        val btcPriceView = findViewById<TextView>(R.id.btcPrice)
        val satsPagarView = findViewById<TextView>(R.id.charge_btc)
        val paymentData = findViewById<TextView>(R.id.payment_data)

        getBtcPrice(currency, amount, btcPriceView, satsPagarView,
                paymentData, lnWalletId, onChainWalletId, completeLink,
                webHook, callbackMessage, merchantName, lnbitsServer, baseContext)
    }

}