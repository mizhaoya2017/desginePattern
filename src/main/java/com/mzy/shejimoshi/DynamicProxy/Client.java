package com.mzy.shejimoshi.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String [] args) {
        InvocationHandler handler = null;
        AbstractUserDAO userDAO = new UserDAO();
        handler = new DAOLogHandle(userDAO);
        AbstractUserDAO proxy = null;

        proxy = (AbstractUserDAO) Proxy.newProxyInstance(AbstractUserDAO.class.getClassLoader(), new Class[]{AbstractUserDAO.class}, handler);
        proxy.findUserById("张无忌");

        System.out.println("-------------------------------------------------");

        AbstractDocumentDAO documentDAO = new DocumentDAO();
        handler = new DAOLogHandle(documentDAO);
        AbstractDocumentDAO proxy_new = null;

        proxy_new = (AbstractDocumentDAO)Proxy.newProxyInstance(AbstractDocumentDAO.class.getClassLoader(), new Class[]{AbstractDocumentDAO.class}, handler);
        proxy_new.deleteDocumentById("D002");
    }
}
