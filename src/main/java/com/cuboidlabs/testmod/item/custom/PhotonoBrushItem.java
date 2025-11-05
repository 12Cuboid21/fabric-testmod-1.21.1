package com.cuboidlabs.testmod.item.custom;

import com.cuboidlabs.testmod.block.ModBlocks;
import com.cuboidlabs.testmod.component.ModDataComponentTypes;
import com.cuboidlabs.testmod.item.ModItems;
import net.fabricmc.fabric.api.block.v1.FabricBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class PhotonoBrushItem extends Item {
    public PhotonoBrushItem(Settings settings) {
        super(settings);
    }

    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.DIAMOND_BLOCK, Blocks.NETHERITE_BLOCK,
                    Blocks.GOLD_BLOCK, Blocks.DIAMOND_BLOCK,
                    Blocks.IRON_BLOCK, Blocks.GOLD_BLOCK,
                    Blocks.STONE, Blocks.IRON_BLOCK,
                    ModBlocks.TESI_BLOCK, Blocks.DIAMOND_BLOCK
            );

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack handStack = user.getStackInHand(hand);
        Integer charge = handStack.get(ModDataComponentTypes.CHARGE_LEVEL);
        if (charge == null) charge = 0;
        for (ItemStack stack : user.getInventory().main) {
            if (!stack.isEmpty() && stack.isOf(ModItems.PHOTON)) {
                if (charge >= 2 ) {
                    break;
                }
                if (!world.isClient) {
                    world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_BREEZE_CHARGE, SoundCategory.PLAYERS, 0.7f, 1.0f);
                }
                stack.decrement(1);
                handStack.set(ModDataComponentTypes.CHARGE_LEVEL, charge + 1);
                break;
            }
        }
        return TypedActionResult.success(handStack, world.isClient());
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();
        Integer charge = context.getStack().get(ModDataComponentTypes.CHARGE_LEVEL);

        if (charge == null) charge = 0;

        if (charge <= 0) {
            return ActionResult.PASS;
        }

        if(CHISEL_MAP.containsKey(clickedBlock)) {
            if(!world.isClient) {
                world.setBlockState(context.getBlockPos(), CHISEL_MAP.get(clickedBlock).getDefaultState());

                context.getStack().damage(1,((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS);

                context.getStack().set(ModDataComponentTypes.COORDINATES, context.getBlockPos());
                context.getStack().set(ModDataComponentTypes.CHARGE_LEVEL, charge - 1);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        BlockPos pos = stack.get(ModDataComponentTypes.COORDINATES);
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.testmod.photonobrush.shift_down"));
            if(pos != null) {
                tooltip.add(Text.literal("Last Block Changed at " + pos));
            }
            if (stack.get(ModDataComponentTypes.CHARGE_LEVEL) != null) {
                tooltip.add(Text.literal("Charge Level: "  + stack.get(ModDataComponentTypes.CHARGE_LEVEL)));
            }
        } else {
            tooltip.add(Text.translatable("tooltip.testmod.photonobrush.shift_up"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
