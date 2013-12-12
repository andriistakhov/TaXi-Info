package com.as_group.taxi_info.utils;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 22.07.13
 * Time: 06:58
 * To change this template use File | Settings | File Templates.
 */
public class JsonContants {

    interface BaseKeys {
        String ID = "id";
        String NAME = "name";
    }

    interface CityKeys {
        String VERSION = "version";
    }

    interface ServiceKeys {
        String RATE = "rate";
        String CITY_ID = "city_id";
        String INFOS = "infos";
    }

    interface InfosKeys {
        String ID = "id";
        String NUMBER_VALUE = "number_value";
        String SERVICE_ID = "service_id";
        String TYPE = "type";
    }

    public static class JsonCity implements BaseKeys, CityKeys {

    }

    public static class JsonService implements BaseKeys, ServiceKeys {
        public static JsonInfos jsonInfos;

    }

    public static class JsonInfos implements InfosKeys {

    }
}
