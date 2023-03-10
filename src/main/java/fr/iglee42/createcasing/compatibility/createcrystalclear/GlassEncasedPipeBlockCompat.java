package fr.iglee42.createcasing.compatibility.createcrystalclear;

import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.FluidPipeTileEntity;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.ModTiles;
import net.minecraft.world.level.block.entity.BlockEntityType;


/*
This class allow to use the constructore of EncasedPipeBlock from the create code
 */
public class GlassEncasedPipeBlockCompat extends EncasedPipeBlock {

	private BlockEntry<GlassCasing> casing;

	public GlassEncasedPipeBlockCompat(Properties properties, BlockEntry<GlassCasing> casing) {
		super(properties);
		this.casing = casing;
	}

	public BlockEntry<GlassCasing> getCasing() {
		return this.casing;
	}

	@Override
	public BlockEntityType<? extends FluidPipeTileEntity> getTileEntityType() {
		return CreateCrystalClearCompatibility.GLASS_PIPE_TILE.get();
	}
}