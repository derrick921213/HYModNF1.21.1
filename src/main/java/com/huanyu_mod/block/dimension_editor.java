package com.huanyu_mod.block;

import com.huanyu_mod.blockentity.dimension_editor_be;
import com.huanyu_mod.core.HYEng;
import com.huanyu_mod.world.menu.dimensionEditorGuiMenu;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class dimension_editor extends Block implements EntityBlock {
    public final static String CLASS_NAME = HYEng.getCurrentClassName();
    public final static Item.Properties ITEM_PROPERTIES = new Item.Properties().rarity(Rarity.EPIC);
    private final static DirectionProperty FACING = DirectionalBlock.FACING;
    public dimension_editor() {
        super(Properties.of()
                .sound(SoundType.AMETHYST)
                .strength(1f, 1200f)
                .lightLevel(s -> 15)
                .noOcclusion()
        );
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.UP)
        );
    }

    /* Block State */
    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case UP -> box(0, 0, 0, 16, 12, 16);
            case DOWN -> box(0, 4, 0, 16, 16, 16);
            case NORTH -> box(0, 0, 4, 16, 16, 16);
            case SOUTH -> box(0, 0, 0, 16, 16, 12);
            case EAST -> box(0, 0, 0, 12, 16, 16);
            case WEST -> box(4, 0, 0, 16, 16, 16);
        };
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return Objects.requireNonNull(super.getStateForPlacement(context)).setValue(FACING, context.getClickedFace());
    }
    @NotNull
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }
    @NotNull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    /* Block Entity */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new dimension_editor_be(blockPos, blockState);
    }
    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
    }
    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @NotNull
    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult hitResult) {
        if (!(level.isClientSide()) && (player instanceof ServerPlayer serverPlayer)) {
            if (level.getBlockEntity(blockPos) instanceof dimension_editor_be blockEntity) {
                serverPlayer.openMenu(new MenuProvider() {
                    @NotNull
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("");
                    }

                    @Override
                    public boolean shouldTriggerClientSideContainerClosingOnOpen() {
                        return false;
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new dimensionEditorGuiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer())
                                .writeBlockPos(blockPos));
                    }
                }, blockPos);
                return InteractionResult.SUCCESS;
            } else {
                throw new IllegalStateException(CLASS_NAME + " Wrong BlockEntity");
            }
        }
        return InteractionResult.FAIL;
    }
}
