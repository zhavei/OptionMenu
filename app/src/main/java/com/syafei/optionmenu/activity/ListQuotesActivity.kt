package com.syafei.optionmenu.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.syafei.optionmenu.adapters.QuoteListAdapter
import com.syafei.optionmenu.databinding.ActivityListquotesBinding
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class ListQuotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListquotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListquotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "List Of Quotes"

        val layoutManager = LinearLayoutManager(this)
        binding.rvQuotes.layoutManager = layoutManager
        val itemDecoratin = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvQuotes.addItemDecoration(itemDecoratin)

        getListQuotes()
    }

    private fun getListQuotes() {
        binding.pbQuotesList.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://quote-api.dicoding.dev/list"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                //if succeed
                binding.pbQuotesList.visibility = View.INVISIBLE

                val listQuotes = ArrayList<String>()
                val result = String(responseBody)
                Log.d(TAG, result)

                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val quote = jsonObject.getString("en")
                        val author = jsonObject.getString("author")
                        listQuotes.add(quote)
                        listQuotes.add(author)
                        //listQuotes.add("\n$quote\n -- $author\n")
                    }

                    val adapter = QuoteListAdapter(listQuotes)
                    binding.rvQuotes.adapter = adapter


                } catch (e: Exception) {
                    Toast.makeText(this@ListQuotesActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                // if fail
                binding.pbQuotesList.visibility = View.INVISIBLE
                val errrorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(this@ListQuotesActivity, errrorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        private val TAG = ListQuotesActivity::class.java.simpleName
    }
}