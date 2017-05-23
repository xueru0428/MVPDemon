package mvpdemon.observabledemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void create(View view) {
//        onCreate();

        //链表式
        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("Hello");
                    subscriber.onNext("Rxjava");
                    subscriber.onNext("Andriod");

                    subscriber.onCompleted();
                }

            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("TAG", "MainActivity onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG", "MainActivity onError()" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i("TAG", "MainActivity onNext()" + s);
            }
        });


    }

    private void onCreate() {
        //被观察者

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("Hello");
                    subscriber.onNext("Rxjava");
                    subscriber.onNext("Andriod");

                    subscriber.onCompleted();
                }

            }
        });


        //观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("TAG", "MainActivity onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG", "MainActivity onError()" + e.getMessage());
            }

            @Override
            public void onNext(String s) {

                Log.i("TAG", "MainActivity onNext()" + s);
            }
        };

        //订阅
        observable.subscribe(observer);
    }

    public void form(View view) {

        Integer[] item = {1, 2, 3, 4, 5, 6, 7, 8};

        Observable.from(item).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("TAG", "MainActivity onCompleted()");
            }

            @Override
            public void onError(Throwable e) {

                Log.i("TAG", "MainActivity onError()" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {

                Log.i("TAG", "MainActivity onNext()" + integer.toString());
            }
        });
    }

    public void interval(View view) {
        Observable.interval(2, 1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.i("TAG", "MainActivity call()" + aLong.toString());
            }
        });
    }

    public void just(View view) {
        Integer[] item1 = {1, 2, 3, 4, 5};
        Integer[] item2 = {5, 6, 7, 8, 9};
        Observable.just(item1, item2).subscribe(new Observer<Integer[]>() {
            @Override
            public void onCompleted() {
                Log.i("TAG", "MainActivity onCompleted()");

            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG", "MainActivity onError()" + e.getMessage());
            }

            @Override
            public void onNext(Integer[] integers) {

                for (int i = 0; i < integers.length; i++) {
                    Log.i("TAG", "MainActivity onNext()" + integers[i]);
                }

            }

        });
    }

    public void range(View view) {
        Observable.range(5, 20).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("TAG", "MainActivity onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG", "MainActivity onError()" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("TAG", "MainActivity onNext()" + integer);
            }
        });
    }


    public void filter(View view) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 0).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer <= 6;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("TAG", "MainActivity onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG", "MainActivity onError()" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("TAG", "MainActivity onNext()" + integer);
            }
        });
    }
}
