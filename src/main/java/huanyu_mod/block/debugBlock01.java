package huanyu_mod.block;

import huanyu_mod.blockentity.debugBlock01BE;
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

public class debugBlock01 extends Block implements EntityBlock {
    public final static String CLASS_NAME = "debugBlock01";
    public final static BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties
            .of()
            .sound(SoundType.AMETHYST)
            .strength(1f, 1200f)
            .lightLevel(s -> 15)
            .noOcclusion();
    public final static Item.Properties ITEM_PROPERTIES = new Item.Properties()
            .rarity(Rarity.EPIC);
    public final static DirectionProperty FACING = DirectionalBlock.FACING;

    public debugBlock01() {
        super(BLOCK_PROPERTIES);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    /**
     * Block State
     */
    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return Objects.requireNonNull(super.getStateForPlacement(context)).setValue(FACING, context.getClickedFace());
    }

    @NotNull
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @NotNull
    public BlockState mirror(@NotNull BlockState state, Mirror mirror) {
        return this.rotate(state, mirror.getRotation(state.getValue(FACING)));
        //return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    /**
     * Block Entity
     */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new debugBlock01BE(blockPos, blockState);
    }

    /**
     * Block Action
     */
    @NotNull
    @Override
    public InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BlockEntity blockE = level.getBlockEntity(blockPos);
        }
        return InteractionResult.SUCCESS;
    }
}
