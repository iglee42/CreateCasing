package fr.iglee42.createcasing.compatibility.createcrystalclear;

import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.cyvack.create_crystal_clear.blocks.glass_encased_cogwheel.GlassEncasedCogwheel;
import com.simibubi.create.content.contraptions.relays.elementary.SimpleKineticTileEntity;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class GlassEncasedCogwheelBlockCompat extends GlassEncasedCogwheel {


    public GlassEncasedCogwheelBlockCompat(boolean large, Properties properties, BlockEntry<GlassCasing> casing) {
        super(large, properties, casing);
    }

    public BlockEntityType<? extends SimpleKineticTileEntity> getTileEntityType() {
        return isLargeCog() ? CreateCrystalClearCompatibility.GLASS_COGWHEEL_LARGE_TILE.get() : CreateCrystalClearCompatibility.GLASS_COGWHEEL_TILE.get();
    }
}
