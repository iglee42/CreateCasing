package fr.iglee42.createcasing.blocks.cogwheels;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.level.block.entity.BlockEntityType;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WoodenCogwheelBlock extends CogWheelBlock {


	protected WoodenCogwheelBlock(boolean large, Properties properties) {
		super(large,properties);
	}

	public static WoodenCogwheelBlock small(Properties properties) {
		return new WoodenCogwheelBlock(false, properties);
	}

	public static WoodenCogwheelBlock large(Properties properties) {
		return new WoodenCogwheelBlock(true, properties);
	}

	@Override
	public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
		return EncasedBlockEntities.WOODEN_COGWHEELS.get();
	}
}
