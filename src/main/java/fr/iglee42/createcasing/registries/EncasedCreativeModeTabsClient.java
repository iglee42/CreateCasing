package fr.iglee42.createcasing.registries;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.function.Predicate;

@OnlyIn(Dist.CLIENT)
public final class EncasedCreativeModeTabsClient {

    private EncasedCreativeModeTabsClient() {
    }

    public static Predicate<Item> makeClient3dItemPredicate() {
        return item -> {
            ItemRenderer itemRenderer = Minecraft.getInstance()
                    .getItemRenderer();
            BakedModel model = itemRenderer.getModel(new ItemStack(item), null, null, 0);
            return model.isGui3d();
        };
    }
}
