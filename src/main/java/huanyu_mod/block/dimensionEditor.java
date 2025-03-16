package huanyu_mod.block;

import huanyu_mod.blockentity.dimensionEditorBE;
import huanyu_mod.menu.dimensionEditorGuiMenu;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class dimensionEditor extends Block implements EntityBlock {
    public final static String CLASS_NAME = "dimensionEditor";
    public final static BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties
            .of()
            .sound(SoundType.AMETHYST)
            .strength(1f, 1200f)
            .lightLevel(s -> 15)
            .noOcclusion();
    public final static Item.Properties ITEM_PROPERTIES = new Item.Properties()
            .rarity(Rarity.EPIC);
    private final static DirectionProperty FACING = DirectionalBlock.FACING;
    public dimensionEditor() {
        super(BLOCK_PROPERTIES);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    /**
     * Block State
     */
    @NotNull
    @Override
    public VoxelShape getShape(BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
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
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    /**
     * Block Entity
     */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new dimensionEditorBE(blockPos, blockState);
    }

    @Override
    protected void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
    }

    @Override
    protected void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean movedByPiston) {
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    /**
     * Block Action
     */
    @NotNull
    @Override
    protected InteractionResult useWithoutItem(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if (!(level.isClientSide()) && (player instanceof ServerPlayer serverPlayer)) {
            if (level.getBlockEntity(blockPos) instanceof dimensionEditorBE) {
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
                    public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
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
