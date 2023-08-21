package fr.iglee42.createcasing.compat.createcrystalclear;

import com.cyvack.create_crystal_clear.index.GlassUtil;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedShaftBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class PublicGlassEncasedShaftBlock extends PublicEncasedShaftBlock {
    public PublicGlassEncasedShaftBlock(Properties properties, Supplier<Block> casing) {
        super(properties, casing);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState selfState, BlockState adjacentBlock, Direction side) {
        return GlassUtil.checkForGlassBlock(adjacentBlock);
    }
}
