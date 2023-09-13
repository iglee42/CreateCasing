package fr.iglee42.createcasing.compat.rei;

import fr.iglee42.createcasing.CreateCasing;
import me.shedaniel.rei.api.client.entry.filtering.base.BasicFilteringRule;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.forge.REIPluginClient;

@REIPluginClient
public class ReiPlugin implements REIClientPlugin {

    @Override
    public void registerBasicEntryFiltering(BasicFilteringRule<?> rule) {
        CreateCasing.hidedItems.forEach(i->rule.hide(EntryIngredients.of(i)));
    }

}
