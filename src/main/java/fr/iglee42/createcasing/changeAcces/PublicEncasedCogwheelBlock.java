package fr.iglee42.createcasing.changeAcces;

import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import fr.iglee42.createcasing.ModBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;


/*
This class allow to use the constructore of EncasedCogWheelBlock from the create code
 */
public class PublicEncasedCogwheelBlock extends EncasedCogwheelBlock {

	public PublicEncasedCogwheelBlock( Properties properties, boolean large,Supplier<Block> casing) {
		super(properties, large, casing);
	}

	@Override
	public BlockEntityType<? extends SimpleKineticBlockEntity> getBlockEntityType() {
		return this.isLargeCog() ? (BlockEntityType) ModBlockEntities.ENCASED_COGWHEEL_LARGE.get() : (BlockEntityType) ModBlockEntities.ENCASED_COGWHEEL.get();
	}
}