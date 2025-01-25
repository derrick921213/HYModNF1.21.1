package com.huanyu_mod.client.screen;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public interface HYScreen {
    default void hideEBInitStr(EditBox editBox, String string) {
        if (!editBox.getValue().isEmpty()) {
            editBox.setSuggestion(null);
        } else {
            editBox.setSuggestion(Component.translatable(string).getString());
        }
    }
}
