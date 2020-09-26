package com.imooc.service;

import com.imooc.pojo.UserAddress;

import java.util.List;

public interface AddressService {

    public List<UserAddress> queryAll(String userId);

}
