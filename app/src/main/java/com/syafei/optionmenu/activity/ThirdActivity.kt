package com.syafei.optionmenu.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.syafei.optionmenu.BuildConfig
import com.syafei.optionmenu.data.model.ApiService
import com.syafei.optionmenu.data.model.DataItem
import com.syafei.optionmenu.data.model.ResponseUser
import com.syafei.optionmenu.databinding.ActivityThirdBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataRetrofit()
        getLoggingInteceptor()
        getLoggingChucker()

        binding.pickImageButton.setOnClickListener {
            pickImageFromGallery()
        }
        binding.btCapturePhoto.setOnClickListener {
            openCamera()
        }
    }

    //region checker example
    private fun getLoggingChucker() {
        val clientChucker = OkHttpClient.Builder().addInterceptor(ChuckerInterceptor(this)).build()
        val retrofitChuk =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(clientChucker).build()
    }

    private fun getLoggingInteceptor() {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val retrofitIntercept =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(client).build()

        val loggingInterceptorLog = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

    }

    private fun getDataRetrofit() {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val apiService = retrofit.create(ApiService::class.java)

        val clients = apiService.getListUser("1")
        clients.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    val listUser = response.body()?.data as List<DataItem>
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    //endregion

    // region 2nd pick camera
    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(packageManager)?.also {
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_PERMISSION
            )
        }
    }
    //endregion

    // region 1st image pick galery
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }
    // endregion

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // region 1st pick galery
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.imageView.setImageURI(data?.data)
        }
        // endregion
        //region 2nd pick from camera
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                val bitmap = data?.extras?.get("data") as Bitmap
                binding.imageView.setImageBitmap(bitmap)
            } else if (requestCode == REQUEST_PICK_IMAGE) {
                val uri = data?.getData()
                binding.imageView.setImageURI(uri)
            }
        }
        //endregion
    }

    companion object {
        const val BASE_URL = "https://reqres.in/"
        val TAG: String = ThirdActivity::class.java.simpleName

        val IMAGE_REQUEST_CODE = 1_000;

        private val REQUEST_PERMISSION = 100
        private val REQUEST_IMAGE_CAPTURE = 1
        private val REQUEST_PICK_IMAGE = 2
    }

    override fun onResume() {
        super.onResume()
        checkCameraPermission()
    }


}