package com.bitliker.simple.modular.network;

import com.bitliker.controller.bitnetwork.interceptor.CommonHttpBoyInterceptor;

import java.util.HashMap;
import java.util.Map;

public class FaceParameterInterceptor extends CommonHttpBoyInterceptor {
    @Override
    public String[] urlList() {
        return new String[]{
                "http://218.18.115.198:8888/ERP/"
        };
    }

    @Override
    public Map<String, String> getHeader() {
        Map<String, String> header = null;
        String sessionId = "E4D2F4961DFD437FDFD1890EC2BF6C59";
        String sessionUser = "U0736";
        header = new HashMap<>();
        header.put("Cookie", "JSESSIONID=" + sessionId);
        header.put("sessionUser", sessionUser);
        return header;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        String sessionId = "E4D2F4961DFD437FDFD1890EC2BF6C59";
        String sessionUser = "U0736";
        String master = "YITOA_DATACENTER";
        params.put("sessionId", sessionId);
        params.put("master", master);
        params.put("sessionUser", sessionUser);
        return params;
    }
}
