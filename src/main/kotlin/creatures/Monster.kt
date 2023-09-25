package creatures

class Monster(
    attack: Int,
    armor: Int,
    health: Int,
    damage: Pair<Int, Int>
) : Entity(attack, armor, health, damage)