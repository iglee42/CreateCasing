package fr.iglee42.createcasing.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public final class ClientItemPredicates {

    private ClientItemPredicates() {
    }

    public static Predicate<Item> make3dItemPredicate() {
        return item -> {
            ItemRenderer itemRenderer = Minecraft.getInstance()
                    .getItemRenderer();
            BakedModel model = itemRenderer.getModel(new ItemStack(item), null, null, 0);
            return model.isGui3d();
        };
    }
}
