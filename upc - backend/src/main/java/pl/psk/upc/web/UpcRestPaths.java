package pl.psk.upc.web;

public class UpcRestPaths {

    public static final String UPC_PREFIX = "upc";
    public static final String UPC_SECURED_PREFIX = UPC_PREFIX + "/v1";
    public static final String UPC_UNSECURED_PREFIX = UPC_PREFIX + "/unsecured/v1";
    public static final String GET_USER_DATA = UPC_SECURED_PREFIX + "/user";
    public static final String GET_ALL_USERS = UPC_SECURED_PREFIX + "/user/all";
    public static final String GET_EMPLOYEE_DATA = UPC_SECURED_PREFIX + "/employee";
    public static final String GET_ALL_EMPLOYEES = UPC_SECURED_PREFIX + "/employee/all";
    public static final String LOGIN = UPC_UNSECURED_PREFIX + "/login";
    public static final String CLIENT_REGISTER = UPC_UNSECURED_PREFIX + "/client-register";
    public static final String EMPLOYEE_REGISTER = UPC_UNSECURED_PREFIX + "/employee-register";
    public static final String GET_OFFER_TYPES = UPC_UNSECURED_PREFIX + "/get-offer-types";
    public static final String GET_OFFERS_BY_TYPE = UPC_UNSECURED_PREFIX + "/get-offers-by-type";
    public static final String GET_ALL_OFFERS = UPC_UNSECURED_PREFIX + "/get-all-offers";
    public static final String GET_OFFER_BY_UUID = UPC_UNSECURED_PREFIX + "/get-offer-by-uuid/{uuid}";
    public static final String GET_ALL_PRODUCTS = UPC_UNSECURED_PREFIX + "/get-all-products";
    public static final String GET_PRODUCT_TYPES = UPC_UNSECURED_PREFIX + "/get-product-types";
    public static final String GET_PRODUCTS_BY_TYPE = UPC_UNSECURED_PREFIX + "/get-products-by-type";
    public static final String GET_PRODUCT = UPC_UNSECURED_PREFIX + "/get-product/{uuid}";
    public static final String GET_ALL_FROM_WAREHOUSE = UPC_UNSECURED_PREFIX + "/get-all-from-warehouse";
    public static final String GET_FROM_WAREHOUSE_BY_UUID = UPC_UNSECURED_PREFIX + "/get-from-warehouse-by-uuid/{uuid}";
    public static final String SAVE_USER_USER_PROBLEM = UPC_UNSECURED_PREFIX + "/save-user-problem";
    public static final String GET_USER_PROBLEM = UPC_UNSECURED_PREFIX + "/get-user-problem/{uuid}";
    public static final String GET_USER_PROBLEMS = UPC_UNSECURED_PREFIX + "/get-user-problems";
    public static final String SET_USER_PROBLEM_STATUS = UPC_UNSECURED_PREFIX + "/set-user-problem-status";
    public static final String GET_ALL_SERVICES = UPC_UNSECURED_PREFIX + "/get-all-services";
    public static final String GET_USER_SERVICES = UPC_UNSECURED_PREFIX + "/get-user-services/{email}";
    public static final String GET_SERVICE = UPC_UNSECURED_PREFIX + "/get-service/{uuid}";



}
