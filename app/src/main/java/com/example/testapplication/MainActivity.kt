package com.example.testapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapplication.RetrofitClient.Companion.client
import com.example.testapplication.adapter.ListAdapter
import com.example.testapplication.adapter.ListAdapterListener
import com.example.testapplication.apiService.ApiService
import com.example.testapplication.base.BaseActivity
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.model.ProductListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

class MainActivity : BaseActivity(), ListAdapterListener {

    lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: ListAdapter
    private var list: ArrayList<ProductListModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        setUpAdapter()
        callProductApi()
    }

    private fun callProductApi() {
//        CoroutineScope(Dispatchers.IO).launch {
            client.create(ApiService::class.java).GetProductList()
                .enqueue(object : Callback<ArrayList<ProductListModel>?> {
                    override fun onResponse(
                        p0: Call<ArrayList<ProductListModel>?>,
                        response: Response<ArrayList<ProductListModel>?>
                    ) {
                        if (response.isSuccessful && !response.body()
                                .isNullOrEmpty() && list.isEmpty()
                        ) {
                            response.body()?.let {
//                                list = it
                                list.addAll(it)
                                setUpAdapter()
                            }
                        }
                    }

                    override fun onFailure(p0: Call<ArrayList<ProductListModel>?>, p1: Throwable) {
                        showToast(p1.message.toString())
                    }
                })
        }
//    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setUpAdapter() {
        binding.apply {
            if (::listAdapter.isInitialized) {
                listAdapter.notifyDataSetChanged()
            } else {
                listAdapter = ListAdapter(WeakReference(this@MainActivity), list, this@MainActivity)
                recyclerViewProduct.adapter = listAdapter
            }
        }
    }

    private fun setUpView() {
        binding.apply {
            //todo view set here

        }
    }

    override fun onItemClick() {

    }
}