//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.database;

public interface ConnectionPool {
    void init() throws Exception;

    void refresh() throws Exception;

    void increase(Integer var1) throws Exception;

    void decrease(Integer var1) throws Exception;

    void shutdown() throws Exception;

    Object getConnection() throws Exception;

    void returnConnection(Object var1) throws Exception;
}
