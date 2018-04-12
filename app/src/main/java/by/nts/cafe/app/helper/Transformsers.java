package by.nts.cafe.app.helper;

import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Transformsers {
    private static ObservableTransformer<?, ?> transformer = observable -> observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private static MaybeTransformer<?, ?> maybeTransformer = observable -> observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> schedulersIO() {
        return (ObservableTransformer<T, T>) transformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> MaybeTransformer<T, T> schedulersIOMaybe() {
        return (MaybeTransformer<T, T>) maybeTransformer;
    }
}