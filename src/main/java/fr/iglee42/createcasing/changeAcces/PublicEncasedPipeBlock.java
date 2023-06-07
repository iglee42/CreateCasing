package fr.iglee42.createcasing.changeAcces;

import com.simibubi.create.content.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import fr.iglee42.createcasing.ModBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;


/*
This class allow to use the constructore of EncasedPipeBlock from the create code
 */
public class PublicEncasedPipeBlock extends EncasedPipeBlock {


	public PublicEncasedPipeBlock(Properties properties, Supplier<Block> casing) {
		super(properties,casing);
	}

	@Override
	public BlockEntityType<? extends FluidPipeBlockEntity> getBlockEntityType() {
		return ModBlockEntities.ENCASED_FLUID_PIPE.get();
	}
}