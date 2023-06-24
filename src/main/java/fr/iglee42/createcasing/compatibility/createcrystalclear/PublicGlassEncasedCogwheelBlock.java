package fr.iglee42.createcasing.compatibility.createcrystalclear;

import com.cyvack.create_crystal_clear.content.blocks.GlassEncasedCogwheel;
import com.cyvack.create_crystal_clear.index.GlassUtil;
import fr.iglee42.createcasing.changeAcces.PublicEncasedCogwheelBlock;
import fr.iglee42.createcasing.changeAcces.PublicEncasedShaftBlock;
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

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pDirection) {
        return pAdjacentBlockState.getBlock() instanceof GlassEncasedCogwheel || pAdjacentBlockState.getBlock() instanceof PublicEncasedCogwheelBlock;
    }
}
