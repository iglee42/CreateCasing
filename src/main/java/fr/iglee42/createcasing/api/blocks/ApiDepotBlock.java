package fr.iglee42.createcasing.api.blocks;

import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import fr.iglee42.createcasing.blocks.customs.CustomDepotBlock;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ApiDepotBlock extends CustomDepotBlock {
    public ApiDepotBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public BlockEntityType<? extends DepotBlockEntity> getBlockEntityType() {
        return ModBlockEntities.API_DEPOT.get();
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        super.appendHoverText(p_49816_, p_49817_, p_49818_, p_49819_);
        if (getDescriptionId().contains("xii")){
            p_49818_.add(Component.literal("Thanks to Delta Prime XII for top textures of depot").withStyle(ChatFormatting.GOLD));
        }
    }
}
