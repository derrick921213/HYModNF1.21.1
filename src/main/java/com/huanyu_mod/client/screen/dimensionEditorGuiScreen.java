package com.huanyu_mod.client.screen;

import com.huanyu_mod.blockentity.dimension_editor_be;
import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.payload.dimensionEditorPayload;
import com.huanyu_mod.world.menu.dimensionEditorGuiMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class dimensionEditorGuiScreen extends AbstractContainerScreen<dimensionEditorGuiMenu> implements HYScreen{
	public final static String CLASS_NAME = HYEng.getCurrentClassName();
	private final static HashMap<String, Object> GUI_STATE = dimensionEditorGuiMenu.GUI_STATE;
	public final Level level;
	public final Player player;
	public final BlockPos blockPos;
	public final dimension_editor_be blockEntity;

	private final static ResourceLocation texture = HYEng.makeRL("textures/screens/dimension_editor_gui_bg.png");
	Button button_time_00;
	Button button_time_06;
	Button button_time_12;
	Button button_time_18;
	EditBox edit_box_01;

	public dimensionEditorGuiScreen(dimensionEditorGuiMenu container, Inventory inventory, Component component) {
		super(container, inventory, component);
		this.level = container.level;
		this.player = container.player;
		this.blockPos = container.blockPos;
		this.blockEntity = container.blockEntity;
        this.imageWidth = 384;
		this.imageHeight = 216;
	}

	@Override
	public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		edit_box_01.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}
	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0,
				this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}
	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font,
				Component.translatable("gui.huanyu_mod.dimension_editor_gui.dimension_editor_title"),
				10, 10, 0x000000, false);
	}

	@Override
	public void resize(@NotNull Minecraft minecraft, int width, int height) {
		String edit_box_01Value = edit_box_01.getValue();
		super.resize(minecraft, width, height);
		edit_box_01.setValue(edit_box_01Value);
	}
	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			if (edit_box_01.isFocused()) {
				edit_box_01.setFocused(false);
				return true;
			}
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void init() {
		super.init();
		int[] time_buttonPosSize = {this.leftPos + 10, this.topPos + 20, 20, 20};
		button_time_00 = Button.builder(
				Component.translatable("gui.huanyu_mod.dimension_editor_gui.button_time_00"), e -> {
					PacketDistributor.sendToServer(dimensionEditorPayload.of(
							blockPos, "button", "t0", "t1"));
				}).bounds(time_buttonPosSize[0], time_buttonPosSize[1], time_buttonPosSize[2], time_buttonPosSize[3])
				.build();
		GUI_STATE.put("button:button_time_00", button_time_00);
		this.addRenderableWidget(button_time_00);
		button_time_06 = Button.builder(
				Component.translatable("gui.huanyu_mod.dimension_editor_gui.button_time_06"), e -> {

				}).bounds(time_buttonPosSize[0] + 25, time_buttonPosSize[1], time_buttonPosSize[2], time_buttonPosSize[3])
				.build();
		GUI_STATE.put("button:button_time_06", button_time_06);
		this.addRenderableWidget(button_time_06);
		button_time_12 = Button.builder(
				Component.translatable("gui.huanyu_mod.dimension_editor_gui.button_time_12"), e -> {

				}).bounds(time_buttonPosSize[0] + 50, time_buttonPosSize[1], time_buttonPosSize[2], time_buttonPosSize[3])
				.build();
		GUI_STATE.put("button:button_time_12", button_time_12);
		this.addRenderableWidget(button_time_12);
		button_time_18 = Button.builder(
				Component.translatable("gui.huanyu_mod.dimension_editor_gui.button_time_18"), e -> {

				}).bounds(time_buttonPosSize[0] + 75, time_buttonPosSize[1], time_buttonPosSize[2], time_buttonPosSize[3])
				.build();
		GUI_STATE.put("button:button_time_18", button_time_18);
		this.addRenderableWidget(button_time_18);

		var EB01String = "1234567890";
		edit_box_01 = new EditBox(this.font, this.leftPos + 10, this.topPos + 40, 120, 15,
				Component.empty()) /*{
					@Override
					public void insertText(@NotNull String text) {
						super.insertText(text);
						hideEBInitStr(this, EB01String);
					}
					@Override
					public void moveCursorTo(int pos, boolean flag) {
						super.moveCursorTo(pos, flag);
						hideEBInitStr(this, EB01String);
					}
				}*/;
		edit_box_01.setValue(this.blockEntity.getString());
		edit_box_01.setMaxLength(32767);
		GUI_STATE.put("text:edit_box_01", edit_box_01);
		this.addWidget(this.edit_box_01);
	}

	@Override
	public void onClose() {
		super.onClose();
		PacketDistributor.sendToServer(dimensionEditorPayload.of(
				blockPos, "close", edit_box_01.getValue()));
	}
}
