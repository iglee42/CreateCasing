package fr.iglee42.createcasing.config;

import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.infrastructure.config.CStress;


public class CCKinetics extends ConfigBase {

    public ConfigBool casingBlockSwappable = b(true,"casingBlockSwappable",Comments.casingBlockSwappable);
    public ConfigBool shaftCogwheelsSwappable = b(true,"shaftCogwheelsSwappable",Comments.shaftCogwheelsSwappable);
    public ConfigBool shouldCustomMixerMixeFaster = b(true,"shouldCustomMixerMixeFaster",Comments.shouldCustomMixerMixeFaster);

    public ConfigBool shouldWoodenShaftBreak = b(true,"shouldWoodenShaftBreak", Comments.shouldWoodenShaftBreak);
    public ConfigBool shouldGlassShaftBreak = b(true,"shouldGlassShaftBreak", Comments.shouldGlassShaftBreak);

    public ConfigInt maxSpeedWoodenShaft = i(32,2, 256,"maxSpeedWoodenShaft",Comments.maxSpeedWoodenShaft);
    //public final CStress stressValues = nested(1, CStress::new, Comments.stress);

    @Override
    public String getName() {
        return "kinetics";
    }

    private static class Comments {
        static String casingBlockSwappable = "Can swap casing between block (Mixer, Press, Depot, Gearbox, etc...)";
        static String shaftCogwheelsSwappable = "Can swap material between shafts,cogwheels and large cogwheels (includes brass,andesite and glass)";
        static String shouldCustomMixerMixeFaster = "Should Brass/Copper/Train mixe faster";
        static String shouldWoodenShaftBreak = "Should Wooden Shaft break if the speed is too high";
        static String shouldGlassShaftBreak = "Should Glass Shaft break if the system is overstressed";
        static String maxSpeedWoodenShaft = "The max speed wooden shafts can endure";
        static String stress = "Fine tune the kinetic stats of individual components";

    }
}
