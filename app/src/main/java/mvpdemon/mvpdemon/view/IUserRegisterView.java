package mvpdemon.mvpdemon.view;

/**
 * Created by XUERU on 2017/4/1.
 */

public interface IUserRegisterView {


    String getUserName();

    String getPassword();

    String getPhone();

    void toLoginActivity();

    void showFailed(String e);

    void clearUserName();

    void clearPassword();

    void clearPhone();

}
