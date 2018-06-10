package com.github.commons.athena;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import com.github.commons.athena.request.AthenaRequest;
import com.github.commons.athena.validate.AthenaRequestValidator;
import com.github.commons.log.ILogger;
import com.github.commons.utils.JsonUtils;
import com.google.inject.Inject;

/**
 * Created by aashok on 11/27/2017.
 */
public class AthenaServiceImpl<O> implements IAthenaService<O> {

    private static final String ATHENA_URL_FORMAT = "jdbc:awsathena://athena.%s.amazonaws.com:443";

    private static final String ATHENA_DRIVER_CLASSNAME = "com.simba.athena.jdbc.Driver";

    private ILogger logger;

    @Inject
    public AthenaServiceImpl(ILogger logger) throws ClassNotFoundException {
        this.logger = logger;
        Class.forName(ATHENA_DRIVER_CLASSNAME);
    }

    @Override
    public O query(AthenaRequest<O> request) throws Exception {
    	logger.log(JsonUtils.writeValueAsStringSilent(request));
        new AthenaRequestValidator<O>().validate(request);
        String athenaUrl = String.format(ATHENA_URL_FORMAT, request.region.getName());
        Properties info = getProperties(request);
        Connection conn = DriverManager.getConnection(athenaUrl, info);
        Statement statement = conn.createStatement();
        return (O) request.resultSetParser.apply(statement.executeQuery(request.sql));
    }

    private Properties getProperties(AthenaRequest<O> request) {
        Properties properties = new Properties();
        properties.put("s3_staging_dir", request.s3Path);
        properties.put("aws_credentials_provider_class", "com.amazonaws.auth.PropertiesFileCredentialsProvider");
        properties.put("aws_credentials_provider_arguments", "resources/config/credential");
        return properties;
    }
}
