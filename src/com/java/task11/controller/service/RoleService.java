package com.java.task11.controller.service;

import java.util.List;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.UserRole;


public class RoleService implements IBaseService<UserRole> {

    @Override
    public UserRole getByID(Integer id) throws DAOException {
        return DAOFactory.getInstance().getRoleDAO().getByPrimaryKey(id);
    }

    @Override
    public void save(UserRole element) throws DAOException {
        DAOFactory.getInstance().getRoleDAO().insert(element);
    }

    @Override
    public void update(UserRole element) throws DAOException {
        DAOFactory.getInstance().getRoleDAO().update(element);
    }

    @Override
    public void delete(UserRole element) throws DAOException {
        DAOFactory.getInstance().getRoleDAO().delete(element);
    }

    @Override
    public List<UserRole> getListOfObjects() throws DAOException {
        return DAOFactory.getInstance().getRoleDAO().selectAll();
    }
}
