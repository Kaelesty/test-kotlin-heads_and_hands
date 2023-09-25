import creatures.Entity
import creatures.Monster
import creatures.Player

fun main() {
    // example
    val kaelesty = Player(5, 4, 100, 10 to 15)
    val vampire = Monster(8, 5, 100, 60 to 80)

    while (true) {
        println("Kaelesty attacks vampire")
        assaultPresentation(kaelesty.assault(vampire))
        if (!vampire.isAlive) {
            println("Kaelesty won")
            break
        }
        println("Vampire attacks Kaelesty")
        assaultPresentation(vampire.assault(kaelesty))
        if (!kaelesty.isAlive) {
            println("Kaelesty dies")
            break
        }

        if (kaelesty.health < kaelesty.maxHealth * 0.7) {
            when (kaelesty.heal()) {
                Player.PlayerHealResult.SUCCESSFUL -> println("Kaelesty heals")
                Player.PlayerHealResult.HEAL_LIMIT -> println("Kaelesty cannot heal anymore")
                else -> {}
            }
        }
    }
}

fun assaultPresentation(assault: Pair<Entity.EntityAssaultResult, Int?>) {
    when (assault.first) {
        Entity.EntityAssaultResult.TOO_ARMORED -> println(" Attacker cannot break target's armor (${assault.second})")
        Entity.EntityAssaultResult.TARGET_EVADED -> println("   The target evaded all attacks")
        Entity.EntityAssaultResult.TARGET_HIT -> println("  Target hit (${assault.second})")
    }
}