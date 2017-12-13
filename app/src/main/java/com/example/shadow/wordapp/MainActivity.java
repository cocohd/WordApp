package com.example.shadow.wordapp;

/*import com.example.shadow.wordapp.Word;*/

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.litepal.LitePal;

import java.io.IOException;
import java.net.URL;


public class MainActivity extends Activity {

    private TextView textView;
    private SearchView edit_word;
    private TextView tv_word;
    public TextView tv_ps;
    public static String tv_ps_1;
    private TextView tv_cog_1;
    private TextView tv_acceptation_1;
    private TextView tv_cog_2;
    private TextView tv_acceptation_2;
    private TextView tv_cog_3;
    private TextView tv_acceptation_3;
    private TextView tv_orig;
    private TextView tv_trans;
    private String pron;
    private Button button_pron_en;
    /*private TextView tv_example;*/
    /*private ProgressDialog dialog;*/


    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        edit_word = (SearchView) findViewById(R.id.edit_word);
        button_pron_en = (Button) findViewById(R.id.button_pron_en);
        //tv_word = (TextView) findViewById(R.id.tv_word);
        tv_ps = (TextView) findViewById(R.id.tv_ps);
        tv_cog_1 = (TextView) findViewById(R.id.tv_cog_1);
        tv_acceptation_1 = (TextView) findViewById(R.id.tv_acceptation_1);
        tv_cog_2 = (TextView) findViewById(R.id.tv_cog_2);
        tv_acceptation_2 = (TextView) findViewById(R.id.tv_acceptation_2);
        tv_cog_3 = (TextView) findViewById(R.id.tv_cog_3);
        tv_acceptation_3 = (TextView) findViewById(R.id.tv_acceptation_3);
        tv_orig = (TextView) findViewById(R.id.tv_orig);
        tv_trans = (TextView) findViewById(R.id.tv_trans);

        /*ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("正在查询");*/

        edit_word.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                textView.setText(query);
                button_pron_en.setVisibility(View.VISIBLE);
                WordTask task = new WordTask();
                task.execute();
                /*Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);*/

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        edit_word.setSubmitButtonEnabled(true);


        //设置发音
        Button button1 = (Button) findViewById(R.id.button_pron_en);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setData(Uri.parse(pron));
                startActivity(intent);
            }
        });

        //设置单词数据库
        /*Button button2 = (Button) findViewById(R.id.button_save);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WordDataBase.class);
                startActivity(intent);
                *//*Word word_1=new Word();
                word_1.setId(tv_ps.getText().toString());*//*
                // word_1.setPronounce(pron.toString());
                *//*word_1.setMean1(tv_cog_1.getText().toString());
                word_1.setMean2(tv_cog_2.getText().toString());
                word_1.setMean3(tv_cog_3.getText().toString());
                word_1.setExam(tv_orig.getText().toString());
                word_1.setMean4(tv_trans.getText().toString());*//*
           }
        });*/
        Button create_database=findViewById(R.id.create_database);
        create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
                Toast.makeText(MainActivity.this,"success2",Toast.LENGTH_SHORT).show();
            }
        });
        Button add_database=findViewById(R.id.add_database);
        add_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word_1=new Word();
                word_1.setId("1");
                word_1.setPronounce("baa");
                word_1.setMean1("lol");
                word_1.setMean2("low");
                word_1.setMean3("696");
                word_1.setExam("696");
                word_1.setMean4("696");
                word_1.save();
                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();


            }
        });


    }


    class WordTask extends AsyncTask {
        private Document document;

        private String url = "http://dict-co.iciba.com/api/dictionary.php?w=";
        private String url1 = "&key=FDE3D3CDEF598D851D3CF6EAA96AC278";
        private String word;
        private String ps;
        private String cog_1;
        private String acceptation_1;
        private String cog_2;
        private String acceptation_2;
        private String cog_3;
        private String acceptation_3;
        private String trans;
        private String orig;

        /*private String example;*/

        public WordTask() {
            word = textView.getText().toString();
            url = url + word + url1;
        }

        @Override
        protected Boolean doInBackground(Object[] params) {
            try {
                document = Jsoup.connect(url).get();
                //word=document.select("key").first().text();
                ps = document.select("ps").text();
                pron = document.select("pron").first().text();
                orig = document.select("orig").first().text();
                trans = document.select("trans").first().text();
                cog_1 = document.select("pos").get(0).text();
                acceptation_1 = document.select("acceptation").get(0).text();

                cog_2 = document.select("pos").get(1).text();
                acceptation_2 = document.select("acceptation").get(1).text();

                cog_3 = document.select("pos").get(2).text();
                acceptation_3 = document.select("acceptation").get(2).text();


                //Elements links = document.select("acceptation");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
           /* dialog.show();*/
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            //tv_word.setText(word);
            tv_ps.setText(ps);
            tv_ps_1=tv_ps.toString();
            tv_cog_1.setText(cog_1);
            tv_acceptation_1.setText(acceptation_1);
            tv_cog_2.setText(cog_2);
            tv_acceptation_2.setText(acceptation_2);
            tv_cog_3.setText(cog_3);
            tv_acceptation_3.setText(acceptation_3);

            tv_orig.setText(orig);
            tv_trans.setText(trans);

            /*tv_example.setText(example);*//*
            /*dialog.dismiss();*/
            super.onPostExecute(o);

        }


    }



}






