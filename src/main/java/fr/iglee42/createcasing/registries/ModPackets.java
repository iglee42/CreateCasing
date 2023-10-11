package fr.iglee42.createcasing.registries;

import static net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT;
import static net.minecraftforge.network.NetworkDirection.PLAY_TO_SERVER;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.simibubi.create.compat.computercraft.AttachedComputerPacket;
import com.simibubi.create.content.contraptions.ContraptionBlockChangedPacket;
import com.simibubi.create.content.contraptions.ContraptionColliderLockPacket;
import com.simibubi.create.content.contraptions.ContraptionColliderLockPacket.ContraptionColliderLockPacketRequest;
import com.simibubi.create.content.contraptions.ContraptionDisassemblyPacket;
import com.simibubi.create.content.contraptions.ContraptionRelocationPacket;
import com.simibubi.create.content.contraptions.ContraptionStallPacket;
import com.simibubi.create.content.contraptions.TrainCollisionPacket;
import com.simibubi.create.content.contraptions.actors.contraptionControls.ContraptionDisableActorPacket;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsInputPacket;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsStopControllingPacket;
import com.simibubi.create.content.contraptions.elevator.ElevatorContactEditPacket;
import com.simibubi.create.content.contraptions.elevator.ElevatorFloorListPacket;
import com.simibubi.create.content.contraptions.elevator.ElevatorTargetFloorPacket;
import com.simibubi.create.content.contraptions.gantry.GantryContraptionUpdatePacket;
import com.simibubi.create.content.contraptions.glue.GlueEffectPacket;
import com.simibubi.create.content.contraptions.glue.SuperGlueRemovalPacket;
import com.simibubi.create.content.contraptions.glue.SuperGlueSelectionPacket;
import com.simibubi.create.content.contraptions.minecart.CouplingCreationPacket;
import com.simibubi.create.content.contraptions.minecart.capability.MinecartControllerUpdatePacket;
import com.simibubi.create.content.contraptions.sync.ClientMotionPacket;
import com.simibubi.create.content.contraptions.sync.ContraptionFluidPacket;
import com.simibubi.create.content.contraptions.sync.ContraptionInteractionPacket;
import com.simibubi.create.content.contraptions.sync.ContraptionSeatMappingPacket;
import com.simibubi.create.content.contraptions.sync.LimbSwingUpdatePacket;
import com.simibubi.create.content.equipment.bell.SoulPulseEffectPacket;
import com.simibubi.create.content.equipment.blueprint.BlueprintAssignCompleteRecipePacket;
import com.simibubi.create.content.equipment.clipboard.ClipboardEditPacket;
import com.simibubi.create.content.equipment.extendoGrip.ExtendoGripInteractionPacket;
import com.simibubi.create.content.equipment.potatoCannon.PotatoCannonPacket;
import com.simibubi.create.content.equipment.potatoCannon.PotatoProjectileTypeManager;
import com.simibubi.create.content.equipment.symmetryWand.ConfigureSymmetryWandPacket;
import com.simibubi.create.content.equipment.symmetryWand.SymmetryEffectPacket;
import com.simibubi.create.content.equipment.toolbox.ToolboxDisposeAllPacket;
import com.simibubi.create.content.equipment.toolbox.ToolboxEquipPacket;
import com.simibubi.create.content.equipment.zapper.ZapperBeamPacket;
import com.simibubi.create.content.equipment.zapper.terrainzapper.ConfigureWorldshaperPacket;
import com.simibubi.create.content.fluids.transfer.FluidSplashPacket;
import com.simibubi.create.content.kinetics.gauge.GaugeObservedPacket;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmPlacementPacket;
import com.simibubi.create.content.kinetics.transmission.sequencer.ConfigureSequencedGearshiftPacket;
import com.simibubi.create.content.logistics.depot.EjectorAwardPacket;
import com.simibubi.create.content.logistics.depot.EjectorElytraPacket;
import com.simibubi.create.content.logistics.depot.EjectorPlacementPacket;
import com.simibubi.create.content.logistics.depot.EjectorTriggerPacket;
import com.simibubi.create.content.logistics.filter.FilterScreenPacket;
import com.simibubi.create.content.logistics.funnel.FunnelFlapPacket;
import com.simibubi.create.content.logistics.tunnel.TunnelFlapPacket;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkConfigurationPacket;
import com.simibubi.create.content.redstone.link.controller.LinkedControllerBindPacket;
import com.simibubi.create.content.redstone.link.controller.LinkedControllerInputPacket;
import com.simibubi.create.content.redstone.link.controller.LinkedControllerStopLecternPacket;
import com.simibubi.create.content.redstone.thresholdSwitch.ConfigureThresholdSwitchPacket;
import com.simibubi.create.content.schematics.cannon.ConfigureSchematicannonPacket;
import com.simibubi.create.content.schematics.packet.InstantSchematicPacket;
import com.simibubi.create.content.schematics.packet.SchematicPlacePacket;
import com.simibubi.create.content.schematics.packet.SchematicSyncPacket;
import com.simibubi.create.content.schematics.packet.SchematicUploadPacket;
import com.simibubi.create.content.trains.HonkPacket;
import com.simibubi.create.content.trains.TrainHUDUpdatePacket;
import com.simibubi.create.content.trains.entity.TrainPacket;
import com.simibubi.create.content.trains.entity.TrainPromptPacket;
import com.simibubi.create.content.trains.entity.TrainRelocationPacket;
import com.simibubi.create.content.trains.graph.TrackGraphRequestPacket;
import com.simibubi.create.content.trains.graph.TrackGraphRollCallPacket;
import com.simibubi.create.content.trains.graph.TrackGraphSyncPacket;
import com.simibubi.create.content.trains.schedule.ScheduleEditPacket;
import com.simibubi.create.content.trains.signal.SignalEdgeGroupPacket;
import com.simibubi.create.content.trains.station.StationEditPacket;
import com.simibubi.create.content.trains.station.TrainEditPacket;
import com.simibubi.create.content.trains.station.TrainEditPacket.TrainEditReturnPacket;
import com.simibubi.create.content.trains.track.CurvedTrackDestroyPacket;
import com.simibubi.create.content.trains.track.CurvedTrackSelectionPacket;
import com.simibubi.create.content.trains.track.PlaceExtendedCurvePacket;
import com.simibubi.create.foundation.blockEntity.RemoveBlockEntityPacket;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsPacket;
import com.simibubi.create.foundation.config.ui.CConfigureConfigPacket;
import com.simibubi.create.foundation.gui.menu.ClearMenuPacket;
import com.simibubi.create.foundation.gui.menu.GhostItemSubmitPacket;
import com.simibubi.create.foundation.networking.ISyncPersistentData;
import com.simibubi.create.foundation.networking.LeftClickPacket;
import com.simibubi.create.foundation.networking.SimplePacketBase;
import com.simibubi.create.foundation.utility.ServerSpeedProvider;
import com.simibubi.create.infrastructure.command.HighlightPacket;
import com.simibubi.create.infrastructure.command.SConfigureConfigPacket;

import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.packets.ConfigureBrassShaftPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.TargetPoint;
import net.minecraftforge.network.simple.SimpleChannel;

public enum ModPackets {

	// Client to Server
	BRASS_SHAFT_CONFIGURE(ConfigureBrassShaftPacket.class,
			ConfigureBrassShaftPacket::new, PLAY_TO_SERVER),

	// Server to Client
	//SYMMETRY_EFFECT(SymmetryEffectPacket.class, SymmetryEffectPacket::new, PLAY_TO_CLIENT),

	;

	public static final ResourceLocation CHANNEL_NAME = CreateCasing.asResource("main");
	public static final int NETWORK_VERSION = 3;
	public static final String NETWORK_VERSION_STR = String.valueOf(NETWORK_VERSION);
	private static SimpleChannel channel;

	private PacketType<?> packetType;

	<T extends SimplePacketBase> ModPackets(Class<T> type, Function<FriendlyByteBuf, T> factory,
											NetworkDirection direction) {
		packetType = new PacketType<>(type, factory, direction);
	}

	public static void registerPackets() {
		channel = NetworkRegistry.ChannelBuilder.named(CHANNEL_NAME)
			.serverAcceptedVersions(NETWORK_VERSION_STR::equals)
			.clientAcceptedVersions(NETWORK_VERSION_STR::equals)
			.networkProtocolVersion(() -> NETWORK_VERSION_STR)
			.simpleChannel();

		for (ModPackets packet : values())
			packet.packetType.register();
	}

	public static SimpleChannel getChannel() {
		return channel;
	}

	public static void sendToNear(Level world, BlockPos pos, int range, Object message) {
		getChannel().send(
			PacketDistributor.NEAR.with(TargetPoint.p(pos.getX(), pos.getY(), pos.getZ(), range, world.dimension())),
			message);
	}

	private static class PacketType<T extends SimplePacketBase> {
		private static int index = 0;

		private BiConsumer<T, FriendlyByteBuf> encoder;
		private Function<FriendlyByteBuf, T> decoder;
		private BiConsumer<T, Supplier<Context>> handler;
		private Class<T> type;
		private NetworkDirection direction;

		private PacketType(Class<T> type, Function<FriendlyByteBuf, T> factory, NetworkDirection direction) {
			encoder = T::write;
			decoder = factory;
			handler = (packet, contextSupplier) -> {
				Context context = contextSupplier.get();
				if (packet.handle(context)) {
					context.setPacketHandled(true);
				}
			};
			this.type = type;
			this.direction = direction;
		}

		private void register() {
			getChannel().messageBuilder(type, index++, direction)
				.encoder(encoder)
				.decoder(decoder)
				.consumer(handler)
				.add();
		}
	}

}
