package com.twilio.survey.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class AppSetup {
  private Map<String, String> env;

  public AppSetup() {
    this.env = System.getenv();
  }

  public Map<String, String> getParamsFromDBURL(String url) {
    Map<String, String> params = new HashMap<>();
    URI dbUri = null;

    try {
      dbUri = new URI(url);
    } catch (URISyntaxException e) {
      System.out.println("Unable to parse DB URL");
    }
    String[] userInfo = dbUri.getUserInfo().split(":");
    String username = dbUri.getUserInfo().split(":")[0];
    String password = null;
    if (userInfo.length > 1) {
      password = dbUri.getUserInfo().split(":")[1];
    } else {
      password = "";
    }

    String dBUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath() +
        "?user=" + username + "&password=" + password + "&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    System.out.println(dBUrl);
    params.put("url", dBUrl);

    return params;
  }

  public int getPortNumber() {
    String port = env.get("PORT");

    if (port != null) {
      return Integer.parseInt(port);
    } else {
      return 8080;
    }
  }

  public String getDatabaseURL() {
    return env.get("DATABASE_URL");
  }

  public String getAccountSid() {
    return env.get("TWILIO_ACCOUNT_SID");
  }

  public String getAuthToken() {
    return env.get("TWILIO_AUTH_TOKEN");
  }

  public String getTwilioPhoneNumber() {
    return env.get("TWILIO_PHONE_NUMBER");
  }
}
