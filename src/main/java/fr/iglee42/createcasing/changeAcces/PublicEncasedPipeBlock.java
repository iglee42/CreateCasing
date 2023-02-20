package fr.iglee42.createcasing.changeAcces;

import com.simibubi.create.content.contraptions.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.contraptions.fluids.pipes.FluidPipeTileEntity;
import fr.iglee42.createcasing.ModTiles;
import net.minecraft.world.level.block.entity.BlockEntityType;


/*
This class allow to use the constructore of EncasedPipeBlock from the create code
 */
public class PublicEncasedPipeBlock extends EncasedPipeBlock {

	public PublicEncasedPipeBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockEntityType<? extends FluidPipeTileEntity> getTileEntityType() {
		return ModTiles.ENCASED_FLUID_PIPE.get();
	}
}