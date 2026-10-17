package fr.iglee42.createcasing.items.recaser;

import com.simibubi.create.foundation.item.render.SimpleCustomRenderer;
import fr.iglee42.createcasing.client.RecaserItemRenderer;
import fr.iglee42.createcasing.registries.EncasedDataComponents;
import fr.iglee42.createcasing.sets.SetBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Consumer;


public class RecaserItem extends Item {

	private static final BiPredicate<RegistryAccess, ItemStack> AMMO_PREDICATE = (access, it)->!RecaserChangeManager.getSetsForItem(access, it.getItem()).isEmpty();


	public RecaserItem(Properties properties) {
        super(properties);
    }

    @Nullable
    public static Ammo getAmmo(Player player, ItemStack heldStack) {
		ItemStack ammoStack = getFirstAmmo(player, player.registryAccess(), heldStack);
		if (ammoStack.isEmpty()) {
			return null;
		}

        List<SetBase<?, ?>> sets = RecaserChangeManager.getSetsForItem(player.registryAccess(), ammoStack.getItem());
        if (sets.isEmpty())
            return null;

        return new Ammo(ammoStack, sets);
    }

	private static ItemStack getFirstAmmo(Player player, RegistryAccess access, ItemStack heldStack) {
		List<ItemStack> ammoStacks = findAllAmmo(player, access);
		if (heldStack.has(EncasedDataComponents.RECASER_PREFERRED_ITEM)){
			Item preferredItem = heldStack.get(EncasedDataComponents.RECASER_PREFERRED_ITEM);
			Optional<ItemStack> preferredStack = ammoStacks.stream().filter(stack -> stack.getItem() == preferredItem).findFirst();
			if (preferredStack.isPresent())
				return preferredStack.get();
		}
		return ammoStacks.stream().findFirst().orElse(ItemStack.EMPTY);
	}

	public static List<ItemStack> findAllAmmo(Player player, RegistryAccess access) {
		List<ItemStack> stacks = new ArrayList<>();
		if (AMMO_PREDICATE.test(access, player.getOffhandItem()))
			stacks.add(player.getOffhandItem());
		if (AMMO_PREDICATE.test(access, player.getMainHandItem()))
			stacks.add(player.getMainHandItem());

		for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
			ItemStack invStack = player.getInventory().getItem(i);
			if (AMMO_PREDICATE.test(access,invStack)) {
				stacks.add(player.getInventory().getItem(i));
			}
		}
		return stacks;
	}



    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		if (player == null || !player.mayBuild())
			return super.useOn(context);

		Ammo ammo = getAmmo(player, context.getItemInHand());
		if (ammo == null)
			return super.useOn(context);

		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);
        return RecaserChangeManager.tryChangeBlock(level, pos, state, context.getClickedFace(), player, ammo);
    }

	@Override
	public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
		if (getAmmo(context.getPlayer(), stack) == null)
			return InteractionResult.PASS;
		return useOn(context);
	}

	@Override
	public boolean doesSneakBypassUse(ItemStack stack, LevelReader level, BlockPos pos, Player player) {
		return getAmmo(player, stack) != null || super.doesSneakBypassUse(stack, level, pos, player);
	}

	@Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(SimpleCustomRenderer.create(this, new RecaserItemRenderer()));
    }

    public record Ammo(ItemStack stack, List<SetBase<?, ?>> sets) {
    }

}
