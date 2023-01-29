package cl.icripto.btcprice

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.icripto.btcprice.repositories.getBtcPrice
import java.util.*

var urlSite = "https://lnbits.icripto.cl/"
var invoiceKey = "149e50e346754b9695178a011ddd05e4"

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currency = "CLP"
        urlSite = "https://lnbits.icripto.cl/"
        val amount = 4500.0
        invoiceKey = "149e50e346754b9695178a011ddd05e4"
        val lnWalletId = "485dd2ed0cad49cb8e1ffcebe8fdcf8b"
        var onChainWalletId = "XLtdyJvwKYBHYxiVbPEhNX"
        val completeLink = "https://icripto.cl"
        val webHook = "https://icripto.cl"
        val callbackMessage = "Thank you"
        val merchantName = "Restaurant A"
        val lnbitsServer = "${urlSite}satspay"
        val limitForOnchain = 5000

        if (limitForOnchain > amount) {
            onChainWalletId = ""
        }



        val btcPriceView = findViewById<TextView>(R.id.btcPrice)
        val satsPagarView = findViewById<TextView>(R.id.charge_btc)
        val paymentData = findViewById<TextView>(R.id.payment_data)

        getBtcPrice(currency, amount, btcPriceView, satsPagarView,
                paymentData, lnWalletId, onChainWalletId, completeLink,
                webHook, callbackMessage, merchantName, lnbitsServer, baseContext)
    }

}