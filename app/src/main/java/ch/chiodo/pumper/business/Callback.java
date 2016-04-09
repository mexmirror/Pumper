package ch.chiodo.pumper.business;

public interface Callback<T> {
    void onSuccess(T object);
    void onError(Throwable exception);
}
