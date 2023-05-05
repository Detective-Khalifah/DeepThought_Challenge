package com.blogspot.thengnet.khadeepthoughtchallenge

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.loader.content.AsyncTaskLoader

class ThoughtLoader(context: Context?, lookupThoughts: Bundle) : AsyncTaskLoader<List<Thoughts>?>(
    context!!
) {
    private val thoughtURL: String?
    private var result: List<Thoughts>? = null

    init {
        thoughtURL = lookupThoughts.getString("link")
    }

    override fun deliverResult(data: List<Thoughts>?) {
        result = data
        super.deliverResult(data)
    }

    override fun onStartLoading() {
        super.onStartLoading()
        if (result != null) {
            deliverResult(result)
        } else forceLoad()
    }

    override fun loadInBackground(): List<Thoughts>? {
        Log.v("ThoughtLoader", "loadInBackground() called.")

        // Don't perform the request if there are no URLs, or the first URL is null.
        return if (thoughtURL == null) {
            null
        } else {
            Log.v("ThoughtLoader", "loadinginbg")
            // Call static method #lookUpArticles, passing context passed when class was
            // instantiated by call to super(context), the {@link URL} & API code
            result = Think.lookupArticles(context, thoughtURL)
            result
        }
    }
}