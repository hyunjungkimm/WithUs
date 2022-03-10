package com.example.fundingapi.interceptor;

import com.example.fundingapi.domain.User;
import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.exception.service.user.UserServiceException;
import com.example.fundingapi.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class HttpInterceptor implements HandlerInterceptor { //extends HandlerInterceptorAdapter

    private final UserRepository userRepository;

    public HttpInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //HTTP 요청 처리 전 수행할 로직 작성
        String userId = request.getHeader("X-USER-ID");
        System.out.println("userId=" + userId);
        if(userId != null) {

            Optional<User> user = userRepository.findById(Long.parseLong(userId));
            if (user.isPresent()) {
                request.setAttribute("userId", user.get().getUserId());
            } else if (!user.isPresent()) {
                //TODO else throw Exception 처리 필요.
                throw new UserServiceException(ErrorCode.NOT_SIGNED_UP_USER);
            }
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }else{
            throw new UserServiceException(ErrorCode.NOT_EXISTS_USED_ID_HEADER);
        }

    }

}
