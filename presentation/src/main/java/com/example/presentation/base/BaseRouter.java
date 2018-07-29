package com.example.presentation.base;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.android.hometasks2.R;
import com.example.domain.entity.Error;
import com.example.presentation.screens.user.view.UserInfoActivity;
import com.example.presentation.utils.Extras;

public class BaseRouter<A extends BaseActivity> {

    protected A activity;

    public BaseRouter(A activity) {
        this.activity = activity;
    }

    public void showError(Throwable throwable) {
        if(throwable instanceof Error) {
            Error error = (Error)throwable;
            switch (error.getType()) {
                case INTERNET_IS_NOT_AVAILABLE:
                    showToastError(R.string.error_internet_not_available);
                    break;
                case SERVER_IS_NOT_AVAILABLE:
                    showToastError(R.string.error_server_not_available);
                    break;
                case SERVER_ERROR:
                    showToastError(R.string.error_server);
                    break;
                case REQUEST_ERROR:
                    showToastError(R.string.input_error);
                    break;
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
        Toast.makeText(activity, messageErrorId, Toast.LENGTH_SHORT)
                .show();
    }

    public void finishActivity() {
        activity.finish();
    }

    public void showToast(int messageId){
        Toast.makeText(activity, messageId, Toast.LENGTH_SHORT).show();
    }

    public void sendChanges(int resultCode, String objectId){
        Intent data = new Intent();
        data.putExtra(Extras.EXTRA_OBJECT_ID, objectId);
        activity.setResult(resultCode, data);
        activity.finish();
    }

    public void sendCancelled(){
        activity.setResult(Activity.RESULT_CANCELED);
        activity.finish();
    }
}