package fr.iglee42.createcasing.changeAcces;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import fr.iglee42.createcasing.ModBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;


/*
This class allow to use the constructore of EncasedShaftBlock from the create code
 */
public class PublicEncasedShaftBlock extends EncasedShaftBlock {

	public PublicEncasedShaftBlock(BlockBehaviour.Properties properties, Supplier<Block> casing) {
		super(properties, casing);
	}

	@Override
	public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
		return ModBlockEntities.ENCASED_SHAFT.get();
	}
}