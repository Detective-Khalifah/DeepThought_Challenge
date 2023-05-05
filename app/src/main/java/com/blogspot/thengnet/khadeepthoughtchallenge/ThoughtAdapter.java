package com.blogspot.thengnet.khadeepthoughtchallenge;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.blogspot.thengnet.khadeepthoughtchallenge.databinding.DeepthoughtBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class ThoughtAdapter extends ArrayAdapter<Thoughts> {

    private LayoutInflater inflater;
    private Context mAppContext;

    protected DeepthoughtBinding binder;

    public ThoughtAdapter (Context context, List<Thoughts> objects) {
        super(context, 0, objects);
        mAppContext = context;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = ((Activity) mAppContext).getLayoutInflater();

        Thoughts currentThought = getItem(position);

        binder = DataBindingUtil.getBinding(convertView);
        if (binder == null)
            binder = DataBindingUtil.inflate(inflater, R.layout.deepthought, parent, false);

        Picasso.get().load(Uri.parse(currentThought.getPicture()))
                .placeholder(R.drawable.dt_vector_logo) // placeholder image resource
                .into(binder.imagePicture); // image view in the DeepthoughtBinding layout

        binder.setThought(currentThought);
        binder.executePendingBindings();

        return binder.getRoot();
    }
}