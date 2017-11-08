package com.keba.keba.util;

import android.widget.ImageView;

import com.keba.keba.R;

/**
 * Created by Sparrow on 11/8/2017.
 */

public class AvatarHelper {
    
    public static void addAvatar(ImageView imageView, String name) {
        if(name.equals("Fabian123")) {
            imageView.setImageResource(R.drawable.fabian123);
        } else if(name.equals("Bernhard5001")) {
            imageView.setImageResource(R.drawable.bernhard5001);
        } else {
            imageView.setImageResource(R.drawable.ic_action_avatar);
        }
    }
    
    
}
