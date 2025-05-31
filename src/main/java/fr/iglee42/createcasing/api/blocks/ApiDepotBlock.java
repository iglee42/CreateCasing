package fr.iglee42.createcasing.api.blocks;

import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import fr.iglee42.createcasing.blocks.customs.CustomDepotBlock;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;

public class ApiDepotBlock extends CustomDepotBlock {
    public ApiDepotBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public BlockEntityType<? extends DepotBlockEntity> getBlockEntityType() {
        return EncasedBlockEntities.API_DEPOT.get();
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> p_49818_, TooltipFlag p_49819_) {
        super.appendHoverText(p_49816_, p_339606_, p_49818_, p_49819_);
        if (getDescriptionId().contains("xii")){
            p_49818_.add(Component.literal("Thanks to Delta Prime XII for top textures of depot").withStyle(ChatFormatting.GOLD));
        }
    }
}
