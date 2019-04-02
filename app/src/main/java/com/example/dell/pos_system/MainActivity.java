package com.example.dell.pos_system;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Show = findViewById(R.id.SignIn);

        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        Show.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View layout = inflater.inflate(R.layout.dialog_design, null);
                builder1.setView(layout);
                final AlertDialog dialog = builder1.create();

                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
                dialog.show();


                final ImageButton img1 = (ImageButton) layout.findViewById(R.id.image);
                final TextView text =(TextView) layout.findViewById(R.id.msg);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        int cx = img1.getWidth() / 2;
                        int cy = img1.getHeight() / 2;

                        float finalRadius = (float) Math.hypot(cx, cy);

                        Animator anim = ViewAnimationUtils.createCircularReveal(img1, cx, cy, 0, finalRadius);
                        img1.setVisibility(View.VISIBLE);
                        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                        anim.start();
                        img1.startAnimation(shake);
                    }

                },  700);


                text.postDelayed(new Runnable() {
                    public void run() {
                        text.setVisibility(View.VISIBLE);
                    }
                }, 2200);

                // Hide after some seconds
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                };

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        handler.removeCallbacks(runnable);
                    }
                });

                handler.postDelayed(runnable, 3800);
            }
        });
    }


}
