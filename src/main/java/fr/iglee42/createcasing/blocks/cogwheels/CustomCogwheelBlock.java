package fr.iglee42.createcasing.blocks.cogwheels;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.level.block.entity.BlockEntityType;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CustomCogwheelBlock extends CogWheelBlock {


	protected CustomCogwheelBlock(boolean large, Properties properties) {
		super(large,properties);
	}

	public static CustomCogwheelBlock small(Properties properties) {
		return new CustomCogwheelBlock(false, properties);
	}

	public static CustomCogwheelBlock large(Properties properties) {
		return new CustomCogwheelBlock(true, properties);
	}

	@Override
	public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
		return EncasedBlockEntities.CUSTOM_COGWHEELS.get();
	}
}
