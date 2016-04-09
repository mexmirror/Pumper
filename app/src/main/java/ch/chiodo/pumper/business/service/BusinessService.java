package ch.chiodo.pumper.business.service;

import java.util.List;

import ch.chiodo.pumper.business.Callback;

public interface BusinessService<T> {
    void insert(T t, Callback<T> callback);
    void get(long id, Callback<T> callback);
    void getList(Callback<List<T>> callback);
    void update(T t, Callback<T> callback);
    void delete(T t, Callback<T> callback);
}
