package creatures

import java.lang.RuntimeException
import kotlin.random.Random

const val N = 1000 // max HP
const val M = 5 // min damage

sealed class Entity(
    val attack: Int,
    val armor: Int,
    health_: Int,
    val damage: Pair<Int, Int>,
) {

    var isAlive: Boolean = true
        protected set
    var health: Int = 0
        protected set
    val maxHealth: Int

    init {
        if (attack < 0 || armor < 0 || health < 0) throw RuntimeException("Entity parameters cannot be less than 0.")
        if (health_ > N) throw RuntimeException("Entity health is greater than max health.")
        if (damage.first < M || damage.second > N) throw RuntimeException("Entity damage out of range.")

        health = health_
        isAlive = health > 0
        maxHealth = health
    }

    fun assault(other: Entity): Pair<EntityAssaultResult, Int?> {
        val assaultModifier = attack - other.armor + 1
        if (assaultModifier <= 0) return EntityAssaultResult.TOO_ARMORED to assaultModifier

        for (diceRoll in 0 until assaultModifier) {
            val result = Random.nextInt(1, 6)
            if (result >= 5) {
                val damage = Random.nextInt(damage.first, damage.second)
                other.takeDamage(damage)
                return EntityAssaultResult.TARGET_HIT to damage
            }
        }
        return EntityAssaultResult.TARGET_EVADED to null
    }

    fun takeDamage(damage_: Int) {
        if (damage_ >= health) {
            health = 0
            isAlive = false
        } else {
            health -= damage_
        }
    }

    enum class EntityAssaultResult {
        TOO_ARMORED, TARGET_HIT, TARGET_EVADED
    }
}
