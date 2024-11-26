package by.it_academy.jd2.finance.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.pagination")
public class PageProperty {

    private int minPageSize = 5;
    private int maxPageSize = 50;

    public int getMinPageSize() {
        return minPageSize;
    }

    public void setMinPageSize(int minPageSize) {
        this.minPageSize = minPageSize;
    }

    public int getMaxPageSize() {
        return maxPageSize;
    }

    public void setMaxPageSize(int maxPageSize) {
        this.maxPageSize = maxPageSize;
    }
}
