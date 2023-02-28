package com.devolpay.dao.inter;

import java.util.List;

public interface BaseRepository<T> {

    List<T> getAll();
    void insert(T entity);
    void update(T entity);
    void delete(String id);

}
