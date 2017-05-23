package mvpdemon.rxjavalogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.activity_main)
    RelativeLayout activityMain;

    private LoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        loginBean = (LoginBean) getIntent().getSerializableExtra("content");
        tv.setText(loginBean.toString());
    }
}
