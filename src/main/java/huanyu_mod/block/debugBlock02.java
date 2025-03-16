package huanyu_mod.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class debugBlock02 extends Block {
    public final static String CLASS_NAME = "debugBlock02";
    public final static BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties
            .of()
            .sound(SoundType.AMETHYST)
            .strength(1f, 1200f)
            .lightLevel(s -> 15)
            .noOcclusion();
    public final static Item.Properties ITEM_PROPERTIES = new Item.Properties()
            .rarity(Rarity.EPIC);
    public final static DirectionProperty FACING = DirectionalBlock.FACING;

    public debugBlock02() {
        super(BLOCK_PROPERTIES);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
