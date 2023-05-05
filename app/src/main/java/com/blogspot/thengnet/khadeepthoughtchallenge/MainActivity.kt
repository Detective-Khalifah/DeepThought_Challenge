package com.blogspot.thengnet.khadeepthoughtchallenge

import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.blogspot.thengnet.khadeepthoughtchallenge.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<Thoughts>?> {

    // Data binding blueprint/class of MainActivity
    private lateinit var mMainBinding: ActivityMainBinding

    private var thoughtsAdapter: ThoughtAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Create a new {@link ThoughtAdapter} that takes an empty, non-null {@link ArrayList} of
        // {@link Thoughts} as input.
        thoughtsAdapter = ThoughtAdapter(this, ArrayList())

        mMainBinding.recyclerDeepthought.adapter = thoughtsAdapter

        // Parse JSON data.
//        val json = Deepthought.fromJson(jsonString)
//        val deepthoughts : MutableList<Datum> = json.data

//        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
//        binding.recyclerDeepthought.adapter = DeepthoughtAdapter(this, deepthoughts)

        // TODO: Make network call to fetch data.
        val jsonString = ""

        val url: Bundle = Bundle()
        url.putString(
            "link",
            "https://dev.deepthought.education/assets/uploads/files/others/project.json"
        )

        val base: Uri =
            Uri.parse("https://dev.deepthought.education/assets/uploads/files/others/project.json")

        // Check network state and start up {@link Loader}, passing generated {@link URL} if it's
        // connected, otherwise notify via {@link Snackbar}
        val connManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connManager.activeNetworkInfo
        if (netInfo != null && netInfo.isConnected) {
            val loaderManager = supportLoaderManager
            loaderManager.initLoader(
                LOADER_ID, url,
                this@MainActivity as LoaderManager.LoaderCallbacks<List<Thoughts>>
            )
        } else {
            mMainBinding.pbNews.visibility = View.GONE
            Snackbar.make(
                this, mMainBinding.frameSnack, "No net access!",
                Snackbar.LENGTH_LONG
            ).show()
        }

    }

    override fun onCreateLoader(i: Int, bundle: Bundle?): Loader<List<Thoughts>?> {
        Log.v("MainActivity", "onCreateLoader() called.")
        return ThoughtLoader(this, bundle!!)
    }

    override fun onLoadFinished(loader: Loader<List<Thoughts>?>, data: List<Thoughts>?) {
        mMainBinding.pbNews.visibility = View.GONE
        thoughtsAdapter!!.clear()

        // If there is a valid list of {@link Thoughts}, then add them to the {@link ThoughtsAdapter}'s dataset.
        // This will trigger the {@link ListView} to update.
        if (data != null && data.isNotEmpty()) {
            mMainBinding.tvNoa.visibility = View.VISIBLE
            thoughtsAdapter!!.addAll(data)
        } else {
            mMainBinding.tvNoa.setText(R.string.no_thought_fetched)
            mMainBinding.tvNoa.visibility = View.VISIBLE
        }
    }

    override fun onLoaderReset(loader: Loader<List<Thoughts>?>) {
        thoughtsAdapter!!.clear()
    }

    companion object {
        private const val LOADER_ID = 1
    }
}
