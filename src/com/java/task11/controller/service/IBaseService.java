package com.java.task11.controller.service;

import java.util.List;

import com.java.task11.controller.dao.factory.DAOException;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public interface IBaseService<T> {
    T getByID(Integer id) throws DAOException;
    void save(T element) throws DAOException;
    void update(T element) throws DAOException;
    void delete(T element) throws DAOException;
    List<T> getListOfObjects() throws DAOException;
}
