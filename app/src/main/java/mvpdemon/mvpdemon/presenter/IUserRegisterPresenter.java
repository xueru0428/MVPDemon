package mvpdemon.mvpdemon.presenter;

import mvpdemon.mvpdemon.model.IUserLoginModel;
import mvpdemon.mvpdemon.model.OnLoginLister;
import mvpdemon.mvpdemon.model.UserLoginModel;
import mvpdemon.mvpdemon.model.bean.User;
import mvpdemon.mvpdemon.view.IUserRegisterView;

/**
 * Created by XUERU on 2017/4/1.
 */

public class IUserRegisterPresenter {

    private IUserRegisterView iUserRegisterView;

    private IUserLoginModel iUserLoginModel;
    public IUserRegisterPresenter(IUserRegisterView iUserRegisterView) {
        this.iUserRegisterView = iUserRegisterView;

        this.iUserLoginModel = new UserLoginModel();
    }


    public void register(){

        iUserLoginModel.register(iUserRegisterView.getUserName(), iUserRegisterView.getPassword(), iUserRegisterView.getPhone(), new OnLoginLister() {
            @Override
            public void success(User user) {

                iUserRegisterView.toLoginActivity();

            }

            @Override
            public void failed(String e) {

                iUserRegisterView.showFailed(e);
            }
        });

    }

    public void clear() {
        iUserRegisterView.clearUserName();
        iUserRegisterView.clearPassword();
        iUserRegisterView.clearPhone();

    }
}
