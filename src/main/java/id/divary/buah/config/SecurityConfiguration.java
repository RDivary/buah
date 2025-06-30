package id.divary.buah.config;

import id.divary.buah.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final UserService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Izinkan akses ke halaman home, login, dan resource statis (css, js) tanpa otentikasi
                        .requestMatchers("/auth/**", "/css/**").permitAll()
                        // Halaman /admin memerlukan role ADMIN
                        .requestMatchers("/buah/**").hasRole("ADMIN")
                        // Semua request lain harus diotentikasi
                        .requestMatchers("/").hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // Tentukan halaman login kustom
                        .loginPage("/auth/login")
                        // URL tujuan setelah berhasil login
                        .defaultSuccessUrl("/", true)
                        // Izinkan semua orang mengakses halaman login
                        .permitAll()
                )
                .logout(logout -> logout
                        // URL untuk logout
                        .logoutUrl("/auth/logout")
                        // 2. Hapus sesi setelah logout
                        .invalidateHttpSession(true)
                        // 3. Hapus cookie session
                        .deleteCookies("JSESSIONID")
                        // URL tujuan setelah berhasil logout
                        .logoutSuccessUrl("/auth/login?logout")
                        .permitAll()
                );

        return http.build();
    }

}
