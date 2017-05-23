package mvpdemon.mvpdemon.model;

/**
 * Created by XUERU on 2017/4/1.
 */

public interface IUserLoginModel {

     void Login(String userName,String password,String phone,OnLoginLister loginLister);
     void register(String userName,String password,String phone,OnLoginLister loginLister);
}
