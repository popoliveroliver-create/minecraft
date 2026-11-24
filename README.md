# Piglin Spears

A small Fabric mod for Minecraft 26.3. Sneak (hold Shift) and right-click a zombified piglin to open a menu. Choose **Give held item** to transfer one item from your main hand.

The full item stack data is copied, so enchantments (including Looting III), durability, and item components stay attached. The piglin must have an empty main hand and be within six blocks. The action is validated and performed server-side, so it works in single-player and on multiplayer servers that have the mod installed.

## Build

Requires Java 25 and Gradle 9.6 or newer. From the project directory, run:

```sh
gradle build
```

The mod jar is created in `build/libs/`.

## Notes

The mod equips the piglin with the held item; its normal mob combat behavior remains responsible for attacking. Enchantment effects only apply where Minecraft supports them for a mob-held weapon and when the piglin gets the relevant hit or kill. Looting is not a general bonus to every kill: it applies to eligible kills credited to the piglin.
