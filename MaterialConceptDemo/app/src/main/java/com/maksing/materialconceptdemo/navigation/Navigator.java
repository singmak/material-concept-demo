package com.maksing.materialconceptdemo.navigation;

import android.content.Context;
import android.content.Intent;

import com.maksing.materialconceptdemo.activity.MainActivity;
import com.maksing.materialconceptdemo.activity.SignInActivity;

/**
 * Created by maksing on 25/12/14.
 */
public class Navigator {
    public static void gotoSignInPage(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

    public static void gotoMainPage(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
