package com.huanyu_mod.client.gui;

import com.huanyu_mod.HuanYuMod;
import com.huanyu_mod.world.inventory.dimension_editor_interfaceInv;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class dimension_editor_interfaceGui extends AbstractContainerScreen<dimension_editor_interfaceInv> {
	private final static HashMap<String, Object> guiState = dimension_editor_interfaceInv.guiState;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(
			HuanYuMod.MOD_ID, "textures/screens/dimension_editor_bg.png");
	Button button_time_00;
	Button button_time_06;
	Button button_time_12;
	Button button_time_18;

	public dimension_editor_interfaceGui(dimension_editor_interfaceInv container, Inventory inventory, Component component) {
		super(container, inventory, component);
		this.world = container.level;
		this.x = container.blockX;
		this.y = container.blockY;
		this.z = container.blockZ;
		this.entity = container.player;
		this.imageWidth = 384;
		this.imageHeight = 216;
	}

	@Override
	public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
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
				Component.translatable("gui.huanyu_mod.dimension_editor.dimension_editor_title"),
				10, 10, 0x000000, false);
	}
	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
            assert this.minecraft != null;
            assert this.minecraft.player != null;
            this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}
	@Override
	public void init() {
		super.init();
		int[] time_buttonPosSize = {this.leftPos + 10, this.topPos + 20, 20, 20};
		button_time_00 = Button.builder(
				Component.translatable("gui.huanyu_mod.dimension_editor.button_time_00"), e -> {

				}).bounds(time_buttonPosSize[0], time_buttonPosSize[1], time_buttonPosSize[2], time_buttonPosSize[3])
				.build();
		guiState.put("button:button_time_00", button_time_00);
		this.addRenderableWidget(button_time_00);
		button_time_06 = Button.builder(
				Component.translatable("gui.huanyu_mod.dimension_editor.button_time_06"), e -> {

				}).bounds(time_buttonPosSize[0] + 25, time_buttonPosSize[1], time_buttonPosSize[2], time_buttonPosSize[3])
				.build();
		guiState.put("button:button_time_06", button_time_06);
		this.addRenderableWidget(button_time_06);
		button_time_12 = Button.builder(
				Component.translatable("gui.huanyu_mod.dimension_editor.button_time_12"), e -> {

				}).bounds(time_buttonPosSize[0] + 50, time_buttonPosSize[1], time_buttonPosSize[2], time_buttonPosSize[3])
				.build();
		guiState.put("button:button_time_12", button_time_12);
		this.addRenderableWidget(button_time_12);
		button_time_18 = Button.builder(
				Component.translatable("gui.huanyu_mod.dimension_editor.button_time_18"), e -> {

				}).bounds(time_buttonPosSize[0] + 75, time_buttonPosSize[1], time_buttonPosSize[2], time_buttonPosSize[3])
				.build();
		guiState.put("button:button_time_18", button_time_18);
		this.addRenderableWidget(button_time_18);
	}
}
