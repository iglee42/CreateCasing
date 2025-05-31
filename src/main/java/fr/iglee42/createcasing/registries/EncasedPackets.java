package fr.iglee42.createcasing.registries;

import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.packets.ConfigureBrassShaftPacket;
import net.createmod.catnip.net.base.BasePacketPayload;
import net.createmod.catnip.net.base.CatnipPacketRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.Locale;

public enum EncasedPackets implements BasePacketPayload.PacketTypeProvider {

	// Client to Server
	BRASS_SHAFT_CONFIGURE(ConfigureBrassShaftPacket.class, ConfigureBrassShaftPacket.STREAM_CODEC),

	// Server to Client
	//SYMMETRY_EFFECT(SymmetryEffectPacket.class, SymmetryEffectPacket::new, PLAY_TO_CLIENT),

	;

	private final CatnipPacketRegistry.PacketType<?> type;

	<T extends BasePacketPayload> EncasedPackets(Class<T> clazz, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
		String name = this.name().toLowerCase(Locale.ROOT);
		this.type = new CatnipPacketRegistry.PacketType<>(
				new CustomPacketPayload.Type<>(CreateCasing.asResource(name)),
				clazz, codec
		);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends CustomPacketPayload> CustomPacketPayload.Type<T> getType() {
		return (CustomPacketPayload.Type<T>) this.type.type();
	}

	public static void register() {
		CatnipPacketRegistry packetRegistry = new CatnipPacketRegistry(CreateCasing.MODID, 1);
		for (EncasedPackets packet : EncasedPackets.values()) {
			packetRegistry.registerPacket(packet.type);
		}
		packetRegistry.registerAllPackets();
	}

}
