package mvpdemon.rxjavaokhttpimg;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by XUERU on 2017/4/2.
 */

public class NetUtils {

    public static Observable downLoadImg(final String path){
       final OkHttpClient client = new OkHttpClient();
        return  Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {
                if(!subscriber.isUnsubscribed()) {
                    Request request = new Request.Builder()
                            .url(path)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                            Log.i("TAG", "MainActivity onFailure()"+e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            byte[] bytes = response.body().bytes();
                            Log.i("TAG", "MainActivity onResponse()" );

                            if(bytes != null) {
                                subscriber.onNext(bytes);
                                subscriber.onCompleted();
                            }

                        }
                    });

                }
            }
        });
    }
}
