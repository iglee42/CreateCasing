package fr.iglee42.createcasing.changeAcces;

import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.FluidPipeTileEntity;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.ModTiles;
import net.minecraft.world.level.block.entity.BlockEntityType;


/*
This class allow to use the constructore of EncasedPipeBlock from the create code
 */
public class PublicEncasedPipeBlock extends EncasedPipeBlock {

	private BlockEntry<CasingBlock> casing;

	public PublicEncasedPipeBlock(Properties properties, BlockEntry<CasingBlock> casing) {
		super(properties);
		this.casing = casing;
	}

	public BlockEntry<CasingBlock> getCasing() {
		return this.casing;
	}

	@Override
	public BlockEntityType<? extends FluidPipeTileEntity> getTileEntityType() {
		return ModTiles.ENCASED_FLUID_PIPE.get();
	}
}