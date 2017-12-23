package com.example.wenkun.githubapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static OkHttpClient createOkHttp() {
        OkHttpClient okHttpClient=new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS).writeTimeout(10,TimeUnit.SECONDS).build();
        return okHttpClient;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView mRecyclerView=(RecyclerView)findViewById(R.id.mRecycleView);
        final List<Github> data=new ArrayList<>();;
        Github temp=new Github("zwk","fuck",222);
            data.add(temp);

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.github.com/").
                client(createOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        final GithubService service=retrofit.create(GithubService.class);

        Button fetch=(Button) findViewById(R.id.fetch);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name=(EditText)findViewById(R.id.name);

                rx.Observable<Github> githubCall = service.getUser(name.getText().toString());

                githubCall.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Subscriber<Github>() {
                                    @Override
                                    public void onCompleted() {
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                    }

                                    @Override
                                    public void onNext(Github github) {
                                        data.add(github);
                                        final CardAdapter myAdapter=new CardAdapter(MainActivity.this,data);
                                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        mRecyclerView.setAdapter(myAdapter);
                                        //tvMsg.setText("无封装：\n" + retrofitEntity.getData().toString());
                                    }
                                }

                        );
            }
        });



        final CardAdapter myAdapter=new CardAdapter(this,data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(myAdapter);
    }


}
