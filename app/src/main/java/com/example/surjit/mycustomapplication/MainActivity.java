package com.example.surjit.mycustomapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private AlertDialog alertDialog;
    private LinearLayout linearLayout;
    private ProgressDialog progressBar;
    long fileSize = 0;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private boolean wantToCloseDialog = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.main_layout);

        button = findViewById(R.id.click);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
                builder.setView(view);
                builder.setCancelable(false);

                builder.setNeutralButton("Neutal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkPoint(1, view);
                        if (wantToCloseDialog)
                            alertDialog.dismiss();
                    }
                });
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);

                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkPoint(2, view);
                        if (wantToCloseDialog)
                            alertDialog.dismiss();
                    }
                });
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE);

                alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkPoint(3, view);
                        if (wantToCloseDialog)
                            alertDialog.dismiss();
                    }
                });
                alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLUE);
            }
        });

    }

    private void checkPoint(int i, View view) {
        EditText t1 = (view.findViewById(R.id.uname));
        String text1 = t1.getText().toString();
        EditText t2 = (view.findViewById(R.id.pass));
        String text2 = t2.getText().toString();
        if (text1.isEmpty() || text2.isEmpty()) {
            if (text1.isEmpty()) t1.setError("enter username");
            if (text2.isEmpty()) t2.setError("enter password");
        } else if (text1.equals("surjit") && text2.equals("singh")) {
        wantToCloseDialog = true;
        switch (i) {
            case 1:
                showListView();
                break;

            case 2:
                showProgressBar2();
                break;

            case 3:
//                push2();
                showGridView();
//                showToast();
                break;

            default:
        }
        } else {
            TextView error = new TextView(this);
            error.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            error.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            error.setTextColor(Color.RED);
            error.setText("Invalid Username/Password");
            error.setGravity(Gravity.CENTER);
            error.setPadding(0, 0, 0, 10);
            ((LinearLayout) view.findViewById(R.id.custom_dialog)).addView(error);
        }
    }

    private void showGridView() {
        button.setVisibility(View.GONE);

        GridView gridView = new GridView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(layoutParams);
        gridView.setVisibility(View.VISIBLE);
        gridView.setNumColumns(2);
        gridView.setVerticalSpacing(200);
        gridView.setBottom(35);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setGravity(Gravity.CENTER);


        ArrayList<Mobile> mobiles = new ArrayList<>();
        mobiles.add(new Mobile("Lenovo", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Samsung", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Apple", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Nokia", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Relience", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Sony", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Microsoft", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Google", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Oppo", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Vivo", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Micromax", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Intex", R.drawable.ic_launcher_background));

        CustomGridViewAdapter adapter = new CustomGridViewAdapter(this, R.layout.custom_list_view, mobiles);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.textView);
                Toast.makeText(MainActivity.this, textView.getText().toString(), Toast.LENGTH_LONG).show();
                view.setBackgroundColor(Color.RED);
            }
        });
        linearLayout.addView(gridView);
    }


    private void showListView() {
        button.setVisibility(View.GONE);

        ListView listView = new ListView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        listView.setLayoutParams(layoutParams);
        listView.setVisibility(View.VISIBLE);

        ArrayList<Mobile> mobiles = new ArrayList<>();
        mobiles.add(new Mobile("Lenovo", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Samsung", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Apple", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Nokia", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Relience", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Sony", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Microsoft", R.drawable.ic_launcher_background));
        mobiles.add(new Mobile("Google", R.drawable.ic_launcher_background));

        CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.custom_list_view, mobiles);

        listView.setAdapter(adapter);
        linearLayout.addView(listView);

    }

    private void showProgressBar() {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setTitle("ProgressDialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }

    private void showProgressBar2() {

        final ProgressDialog progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMax(100);

        final Handler handle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progressDoalog.incrementProgressBy(1);
            }
        };
        progressDoalog.setCancelable(false);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressDoalog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        progressDoalog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        progressDoalog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDoalog.getProgress() <= progressDoalog
                            .getMax()) {
                        Thread.sleep(200);
                        handle.sendMessage(handle.obtainMessage());
                        if (progressDoalog.getProgress() == progressDoalog
                                .getMax()) {
                            progressDoalog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public void push2() {
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("File downloading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        progressBarStatus = 0;
        fileSize = 0;
        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {


                    progressBarStatus = doSomeTasks();


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }

                if (progressBarStatus >= 100) {

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.dismiss();
                }

            }

        }).start();

    }


    public int doSomeTasks() {

        while (fileSize <= 1000000) {

            fileSize++;

            if (fileSize == 100000) return 10;
            else if (fileSize == 200000) return 20;
            else if (fileSize == 300000) return 30;
            else if (fileSize == 400000) return 40;
            else if (fileSize == 500000) return 50;
            else if (fileSize == 600000) return 60;
            else if (fileSize == 700000) return 70;
            else if (fileSize == 800000) return 80;
            else if (fileSize == 900000) return 90;
            else if (fileSize == 1000000) return 100;
        }
        return 100;
    }


    private void showToast() {
        Toast toast = new Toast(this);
        View  view = getLayoutInflater().inflate(R.layout.custom_toast, null);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}