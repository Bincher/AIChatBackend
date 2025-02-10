package com.example.AIChat.common;

public interface ResponseMessage {
    // HTTP Status 200
    String SUCCESS = "Success";
    String SUCCESS_ACCEPTED = "Success Accepted";

    // HTTP Status 400
    String VALIDATION_FAILED = "Validation failed";
    String DUPLICATE_EMAIL = "Duplicate email";
    String DUPLICATE_NICKNAME = "Duplicate nickname";
    String DUPLICATE_TEL_NUMBER = "Duplicate tel number";
    String DUPLICATE_ID = "Duplicate id";
    String NOT_EXISTED_USER = "This user does not exist";
    String NOT_EXISTED_CHAT_ROOM = "This chat room does not exist";
    String DISAGREED_PERSONAL = "Disagreed personal";
    String EXISTED_FRIEND = "Existed Friend";
    String DUPLICATED_POSTING = "DUplicated Posting";

    // HTTP Status 401
    String SIGN_IN_FAIL = "Login information mismatch";
    String AUTHORIZATION_FAIL = "Authorization fail";
    String MAIL_FAIL = "Mail fail";
    String CERTIFICATION_FAIL = "Certification fail";

    // HTTP Status 403
    String NO_PERMISSION = "Do not have permission";

    // HTTP Status 500
    String DATABASE_ERROR = "Database error";
}