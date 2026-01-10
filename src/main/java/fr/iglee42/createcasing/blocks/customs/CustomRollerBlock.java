package fr.iglee42.createcasing.blocks.customs;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.content.contraptions.actors.roller.RollerBlock;
import com.simibubi.create.content.contraptions.actors.roller.RollerBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

public class CustomRollerBlock extends RollerBlock {

    public CustomRollerBlock(Properties p_i48377_1_) {
        super(p_i48377_1_);
    }

    @Override
    public BlockEntityType<? extends RollerBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.MECHANICAL_ROLLER.get();
    }

}
