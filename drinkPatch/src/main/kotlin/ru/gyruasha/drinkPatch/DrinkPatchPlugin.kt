package ru.gyruasha.drinkPatch

import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionType


class DrinkPatchPlugin : JavaPlugin() {
    var drinkInfoEnabled = true
    private fun createRecipeCola() {
        val customKey = NamespacedKey(this, "water")
        val key = NamespacedKey(this, "Coca-cola")
        val item = ItemStack(Material.POTION, 1)
        val im = item.itemMeta as PotionMeta
        im.persistentDataContainer.set(customKey, PersistentDataType.STRING, "Coca-cola")
        im.color = Color.fromRGB(111,23,18)
        im.setDisplayName("${ChatColor.RED}Coca-cola")
        im.lore = listOf("${ChatColor.BLUE}Legendary drink - Coca-Cola!","${ChatColor.GREEN}Drink it to experience numerous effects!", "${ChatColor.YELLOW}Effects: +8 point of satiety")
        im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        item.itemMeta = im
        val recipe = ShapedRecipe(key, item)
        val potion = ItemStack(Material.POTION, 1)
        val potionMeta = potion.itemMeta as PotionMeta?
        potionMeta!!.basePotionData = PotionData(PotionType.WATER)
        //potionMeta!!.basePotionType = PotionType.WATER 1.20.4
        potionMeta.setDisplayName("${ChatColor.DARK_BLUE}Clean water bottle")
        potionMeta.persistentDataContainer.set(customKey, PersistentDataType.STRING, "clean_water")
        potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        potion.itemMeta = potionMeta
        recipe.shape("CCC", "BPB", " S ")
        recipe.setIngredient('C', Material.COCOA_BEANS)
        recipe.setIngredient('B', Material.BLACK_DYE)
        recipe.setIngredient('P', RecipeChoice.ExactChoice(potion))
        recipe.setIngredient('S', Material.SUGAR)
        server.addRecipe(recipe)
    }
    private fun createRecipeFanta() {
        val customKey = NamespacedKey(this, "water")
        val key = NamespacedKey(this, "Fanta")
        val item = ItemStack(Material.POTION, 1)
        val im = item.itemMeta as PotionMeta
        im.persistentDataContainer.set(customKey, PersistentDataType.STRING, "Fanta")
        im.color = Color.fromRGB(255, 63, 18)
        im.setDisplayName("${ChatColor.GOLD}Fanta")
        im.lore = listOf("${ChatColor.BLUE}Famous drink - Fanta!","${ChatColor.GREEN}Drink it to experience numerous effects!", "${ChatColor.RED}Effects:", "${ChatColor.RED}+8 point of satiety")
        im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        item.itemMeta = im
        val recipe = ShapedRecipe(key, item)
        val potion = ItemStack(Material.POTION, 1)
        val potionMeta = potion.itemMeta as PotionMeta?
        potionMeta!!.basePotionData = PotionData(PotionType.WATER)
        //potionMeta!!.basePotionType = PotionType.WATER
        potionMeta.setDisplayName("${ChatColor.DARK_BLUE}Clean water bottle")
        potionMeta.persistentDataContainer.set(customKey, PersistentDataType.STRING, "clean_water")
        potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        potion.itemMeta = potionMeta
        recipe.shape("BBB", " P ", " S ")
        recipe.setIngredient('B', Material.GLOW_BERRIES)
        recipe.setIngredient('P', RecipeChoice.ExactChoice(potion))
        recipe.setIngredient('S', Material.SUGAR)
        server.addRecipe(recipe)
    }
    private fun createRecipeEnergy() {
        val customKey = NamespacedKey(this, "water")
        val key = NamespacedKey(this, "Energy")
        val item = ItemStack(Material.POTION, 1)
        val im = item.itemMeta as PotionMeta
        im.persistentDataContainer.set(customKey, PersistentDataType.STRING, "Energy")
        im.color = Color.BLUE
        im.setDisplayName("${ChatColor.BLUE}Energy drink")
        im.lore = listOf("${ChatColor.GOLD}Energy drink from a popular company!","${ChatColor.GREEN}Drink it to experience numerous effects!", "${ChatColor.RED}Effects: ", "${ChatColor.RED}+4 point of satiety","${ChatColor.RED}+1 point of health" ,"${ChatColor.RED}+5 seconds of night vision", "${ChatColor.RED}+30 seconds of speed")
        im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        item.itemMeta = im
        val recipe = ShapedRecipe(key, item)
        val potion = ItemStack(Material.POTION, 1)
        val potionMeta = potion.itemMeta as PotionMeta?
        potionMeta!!.basePotionData = PotionData(PotionType.WATER)
        //potionMeta!!.basePotionType = PotionType.WATER
        potionMeta.setDisplayName("${ChatColor.DARK_BLUE}Clean water bottle")
        potionMeta.persistentDataContainer.set(customKey, PersistentDataType.STRING, "clean_water")
        potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        potion.itemMeta = potionMeta
        recipe.shape("CSC", "SPS", "CSC")
        recipe.setIngredient('C', Material.COCOA_BEANS)
        recipe.setIngredient('P', RecipeChoice.ExactChoice(potion))
        recipe.setIngredient('S', Material.SUGAR)
        server.addRecipe(recipe)
    }
    private fun createRecipeVodka() {
        val customKey = NamespacedKey(this, "water")
        val key = NamespacedKey(this, "Vodka")
        val item = ItemStack(Material.POTION, 1)
        val im = item.itemMeta as PotionMeta
        im.persistentDataContainer.set(customKey, PersistentDataType.STRING, "Vodka")
        im.color = Color.WHITE
        im.setDisplayName("${ChatColor.WHITE}Vodka")
        im.lore = listOf("${ChatColor.GOLD}THE BEST RUSSIAN DRINK IN THE WORLD!","${ChatColor.GREEN}Drink it to experience BEST (or not) effects!", "${ChatColor.RED}Effects:", "${ChatColor.MAGIC}123123123123124", "${ChatColor.MAGIC}1231231231231231234")
        im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        item.itemMeta = im
        val recipe = ShapedRecipe(key, item)
        val potion = ItemStack(Material.POTION, 1)
        val potionMeta = potion.itemMeta as PotionMeta?
        potionMeta!!.basePotionData = PotionData(PotionType.WATER)
        //potionMeta!!.basePotionType = PotionType.WATER
        potionMeta.setDisplayName("${ChatColor.DARK_BLUE}Clean water bottle")
        potionMeta.persistentDataContainer.set(customKey, PersistentDataType.STRING, "clean_water")
        potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        potion.itemMeta = potionMeta
        recipe.shape("BSB", "SPS", "BSB")
        recipe.setIngredient('B', Material.BREAD)
        recipe.setIngredient('P', RecipeChoice.ExactChoice(potion))
        recipe.setIngredient('S', Material.SUGAR)
        server.addRecipe(recipe)
    }
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean{
        if (command.name == "drinkinfo") {
            if (args.size == 1) {
                if (args[0].equals("on", ignoreCase = true)) {
                    enableDrinkInfo(sender)
                    return true
                } else if (args[0].equals("off", ignoreCase = true)) {
                    disableDrinkInfo(sender)
                    return true
                }
            }
            sender.sendMessage("Use command: /drinkinfo on|off")
            return true
        }
        return false
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String>? {
        if (command.name == "drinkinfo" && args[0] == ""){
            return mutableListOf("on", "off")
        }
        else{
            return mutableListOf()
        }
        return super.onTabComplete(sender, command, alias, args)
    }
    private fun enableDrinkInfo(sender: CommandSender) {
        if (!drinkInfoEnabled) {
            drinkInfoEnabled = true
            sender.sendMessage("DrinkInfo is enabled.")
        } else {
            sender.sendMessage("DrinkInfo is already enabled")
        }
    }

    private fun disableDrinkInfo(sender: CommandSender) {
        if (drinkInfoEnabled) {
            drinkInfoEnabled = false
            sender.sendMessage("DrinkInfo is disabled.")
        } else {
            sender.sendMessage("DrinkInfo is already disabled.")
        }
    }
    override fun onEnable() {
        createRecipeCola()
        createRecipeFanta()
        createRecipeEnergy()
        createRecipeVodka()
        server.pluginManager.registerEvents(EventList(this), this)
        getCommand("drinkinfo")?.setExecutor(this)
    }
}