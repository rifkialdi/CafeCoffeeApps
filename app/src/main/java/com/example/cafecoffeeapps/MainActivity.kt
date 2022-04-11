package com.example.cafecoffeeapps

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ulang = 0

        idbtn_tambah.setOnClickListener {
            if (idtv_qty.text.toString().toInt() < 5){
                idtv_qty.text = "${++ulang}"
            }
        }

        idbtn_kurang.setOnClickListener {
            if (idtv_qty.text.toString().toInt() > 0){
                idtv_qty.text = "${--ulang}"
            }
        }

        idbtn_pesan.setOnClickListener {
            /*Check radio button topping dan harga nya*/
            var checkTopping = "Tanpa Topping"
            var checkHargaTopping = "0"
            when (idrg_topping.checkedRadioButtonId){
                R.id.idrb_krimkocok -> {
                    checkTopping = "Krim Kocok"
                    checkHargaTopping = "2000"
                }
                R.id.idrb_coklat -> {
                    checkTopping = "Coklat"
                    checkHargaTopping = "1500"
                }
                R.id.idrb_bicuit -> {
                    checkTopping = "Biscuit"
                    checkHargaTopping = "1000"
                }
            }
            //

            /*Check total harga Kopi dan topping*/
            val totalHargaKopi = "${1500 * ulang}"
            val totalHargaTopping = "${checkHargaTopping.toInt() * ulang}"
            //

            if (idedt_name.text.isEmpty()){
                idedt_name.error = "Isi dulu bro"
                idedt_name.requestFocus()
            }else if (idtv_qty.text.toString().toInt() == 0){
                Toast.makeText(this, "Pilih jumlah yang di pesan !",Toast.LENGTH_SHORT).show()
            }else {
                val namePelanggan = idedt_name.text.toString()
                val qtyPesanan = idtv_qty.text.toString()
                tampilkan(namePelanggan, qtyPesanan, checkTopping, totalHargaKopi, totalHargaTopping)

                /*To reset all option*/
                idedt_name.setText("")
                idtv_qty.setText("0")
                ulang = 0
                idrg_topping.clearCheck()
                //

                /*to hide keyboard*/
                hideKeyboard()
                //
            }
        }
    }

    /*to hide keyboard*/
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    //

    /*function tampil tagihan*/
    fun tampilkan (
        name: String,
        qty: String,
        topping: String,
        TotalHargaKopi: String,
        TotalHargaTopping: String
    ){
        val data =  """
            Nama : $name
            Qty Kopi : $qty         
            Topping : $topping
            Total Harga Kopi : $TotalHargaKopi
            Total Harga Topping : $TotalHargaTopping
            Sub Total : ${TotalHargaKopi.toInt() + TotalHargaTopping.toInt()}
        """.trimIndent()
        idtv_tampilkan.text = data
    }
    //
}