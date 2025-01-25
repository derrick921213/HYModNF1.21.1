package com.huanyu_mod.core.file_action;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.huanyu_mod.core.HYEng;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class json {
    public final static String CLASS_NAME = HYEng.getCurrentClassName();
    public static List<String> loadJsonListString(ResourceLocation resourceLocation, String objName, MinecraftServer server) {
        List<String> _return = List.of();
        try {
            var jsonElement = JsonParser.parseReader(
                    new InputStreamReader(server.getResourceManager().open(resourceLocation)));
            if (jsonElement.isJsonObject()) {
                _return = new Gson().fromJson(
                        jsonElement.getAsJsonObject().getAsJsonArray(objName),
                        new TypeToken<List<String>>(){}.getType()
                );
            }
        } catch (IOException e) {
            System.err.println(CLASS_NAME + " Failed to load Json: " + e.getMessage());
        }
        return _return;
    }
}
