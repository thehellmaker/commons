package com.github.commons.athena.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.amazonaws.regions.Regions;
import com.github.commons.athena.IResultSetParser;

/**
 * Created by aashok on 11/27/2017.
 */
public class AthenaRequest<O> {

    public Regions region;

    public String s3Path;

    public String sql;

    public IResultSetParser<O> resultSetParser;

    public AthenaRequest(Regions region, String s3Path, String sql) {
        this.region = region;
        this.s3Path = s3Path;
        this.sql = sql;
    }



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
