package today.dozzari.dozzari.global.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import today.dozzari.dozzari.global.constant.SecurityConstant

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }

        http.cors {
            it.configurationSource(corsConfiguration())
        }

        http.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.NEVER)
        }

        http.authorizeHttpRequests { it ->
            SecurityConstant.NO_AUTHENTICATION_API.forEach { api ->
                it.requestMatchers(api).permitAll()
            }

            SecurityConstant.SWAGGER_URIS.forEach { uri ->
                it.requestMatchers(uri).permitAll()
            }

            SecurityConstant.H2_URIS.forEach { uri ->
                it.requestMatchers(uri).permitAll()
            }

            it.anyRequest().authenticated()
        }

        return http.build()
    }

    @Bean
    fun corsConfiguration(): CorsConfigurationSource {
        val cors = CorsConfiguration().apply {
            allowedOriginPatterns = SecurityConstant.ALLOWED_CORS_URLS
            allowedMethods = SecurityConstant.ALLOWED_METHODS
            exposedHeaders = SecurityConstant.ALLOWED_HEADERS
            allowCredentials = true
        }

        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", cors)
        }
    }
}