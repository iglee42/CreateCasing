package fr.iglee42.createcasing.compatibility.createcrystalclear;

import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.cyvack.create_crystal_clear.blocks.glass_encased_shaft.GlassEncasedShaftBlock;
import com.cyvack.create_crystal_clear.tile_entities.ModtileEntities;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class GlassEncasedShaftBlockCompat extends GlassEncasedShaftBlock {
    public GlassEncasedShaftBlockCompat(Properties properties, BlockEntry<GlassCasing> GlassCasing) {
        super(properties, GlassCasing);
    }


    public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
        return CreateCrystalClearCompatibility.GLASS_SHAFT_TILE.get();
    }
}
