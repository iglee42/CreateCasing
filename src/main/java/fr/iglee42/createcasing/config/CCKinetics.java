package fr.iglee42.createcasing.config;

import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.simibubi.create.infrastructure.config.CKinetics;

public class CCKinetics extends ConfigBase {

    public ConfigBool shouldCustomMixerMixeFaster = b(true,"shouldCustomMixerMixeFaster",Comments.shouldCustomMixerMixeFaster);

    public ConfigBool shouldWoodenShaftBreak = b(true,"shouldWoodenShaftBreak", Comments.shouldWoodenShaftBreak);
    public ConfigBool shouldGlassShaftBreak = b(true,"shouldGlassShaftBreak", Comments.shouldGlassShaftBreak);

    public ConfigInt maxSpeedWoodenShaft = i(32,2, 256,"maxSpeedWoodenShaft",Comments.maxSpeedWoodenShaft);


    @Override
    public String getName() {
        return "kinetics";
    }

    private static class Comments {
        static String shouldCustomMixerMixeFaster = "Should Brass/Copper/Train mixe faster";
        static String shouldWoodenShaftBreak = "Should Wooden Shaft break if the speed is too high";
        static String shouldGlassShaftBreak = "Should Glass Shaft break if the system is overstressed";
        static String maxSpeedWoodenShaft = "The max speed wooden shafts can endure";
    }
}
