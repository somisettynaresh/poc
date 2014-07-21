package com.thoughtvalley.poc.constants;

/**
 * Created by Naresh on 7/21/2014.
 */
public interface IConfigConstants {


    public static final String CONFIG_LOCATION = "org.ec.app.config";
    public static final String MAPPING_URL = "/";

    public static final Integer LOAD_ON_STARTUP_VALUE=1;

    public static final String RESOURCE_HANDLER="/resources/**";
    public static final String RESOURCE_LOCATION="/resources/";
    public static final String CSS_HANDLER="/css/**";
    public static final String CSS_LOCATION="/css/";
    public static final String IMAGE_HANDLER="/img/**";
    public static final String IMAGE_LOCATION="/img/";
    public static final String SCRIPT_HANDLER="/scripts/**";
    public static final String SCRIPT_LOCATION="/script/";

    public static final Integer CACHE_PERIOD_DEFAULT=30*24*3600*1000;
    public static final Integer CACHE_PERIOD_6_MONTHS=6*30*24*3600*1000;
    public static final Integer CACHE_PERIOD_YEAR=365*24*3600*1000;
}
