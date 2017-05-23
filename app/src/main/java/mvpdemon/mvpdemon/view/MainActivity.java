package mvpdemon.mvpdemon.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import mvpdemon.mvpdemon.R;
import mvpdemon.mvpdemon.model.bean.User;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_user)
    TextView tvUser;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;


    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        user =(User) getIntent().getSerializableExtra("content");

        tvUser.setText(user.toString());
    }
}
