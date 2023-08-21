package fr.iglee42.createcasing.compat.createcrystalclear;

import com.cyvack.create_crystal_clear.content.blocks.GlassEncasedCogwheel;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedCogwheelBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class PublicGlassEncasedCogwheelBlock extends PublicEncasedCogwheelBlock {

    public PublicGlassEncasedCogwheelBlock(Properties properties, boolean large, Supplier<Block> casing) {
        super(properties, large, casing);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pDirection) {
        return pAdjacentBlockState.getBlock() instanceof GlassEncasedCogwheel || pAdjacentBlockState.getBlock() instanceof PublicEncasedCogwheelBlock || pAdjacentBlockState.getBlock() instanceof PublicGlassEncasedCogwheelBlock;
    }
}
