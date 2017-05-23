package mvpdemon.rxjavaokhttpimg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.iv)
    ImageView iv;
    @InjectView(R.id.btn_loadimage)
    Button btnLoadimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }



    private String path = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491132622246&di=4559752cc3e74351a72f99119243c4c3&imgtype=0&src=http%3A%2F%2Fwww.dianmi.net%2Fuploads%2Fallimg%2F170313%2F95-1F313161951.jpg";

//    private String path = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491129321458&di=e82b32a6a2d694471c8823ef32299567&imgtype=0&src=http%3A%2F%2Fp3.gexing.com%2FG1%2FM00%2F81%2F55%2FrBACJlVzvLqRKAbqAAAaBodntMw957_200x200_3.jpg%3Frecache%3D20131108";
    @OnClick(R.id.btn_loadimage)
    public void onViewClicked() {
        NetUtils.downLoadImg(path).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<byte[]>() {
                    @Override
                    public void onCompleted() {
                        Log.i("TAG", "MainActivity onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", "MainActivity onError()" + e.getMessage());
                    }

                    @Override
                    public void onNext(byte[] bytes) {

                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        iv.setImageBitmap(bitmap);
                    }
                });
    }
}
