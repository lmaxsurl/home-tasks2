package com.example.data.network;

import com.example.domain.entity.Error;
import com.example.domain.entity.ErrorType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

public class ErrorParserTransformer {

    private Gson gson;

    public ErrorParserTransformer(Gson gson) {
        this.gson = gson;
    }

    public <T, E extends Throwable> ObservableTransformer<T, T> parseHttpError() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {

                return upstream
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<T>>() {
                            @Override
                            public ObservableSource<T> apply(Throwable throwable) throws Exception {

                                Error error;
                                if (throwable instanceof HttpException) {
                                    HttpException httpException = (HttpException) throwable;
                                    String errorBody = httpException.response().errorBody().string();
                                    E httpError = gson.fromJson(errorBody,
                                            new TypeToken<E>(){}.getType());

                                    //тут проверить параметры в HttpError и решить что делать дальше

                                    error = new Error(httpError.getMessage(), ErrorType.SERVER_ERROR);

                                } else if (throwable instanceof SocketTimeoutException) {
                                    error = new Error("Internet is not available",
                                            ErrorType.INTERNET_IS_NOT_AVAILABLE);
                                } else {
                                    error = new Error("Unexpected error",
                                            ErrorType.UNEXPECTED_ERROR);
                                }

                                return Observable.error(error);
                            }
                        });
            }
        };
    }
}
