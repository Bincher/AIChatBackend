package com.example.AIChat.common;

public interface ResponseCode {

    // HTTP Status 200
    //public static final String SUCCESS = "SU";
    String SUCCESS = "SU";
    String SUCCESS_ACCEPTED = "SA";

    // HTTP Status 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_ID = "DI";
    String NOT_EXISTED_USER = "NU";
    String DISAGREED_PERSONAL = "DP";
    String NOT_EXISTED_CHAT_ROOM = "NC";
    String EXISTED_FRIEND = "EF";
    String DUPLICATED_POSTING = "DU";

    // HTTP Status 401
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL = "AF";
    String MAIL_FAIL = "MF";
    String CERTIFICATION_FAIL = "CF";
    

    // HTTP Status 403
    String NO_PERMISSION = "NP";

    // HTTP Status 500
    String DATABASE_ERROR = "DBE";

}