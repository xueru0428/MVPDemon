package mvpdemon.mvpdemon.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvpdemon.mvpdemon.R;
import mvpdemon.mvpdemon.model.IUserLoginModel;
import mvpdemon.mvpdemon.model.UserLoginModel;
import mvpdemon.mvpdemon.model.bean.User;
import mvpdemon.mvpdemon.presenter.IUserLoginPresenter;

public class LoginActivity extends AppCompatActivity implements IUserLoginView {

    @Bind(R.id.id_et_username)
    EditText idEtUsername;
    @Bind(R.id.id_et_password)
    EditText idEtPassword;
    @Bind(R.id.id_btn_login)
    Button idBtnLogin;
    @Bind(R.id.id_btn_clear)
    Button idBtnClear;
    @Bind(R.id.id_pb_loading)
    ProgressBar idPbLoading;
    @Bind(R.id.id_btn_register)
    Button idBtnRegister;
    @Bind(R.id.id_et_phone)
    EditText idEtPhone;

    private IUserLoginModel userLoginModel;
    private IUserLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        userLoginModel = new UserLoginModel();

        presenter = new IUserLoginPresenter(this);

    }


    @OnClick({R.id.id_btn_login, R.id.id_btn_clear, R.id.id_btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_btn_login:

                presenter.login();
                break;
            case R.id.id_btn_clear:
                presenter.clear();
                break;
            case R.id.id_btn_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
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
        return "12345678901";
    }

    @Override
    public void showLoding() {
        idPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        idPbLoading.setVisibility(View.GONE);

    }

    @Override
    public void showMainActivity(User user) {

//        if(user.getBody().getUser().getUsername().equals(idEtUsername.getText())
//                && user.getBody().getUser().getPassword().equals(idEtPassword.getText())) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("content", user);
        startActivity(intent);
//        }


    }

    @Override
    public void showFailed(String e) {

        Toast.makeText(LoginActivity.this, "登录失败" + e.toString(), Toast.LENGTH_SHORT).show();
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
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
