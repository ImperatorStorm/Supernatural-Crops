package com.fusionflux.supernaturalcrops.block;

import com.fusionflux.supernaturalcrops.OreBush;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OreBushBlock extends SweetBerryBushBlock {
    private final OreBush bush;

    public OreBushBlock(Settings settings, OreBush bush) {
        super(settings);
        this.bush = bush;
    }

    public OreBush getBush() {
        return bush;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        if (!bush.isEnabled()) {
            tooltip.add(new TranslatableText("text.supernaturalcrops.bush_disabled")
                    .styled(style -> style.withColor(Formatting.RED).withItalic(true)));
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!bush.isEnabled()) {
            if (!world.isClient) {
                player.sendMessage(new TranslatableText("text.supernaturalcrops.bush_disabled"), false);
                world.breakBlock(pos, true, player);
            }
            return ActionResult.success(world.isClient);
        }

        int i = state.get(AGE);
        boolean bl = i == 3;
        if (!bl && player.getStackInHand(hand).getItem() == Items.BONE_MEAL)
            return ActionResult.CONSUME;
        else if (i > 1) {
            int j = world.random.nextInt(2);
            dropStack(world, pos, new ItemStack(bush.getHarvestResult(), j + (bl ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS,
                    1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            world.setBlockState(pos, state.with(AGE, 1), 2);
            return ActionResult.success(world.isClient);
        } else
            return super.onUse(state, world, pos, player, hand, hit);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(SupernaturalCropsBlocks.SCRAPED_STONE);
    }
}
