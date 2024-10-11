package com.example.mom_check.common.config;

import com.example.mom_check.auth.service.AuthService;
import com.example.mom_check.common.annotation.Login;
import com.example.mom_check.common.exception.BusinessException;
import com.example.mom_check.common.exception.ErrorCode;
import com.example.mom_check.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.example.mom_check.common.exception.ErrorCode.UNAUTHORIZED_MEMBER;

@Slf4j
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthService authService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasMemberType = User.class.isAssignableFrom(parameter.getParameterType());

        return hasLoginAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authorization = webRequest.getHeader("authorization");

        if (authorization == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_MEMBER);
        }

        String token = authorization.substring(7);

        return authService.findMemberByToken(token);
    }
}