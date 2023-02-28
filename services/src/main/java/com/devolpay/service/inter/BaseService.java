package com.devolpay.service.inter;

import java.util.List;

public interface BaseService<T> {
    List<T> getAll();
    T save(T entity);
    T update(T entity);
    boolean delete(String id);
}
