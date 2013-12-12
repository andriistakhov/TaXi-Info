package com.as_group.taxi_info.service;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 19.07.13
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public interface IServerInfo {

    int getId();

    void setId(int id);

    void setValue(String key, String value);

    String getValueByKey(String key);

}
