package com.example.backend.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RunAfterStartup {
    private final Environment environment;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        String port = environment.getProperty("server.port", "8080");
        
        System.out.println("\n" + 
                "=================================================================\n" +
                "   Credit Management System Started Successfully!\n" +
                "=================================================================\n" +
                "   🔹 API Documentation: http://localhost:" + port + "/swagger-ui/index.html\n" +
                "   🔹 H2 Database Console: http://localhost:" + port + "/h2-console\n" +
                "   🔹 API Base URL: http://localhost:" + port + "/api\n" +
                "=================================================================\n" +
                "   Default Users:\n" +
                "   🔹 Admin: admin@bank.com / admin123\n" +
                "   🔹 Employee: employee@bank.com / employee123\n" +
                "   🔹 Client: client@example.com / client123\n" +
                "=================================================================\n");
    }
}
