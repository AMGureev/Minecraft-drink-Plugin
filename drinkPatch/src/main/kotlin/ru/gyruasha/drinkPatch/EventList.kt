package ru.gyruasha.drinkPatch

import org.bukkit.*
import org.bukkit.block.data.Levelled
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.BrewerInventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.potion.PotionType


class EventList(val plugin: DrinkPatchPlugin) : Listener {
    val customKey = NamespacedKey(plugin, "water")
    @EventHandler
    fun onPlayerInteractEntity(event: PlayerInteractEvent) {
        if (event.item == null || event.action == Action.LEFT_CLICK_BLOCK || event.action == Action.LEFT_CLICK_AIR) {
            return
        }
        if (event.clickedBlock?.type == Material.WATER_CAULDRON) {
            if (event.item!!.type == Material.GLASS_BOTTLE) {
                val potion = ItemStack(Material.POTION, 1)
                val potionMeta = potion.itemMeta as PotionMeta?
                if (event.clickedBlock!!.location.subtract(0.0, 1.0, 0.0).block.type == Material.CAMPFIRE) {
                    event.isCancelled = true
                    potionMeta!!.basePotionData = PotionData(PotionType.WATER)
                    //potionMeta!!.basePotionType = PotionType.WATER
                    potionMeta!!.setDisplayName("${ChatColor.DARK_BLUE}Clean water bottle")
                    potionMeta.persistentDataContainer.set(customKey, PersistentDataType.STRING, "clean_water")
                    potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
                    potion.itemMeta = potionMeta
                    event.item!!.amount -= 1
                    event.player.inventory.addItem(potion)
                    if (plugin.drinkInfoEnabled){
                        event.player.sendMessage("${ChatColor.RED}The glass bottle was filled with pure water!")
                    }
                    event.player.playSound(event.player.location, Sound.ITEM_BOTTLE_FILL, 1f, 1f)
                } else {
                    event.isCancelled = true
                    potionMeta!!.basePotionData = PotionData(PotionType.WATER)
                    //potionMeta!!.basePotionType = PotionType.WATER
                    potionMeta!!.setDisplayName("${ChatColor.WHITE}Dirt water bottle")
                    potionMeta.persistentDataContainer.set(customKey, PersistentDataType.STRING, "dirt_water")
                    potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
                    potionMeta.color = Color.OLIVE
                    potion.itemMeta = potionMeta
                    event.item!!.amount -= 1
                    event.player.inventory.addItem(potion)
                    if (plugin.drinkInfoEnabled){
                        event.player.sendMessage("${ChatColor.RED}The glass bottle was filled with dirty water!") // ${ChatColor.BLACK}
                    }
                    event.player.playSound(event.player.location, Sound.ITEM_BOTTLE_FILL, 1f, 1f)
                }
                val block = event.clickedBlock
                val level = block?.blockData as Levelled
                if (level.level == 1) {
                    event.clickedBlock!!.type = Material.CAULDRON
                } else {
                    level.level -= 1
                    block.blockData = level
                }
            }
        }
        if (event.player.getTargetBlock(null, 4).type == Material.WATER) {
            if (event.item!!.type == Material.GLASS_BOTTLE) {
                val potion = ItemStack(Material.POTION, 1)
                val potionMeta = potion.itemMeta as PotionMeta?
                event.isCancelled = true
                potionMeta!!.basePotionData = PotionData(PotionType.WATER)
                // potionMeta!!.basePotionType = PotionType.WATER
                potionMeta.color = Color.OLIVE
                potionMeta!!.setDisplayName("${ChatColor.GRAY}Dirt water bottle")
                potionMeta.persistentDataContainer.set(customKey, PersistentDataType.STRING, "dirt_water")
                potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
                potion.itemMeta = potionMeta
                event.item!!.amount -= 1
                event.player.inventory.addItem(potion)
                if (plugin.drinkInfoEnabled){
                    event.player.sendMessage("${ChatColor.RED}The glass bottle was filled with dirty water!")
                }
                event.player.playSound(event.player.location, Sound.ITEM_BOTTLE_FILL, 1f, 1f)
            }
        }
    }

    @EventHandler
    fun drinkBottle(e: PlayerItemConsumeEvent) {
        if (e.player.itemInHand.type == Material.POTION) {
            // if (e.player.itemInHand.itemMeta?.persistentDataContainer?.has(customKey) == true) {
            if (e.player.itemInHand.itemMeta?.persistentDataContainer?.has(customKey, PersistentDataType.STRING) == true) {
                val bottle = ItemStack(Material.GLASS_BOTTLE, 1)
                if (e.player.itemInHand.itemMeta?.persistentDataContainer!!.get(
                        customKey,
                        PersistentDataType.STRING
                    ) == "dirt_water"
                ) {
                    e.player.addPotionEffect(PotionEffect(PotionEffectType.CONFUSION, 20 * 30 /*sec*/, 1))
                    e.player.fireTicks = 0
                    e.player.isVisualFire = false
                    if (plugin.drinkInfoEnabled){
                        e.player.sendMessage("${ChatColor.RED}The player was poisoned!")
                    }
                }
                if (e.player.itemInHand.itemMeta?.persistentDataContainer!!.get(
                        customKey,
                        PersistentDataType.STRING
                    ) == "clean_water"
                ) {
                    e.player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 20 * 5 /*sec*/, 1))
                    if (e.player.health < 19F ) {
                        e.player.health += 1
                    }
                    e.player.fireTicks = 0
                    e.player.isVisualFire = false
                }
                if (e.player.itemInHand.itemMeta?.persistentDataContainer!!.get(
                        customKey,
                        PersistentDataType.STRING
                    ) == "Coca-cola"
                ) {
                    e.isCancelled = true
                    if (e.player.foodLevel <= 20) {
                        if (e.player.foodLevel >= 12) {
                            e.player.foodLevel = 20
                        } else {
                            e.player.foodLevel += 8
                        }
                    }
                    e.player.itemInUse?.type = Material.GLASS_BOTTLE
                    val im = bottle.itemMeta
                    e.player.itemInUse?.itemMeta = im
                    e.player.playSound(e.player.location, Sound.ENTITY_PLAYER_BURP, 1f, 1f)
                }
                if (e.player.itemInHand.itemMeta?.persistentDataContainer!!.get(
                        customKey,
                        PersistentDataType.STRING
                    ) == "Fanta"
                ) {
                    e.isCancelled = true
                    if (e.player.foodLevel <= 20) {
                        if (e.player.foodLevel >= 12) {
                            e.player.foodLevel = 20
                        } else {
                            e.player.foodLevel += 8
                        }
                    }
                    e.player.itemInUse?.type = Material.GLASS_BOTTLE
                    val im = bottle.itemMeta
                    e.player.itemInUse?.itemMeta = im
                    e.player.playSound(e.player.location, Sound.ENTITY_PLAYER_BURP, 1f, 1f)
                }
                if (e.player.itemInHand.itemMeta?.persistentDataContainer!!.get(
                        customKey,
                        PersistentDataType.STRING
                    ) == "Energy"
                ) {
                    e.isCancelled = true
                    if (e.player.health < 19F ) {
                        e.player.health += 1
                    }
                    if (e.player.foodLevel <= 20) {
                        if (e.player.foodLevel >= 16) {
                            e.player.foodLevel = 20
                        } else {
                            e.player.foodLevel += 4
                        }
                    }
                    e.player.itemInUse?.type = Material.GLASS_BOTTLE
                    val im = bottle.itemMeta
                    e.player.itemInUse?.itemMeta = im
                    e.player.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 5 /*sec*/, 1))
                    e.player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 20 * 30 /*sec*/, 1))
                    e.player.playSound(e.player.location, Sound.ENTITY_PLAYER_BURP, 1f, 1f)
                }

                if (e.player.itemInHand.itemMeta?.persistentDataContainer!!.get(
                        customKey,
                        PersistentDataType.STRING
                    ) == "Vodka"
                ) {
                    val rnds = (0..9).random()
                    e.isCancelled = true
                    when (rnds){
                        0->{
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.DARKNESS, 20 * 30 /*sec*/, 1))
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.CONFUSION, 20 * 25 /*sec*/, 1))
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 20 * 45 /*sec*/, 1))
                        }
                        1->{
                            e.player.health = 20.0
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 20 * 30 /*sec*/, 1))
                        }
                        2->{
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.LUCK, 20 * 45 /*sec*/, 1))
                        }
                        3->{
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.SATURATION, 20 * 45 /*sec*/, 1))
                        }
                        4->{
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.DARKNESS, 20 * 60 /*sec*/, 1))
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 20 * 60 /*sec*/, 1))
                        }
                        5->{
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 20 * 12 /*sec*/, 1))
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 14 /*sec*/, 1))
                        }
                        6->{
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.POISON, 20 * 15 /*sec*/, 1))
                        }
                        7->{
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, 20 * 30 /*sec*/, 1))
                        }
                        8->{
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 20 * 45 /*sec*/, 1))
                        }
                        9->{
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.HEALTH_BOOST, 20 * 15 /*sec*/, 1))
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 45 /*sec*/, 1))
                            e.player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 55 /*sec*/, 1))
                        }
                    }
                    e.player.itemInUse?.type = Material.GLASS_BOTTLE
                    val im = bottle.itemMeta
                    e.player.itemInUse?.itemMeta = im
                }
            }
        }
    }

    @EventHandler
    fun onBrew(event: InventoryClickEvent) {
        //if (event.currentItem?.itemMeta?.persistentDataContainer?.has(customKey) == true) {
        if (event.currentItem?.itemMeta?.persistentDataContainer?.has(customKey, PersistentDataType.STRING) == true) {
            if (event.inventory is BrewerInventory && event.currentItem?.itemMeta?.persistentDataContainer!!.get(
                    customKey,
                    PersistentDataType.STRING
                ) != "clean_water"
            ) {
                event.isCancelled = true
                event.whoClicked.closeInventory()
                event.whoClicked.sendMessage("${ChatColor.RED}You can't use this drink in brewing!")
            }
        }
    }
}