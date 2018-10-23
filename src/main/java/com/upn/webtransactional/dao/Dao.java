package com.upn.webtransactional.dao;

import java.util.List;

public interface Dao<T,K> {

    void insertar(T t);
    T obtenerPorId(K k);
    List<T> obtenerTodos();
    void eliminar(K k);

}
