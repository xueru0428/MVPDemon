package mvpdemon.mvpdemon.model;

import mvpdemon.mvpdemon.model.bean.User;

/**
 * Created by XUERU on 2017/4/1.
 */

public interface OnLoginLister {

    void success(User user);

    void failed(String e);
}
