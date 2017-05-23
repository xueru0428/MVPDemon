package mvpdemon.rxjavalogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.textView2)
    TextView textView2;
    @InjectView(R.id.editText)
    EditText editText;
    @InjectView(R.id.editText2)
    EditText editText2;
    @InjectView(R.id.button)
    Button button;
    private NetUtils netUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        netUtils = new NetUtils();
    }


    private String path = "http://47.93.118.241:8081/android/user/login?";

    @OnClick(R.id.button)
    public void onViewClicked() {


        final Map<String, String> map = new HashMap();
        map.put("username", editText.getText().toString());
        map.put("password", editText2.getText().toString());
        map.put("phone", "18601042258");

//        Login1(map);
        
        login2(map);

    }

    private void login2(Map<String, String> map) {
        netUtils.login(path,map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LoginBean>() {


            @Override
            public void onCompleted() {
                Log.i("TAG", "LoginActivity onCompleted()");

            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG", "LoginActivity onError()" + e.getMessage());
            }

            @Override
            public void onNext(LoginBean loginBean) {
                if(loginBean.getStatus() == 200) {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("content",loginBean);
                    startActivity(intent);
                }
            }

        });
    }

    private void Login1(final Map<String, String> map) {
        final OkHttpClient client = new OkHttpClient();

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {

                    FormBody.Builder builder = new FormBody.Builder();

                    if (map != null && map.size() > 0) {

                        for (Map.Entry<String, String> entry : map.entrySet()) {

                            builder.add(entry.getKey(), entry.getValue());
                        }

                    }
                    //发起请求
                    FormBody build = builder.build();


                    final Request request = new Request.Builder()
                            .url(path)
                            .post(build)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i("TAG", "LoginActivity onFailure()" + e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            if (response.isSuccessful()) {
                                String data = response.body().string();

                                subscriber.onNext(data);
                                subscriber.onCompleted();
                            }
                        }
                    });


                }
            }


        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {


            @Override
            public void onCompleted() {
                Log.i("TAG", "LoginActivity onCompleted()");

            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG", "LoginActivity onError()" + e.getMessage());
            }

            @Override
            public void onNext(String s) {


                //解析数据
                LoginBean loginBean = new Gson().fromJson(s, LoginBean.class);

                if(loginBean.getStatus() == 200) {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("content",loginBean);
                    startActivity(intent);
                }

            }


        });
    }
}
