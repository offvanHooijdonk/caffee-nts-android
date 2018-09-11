package by.nts.cafe.app.helper.rx;

import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Transformers {
    private static ObservableTransformer<?, ?> transformer = observable -> observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private static MaybeTransformer<?, ?> maybeTransformer = maybe -> maybe
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private static FlowableTransformer<?, ?> flowableTransformer = flowable -> flowable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private static SingleTransformer<?, ?> singleTransformer = single -> single
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

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> schedulersIOFlowable() {
        return (FlowableTransformer<T, T>) flowableTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> SingleTransformer<T, T> schedulersIOSingle() {
        return (SingleTransformer<T, T>) singleTransformer;
    }
}