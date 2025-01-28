package com.huanyu_mod.block;

import appeng.api.networking.IManagedGridNode;
import com.huanyu_mod.blockentity.debug_block00_be;
import com.huanyu_mod.core.IHYEng;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class debug_block00 extends Block implements EntityBlock {
    private final static String CLASS_NAME = IHYEng.getCurrentClassName();
    public final static Item.Properties ITEM_PROPERTIES = new Item.Properties().rarity(Rarity.EPIC);
    public final static DirectionProperty FACING = DirectionalBlock.FACING;
    public debug_block00() {
        super(BlockBehaviour.Properties
                .of()
                .sound(SoundType.AMETHYST)
                .strength(1f, 1200f)
                .lightLevel(s -> 15)
                .noOcclusion()
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    /* Block State */
    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return Objects.requireNonNull(super.getStateForPlacement(context)).setValue(FACING, context.getClickedFace());
    }
    @NotNull
    public BlockState rotate(@NotNull BlockState state, @NotNull Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }
    @NotNull
    public BlockState mirror(@NotNull BlockState state, @NotNull Mirror mirror) {
        return this.rotate(state, mirror.getRotation(state.getValue(FACING)));
        //return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    /* Block Entity */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new debug_block00_be(blockPos, blockState);
    }

    @NotNull
    @Override
    protected InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof debug_block00_be customEntity) {
                IManagedGridNode node = customEntity.getMainNode();
                System.out.println(CLASS_NAME + " O: " + node);
                System.out.println(CLASS_NAME + " O: " + node.isReady());
            }
        }
        return InteractionResult.SUCCESS;
    }
}
