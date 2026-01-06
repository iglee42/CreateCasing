package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.press.MechanicalPressBlock;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CustomPressBlock extends MechanicalPressBlock {

	public CustomPressBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockEntityType<? extends MechanicalPressBlockEntity> getBlockEntityType() {
		return EncasedBlockEntities.PRESS.get();
	}
}
