package mvpdemon.mvpdemon.model;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import mvpdemon.mvpdemon.model.bean.User;
import okhttp3.Call;

/**
 * Created by XUERU on 2017/4/1.
 */

public class UserLoginModel implements IUserLoginModel {

    @Override
    public void Login(String userName, String password,String phone, final OnLoginLister loginLister) {


        final String url = "http://47.93.118.241:8081/android/user/login?";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("username", userName)
                .addParams("password", password)
                .addParams("phone", phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //当失败的时候回调过去
                        loginLister.failed(e.getMessage());
                        Log.e("TAG", "联网请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        Log.e("TAG", "联网请求成功==" + response);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //当成功的时候回调过去
                                User user = new Gson().fromJson(response, User.class);//解析json数据
                                if(user.getStatus() == 200) {
                                    loginLister.success(user);
                                }else {
                                    loginLister.failed(user.getMessage());
                                }
                            }
                        }, 2000);

                    }
                });

    }

    @Override
    public void register(String userName, String password, String phone, final OnLoginLister loginLister) {
        final String url = "http://47.93.118.241:8081/android/user/reg?";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("username", userName)
                .addParams("password", password)
                .addParams("phone", phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //当失败的时候回调过去
                        loginLister.failed(e.getMessage());
                        Log.e("TAG", "联网请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        Log.e("TAG", "联网请求成功==" + response);
                        //当成功的时候回调过去
                        User user = new Gson().fromJson(response, User.class);//解析json数据
                        if(user.getStatus() == 200 ) {

                            loginLister.success(user);
                        } else {
                            loginLister.failed(user.getMessage());
                        }

                    }
                });
    }
}
