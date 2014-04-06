package com.java.task11.application;

import org.apache.log4j.PropertyConfigurator;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;


public class Application {

    static {
        PropertyConfigurator.configure("src/log4j.properties");
    }

    public static void main(String[] args) {
        User user = new User();
        user.setFirstName("Christoforo");
        user.setLastName("Columb");
        user.setEmail("columb@gmail.com");
        user.setEncryptedPassword("columb");
        user.setRoleId(1);

        UserService employeeService = new UserService();
        try {
			employeeService.save(user);
		} catch (DAOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        System.out.println(user.toString());
        

        User empl = null;
		try {
			empl = employeeService.getByEmail("columb@gmail.com");
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.printf("Info: %s %s%n", empl.getFirstName(), empl.getLastName());
        try {
			employeeService.delete(empl);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("\nTHE END");
    }
}
