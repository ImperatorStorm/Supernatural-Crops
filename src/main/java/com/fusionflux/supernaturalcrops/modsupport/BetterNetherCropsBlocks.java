package com.fusionflux.supernaturalcrops.modsupport;

import com.fusionflux.supernaturalcrops.block.OreBush;
import com.fusionflux.supernaturalcrops.block.SupernaturalCropsBlocks;
import com.fusionflux.supernaturalcrops.config.SupernaturalCropsConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import paulevs.betternether.registry.ItemsRegistry;

public class BetterNetherCropsBlocks {
    public static final OreBush CINCINNASITE_BUSH = new OreBush(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.SWEET_BERRY_BUSH), SupernaturalCropsConfig.BETTER_END_NUGGET_BALANCE.ENABLE_TERMINITE_CROP_NUGGETS.getValue() ? ItemsRegistry.CINCINNASITE : ItemsRegistry.CINCINNASITE_INGOT);
    public static final OreBush NETHER_RUBY_BUSH = new OreBush(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.SWEET_BERRY_BUSH), ItemsRegistry.NETHER_RUBY);

    public static void registerBlocks() {
        if (SupernaturalCropsConfig.BETTER_NETHER_ENABLED.ENABLE_CINCINNASITE_CROPS.getValue()) {
            SupernaturalCropsBlocks.registerBush("cincinnasite_bush", BetterNetherCropsBlocks.CINCINNASITE_BUSH, ItemsRegistry.CINCINNASITE_INGOT);
        }
        if (SupernaturalCropsConfig.BETTER_NETHER_ENABLED.ENABLE_NETHER_RUBY_CROPS.getValue()) {
            SupernaturalCropsBlocks.registerBush("nether_ruby_bush", BetterNetherCropsBlocks.NETHER_RUBY_BUSH, ItemsRegistry.NETHER_RUBY);
        }
    }

    @Environment(EnvType.CLIENT)
    public static void registerRenderLayers() {
        BlockRenderLayerMap.INSTANCE.putBlock(BetterNetherCropsBlocks.CINCINNASITE_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BetterNetherCropsBlocks.NETHER_RUBY_BUSH, RenderLayer.getCutout());
    }

}
