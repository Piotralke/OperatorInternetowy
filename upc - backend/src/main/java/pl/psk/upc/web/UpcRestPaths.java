package pl.psk.upc.web;

public class UpcRestPaths {

    public static final String UPC_PREFIX = "upc";
    public static final String UPC_SECURED_PREFIX = UPC_PREFIX + "/unsecured/v1";
    public static final String UPC_UNSECURED_PREFIX = UPC_PREFIX + "/unsecured/v1";
    public static final String GET_USER_DATA = UPC_SECURED_PREFIX + "/user";
    public static final String GET_USER_DATA_BY_UUID = UPC_SECURED_PREFIX + "/user/{uuid}";
    public static final String GET_ALL_USERS = UPC_SECURED_PREFIX + "/user/all";
    public static final String GET_EMPLOYEE_DATA = UPC_SECURED_PREFIX + "/employee";
    public static final String GET_EMPLOYEE_DATA_BY_UUID = UPC_SECURED_PREFIX + "/employee/{uuid}";
    public static final String GET_ALL_EMPLOYEES = UPC_SECURED_PREFIX + "/employee/all";
    public static final String LOGIN = UPC_UNSECURED_PREFIX + "/login";
    public static final String CLIENT_REGISTER = UPC_UNSECURED_PREFIX + "/client-register";
    public static final String EDIT_CLIENT = UPC_UNSECURED_PREFIX + "/edit-client";
    public static final String EMPLOYEE_REGISTER = UPC_UNSECURED_PREFIX + "/employee-register";
    public static final String EDIT_EMPLOYEE = UPC_UNSECURED_PREFIX + "/edit-employee";
    public static final String GET_OFFER_TYPES = UPC_UNSECURED_PREFIX + "/get-offer-types";
    public static final String GET_OFFERS_BY_TYPE = UPC_UNSECURED_PREFIX + "/get-offers-by-type";
    public static final String GET_ALL_OFFERS = UPC_UNSECURED_PREFIX + "/get-all-offers";
    public static final String EDIT_OFFER_STATUS = UPC_UNSECURED_PREFIX + "/edit-offer-status/{uuid}";
    public static final String GET_OFFER_BY_UUID = UPC_UNSECURED_PREFIX + "/get-offer-by-uuid/{uuid}";
    public static final String SAVE_OFFER = UPC_UNSECURED_PREFIX + "/save-offer";
    public static final String GET_ALL_PRODUCTS = UPC_UNSECURED_PREFIX + "/get-all-products";
    public static final String GET_PRODUCT_TYPES = UPC_UNSECURED_PREFIX + "/get-product-types";
    public static final String GET_PRODUCTS_BY_TYPE = UPC_UNSECURED_PREFIX + "/get-products-by-type";
    public static final String GET_PRODUCT = UPC_UNSECURED_PREFIX + "/get-product/{uuid}";
    public static final String EDIT_PRODUCT = UPC_UNSECURED_PREFIX + "/edit-product";
    public static final String SAVE_PRODUCT = UPC_UNSECURED_PREFIX + "/save-product";
    public static final String GET_ALL_FROM_WAREHOUSE = UPC_UNSECURED_PREFIX + "/get-all-from-warehouse";
    public static final String GET_FROM_WAREHOUSE_BY_UUID = UPC_UNSECURED_PREFIX + "/get-from-warehouse-by-uuid/{uuid}";
    public static final String SAVE_USER_USER_PROBLEM = UPC_UNSECURED_PREFIX + "/save-user-problem";
    public static final String GET_USER_PROBLEM = UPC_UNSECURED_PREFIX + "/get-user-problem/{uuid}";
    public static final String GET_USER_PROBLEMS = UPC_UNSECURED_PREFIX + "/get-user-problems";
    public static final String SET_USER_PROBLEM_STATUS = UPC_UNSECURED_PREFIX + "/set-user-problem-status";
    public static final String GET_ALL_PROBLEMS = UPC_UNSECURED_PREFIX + "/get-all-user-problems";
    public static final String GET_ALL_SERVICES = UPC_UNSECURED_PREFIX + "/get-all-services";
    public static final String GET_USER_SERVICES = UPC_UNSECURED_PREFIX + "/get-user-services/{email}";
    public static final String GET_CONTRACT_FORMS = UPC_UNSECURED_PREFIX + "/get-contract-forms";
    public static final String GET_SERVICE = UPC_UNSECURED_PREFIX + "/get-service/{uuid}";
    public static final String GET_ORDERS_BY_CLIENT_EMAIL = UPC_UNSECURED_PREFIX + "/get-orders/client";
    public static final String GET_ORDERS_BY_CLIENT_UUID = UPC_UNSECURED_PREFIX + "/get-orders/client/{uuid}";
    public static final String GET_ORDERS_BY_EMPLOYEE_EMAIL = UPC_UNSECURED_PREFIX + "/get-orders/employee";
    public static final String GET_ORDERS_BY_EMPLOYEE_UUID = UPC_UNSECURED_PREFIX + "/get-orders/employee/{uuid}";
    public static final String GET_ORDER_BY_UUID = UPC_UNSECURED_PREFIX + "/get-order/{uuid}";
    public static final String SAVE_ORDER = UPC_UNSECURED_PREFIX + "/save-order";
    public static final String EDIT_ORDER = UPC_UNSECURED_PREFIX + "/edit-order";
    public static final String CREATE_PAYMENT = UPC_UNSECURED_PREFIX + "/payment/create";
    public static final String PAYMENT_STATUSES = UPC_UNSECURED_PREFIX + "/payment-statuses";
    public static final String EXECUTE_PAYMENT = UPC_UNSECURED_PREFIX + "/payment/execute/{paymentId}";
    public static final String GET_CONTRACT_BY_UUID = UPC_UNSECURED_PREFIX + "/get-contract/{uuid}";
    public static final String GET_CONTRACT_LENGTHS = UPC_UNSECURED_PREFIX + "/get-contract-lengths";
    public static final String GET_CONTRACTS_BY_USER = UPC_UNSECURED_PREFIX + "/get-contract-by-user/{uuid}";
    public static final String GET_CONTRACTS_BY_SERVICE = UPC_UNSECURED_PREFIX + "/get-contract-by-service/{uuid}";

    public static final String GET_INVOICES_BY_CLIENT_UUID = UPC_UNSECURED_PREFIX + "/get-invoices/{uuid}";
    public static final String GET_INVOICE_AS_PDF = UPC_UNSECURED_PREFIX + "/get-invoice";
    public static final String SAVE_NOTICE = UPC_UNSECURED_PREFIX + "/save-notice";
    public static final String GET_ALL_NOTICES_BY_USER = UPC_UNSECURED_PREFIX + "/get-notices";
    public static final String GET_NOTICE_BY_UUID = UPC_UNSECURED_PREFIX + "/get-notice/{uuid}";
    public static final String EDIT_NOTICE_STATUS = UPC_UNSECURED_PREFIX + "/edit-notice";


}
