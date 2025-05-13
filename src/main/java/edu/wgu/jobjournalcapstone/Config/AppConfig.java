package edu.wgu.jobjournalcapstone.Config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix ="app")
public class AppConfig {
// This config class allows the dynamic setting of URLs from environment variables
    @NotNull
    String url;

    public @NotNull String getUrl() {
        return url;
    }

    public void setUrl(@NotNull String url) {
        this.url = url;
    }
}
