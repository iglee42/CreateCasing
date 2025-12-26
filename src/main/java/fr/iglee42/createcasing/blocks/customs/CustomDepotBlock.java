package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.logistics.depot.DepotBlock;
import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.level.block.entity.BlockEntityType;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CustomDepotBlock extends DepotBlock {

	public CustomDepotBlock(Properties props) {
		super(props);
	}

	@Override
	public BlockEntityType<? extends DepotBlockEntity> getBlockEntityType() {
		return EncasedBlockEntities.DEPOT.get();
	}
}
