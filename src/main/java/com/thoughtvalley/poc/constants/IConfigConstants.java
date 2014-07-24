package com.thoughtvalley.poc.constants;

/**
 * Created by Naresh on 7/21/2014.
 */
public interface IConfigConstants {


    public static final String CONFIG_LOCATION = "org.ec.app.config";
    public static final String MAPPING_URL = "/";

    public static final Integer LOAD_ON_STARTUP_VALUE=1;

    public static final String RESOURCE_HANDLER="/ui/**";
    public static final String RESOURCE_LOCATION="/ui/**";
    public static final String CSS_HANDLER="/ui/app/styles/**";
    public static final String CSS_LOCATION="/ui/app/styles/";
    public static final String IMAGE_HANDLER="/ui/app/images/**";
    public static final String IMAGE_LOCATION="/ui/app/images/";
    public static final String SCRIPT_HANDLER="/ui/app/scripts/**";
    public static final String SCRIPT_LOCATION="/ui/app/scripts/";
    public static final String VIEW_HANDLER="/ui/app/views/**";
    public static final String VIEW_LOCATION="/ui/app/views/";

    public static final Integer CACHE_PERIOD_DEFAULT=30*24*3600*1000;
    public static final Integer CACHE_PERIOD_6_MONTHS=6*30*24*3600*1000;
    public static final Integer CACHE_PERIOD_YEAR=365*24*3600*1000;
}
