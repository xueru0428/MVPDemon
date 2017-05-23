package mvpdemon.rxjavalogin;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by XUERU on 2017/4/2.
 */

public class NetUtils {

    final OkHttpClient client ;

    public NetUtils(){
        client = new OkHttpClient();
    }

    public Observable<LoginBean> login(final String path, final Map<String, String> map){
        return Observable.create(new Observable.OnSubscribe<LoginBean>() {
            @Override
            public void call(final Subscriber<? super LoginBean> subscriber) {
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
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            if (response.isSuccessful()) {
                                String data = response.body().string();

                                LoginBean loginBean = new Gson().fromJson(data, LoginBean.class);

                                subscriber.onNext(loginBean);
                                subscriber.onCompleted();
                            }
                        }
                    });


                }
            }


        });
    }
}
