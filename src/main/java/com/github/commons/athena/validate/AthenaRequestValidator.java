package com.github.commons.athena.validate;

import org.apache.commons.lang3.StringUtils;

import com.atom8.functional.interfaces.IValidator;
import com.github.commons.athena.request.AthenaRequest;
import com.github.commons.exception.Atom8ValidationException;

/**
 * Created by aashok on 11/28/2017.
 */
public class AthenaRequestValidator<O> implements IValidator<AthenaRequest<O>> {

    @Override
    public void validate(AthenaRequest<O> request) {
        if (request == null) {
            throw new Atom8ValidationException("Request Cannot be null");
        }

        if (request.region == null) {
            throw new Atom8ValidationException("region Cannot be blank "+request.toString());
        }

        if (StringUtils.isBlank(request.s3Path)) {
            throw new Atom8ValidationException("s3Path Cannot be blank "+request.toString());
        }

        if (StringUtils.isBlank(request.sql)) {
            throw new Atom8ValidationException("sql Cannot be blank "+request.toString());
        }
    }
}
