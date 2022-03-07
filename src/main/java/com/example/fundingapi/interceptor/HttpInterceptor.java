package com.example.fundingapi.interceptor;

import com.example.fundingapi.domain.User;
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
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        //HTTP 요청 처리 전 수행할 로직 작성
        String userId = request.getHeader("X-USER-ID");
        Optional<User> user= userRepository.findById(Long.parseLong(userId));
        if(user.isPresent()){
            request.setAttribute("userId", user.get().getUserId());
            return true;
        }else {
            return false;
        }

    }

}
