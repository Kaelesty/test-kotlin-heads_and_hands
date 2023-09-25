package creatures

const val MAX_HEALS = 4


class Player(
    attack: Int,
    armor: Int,
    health: Int,
    damage: Pair<Int, Int>
) : Entity(attack, armor, health, damage) {

    private val healCounter = 0

    fun heal(): PlayerHealResult{

        if (healCounter == MAX_HEALS ) return PlayerHealResult.HEAL_LIMIT
        if (health == 0) return PlayerHealResult.ALREADY_DEAD

        val newHealth = health + maxHealth / 3
        health = if (newHealth > maxHealth) maxHealth else newHealth
        return PlayerHealResult.SUCCESSFUL
    }

    enum class PlayerHealResult {
        SUCCESSFUL, HEAL_LIMIT, ALREADY_DEAD
    }
}