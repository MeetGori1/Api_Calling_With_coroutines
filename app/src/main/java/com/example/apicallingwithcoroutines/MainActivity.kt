package com.example.apicallingwithcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apicallingwithcoroutines.RetrofitBuilder.api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await
import retrofit2.awaitResponse

const val BASE_URL = "https://jsonplaceholder.typicode.com"

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        GlobalScope.launch(Dispatchers.IO) {
//            // the await function will give us direct the list of comments if any error happened we can't handle it
//            val comment =api.getComments().await()
//            for (comment in comment){
//                Log.e(TAG,comment.name.toString())
//            }
//        }

        // this is not ideal
//        GlobalScope.launch(Dispatchers.IO) {
//            // to handle eror we neeed to implement await response
//            val response =api.getComments().awaitResponse()
//            if (response.isSuccessful){
//                for (comment in response.body()!!){
//                    Log.e(TAG,comment.name.toString())
//                }
//            }
//        }

        // here is a ideal way
        /*make get comment suspend and replace call with Response now we don't need to use waitResponse  */

        GlobalScope.launch(Dispatchers.IO) {
            // to handle eror we neeed to implement await response
            val response =api.getComments()
            if (response.isSuccessful){
                for (comment in response.body()!!){
                    Log.e(TAG,comment.name.toString())
                }
            }else{
                Log.e(TAG,"Failed")
            }
        }
    }
}