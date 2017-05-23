package mvpdemon.mvpdemon.view;

import mvpdemon.mvpdemon.model.bean.User;

/**
 * Created by XUERU on 2017/4/1.
 */

public interface IUserLoginView {

    /**
     * 得到用户名
     *
     * @return
     */
    String getUserName();

    /**
     * 得到密码
     * @return
     */
    String getPassword();

    /**
     * 得到电话号码
     * @return
     */

    String getPhone();

    /**
     * 当做耗时操作的时候huid
     */
    void showLoding();

    /**
     * 耗时操作完成时回调
     */
    void hideLoading();

    /**
     * 登录成功回调
     * @param user
     */
    void showMainActivity(User user);

    /**
     * 登录失败回调
     * @param e
     */
    void showFailed(String e);

    /**
     * 清除用户名
     */
    void clearUserName();

    /**
     * 清除密码
     */
    void clearPassword();



}
