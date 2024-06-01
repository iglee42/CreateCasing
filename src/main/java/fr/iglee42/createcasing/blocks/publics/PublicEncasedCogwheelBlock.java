package fr.iglee42.createcasing.blocks.publics;

import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;


/*
This class allow to use the constructor of EncasedCogWheelBlock from the create code
 */
public class PublicEncasedCogwheelBlock extends EncasedCogwheelBlock /*implements DontShowInCreativeTab*/ {

	public PublicEncasedCogwheelBlock( Properties properties, boolean large,Supplier<Block> casing) {
		super(properties, large, casing);
	}

	@Override
	public BlockEntityType<? extends SimpleKineticBlockEntity> getBlockEntityType() {
		return this.isLargeCog() ?  ModBlockEntities.ENCASED_COGWHEEL_LARGE.get() : ModBlockEntities.ENCASED_COGWHEEL.get();
	}
}