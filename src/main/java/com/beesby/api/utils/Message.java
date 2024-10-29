package com.beesby.api.utils;

public class Message {

    public static final String INTERNAL_SERVER_ERROR = "An unexpected error occurred.";
    public static final String INVALID_REQUEST = "The request is invalid.";
    public static final String ACCESS_DENIED = "Access denied. You are not authorized to access this resource.";
    public static final String JWT_EXPIRED = "The JWT token has expired.";
    public static final String JWT_SIGNATURE_INVALID = "The JWT signature is invalid.";

    public static final String BAD_CREDENTIALS = "The username or password is incorrect.";
    public static final String ACCOUNT_LOCKED = "The account is locked. Please contact support.";

    public static final String RESOURCE_NOT_FOUND = "Requested resource not found.";
    public static final String RESOURCE_CONFLICT = "Resource already exists";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access. Please authenticate.";
    public static final String FORBIDDEN_OPERATION = "Operation not permitted.";
    public static final String BAD_REQUEST = "The request could not be processed due to bad request format.";

    public static final String NO_ALLOWED_FILTERS_DEFINED = "No filters are configured for this request. Please define 'allowedFilters'.";
    public static final String INVALID_FILTER = "Filter key '%s' with operation '%s' is not permitted.";
    public static final String EMPTY_FILTER_VALUE = "The filter value for key '%s' cannot be empty.";
}
