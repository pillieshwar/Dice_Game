package com.example.admin.dice_game;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int player_total = 0, player_sub_total = 0;
    int comp_total = 0, comp_sub_total = 0;

    Button b1, b2, b3;
    TextView t1, t2, t3, t4, t6;
    ImageView dice;
    int no = 0;
    int[] arr = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    MediaPlayer mySound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySound = MediaPlayer.create(this,R.raw.ltheme);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t4 = (TextView) findViewById(R.id.textView4);
        t6 = (TextView) findViewById(R.id.textView6);

        dice = (ImageView) findViewById(R.id.imageView);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(" RULES");
        alertDialogBuilder.setMessage("Scarne’s Dice is a turn-based dice game where players score points by rolling a die and then:\n" +
                "•if they roll a 1, score no points and lose their turn\n" +
                "•if they roll a 2 to 6:◦add the rolled value to their points\n" +
                "◦choose to either reroll or keep their score and end their turn\n" +
                "\n" +
                "\n" +
                "The winner is the first player that reaches (or exceeds) 100 points\n");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mySound.start();


                Random r = new Random();
                no = r.nextInt(6);

                dice.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, arr[no]));
                //Animation rotateAnimation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_animation);
                no = no + 1;
                if(player_total>=100 && player_total>comp_total){
                    alertDialogBuilder.setMessage("Player WINS");
                    alertDialogBuilder.setPositiveButton("RESET", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(MainActivity.this,"Done Resetting......", Toast.LENGTH_LONG).show();
                            player_total = 0;
                            player_sub_total = 0;
                            comp_sub_total = 0;
                            comp_total = 0;
                            t3.setText(" " );
                            t4.setText(" " );
                            t6.setText(" ");
                        }
                    });
                    alertDialogBuilder.setNegativeButton("EXIT",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mySound.release();
                            finish();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else
                if (comp_total>=100 && comp_total>player_total){
                    alertDialogBuilder.setMessage("Computer WINS");
                    alertDialogBuilder.setPositiveButton("RESET", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(MainActivity.this,"Done Resetting.......", Toast.LENGTH_LONG).show();
                            player_total = 0;
                            player_sub_total = 0;
                            comp_sub_total = 0;
                            comp_total = 0;
                            t3.setText(" " );
                            t4.setText(" " );
                            t6.setText(" ");
                        }
                    });
                    alertDialogBuilder.setNegativeButton("EXIT",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mySound.release();
                            finish();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                if (no == 1) {
                    player_sub_total = 0;
                    t3.setText(" " + player_total);
                    comp();
                } else {
                    player_sub_total = player_sub_total + no;
                    t4.setText(" " + player_sub_total);

                }

            }
        });
        b2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player_total = player_total + player_sub_total;
                player_sub_total = 0;
                t3.setText(" " + player_total);
                comp();
            }
        }));

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogBuilder.setMessage("Are you sure,You wanted to RESET");
                alertDialogBuilder.setPositiveButton("RESET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Toast.makeText(MainActivity.this,"Done Resetting.........", Toast.LENGTH_LONG).show();
                        player_total = 0;
                        player_sub_total = 0;
                        comp_sub_total = 0;
                        comp_total = 0;
                        t3.setText(" " );
                        t4.setText(" " );
                        t6.setText(" ");
                    }
                });
                alertDialogBuilder.setNegativeButton("EXIT",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mySound.release();
                        finish();
                    }
                });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                player_total = 0;
                player_sub_total = 0;
                comp_sub_total = 0;
                comp_total = 0;
                t3.setText(" " );
                t4.setText(" " );
                t6.setText(" ");

            }
        });

    }

    public void comp() {
        int checkno;
        do {
            Random z = new Random();
            int no2 = z.nextInt(6);
            //dice.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, arr[no2]));
            no2 = no2 + 1;
            if (no2 == 1) {
                comp_sub_total = 0;
                //  t5.setText(" "+comp_total);
                break;
            } else {
                comp_sub_total = comp_sub_total + no2;
                comp_total = comp_total + comp_sub_total;
            }
            Random z1 = new Random();
            checkno = z1.nextInt(6);
            checkno = checkno + 1;
        } while (checkno < 4);
        comp_sub_total = 0;
        t6.setText(" " + comp_total);
    }

//    public void startAnimation(View view) {
//        float dest = 0;
//        //switch (view.getId()) {
//        dest = 360;
//        if (dice.getRotation() == 360) {
//            System.out.println(dice.getAlpha());
//            dest = 0;
//        }
//        ObjectAnimator animation1 = ObjectAnimator.ofFloat(dice,
//                "rotation", dest);
//        animation1.setDuration(2000);
//        animation1.start();
//
//    }
}