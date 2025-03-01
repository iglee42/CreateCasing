package fr.iglee42.createcasing.packets;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.networking.BlockEntityConfigurationPacket;

import fr.iglee42.createcasing.blockEntities.BrassShaftBlockEntity;
import fr.iglee42.createcasing.blockEntities.MetalShaftBlockEntity;
import fr.iglee42.createcasing.registries.ModPackets;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class ConfigureBrassShaftPacket extends BlockEntityConfigurationPacket<BrassShaftBlockEntity> {

	public static final StreamCodec<ByteBuf, ConfigureBrassShaftPacket> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC, packet -> packet.pos,
			ByteBufCodecs.INT, p->p.stress,
			ConfigureBrassShaftPacket::new
	);

	private final int stress;

	public ConfigureBrassShaftPacket(BlockPos pos, int stress) {
		super(pos);
		this.stress = stress;
	}


	@Override
	protected void applySettings(ServerPlayer serverPlayer, BrassShaftBlockEntity be) {
		be.setMaxSupportedStress(stress);
		RotationPropagator.handleAdded(be.getLevel(),pos, be);
	}

	@Override
	public PacketTypeProvider getTypeProvider() {
		return ModPackets.BRASS_SHAFT_CONFIGURE;
	}
}
