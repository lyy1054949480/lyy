package com.example.lyy.oauth2.access;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import java.util.*;

public class CustomSecurityContext {
    private static final Map<String, Collection<ConfigAttribute>> METADATA_SOURCE_MAP = new HashMap<>();

    public static Map<String, Collection<ConfigAttribute>> getMetadataSource() throws Exception {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath*:/security/*.properties");
        if (ArrayUtils.isEmpty(resources)) {
            return null;
        }

        Properties properties = new Properties();
        for (Resource resource : resources) {
            properties.load(resource.getInputStream());
        }

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {

            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            String[] values = StringUtils.split(value, ",");

            Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
            ConfigAttribute configAttribute = new SecurityConfig(key);
            configAttributes.add(configAttribute);

            for (String v : values) {
                METADATA_SOURCE_MAP.put(StringUtils.trim(v), configAttributes);
            }
        }
        return METADATA_SOURCE_MAP;
    }
}
