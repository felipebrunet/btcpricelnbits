package cl.icripto.btcprice

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.icripto.btcprice.repositories.getBtcPrice
import java.util.*



class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currency = "CLP"
        val amount = 5500.0
        val invoiceKey = "149e50e346754b9695178a011ddd05e4"
        val lnWalletId = "485dd2ed0cad49cb8e1ffcebe8fdcf8b"
        var onChainWalletId = "XLtdyJvwKYBHYxiVbPEhNX"
        val completeLink = ""
        val webHook = ""
        val callbackMessage = ""
        val merchantName = "Restaurant A"
        //val lnbitsServer = "http://3uq56a63buq7snqlwiycosr6sq5dlueql2oj7jsapyop2wuhydonrkyd.onion/satspay/"
        val lnbitsServer = "https://lnbits.icripto.cl/satspay/"
        val limitForOnchain = 5000

        if (limitForOnchain > amount) {
            onChainWalletId = ""
        }



        val btcPriceView = findViewById<TextView>(R.id.btcPrice)
        val satsPagarView = findViewById<TextView>(R.id.charge_btc)
        val paymentData = findViewById<TextView>(R.id.payment_data)

        getBtcPrice(currency, amount, btcPriceView, satsPagarView,
                paymentData, lnWalletId, onChainWalletId, completeLink,
                webHook, callbackMessage, merchantName, lnbitsServer, invoiceKey, baseContext)
    }

}