package com.banckend.blogappapi.services;

import org.springframework.stereotype.Service;

import com.banckend.blogappapi.payloads.UserAllResponse;

public interface AdminServices {

	UserAllResponse getUsrInfo(Integer userId);
}
