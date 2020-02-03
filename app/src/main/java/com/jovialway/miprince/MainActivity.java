package com.jovialway.miprince;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    String[] poemName;
    String[] poemWriter;
    String[] poem;

    ArrayList<Model> arrayList = new ArrayList<Model>();
    private long backpresstime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.black));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        poemName = new String[]{getString(R.string.poem),getString(R.string.poem1),getString(R.string.poem2),getString(R.string.poem3),getString(R.string.poem4),getString(R.string.poem5),getString(R.string.poem6),getString(R.string.poem7),getString(R.string.poem8),getString(R.string.poem9),getString(R.string.poem10)};
        poemWriter = new String[]{getString(R.string.writer),getString(R.string.writer1),getString(R.string.writer2),getString(R.string.writer3),getString(R.string.writer4),getString(R.string.writer5),getString(R.string.writer6),getString(R.string.writer7),getString(R.string.writer8),getString(R.string.writer9),getString(R.string.writer10)};
        poem = new String[]{getString(R.string.detail),getString(R.string.detail1),getString(R.string.detail2),getString(R.string.detail3),getString(R.string.detail4),getString(R.string.detail5),getString(R.string.detail6),getString(R.string.detail7),getString(R.string.detail8),getString(R.string.detail9),getString(R.string.detail10)};
        recyclerView =  findViewById(R.id.recyclerView);

        for (int i = 0; i < poemName.length; i++) {
            Model model = new Model(poemName[i], poemWriter[i], poem[i]);

            arrayList.add(model);
        }


        adapter = new RecyclerViewAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        int x = toolbar.getScrollX();
                        int y = toolbar.getScrollY();
                    }
                });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter.filter("");
                } else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.other) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=5405265530863735793"));
            startActivity(intent);

            return true;
        }
        if (id == R.id.privacy) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mniprinceapp.blogspot.com/p/privacy-policy-jovialway-built-yoga-for.html"));
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (backpresstime + 2000 > System.currentTimeMillis()) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Do you really want to close this app?");
            builder.setTitle("Confirmation Alert!");
            builder.setCancelable(true);
            builder.setIcon(R.mipmap.ic_launcher_round);
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                    finish();
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);


                }


            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else {
            Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backpresstime = System.currentTimeMillis();

    }

}
