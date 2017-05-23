package mvpdemon.mvpdemon.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvpdemon.mvpdemon.R;
import mvpdemon.mvpdemon.presenter.IUserRegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements IUserRegisterView{

    @Bind(R.id.id_et_username)
    EditText idEtUsername;
    @Bind(R.id.id_et_password)
    EditText idEtPassword;
    @Bind(R.id.id_et_phone)
    EditText idEtPhone;
    @Bind(R.id.id_btn_login)
    Button idBtnLogin;
    @Bind(R.id.id_btn_clear)
    Button idBtnClear;

    private IUserRegisterPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        presenter = new IUserRegisterPresenter(this);
    }

    @OnClick({R.id.id_btn_login, R.id.id_btn_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_btn_login:

                presenter.register();
                break;
            case R.id.id_btn_clear:
                presenter.clear();
                break;
        }
    }


    @Override
    public String getUserName() {
        return idEtUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return idEtPassword.getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return idEtPhone.getText().toString().trim();
    }

    @Override
    public void toLoginActivity() {

        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void showFailed(String e) {
        Toast.makeText(this, "注册失败" +e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearUserName() {

        idEtUsername.setText("");
    }

    @Override
    public void clearPassword() {

        idEtPassword.setText("");
    }

    @Override
    public void clearPhone() {

        idEtPhone.setText("");
    }
}
