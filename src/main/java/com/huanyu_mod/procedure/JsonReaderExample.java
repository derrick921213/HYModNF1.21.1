package com.huanyu_mod.procedure;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.io.Reader;

public class JsonReaderExample {
    public static void main(String[] args) {
        // JSON 文件路徑
        String jsonPath = "/assets/<modid>/data/config/example.json";

        try (Reader reader = new InputStreamReader(JsonReaderExample.class.getResourceAsStream(jsonPath))) {
            // 使用 Gson 解析 JSON
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            // 獲取 JSON 資料
            String name = jsonObject.get("name").getAsString();
            int value = jsonObject.get("value").getAsInt();

            System.out.println("Name: " + name);
            System.out.println("Value: " + value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
