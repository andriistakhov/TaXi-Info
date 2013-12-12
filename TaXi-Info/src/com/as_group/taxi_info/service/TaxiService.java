package com.as_group.taxi_info.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 19.07.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class TaxiService extends ServiceInfo {
    private List<ServiceInfo> serviceInfoMap;

    public List<ServiceInfo> getServiceInfoMap() {
        return serviceInfoMap;
    }

    public void addServiceInfoMap(ServiceInfo serviceInfo) {
        if (serviceInfoMap == null) {
            serviceInfoMap = new ArrayList<ServiceInfo>();
        }

        serviceInfoMap.add(serviceInfo);
    }
}
