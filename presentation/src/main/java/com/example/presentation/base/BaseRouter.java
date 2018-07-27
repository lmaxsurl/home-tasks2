package com.example.presentation.base;

import android.util.Log;
import android.widget.Toast;

import com.example.android.hometasks2.R;
import com.example.domain.entity.Error;

public class BaseRouter<A extends BaseActivity> {

    protected A activity;

    public BaseRouter(A activity) {
        this.activity = activity;
    }

    public void showError(Throwable throwable) {

        if(throwable instanceof Error) {

            Error error = (Error)throwable;
            switch (error.getType()) {
                case INTERNET_IS_NOT_AVAILABLE:{
                    showToastError(R.string.error_internet_not_available);
                    break;
                }
                case SERVER_IS_NOT_AVAILABLE: {
                    showToastError(R.string.error_server_not_available);
                    break;
                }
                case SERVER_ERROR: {
                    showToastError(R.string.error_server);
                    break;
                }
                default: {
                    //снова непредвиденная ошибка
                    //делаем соответствующий отчет
                    Log.e(activity.getClass().getSimpleName(),
                            "забыл указать тип ошибки " + throwable.toString());
                    showToastError(R.string.error);
                }
            }
        } else {
            //отправляет отчет программистам
            Log.e(activity.getClass().getSimpleName(),
                    "ужасная проблема " + throwable.toString());
            showToastError(R.string.error);
        }
    }

    private void showToastError(int messageErrorId) {
        //показываете пользователю нейтральное сообщение об ошибке
        Toast.makeText(activity, messageErrorId, Toast.LENGTH_SHORT)
                .show();
    }

    public void finishActivity() {
        activity.finish();
    }

    public void showToast(String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int messageId){
        Toast.makeText(activity, messageId, Toast.LENGTH_SHORT).show();
    }
}