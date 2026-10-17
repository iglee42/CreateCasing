package fr.iglee42.createcasing.packets;

import com.simibubi.create.AllPackets;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import fr.iglee42.createcasing.registries.EncasedDataComponents;
import fr.iglee42.createcasing.registries.EncasedItems;
import fr.iglee42.createcasing.registries.EncasedPackets;
import io.netty.buffer.ByteBuf;
import net.createmod.catnip.codecs.stream.CatnipStreamCodecs;
import net.createmod.catnip.net.base.ServerboundPacketPayload;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public record RadialRecaserMenuSubmitPacket(Item preferredItem) implements ServerboundPacketPayload {
	public static final StreamCodec<RegistryFriendlyByteBuf, RadialRecaserMenuSubmitPacket> STREAM_CODEC = StreamCodec.composite(
	    ByteBufCodecs.registry(Registries.ITEM), RadialRecaserMenuSubmitPacket::preferredItem,
	    RadialRecaserMenuSubmitPacket::new
	);

	@Override
	public PacketTypeProvider getTypeProvider() {
		return EncasedPackets.RADIAL_RECASER_MENU_SUBMIT;
	}

	@Override
	public void handle(ServerPlayer player) {
		if (player.getMainHandItem().getItem() != EncasedItems.RECASER.asItem())
			return;

		player.getMainHandItem().set(EncasedDataComponents.RECASER_PREFERRED_ITEM, preferredItem);
	}
}
