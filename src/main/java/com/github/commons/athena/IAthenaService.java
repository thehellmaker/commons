package com.github.commons.athena;

import com.github.commons.athena.request.AthenaRequest;

/**
 * Created by aashok on 11/27/2017.
 */
public interface IAthenaService<O> {

	O query(AthenaRequest<O> request) throws Exception;

}
