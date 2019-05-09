package com.framework.loippi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

public class HttpSend
{
  public static String getSend(String strUrl)
  {
    URL url = null;
    HttpURLConnection connection = null;
    try
    {
      url = new URL(strUrl);
      connection = (HttpURLConnection)url.openConnection();
      connection.setDoOutput(true);
      connection.setDoInput(true);
      connection.setRequestMethod("GET");
      connection.setUseCaches(false);
      connection.connect();

      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
      StringBuffer buffer = new StringBuffer();
      String line = "";
      while ((line = reader.readLine()) != null) {
        buffer.append(line);
      }

      reader.close();
      return buffer.toString();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (connection != null)
        connection.disconnect();
    }
  }

  public static String getMapSend(String strUrl, String param)
  {
    URL url = null;
    HttpURLConnection connection = null;
    try
    {
      url = new URL(strUrl + "?" + param);
      connection = (HttpURLConnection)url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Connection", "Keep-Alive");
      connection.setUseCaches(false);
      connection.setConnectTimeout(5000);
      connection.setReadTimeout(60000);

      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
      StringBuffer buffer = new StringBuffer();
      String line = "";
      while ((line = reader.readLine()) != null) {
        buffer.append(line);
      }

      reader.close();
      return buffer.toString();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (connection != null)
        connection.disconnect();
    }
  }

  public static String postSend(String serverApiURL, String paramJson)
  {
    String resultS = "";
    try {
      HttpClient client = new DefaultHttpClient();

      HttpPost post = new HttpPost(serverApiURL);

      StringEntity stringEntity = new StringEntity(paramJson, Charset.forName("utf-8"));
      stringEntity.setContentEncoding("UTF-8");
      stringEntity.setContentType("application/json");
      post.setEntity(stringEntity);
      HttpResponse res = client.execute(post);
      if (res.getStatusLine().getStatusCode() == HttpStatus.OK.value())
        return EntityUtils.toString(res.getEntity());
    }
    catch (Exception ex) {
      resultS = ex.getMessage();
    }
    return resultS;
  }

  public static String paraTo16(String str)
    throws UnsupportedEncodingException
  {
    String hs = "";

    byte[] byStr = str.getBytes("UTF-8");
    for (int i = 0; i < byStr.length; i++)
    {
      String temp = "";
      temp = Integer.toHexString(byStr[i] & 0xFF);
      if (temp.length() == 1) temp = "%0" + temp; else
        temp = "%" + temp;
      hs = hs + temp;
    }
    return hs.toUpperCase();
  }

  public static void main(String[] args) {
    String req = "{\"entranceGuardId\":\"35233\",\"entranceGuardName\":\"2号门\",\"accessCardId\":\"14429077\",\"accessUserName\":\"14429077\",\"accessTime\":\"2016-06-25 11:51:42\",\"accessState\":1}";
    String out = postSend("http://newsafe.fashioncomm.com//api/entranceGuard/card/report", req);
    System.out.println(out);
  }
}