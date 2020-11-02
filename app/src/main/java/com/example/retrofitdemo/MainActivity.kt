package com.example.retrofitdemo

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    var mAPIService: ApiInterface? = null
    private var mResponseTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etname = findViewById(R.id.names) as EditText
        val etsalary = findViewById(R.id.salarys) as EditText
        val etage = findViewById(R.id.ages) as EditText
        val sendbtn: Button = findViewById(R.id.test) as Button
        mResponseTv = findViewById(R.id.tv_response) as TextView


        mAPIService = ApiUtils.apiService

        sendbtn.setOnClickListener {
            val name: String = etname.getText().toString().trim()
            val salary: String = etsalary.getText().toString().trim()
            val age: String = etage.getText().toString().trim()
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(salary) && !TextUtils.isEmpty(age)) {
                sendPost(name, salary, age)
            }
        }
    }

    fun sendPost(name: String?, salary: String?, age: String?) {
        mAPIService!!.registrationPost("test", "123", "23")!!.enqueue(object :
            Callback<DataModel?> {

            override fun onResponse(call: Call<DataModel?>, response: Response<DataModel?>) {

//                    Log.i("", "post submitted to API." + response.body()!!)

                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.d("", "post registration to API" + response.body()!!.toString())
//                        Log.d("", "post status to API" + response.body()!!.status)
//                        Log.d("", "post msg to API" + response.body()!!.messages)

                }
            }

            override fun onFailure(call: Call<DataModel?>, t: Throwable) {

            }
        })
    }

    fun showResponse(response: String?) {
        if (mResponseTv!!.visibility == View.GONE) {
            mResponseTv!!.visibility = View.VISIBLE
        }
        mResponseTv!!.text = response
    }
}
