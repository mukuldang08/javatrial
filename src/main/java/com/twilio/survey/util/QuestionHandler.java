package com.twilio.survey.util;

import com.twilio.sdk.verbs.TwiMLResponse;

/**
 * Created by vshyba on 23/03/16.
 */
public interface QuestionHandler {
    public TwiMLResponse getTwilioResponse();
}
