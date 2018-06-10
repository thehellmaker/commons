package com.github.commons.athena;

import java.sql.ResultSet;
import java.util.function.Function;

/**
 * Created by aashok on 11/28/2017.
 */
public interface IResultSetParser<O> extends Function<ResultSet, O> {
}
