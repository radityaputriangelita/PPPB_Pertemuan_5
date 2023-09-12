package com.example.pppb_pertemuan_5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.pppb_pertemuan_5.MainActivity.Companion.EXTRA_NAME
import com.example.pppb_pertemuan_5.databinding.ActivityMainBinding
import com.example.pppb_pertemuan_5.databinding.ActivitySecoundBinding

class SecoundActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecoundBinding
    companion object{
        const val EXTRA_ADDRESS = "extra_address"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecoundBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        with(binding){
            txtname.text = name
            btnToThirdActivity.setOnClickListener {
                val intent = Intent(this@SecoundActivity, ThirdActivity::class.java)
                    .apply { putExtra(EXTRA_NAME,name) }
                launcher.launch(intent)
            }

        }
    }
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
// Memeriksa result code
            if (result.resultCode == Activity.RESULT_OK) {
// Mengambil data Intent
                val data = result.data
// Mendapatkan alamat dari data Intent
                val name = data?.getStringExtra(EXTRA_NAME)
                val address = data?.getStringExtra(EXTRA_ADDRESS)
// Menetapkan teks di TextView
                binding.txtname.text = "$name beralamat di $address"
            }
        }
}

