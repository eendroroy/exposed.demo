package io.github.eendroroy.exposed.demo.security.config

import io.github.eendroroy.exposed.demo.security.filter.RequestAuthenticationFilter
import io.github.eendroroy.exposed.demo.security.handler.AuthenticationEntryPoint
import io.github.eendroroy.exposed.demo.security.handler.AccessDeniedHandler
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(private val requestAuthenticationFilter: RequestAuthenticationFilter) {
    @Bean
    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf {
            it.disable()
        }.cors {
            it.disable()
        }.authorizeHttpRequests {
            it.requestMatchers(
                "/api/v1/user/register",
                "/api/v1/token/create"
            ).permitAll().anyRequest().authenticated()
        }.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }.exceptionHandling {
            it.authenticationEntryPoint(AuthenticationEntryPoint()).accessDeniedHandler(AccessDeniedHandler())
        }.addFilterBefore(requestAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun jwtRequestFilterRegistration(): FilterRegistrationBean<RequestAuthenticationFilter> {
        val registrationBean: FilterRegistrationBean<RequestAuthenticationFilter> =
            FilterRegistrationBean<RequestAuthenticationFilter>()
        registrationBean.filter = requestAuthenticationFilter
        registrationBean.addUrlPatterns("/api/**")
        return registrationBean
    }
}
