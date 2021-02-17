package co.com.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Validated
@ConfigurationProperties(prefix = "bancolombia.kinesis")
public class ConnectionKinesisAwsProperty {

    @Pattern(regexp = "^(https?|ftp)://.*$", message = "must be assigned with HTTP/s")
    @NotEmpty(message = "kinesis url must not be null or empty")
    private String kinesisConnectionUrl;

    @Override
    public String toString() {
        return "ConnectionKinesisAwsProperty{" + "kinesisConnectionUrl='" + kinesisConnectionUrl + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ConnectionKinesisAwsProperty that = (ConnectionKinesisAwsProperty) o;
        return Objects.equals(kinesisConnectionUrl, that.kinesisConnectionUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kinesisConnectionUrl);
    }

    public String getKinesisConnectionUrl() {
        return kinesisConnectionUrl;
    }

    public ConnectionKinesisAwsProperty setKinesisConnectionUrl(String kinesisConnectionUrl) {
        this.kinesisConnectionUrl = kinesisConnectionUrl;
        return this;
    }
}
