package mvpdemon.mvpdemon.presenter;

import mvpdemon.mvpdemon.model.IUserLoginModel;
import mvpdemon.mvpdemon.model.OnLoginLister;
import mvpdemon.mvpdemon.model.UserLoginModel;
import mvpdemon.mvpdemon.model.bean.User;
import mvpdemon.mvpdemon.view.IUserLoginView;

/**
 * Created by XUERU on 2017/4/1.
 */

public class IUserLoginPresenter {

    private IUserLoginModel iUserLoginModel;

    private IUserLoginView iUserLoginView;



    public IUserLoginPresenter(IUserLoginView iUserLoginView) {

        this.iUserLoginView = iUserLoginView;

        this.iUserLoginModel = new UserLoginModel();


    }

    public void login() {

        //显示加载效果
        iUserLoginView.showLoding();

        iUserLoginModel.Login(iUserLoginView.getUserName(), iUserLoginView.getPassword(),iUserLoginView.getPhone(), new OnLoginLister() {
            @Override
            public void success(User user) {
                //登录成功后，隐藏加载效果
                iUserLoginView.hideLoading();
                //跳转到主页面
                iUserLoginView.showMainActivity(user);

            }

            @Override
            public void failed(String e) {
                iUserLoginView.showFailed(e.toString());
                iUserLoginView.hideLoading();
            }
        });


    }

    public void clear() {
        iUserLoginView.clearPassword();
        iUserLoginView.clearUserName();
    }


}
