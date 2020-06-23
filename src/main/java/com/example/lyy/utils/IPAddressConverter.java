package com.example.lyy.utils;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class IPAddressConverter extends ClassicConverter {

    private static String ipAddress;

    static {
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("fetch localhost host address failed", e);
            ipAddress = "UNKNOWN";
        }
    }

    @Override
    public String convert(ILoggingEvent event) {
        return ipAddress;
    }
}